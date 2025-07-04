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
    private Boolean idEtat;

    @Column(nullable = false, length = 50)
    private String libelle;

    // ====================== Getters / Setters ======================== //

    public Etat() {}

    public Etat(Boolean idEtat, String libelle) {
        this.idEtat = idEtat;
        this.libelle = libelle;
    }

    public Boolean getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Boolean idEtat) {
        this.idEtat = idEtat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}
