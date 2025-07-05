package repositories;

import models.Exemplaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    @Query("SELECT e.livre.idLivre, COUNT(e) " +
           "FROM Exemplaire e " +
           "WHERE e.disponible = true " +
           "AND e.livre.idLivre IN :ids " +
           "GROUP BY e.livre.idLivre")
    List<Object[]> countDisponiblesByLivreIds(@Param("ids") List<Integer> livreIds);
    
    List<Exemplaire> findByDisponibleTrue();
}
