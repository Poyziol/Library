package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "livre")       
public class Livre 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livre")  
    private Integer idLivre;

    @Column(nullable = false, length = 70)
    private String titre;

    @Column(nullable = true, length = 300)
    private String resume;

    @Column(nullable = false, length = 50)
    private String auteur;

    @Column(name = "annee_publication")
    private LocalDate anneePublication;

    @Column(name = "age_min")
    private Integer ageMin;

    @Transient
    private Integer nombreExemplairesDisponibles;

    // ====================== Getters / Setters ======================== //

    public Livre() {}


    public Livre(String titre, String resume, String auteur, LocalDate anneePublication, Integer ageMin) {
        this.titre = titre;
        this.resume = resume;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ageMin = ageMin;
    }

    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public LocalDate getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(LocalDate anneePublication) {
        this.anneePublication = anneePublication;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }
    
    public Integer getNombreExemplairesDisponibles() {
        return nombreExemplairesDisponibles;
    }

    public void setNombreExemplairesDisponibles(Integer nombreExemplairesDisponibles) {
        this.nombreExemplairesDisponibles = nombreExemplairesDisponibles;
    }
}
