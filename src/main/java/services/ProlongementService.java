package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 13) ProlongementService
import models.Prolongement;
import repositories.ProlongementRepository;

@Service
@Transactional
public class ProlongementService {
    private final ProlongementRepository repo;
    public ProlongementService(ProlongementRepository repo) { this.repo = repo; }
    public List<Prolongement> listAll() { return repo.findAll(); }
    public Prolongement get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Prolongement introuvable : " + id)); }
    public Prolongement save(Prolongement obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
