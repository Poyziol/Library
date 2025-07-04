package repositories;

import models.Adherant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherantRepository extends JpaRepository<Adherant,Integer> {
  Optional<Adherant> findByUser_IdUsers(Integer userId);
}
