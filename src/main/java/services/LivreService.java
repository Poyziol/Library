package services;

import models.Livre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.LivreRepository;
import java.util.List;

@Service
@Transactional
public class LivreService 
{
    private final LivreRepository repo;

    public LivreService(LivreRepository repo) 
    {
        this.repo = repo;
    }

    public List<Livre> listAll() 
    {
        return repo.findAll();
    }

    /** Filtre par titre partiel, auteur exact (ou partiel), âge min. */
    public List<Livre> filter(String titre, String auteur, Integer ageMin) 
    {
        String t = (titre != null) ? titre : "";
        String a = (auteur != null) ? auteur : "";
        Integer age = (ageMin != null) ? ageMin : 0;
        return repo.findByTitreContainingIgnoreCaseAndAuteurContainingIgnoreCaseAndAgeMinGreaterThanEqual( t, a, age);
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
