package models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "reserver_exemplaire")
@IdClass(ReserverExemplaire.ReserverExemplaireId.class) // Clé composite
public class ReserverExemplaire {
    
    // Clé composite (partie 1)
    @Id
    @ManyToOne
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;
    
    // Clé composite (partie 2)
    @Id
    @ManyToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    // ====================== Classe interne pour la clé composite ======================== //
    public static class ReserverExemplaireId implements Serializable {
        private Exemplaire exemplaire;
        private Reservation reservation;

        // Constructeurs, equals(), hashCode()...
        public ReserverExemplaireId() {}

        public ReserverExemplaireId(Exemplaire exemplaire, Reservation reservation) {
            this.exemplaire = exemplaire;
            this.reservation = reservation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ReserverExemplaireId)) return false;
            ReserverExemplaireId that = (ReserverExemplaireId) o;
            return exemplaire.equals(that.exemplaire) && 
                   reservation.equals(that.reservation);
        }

        @Override
        public int hashCode() {
            return Objects.hash(exemplaire, reservation);
        }
    }

    // ====================== Getters / Setters ======================== //

    public ReserverExemplaire() {}

    public ReserverExemplaire(Exemplaire exemplaire, Reservation reservation) {
        this.exemplaire = exemplaire;
        this.reservation = reservation;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}