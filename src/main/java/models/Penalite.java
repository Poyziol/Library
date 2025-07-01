package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "penalite")       
public class Penalite
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_penalite")  
    private Integer idPenalite;

    @Column(nullable = true, length = 300)
    private String motif;

    @Column(name = "date_debut_penalite")  
    private Integer dateDebutPenalite;

    @Column(name = "est_reglee")  
    private Boolean estReglee;

    @Column(name = "duree")  
    private Integer duree;

    @OneToOne
    @Column(name = "id_pret")  
    private Integer idPret;

    @OneToOne
    @Column(name = "id_adherant")  
    private Integer idAdherant;

    // ====================== Getters / Setters ======================== //

    public Penalite() {}

    public Penalite(String motif, Integer dateDebutPenalite, Boolean estReglee, Integer duree, Integer idPret,
            Integer idAdherant) {
        this.motif = motif;
        this.dateDebutPenalite = dateDebutPenalite;
        this.estReglee = estReglee;
        this.duree = duree;
        this.idPret = idPret;
        this.idAdherant = idAdherant;
    }

    public Integer getIdPenalite() {
        return idPenalite;
    }

    public void setIdPenalite(Integer idPenalite) {
        this.idPenalite = idPenalite;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Integer getDateDebutPenalite() {
        return dateDebutPenalite;
    }

    public void setDateDebutPenalite(Integer dateDebutPenalite) {
        this.dateDebutPenalite = dateDebutPenalite;
    }

    public Boolean getEstReglee() {
        return estReglee;
    }

    public void setEstReglee(Boolean estReglee) {
        this.estReglee = estReglee;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

   
}
