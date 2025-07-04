package repositories;

import models.Exemplaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    List<Exemplaire> findByDisponibleTrue();
}
