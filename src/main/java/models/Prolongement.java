package models;

import java.text.DateFormat;

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
    private DateFormat dateProlongement;

    @Column(name = "nouvelle_date_retour")  
    private DateFormat nouvelleDateRetour;

    @Column(name = "nbr_prolongement_actuel")  
    private DateFormat nbrProlongementActuel;

    @Column(name = "id_pret")  
    private Integer idPret;

    // ====================== Getters / Setters ======================== //

    public Prolongement() {}

    public Prolongement(DateFormat dateProlongement, DateFormat nouvelleDateRetour, DateFormat nbrProlongementActuel,
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

    public DateFormat getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(DateFormat dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public DateFormat getNouvelleDateRetour() {
        return nouvelleDateRetour;
    }

    public void setNouvelleDateRetour(DateFormat nouvelleDateRetour) {
        this.nouvelleDateRetour = nouvelleDateRetour;
    }

    public DateFormat getNbrProlongementActuel() {
        return nbrProlongementActuel;
    }

    public void setNbrProlongementActuel(DateFormat nbrProlongementActuel) {
        this.nbrProlongementActuel = nbrProlongementActuel;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    
}
