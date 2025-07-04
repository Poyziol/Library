package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    private LocalDate dateDeNaissance;

    @Column(nullable = false)  
    private Integer telephone;

    @Column(name = "limite_quota")  
    private Integer limiteQuota;

    @Column(name = "id_reservation")
    private Integer idReservation;

    @Column(name = "id_inscription")
    private Integer idInscription;

    @Column(name = "id_type_adherant")
    private Integer idTypeAdherant;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_users", nullable = false, unique = true)
    private Users user;
    
    // ====================== Getters / Setters ======================== //

    public Adherant() {}

    public Adherant(String nom, String prenom, LocalDate dateDeNaissance, Integer telephone, Integer limiteQuota, Integer idReservation, Integer idInscription, Integer idTypeAdherant) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.telephone = telephone;
        this.limiteQuota = limiteQuota;
        this.idReservation = idReservation;
        this.idInscription = idInscription;
        this.idTypeAdherant = idTypeAdherant;
    }

    public Adherant(String nom, String prenom, LocalDate dateDeNaissance, Integer telephone, Integer limiteQuota,
            Integer idReservation, Integer idInscription, Integer idTypeAdherant, Users user) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.telephone = telephone;
        this.limiteQuota = limiteQuota;
        this.idReservation = idReservation;
        this.idInscription = idInscription;
        this.idTypeAdherant = idTypeAdherant;
        this.user = user;
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

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
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

    public Users getUser() {
      return user;
    }

    public void setUser(Users user) {
      this.user = user;
  }

    
    
}
