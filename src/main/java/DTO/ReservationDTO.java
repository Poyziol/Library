package DTO;

import models.Exemplaire;
import models.Reservation;

public class ReservationDTO {
    private Reservation reservation;
    private Exemplaire exemplaire;
    
    public ReservationDTO(Reservation reservation, Exemplaire exemplaire) {
        this.reservation = reservation;
        this.exemplaire = exemplaire;
    }
    
    // Getters
    public Reservation getReservation() { return reservation; }
    public Exemplaire getExemplaire() { return exemplaire; }
}
