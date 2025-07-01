package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_users")       
public class TypeUsers 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")  
    private Integer idTypeUsers;

    @Column(nullable = false, length = 50)
    private String libelle;

    // ====================== Getters / Setters ======================== //

    public TypeUsers() {}

    public TypeUsers(String libelle) { this.libelle = libelle; }

    public Integer getIdTypeUsers() { return idTypeUsers; }

    public void setIdTypeUsers(Integer idTypeUsers) { this.idTypeUsers = idTypeUsers; }

    public Integer getIdPromotion() { return idTypeUsers; }

    public void setIdPromotion(Integer idTypeUsers) { this.idTypeUsers = idTypeUsers; }

    public String getNom() { return libelle; }

    public void setNom(String libelle) { this.libelle = libelle; }
}
