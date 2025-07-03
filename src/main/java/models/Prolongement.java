package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private LocalDate nbrProlongementActuel;

    @Column(name = "id_pret")  
    private Integer idPret;

    // ====================== Getters / Setters ======================== //

    public Prolongement() {}

    public Prolongement(LocalDate dateProlongement, LocalDate nouvelleDateRetour, LocalDate nbrProlongementActuel,
            Integer idPret) {
        this.dateProlongement = dateProlongement;
        this.nouvelleDateRetour = nouvelleDateRetour;
        this.nbrProlongementActuel = nbrProlongementActuel;
        this.idPret = idPret;
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

    public LocalDate getNbrProlongementActuel() {
        return nbrProlongementActuel;
    }

    public void setNbrProlongementActuel(LocalDate nbrProlongementActuel) {
        this.nbrProlongementActuel = nbrProlongementActuel;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    
}
