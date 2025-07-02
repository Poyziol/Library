package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 12) PretService
import models.Pret;
import repositories.PretRepository;

@Service
@Transactional
public class PretService {
    private final PretRepository repo;
    public PretService(PretRepository repo) { this.repo = repo; }
    public List<Pret> listAll() { return repo.findAll(); }
    public Pret get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Prêt introuvable : " + id)); }
    public Pret save(Pret obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
