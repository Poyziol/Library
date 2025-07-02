package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 14) PenaliteService
import models.Penalite;
import repositories.PenaliteRepository;

@Service
@Transactional
public class PenaliteService {
    private final PenaliteRepository repo;
    public PenaliteService(PenaliteRepository repo) { this.repo = repo; }
    public List<Penalite> listAll() { return repo.findAll(); }
    public Penalite get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Pénalité introuvable : " + id)); }
    public Penalite save(Penalite obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
