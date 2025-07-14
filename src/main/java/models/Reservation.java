package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import repositories.ReserverExemplaireRepository;

@Entity
@Table(name = "reservation")       
public class Reservation
{
    @Transient 
    private Exemplaire exemplaire;
    @Transient
    private ReserverExemplaireRepository reserverExemplaireRepo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Integer idReservation;
    
    @Column(name = "date_reservation")
    private LocalDate dateReservation;
    
    // REMPLACER LE CHAMP "statut" PAR UNE RELATION
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;
    
    // Retirer l'ancien getter/setter pour "statut

    public Exemplaire getExemplaire() {
        if (exemplaire == null) {
            // Charger à partir de reserver_exemplaire
            ReserverExemplaire association = reserverExemplaireRepo
                .findByReservation(this)
                .stream()
                .findFirst()
                .orElse(null);
                
            if (association != null) {
                exemplaire = association.getExemplaire();
            }
        }
        return exemplaire;
    }

    
    // ====================== Getters / Setters ======================== //

    public Reservation() {}

    public Reservation(LocalDate dateReservation, Status status, Adherant adherant) {
        this.dateReservation = dateReservation;
        this.status = status;
        this.adherant = adherant;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }
    
     // Getters et setters
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
        
}
