package models;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "abonnement")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abonnement")
    private Integer id;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    // === Passage de l'Integer à une association ManyToOne ===
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;


    public Abonnement() {}

    public Abonnement(LocalDate dateDebut, LocalDate dateFin, Adherant adherant) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adherant = adherant;
    }

    // ===== Getters / Setters =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }
}
