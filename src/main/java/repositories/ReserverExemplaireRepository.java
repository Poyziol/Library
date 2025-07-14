package repositories;

import models.Reservation;
import models.ReserverExemplaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserverExemplaireRepository extends JpaRepository<ReserverExemplaire, Integer> {
    List<ReserverExemplaire> findByReservation(Reservation reservation);
}