package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "promotion")       
public class Promotion 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")  
    private Integer idPromotion;

    @Column(nullable = false, length = 100)
    private String nom;
    

    // ====================== Getters / Setters ======================== //

    public Promotion() {}

    public Promotion(String nom) { this.nom = nom; }

    public Integer getIdPromotion() { return idPromotion; }

    public void setIdPromotion(Integer idPromotion) { this.idPromotion = idPromotion; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }
}
