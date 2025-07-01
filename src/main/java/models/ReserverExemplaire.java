package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserver_exemplaire")       
public class ReserverExemplaire
{
    @ManyToOne
    @Column(name = "id_exemplaire")  
    private Integer idExemplaire;

    @ManyToOne
    @Column(name = "id_reservation")  
    private Integer idReservation;

    // ====================== Getters / Setters ======================== //

    public ReserverExemplaire() {}

    public ReserverExemplaire(Integer idExemplaire, Integer idReservation) {
        this.idExemplaire = idExemplaire;
        this.idReservation = idReservation;
    }

    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    
}
