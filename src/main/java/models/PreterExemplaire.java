package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "preter_exemplaire")       
public class PreterExemplaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preter_exemplaire")  
    private Integer idPreterExemplaire;

    @Column(name = "id_exemplaire")  
    private Integer idExemplaire;

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

    public Integer getIdPreterExemplaire() {
        return idPreterExemplaire;
    }

    public void setIdPreterExemplaire(Integer idPreterExemplaire) {
        this.idPreterExemplaire = idPreterExemplaire;
    }

}
