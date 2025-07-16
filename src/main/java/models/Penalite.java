package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private LocalDate dateDebutPenalite;

    @Column(name = "est_reglee")  
    private Boolean estReglee;

    @Column(name = "duree")  
    private Integer duree;

    @ManyToOne
    @JoinColumn(name = "id_pret")
    private Pret pret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant", referencedColumnName = "id_adherant")
    private Adherant adherant;

    // ====================== Getters / Setters ======================== //

    public Penalite() {}

    public Penalite(String motif, LocalDate dateDebutPenalite, Boolean estReglee, Integer duree, Pret idPret, Adherant adherant) {
        this.motif = motif;
        this.dateDebutPenalite = dateDebutPenalite;
        this.estReglee = estReglee;
        this.duree = duree;
        this.pret = idPret;
        this.adherant = adherant;
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

    public LocalDate getDateDebutPenalite() {
        return dateDebutPenalite;
    }

    public void setDateDebutPenalite(LocalDate dateDebutPenalite) {
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

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret idPret) {
        this.pret = idPret;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant Adherant) {
        this.adherant = Adherant;
    }

   
}
