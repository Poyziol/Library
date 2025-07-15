package repositories;

import models.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByStatus_Libelle(String libelle);
    List<Reservation> findByAdherantIdReservation(Integer adherantId);
    @Query("SELECT r FROM Reservation r " +
           "WHERE r.id IN (SELECT re.reservation.id FROM ReserverExemplaire re WHERE re.exemplaire.id = :exemplaireId)")
    List<Reservation> findByExemplaireId(@Param("exemplaireId") Integer exemplaireId);
}