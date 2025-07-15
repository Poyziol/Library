package repositories;

import models.TypePret;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePretRepository extends JpaRepository<TypePret, Integer> {
    Optional<TypePret> findByLibelle(String libelle);
}