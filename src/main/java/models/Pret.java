package models;

import java.time.LocalDate;

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
    private LocalDate datePret;

    @Column(name = "date_retour_estime")  
    private LocalDate dateRetourEstime;

    @Column(name = "date_retour_reel")  
    private LocalDate dateRetourReel;

    @Column(name = "quota_actuel")  
    private Integer quotaActuel;

    @Column(name = "id_type_pret")  
    private Integer idTypePret;

    @Column(name = "id_adherant")  
    private LocalDate idAdherant;

    @Column(name = "id_status")  
    private Integer idStatus;

    // ====================== Getters / Setters ======================== //

    public Pret() {}

    public Pret(LocalDate datePret, LocalDate dateRetourEstime, LocalDate dateRetourReel, Integer quotaActuel,
            Integer idTypePret, LocalDate idAdherant, Integer idStatus) {
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

    public LocalDate getDatePret() {
        return datePret;
    }

    public void setDatePret(LocalDate datePret) {
        this.datePret = datePret;
    }

    public LocalDate getDateRetourEstime() {
        return dateRetourEstime;
    }

    public void setDateRetourEstime(LocalDate dateRetourEstime) {
        this.dateRetourEstime = dateRetourEstime;
    }

    public LocalDate getDateRetourReel() {
        return dateRetourReel;
    }

    public void setDateRetourReel(LocalDate dateRetourReel) {
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

    public LocalDate getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(LocalDate idAdherant) {
        this.idAdherant = idAdherant;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

   
}
