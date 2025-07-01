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
@Table(name = "abonnement")       
public class Abonnement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abonnement")  
    private Integer idAbonnement;

    @Column(name = "date_debut")  
    private DateFormat dateDebut;

    @Column(name = "date_fin")  
    private DateFormat dateFin;

    @ManyToOne
    @Column(name = "id_adherant")  
    private Integer idAdherant;

    // ====================== Getters / Setters ======================== //

    public Abonnement() {}

    public Abonnement(DateFormat dateDebut, DateFormat dateFin, Integer idAdherant) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idAdherant = idAdherant;
    }

    public Integer getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Integer idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public DateFormat getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(DateFormat dateDebut) {
        this.dateDebut = dateDebut;
    }

    public DateFormat getDateFin() {
        return dateFin;
    }

    public void setDateFin(DateFormat dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

   
}
