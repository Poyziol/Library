package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "eleve")       
public class Eleve 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eleve")  
    private Integer idEleve;

    @Column(name = "id_promotion")
    private Integer idPromotion;

    @Column(nullable = false, length = 100)
    private String nom;


    // ====================== Getters / Setters ======================== //

    public Eleve() {}

    public Eleve(String nom) { this.nom = nom; }

    public Eleve(int idPromotion,String nom)
    {
        this.idPromotion = idPromotion;
        this.nom = nom;
    }

    public Integer getIdEleve() { return idEleve; }

    public void setIdEleve(Integer idEleve) { this.idEleve = idEleve; }

    public Integer getIdPromotion() { return idPromotion; }

    public void setIdPromotion(Integer idPromotion) { this.idPromotion = idPromotion; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }
}
