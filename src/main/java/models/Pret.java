package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;

@Entity
@Table(name = "pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pret")
    private Integer idPret;

    @Column(name = "date_pret", nullable = false)
    private LocalDate datePret;

    @Column(name = "date_retour_estime", nullable = false)
    private LocalDate dateRetourEstime;

    @Column(name = "date_retour_reel")
    private LocalDate dateRetourReel;

    @Column(name = "quota_actuel", nullable = false)
    private Integer quotaActuel;

    @Column(name = "id_type_pret", nullable = false)
    private Integer idTypePret;

    // Association Adherant
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    // Association Exemplaire via table preter_exemplaire
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
        name = "preter_exemplaire",
        joinColumns = @JoinColumn(name = "id_pret"),
        inverseJoinColumns = @JoinColumn(name = "id_exemplaire")
    )
    private Exemplaire exemplaire;

    @Column(name = "id_status", nullable = false)
    private Integer idStatus;

    @Transient
    public long getDureePret() {
        if (datePret == null || dateRetourReel == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(datePret, dateRetourReel);
    }

    public Pret() {}

    // Getters et Setters

    public Integer getIdPret() { return idPret; }
    public void setIdPret(Integer idPret) { this.idPret = idPret; }

    public LocalDate getDatePret() { return datePret; }
    public void setDatePret(LocalDate datePret) { this.datePret = datePret; }

    public LocalDate getDateRetourEstime() { return dateRetourEstime; }
    public void setDateRetourEstime(LocalDate dateRetourEstime) { this.dateRetourEstime = dateRetourEstime; }

    public LocalDate getDateRetourReel() { return dateRetourReel; }
    public void setDateRetourReel(LocalDate dateRetourReel) { this.dateRetourReel = dateRetourReel; }

    public Integer getQuotaActuel() { return quotaActuel; }
    public void setQuotaActuel(Integer quotaActuel) { this.quotaActuel = quotaActuel; }

    public Integer getIdTypePret() { return idTypePret; }
    public void setIdTypePret(Integer idTypePret) { this.idTypePret = idTypePret; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }

    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }

    public Integer getIdStatus() { return idStatus; }
    public void setIdStatus(Integer idStatus) { this.idStatus = idStatus; }
}
