package repositories;

import models.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
}