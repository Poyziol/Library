package services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.Adherant;
import models.Exemplaire;
import models.Livre;
import models.Pret;
import models.Status;
import models.TypePret;
import repositories.PretRepository;
import repositories.TypePretRepository;

@Service
@Transactional
public class PretService 
{
    @Autowired
    private final PretRepository repo;
    private final ExemplaireService exemplaireService;
    private final AdherantService adherantService;
    private final PenaliteService penaliteService;
    private final AbonnementService abonnementService;
    private final TypePretRepository typePretRepository;

    public PretService(PretRepository repo, 
                      ExemplaireService exemplaireService,
                      AdherantService adherantService,
                      PenaliteService penaliteService,
                      AbonnementService abonnementService,
                      TypePretRepository typePretRepository) {
        this.repo = repo;
        this.exemplaireService = exemplaireService;
        this.adherantService = adherantService;
        this.penaliteService = penaliteService;
        this.abonnementService = abonnementService;
        this.typePretRepository = typePretRepository;
    }

    // Liste tous les prêts
    public List<Pret> listAll() 
    {
        return repo.findAll();
    }

    public Pret get(Integer id) 
    {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Prêt introuvable : " + id));
    }

    public Pret save(Pret obj) 
    {
        return repo.save(obj);
    }

    public List<Pret> getPretsEnCoursByAdherant(Integer adherantId) {
        return repo.findByAdherantIdAdherantAndStatus_Libelle(adherantId, "EN_COURS");
    }

    public void delete(Integer id) {
        Pret p = get(id);
        
        // Mettre à jour le quota de l'adhérent
        Adherant a = p.getAdherant();
        a.setLimiteQuota(a.getLimiteQuota() - 1);
        adherantService.save(a);

        // Libérer l'exemplaire
        Exemplaire e = p.getExemplaire();
        e.setDisponible(true);
        exemplaireService.save(e);

        repo.deleteById(id);
    }

    public List<Pret> getHistoriqueRetours(String dateMin, String dateMax) {
        LocalDate min = null;
        LocalDate max = null;
        
        try {
            if(dateMin != null && !dateMin.isEmpty()) {
                min = LocalDate.parse(dateMin);
            }
            if(dateMax != null && !dateMax.isEmpty()) {
                max = LocalDate.parse(dateMax);
            }
        } catch (DateTimeParseException e) {
            // Gérer l'erreur si nécessaire
        }
        
        return repo.findHistoriqueRetours(min, max);
    }

    public int getQuotaActuel(int idAdherant)
    {
        int quotaActuel = repo.countCurrentPretsByAdherant(idAdherant);

        return quotaActuel;
    }

    public void create(Integer idAdherant, Integer idExemplaire, LocalDate datePret, LocalDate dateRetourEstime) {
        // Règle 1: Vérifier la disponibilité de l'exemplaire
        Exemplaire e = exemplaireService.get(idExemplaire);
        if (!e.getDisponible()) 
        {
            throw new RuntimeException("L'exemplaire #" + idExemplaire + " n'est pas disponible");
        }

        // Règle 2: Vérifier le quota de prêts de l'adhérent
        Adherant a = adherantService.get(idAdherant);
        int quotaActuel = repo.countCurrentPretsByAdherant(idAdherant);
        if (quotaActuel >= a.getLimiteQuota()) 
        {
            throw new RuntimeException("Quota de prêts atteint (" + quotaActuel + "/" + a.getLimiteQuota() + ") pour " + a.getNom());
        }

        // Règle 3: Vérifier les pénalités non réglées
        if (penaliteService.hasActivePenalites(idAdherant)) {
            throw new RuntimeException(a.getNom() + " a des pénalités non réglées");
        }

        // Règle 4: Vérifier la validité de l'abonnement
        if (!abonnementService.isAbonnementValide(idAdherant)) {
            throw new RuntimeException("Abonnement de l'adhérent est expiré");
        }

        // Règle 5: Vérifier l'âge minimal du livre
        Livre livre = e.getLivre();
        int ageAdherant = Period.between(a.getDateDeNaissance(), LocalDate.now()).getYears();
        if (ageAdherant < livre.getAgeMin()) {
            throw new RuntimeException("L'adhérent est trop jeune pour ce livre");
        }

        TypePret type_pret = typePretRepository.findByLibelle("normal")
            .orElseThrow(() -> new IllegalStateException("Type de pret normal non trouvé"));

        // Création du prêt
        Pret p = new Pret();
        Status stat = new Status(1);
        p.setDatePret(datePret);
        p.setDateRetourEstime(dateRetourEstime);
        p.setQuotaActuel(quotaActuel - 1);

        e.setDisponible(false);
        exemplaireService.save(e);
        p.setExemplaire(e);
        p.setTypePret(type_pret);
        p.setAdherant(a);
        p.setStatus(stat); 

        repo.save(p);
    }
}

