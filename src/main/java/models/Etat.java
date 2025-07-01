package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "etat")       
public class Etat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etat")  
    private Integer idEtat;

    @Column(nullable = false, length = 50)
    private String libelle;

    // ====================== Getters / Setters ======================== //

    public Etat() {}

    public Etat(Integer idEtat, String libelle) {
        this.idEtat = idEtat;
        this.libelle = libelle;
    }

    public Integer getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Integer idEtat) {
        this.idEtat = idEtat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}
