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
@Table(name = "prolongement")       
public class Prolongement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prolongeent")  
    private Integer idProlongement;

    @Column(name = "date_prolongement")  
    private LocalDate dateProlongement;

    @Column(name = "nouvelle_date_retour")  
    private LocalDate nouvelleDateRetour;

    @Column(name = "nbr_prolongement_actuel")  
    private Integer nbrProlongementActuel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret")  
    private Pret pret;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    // ====================== Getters / Setters ======================== //

    public Prolongement() {}

    public Prolongement(LocalDate dateProlongement, LocalDate nouvelleDateRetour, Integer nbrProlongementActuel,
            Pret pret) {
        this.dateProlongement = dateProlongement;
        this.nouvelleDateRetour = nouvelleDateRetour;
        this.nbrProlongementActuel = nbrProlongementActuel;
        this.pret = pret;
    }

    public Integer getIdProlongement() {
        return idProlongement;
    }

    public void setIdProlongement(Integer idProlongement) {
        this.idProlongement = idProlongement;
    }

    public LocalDate getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDate dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public LocalDate getNouvelleDateRetour() {
        return nouvelleDateRetour;
    }

    public void setNouvelleDateRetour(LocalDate nouvelleDateRetour) {
        this.nouvelleDateRetour = nouvelleDateRetour;
    }

    public Integer getNbrProlongementActuel() {
        return nbrProlongementActuel;
    }

    public void setNbrProlongementActuel(int nbrProlongementActuel) {
        this.nbrProlongementActuel = nbrProlongementActuel;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
