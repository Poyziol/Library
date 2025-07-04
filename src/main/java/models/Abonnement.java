package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private LocalDate dateDebut;

    @Column(name = "date_fin")  
    private LocalDate dateFin;

    @Column(name = "id_adherant")  
    private Integer idAdherant;

    // ====================== Getters / Setters ======================== //

    public Abonnement() {}

    public Abonnement(LocalDate dateDebut, LocalDate dateFin, Integer idAdherant) {
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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

   
}
