package services;

import models.Eleve;
import repositories.EleveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EleveService 
{
    private final EleveRepository repo;

    public EleveService(EleveRepository repo) 
    { 
        this.repo = repo; 
    }

    public List<Eleve> listAll() { return repo.findAll(); }

    public Eleve get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Eleve introuvable : " + id)); }

    public Eleve save(Eleve eleve) { return repo.save(eleve); }

    public void delete(Integer id) { repo.deleteById(id); }
}
