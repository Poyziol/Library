package services;

import models.Livre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ExemplaireRepository;
import repositories.LivreRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivreService 
{
    private final LivreRepository repo;
    private final ExemplaireRepository exemplaireRepo;

    public LivreService(LivreRepository livreRepo, ExemplaireRepository exemplaireRepo) {
        this.repo = livreRepo;
        this.exemplaireRepo = exemplaireRepo;
    }

    public List<Livre> listAll() 
    {
        return repo.findAll();
    }

    /** Filtre par titre partiel, auteur exact (ou partiel), âge min. */
   public List<Livre> filter(String titre, String auteur, Integer ageMin) {
        String t = (titre != null) ? titre : "";
        String a = (auteur != null) ? auteur : "";
        Integer age = (ageMin != null) ? ageMin : 0;
        
        List<Livre> livres = repo.findByTitreContainingIgnoreCaseAndAuteurContainingIgnoreCaseAndAgeMinGreaterThanEqual(t, a, age);
        
        if (!livres.isEmpty()) {
            // Récupère les IDs des livres
            List<Integer> livreIds = livres.stream()
                .map(Livre::getIdLivre)
                .collect(Collectors.toList());
            
            // Compte les exemplaires disponibles
            Map<Integer, Long> countsMap = new HashMap<>();
            List<Object[]> results = exemplaireRepo.countDisponiblesByLivreIds(livreIds);
            
            for (Object[] result : results) {
                Integer livreId = (Integer) result[0];
                Long count = (Long) result[1];
                countsMap.put(livreId, count);
            }
            
            // Assigner les counts aux livres
            for (Livre livre : livres) {
                Long count = countsMap.get(livre.getIdLivre());
                livre.setNombreExemplairesDisponibles(count != null ? count.intValue() : 0);
            }
        }
        
        return livres;
    }

    public List<String> listAuteurs() 
    {
        return repo.findDistinctAuteurs();
    }

    public List<Integer> listAgeMins() 
    {
        return repo.findDistinctAgeMins();
    }
}
