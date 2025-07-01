package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "preter_exemplaire")       
public class PreterExemplaire
{
    @ManyToOne
    @Column(name = "id_exemplaire")  
    private Integer idExemplaire;

    @ManyToOne
    @Column(name = "id_pret")  
    private Integer idPret;

    // ====================== Getters / Setters ======================== //

    public PreterExemplaire() {}

    public PreterExemplaire(Integer idExemplaire, Integer idPret) {
        this.idExemplaire = idExemplaire;
        this.idPret = idPret;
    }

    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

}
