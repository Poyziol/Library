package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserver_exemplaire")       
public class ReserverExemplaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserver_exemplaire")  
    private Integer idReserverExemplaire;

    @Column(name = "id_exemplaire")  
    private Integer idExemplaire;

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

    public Integer getIdReserverExemplaire() {
        return idReserverExemplaire;
    }

    public void setIdReserverExemplaire(Integer idReserverExemplaire) {
        this.idReserverExemplaire = idReserverExemplaire;
    }

    
}
