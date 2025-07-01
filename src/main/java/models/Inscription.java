package models;

import java.text.DateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscription")       
public class Inscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription")  
    private Integer idInscription;

    @Column(name = "date_inscription")  
    private DateFormat dateInscription;

    @ManyToOne
    @Column(name = "id_status")
    private Integer idStatus;

    // ====================== Getters / Setters ======================== //

    public Inscription() {}

    

    public Inscription(DateFormat dateInscription, Integer idStatus) {
        this.dateInscription = dateInscription;
        this.idStatus = idStatus;
    }



    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer idInscription) {
        this.idInscription = idInscription;
    }

    public DateFormat getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(DateFormat dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    
}
