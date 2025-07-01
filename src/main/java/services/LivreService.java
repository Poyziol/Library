package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.LivreRepository;
import models.Livre;

@Service
@Transactional
public class LivreService {
    private final LivreRepository repo;
    public LivreService(LivreRepository repo) { this.repo = repo; }
    public List<Livre> listAll() { return repo.findAll(); }
    public Livre get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Livre introuvable : " + id)); }
    public Livre save(Livre entity) { return repo.save(entity); }
    public void delete(Integer id) { repo.deleteById(id); }
}
