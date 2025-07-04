package models;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemplaire")
    private Integer idExemplaire;

    // Map BOOLEAN in DB to Boolean in Java
    @Column(name = "disponible", nullable = false)
    private Boolean disponible;

    // Relation vers Etat (clé boolean)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_etat", nullable = false)
    private Etat etat;

    // Relation vers Livre
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    public Exemplaire() {}

    // Getters / Setters
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

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}
