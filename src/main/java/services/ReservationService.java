package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import DTO.ReservationDTO;
import jakarta.transaction.Transactional;
import models.Adherant;
import models.Exemplaire;
import models.Pret;
import models.Status;
import models.TypePret;
import models.Reservation;
import models.ReserverExemplaire;
import repositories.ReservationRepository;
import repositories.ReserverExemplaireRepository;
import repositories.StatusRepository;
import repositories.TypePretRepository;

@Service
public class ReservationService {
    private final ReservationRepository repo;
    private final ExemplaireService exemplaireService;
    private final PretService pretService;
    private final ReserverExemplaireRepository reserverExemplaireRepo;
    private final StatusRepository statusRepository;
    private final TypePretRepository typePretRepository;

    // Ajout du constructeur pour l'injection de dépendances
    public ReservationService(
        ReservationRepository repo,
        ExemplaireService exemplaireService,
        PretService pretService,
        ReserverExemplaireRepository reserverExemplaireRepo,
        StatusRepository statusRepository,
        TypePretRepository typePretRepository
    ) {
        this.repo = repo;
        this.exemplaireService = exemplaireService;
        this.pretService = pretService;
        this.reserverExemplaireRepo = reserverExemplaireRepo;
        this.statusRepository = statusRepository;
        this.typePretRepository = typePretRepository;
    }

    @Transactional
    public Reservation creerReservation(Integer exemplaireId, Integer adherantId, LocalDate date) {
        Exemplaire exemplaire = exemplaireService.get(exemplaireId);
        if(!exemplaire.getDisponible()) {
            throw new IllegalStateException("Exemplaire non disponible");
        }

        Status statusEnAttente = statusRepository.findByLibelle("EN_ATTENTE")
            .orElseThrow(() -> new IllegalStateException("Statut EN_ATTENTE non trouvé"));

        Reservation reservation = new Reservation();
        reservation.setDateReservation(date);
        reservation.setStatus(statusEnAttente);
        reservation.setAdherant(new Adherant(adherantId));

        // Pas besoin de lier directement l'exemplaire ici
        reservation = repo.save(reservation);

        // Créer l'association dans reserver_exemplaire
        ReserverExemplaire association = new ReserverExemplaire(exemplaire, reservation);
        reserverExemplaireRepo.save(association);

        return reservation;
    }

    @Transactional
    public void confirmerReservation(Integer reservationId, int quota_actuel) {
        Reservation reservation = repo.findById(reservationId).orElseThrow();
        List<ReserverExemplaire> associations = reserverExemplaireRepo.findByReservation(reservation);

        // Récupérer le statut "EN_COURS" depuis la base
        Status stat = statusRepository.findByLibelle("EN_COURS")
            .orElseThrow(() -> new IllegalStateException("Statut EN_COURS non trouvé"));
        
        TypePret type_pret = typePretRepository.findByLibelle("reservation")
            .orElseThrow(() -> new IllegalStateException("Type de pret reservation non trouvé"));

        for(ReserverExemplaire association : associations) {
            Exemplaire exemplaire = association.getExemplaire();

            Pret pret = new Pret();
            pret.setDatePret(LocalDate.now());
            pret.setDateRetourEstime(LocalDate.now().plusWeeks(2));
            pret.setExemplaire(exemplaire);
            pret.setQuotaActuel(quota_actuel);
            pret.setTypePret(type_pret);
            pret.setAdherant(reservation.getAdherant());
            pret.setStatus(stat);  // Utilisez le statut récupéré

            pretService.save(pret);

            exemplaire.setDisponible(false);
            exemplaireService.save(exemplaire);
        }

        // Mettre à jour le statut de la réservation
        Status statusConfirme = statusRepository.findByLibelle("CONFIRMEE")
            .orElseThrow(() -> new IllegalStateException("Statut CONFIRMEE non trouvé"));
        reservation.setStatus(statusConfirme);
        repo.save(reservation);

        reserverExemplaireRepo.deleteAll(associations);
    }

    public List<ReservationDTO> getReservationsEnAttenteWithExemplaires() {
        List<Reservation> reservations = repo.findByStatus_Libelle("EN_ATTENTE");
        List<ReservationDTO> dtos = new ArrayList<>();
        
        for (Reservation res : reservations) {
            // Récupérer l'exemplaire associé
            List<ReserverExemplaire> associations = reserverExemplaireRepo.findByReservation(res);
            if (!associations.isEmpty()) {
                Exemplaire exemplaire = associations.get(0).getExemplaire();
                dtos.add(new ReservationDTO(res, exemplaire));
            }
        }

        return dtos;
    }
}