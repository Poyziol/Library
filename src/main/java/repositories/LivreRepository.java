package repositories;

import models.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Integer> 
{
    // Filtre dynamique
    List<Livre> findByTitreContainingIgnoreCaseAndAuteurContainingIgnoreCaseAndAgeMinGreaterThanEqual(String titre, String auteur, Integer ageMin);

    // Pour alimenter la dropdown des auteurs
    @Query("SELECT DISTINCT l.auteur FROM Livre l")
    List<String> findDistinctAuteurs();

    // Pour la dropdown d'âge min
    @Query("SELECT DISTINCT l.ageMin FROM Livre l ORDER BY l.ageMin")
    List<Integer> findDistinctAgeMins();
}
