package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exemplaire")       
public class Exemplaire
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemplaire")  
    private Integer idExemplaire;

    @Column(name = "disponible")
    private Boolean disponible;

    @ManyToOne
    @Column(name = "id_etat")
    private Integer idEtat;

    @ManyToOne
    @Column(name = "id_livre")
    private Integer idLivre;


    // ====================== Getters / Setters ======================== //

    public Exemplaire() {}


    public Exemplaire(Boolean disponible, Integer idEtat, Integer idLivre) {
        this.disponible = disponible;
        this.idEtat = idEtat;
        this.idLivre = idLivre;
    }

    public Integer getIdExemplaire() {
        return idExemplaire;
    }


    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }


    public Boolean getDisponible() {
        return disponible;
    }


    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }


    public Integer getIdEtat() {
        return idEtat;
    }


    public void setIdEtat(Integer idEtat) {
        this.idEtat = idEtat;
    }


    public Integer getIdLivre() {
        return idLivre;
    }


    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    
}
