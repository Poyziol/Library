package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")       
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")  
    private Integer idReservation;

    @Column(name = "date_reservation")  
    private LocalDate dateReservation;

    @Column(name = "id_status")  
    private Integer idStatus;

    // ====================== Getters / Setters ======================== //

    public Reservation() {}

    

    public Reservation(LocalDate dateReservation, Integer idStatus) {
        this.dateReservation = dateReservation;
        this.idStatus = idStatus;
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

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
        
}
