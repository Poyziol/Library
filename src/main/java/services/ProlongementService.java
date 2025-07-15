package services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.Pret;
// 13) ProlongementService
import models.Prolongement;
import models.Status;
import repositories.ProlongementRepository;
import repositories.StatusRepository;

@Service
public class ProlongementService {
    private final ProlongementRepository repo;
    private final PretService pretService;
    private final StatusRepository statusRepository;

    public ProlongementService(ProlongementRepository repo, 
                              PretService pretService,
                              StatusRepository statusRepository) {
        this.repo = repo;
        this.pretService = pretService;
        this.statusRepository = statusRepository;
    }

    @Transactional
    public Prolongement demanderProlongement(Integer pretId, Integer jours) {
        Pret pret = pretService.get(pretId);
        
        if(pret.getProlongements().size() >= 2) {
            throw new IllegalStateException("Nombre maximum de prolongements atteint");
        }
        
        Status statusEnAttente = statusRepository.findByLibelle("EN_ATTENTE")
            .orElseThrow(() -> new IllegalStateException("Statut EN_ATTENTE non trouvé"));
        
        Prolongement prolongement = new Prolongement();
        prolongement.setDateProlongement(LocalDate.now());
        
        // CORRECTION : Conversion explicite en long pour plusDays()
        prolongement.setNouvelleDateRetour(
            pret.getDateRetourEstime().plusDays(jours.longValue()) // <-- Correction ici
        );
        
        prolongement.setNbrProlongementActuel(pret.getProlongements().size() + 1);
        prolongement.setPret(pret);
        prolongement.setStatus(statusEnAttente);
        
        return repo.save(prolongement);
    }
    
    @Transactional
    public void traiterProlongement(Integer prolongementId, boolean accepter) {
        Prolongement prolongement = repo.findById(prolongementId)
            .orElseThrow();
        
        if(accepter) {
            Status statusAccepte = statusRepository.findByLibelle("ACCEPTE")
                .orElseThrow(() -> new IllegalStateException("Statut ACCEPTE non trouvé"));
            
            Pret pret = prolongement.getPret();
            pret.setDateRetourEstime(prolongement.getNouvelleDateRetour());
            pretService.save(pret);
            
            prolongement.setStatus(statusAccepte);
        } else {
            Status statusRefuse = statusRepository.findByLibelle("REFUSE")
                .orElseThrow(() -> new IllegalStateException("Statut REFUSE non trouvé"));
            prolongement.setStatus(statusRefuse);
        }
        
        repo.save(prolongement);
    }

    public List<Prolongement> getDemandesEnAttente() {
        return repo.findByStatus_Libelle("EN_ATTENTE");
    }
}