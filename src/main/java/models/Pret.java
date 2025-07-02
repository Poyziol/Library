package models;

import java.text.DateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pret")       
public class Pret
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pret")  
    private Integer idPret;

    @Column(name = "date_pret")  
    private DateFormat datePret;

    @Column(name = "date_retour_estime")  
    private DateFormat dateRetourEstime;

    @Column(name = "date_retour_reel")  
    private DateFormat dateRetourReel;

    @Column(name = "quota_actuel")  
    private Integer quotaActuel;

    @Column(name = "id_type_pret")  
    private Integer idTypePret;

    @Column(name = "id_adherant")  
    private DateFormat idAdherant;

    @Column(name = "id_status")  
    private Integer idStatus;

    // ====================== Getters / Setters ======================== //

    public Pret() {}

    public Pret(DateFormat datePret, DateFormat dateRetourEstime, DateFormat dateRetourReel, Integer quotaActuel,
            Integer idTypePret, DateFormat idAdherant, Integer idStatus) {
        this.datePret = datePret;
        this.dateRetourEstime = dateRetourEstime;
        this.dateRetourReel = dateRetourReel;
        this.quotaActuel = quotaActuel;
        this.idTypePret = idTypePret;
        this.idAdherant = idAdherant;
        this.idStatus = idStatus;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public DateFormat getDatePret() {
        return datePret;
    }

    public void setDatePret(DateFormat datePret) {
        this.datePret = datePret;
    }

    public DateFormat getDateRetourEstime() {
        return dateRetourEstime;
    }

    public void setDateRetourEstime(DateFormat dateRetourEstime) {
        this.dateRetourEstime = dateRetourEstime;
    }

    public DateFormat getDateRetourReel() {
        return dateRetourReel;
    }

    public void setDateRetourReel(DateFormat dateRetourReel) {
        this.dateRetourReel = dateRetourReel;
    }

    public Integer getQuotaActuel() {
        return quotaActuel;
    }

    public void setQuotaActuel(Integer quotaActuel) {
        this.quotaActuel = quotaActuel;
    }

    public Integer getIdTypePret() {
        return idTypePret;
    }

    public void setIdTypePret(Integer idTypePret) {
        this.idTypePret = idTypePret;
    }

    public DateFormat getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(DateFormat idAdherant) {
        this.idAdherant = idAdherant;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

   
}
