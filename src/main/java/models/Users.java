package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")       
public class Users 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")  
    private Integer idUsers;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(name = "mot_de_passe", nullable = false, length = 16)
    private String motDePasse;

    @Column(name = "id_type_users")
    private Integer idTypeUsers;
    
    // ====================== Getters / Setters ======================== //

    public Users() {}

    
    public Users(String nom, String motDePasse, Integer idTypeUsers) {
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.idTypeUsers = idTypeUsers;
    }



    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Integer getIdTypeUsers() {
        return idTypeUsers;
    }

    public void setIdTypeUsers(Integer idTypeUsers) {
        this.idTypeUsers = idTypeUsers;
    }

}
