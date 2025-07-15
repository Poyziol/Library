package repositories;

import models.Status;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findByLibelle(String libelle);
}