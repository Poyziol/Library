package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_adherant")       
public class TypeAdherant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_adherant")  
    private Integer idTypeAdherant;

    @Column(nullable = false, length = 50)
    private String libelle;

    // ====================== Getters / Setters ======================== //

    public TypeAdherant() {}

    

    public TypeAdherant(String libelle) {
        this.libelle = libelle;
    }



    public Integer getIdTypeAdherant() {
        return idTypeAdherant;
    }

    public void setIdTypeAdherant(Integer idTypeAdherant) {
        this.idTypeAdherant = idTypeAdherant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}
