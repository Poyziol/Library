package models;

import java.text.DateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "adherant")       
public class Adherant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adherant")  
    private Integer idAdherant;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(name = "date_de_naissance")
    private DateFormat dateDeNaissance;

    @Column(nullable = false)  
    private Integer telephone;

    @Column(name = "limite_quota")  
    private Integer limiteQuota;

    @ManyToOne
    @Column(name = "id_reservation")
    private Integer idReservation;

    @ManyToOne
    @Column(name = "id_inscription")
    private Integer idInscription;

    @ManyToOne
    @Column(name = "id_type_adherant")
    private Integer idTypeAdherant;
    
    // ====================== Getters / Setters ======================== //

    public Adherant() {}

    public Adherant(String nom, String prenom, DateFormat dateDeNaissance, Integer telephone, Integer limiteQuota,
            Integer idReservation, Integer idInscription, Integer idTypeAdherant) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.telephone = telephone;
        this.limiteQuota = limiteQuota;
        this.idReservation = idReservation;
        this.idInscription = idInscription;
        this.idTypeAdherant = idTypeAdherant;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public DateFormat getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(DateFormat dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Integer getLimiteQuota() {
        return limiteQuota;
    }

    public void setLimiteQuota(Integer limiteQuota) {
        this.limiteQuota = limiteQuota;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer idInscription) {
        this.idInscription = idInscription;
    }

    public Integer getIdTypeAdherant() {
        return idTypeAdherant;
    }

    public void setIdTypeAdherant(Integer idTypeAdherant) {
        this.idTypeAdherant = idTypeAdherant;
    }

    
}
