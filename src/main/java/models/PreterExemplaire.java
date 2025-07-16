package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "preter_exemplaire")       
public class PreterExemplaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preter_exemplaire")  
    private Integer idPreterExemplaire;

    @ManyToOne @JoinColumn(name="id_pret", nullable=false)
    private Pret pret;

    @ManyToOne @JoinColumn(name="id_exemplaire", nullable=false)
    private Exemplaire exemplaire;

    // ====================== Getters / Setters ======================== //

    public PreterExemplaire() {}

    public PreterExemplaire(Pret pret, Exemplaire exemplaire) {
        this.pret = pret;
        this.exemplaire = exemplaire;
    }

    public Integer getIdPreterExemplaire() {
        return idPreterExemplaire;
    }

    public void setIdPreterExemplaire(Integer idPreterExemplaire) {
        this.idPreterExemplaire = idPreterExemplaire;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    

}
