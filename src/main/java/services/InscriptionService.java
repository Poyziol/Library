package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 9) InscriptionService
import models.Inscription;
import repositories.InscriptionRepository;

@Service
@Transactional
public class InscriptionService {
    private final InscriptionRepository repo;
    public InscriptionService(InscriptionRepository repo) { this.repo = repo; }
    public List<Inscription> listAll() { return repo.findAll(); }
    public Inscription get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Inscription introuvable : " + id)); }
    public Inscription save(Inscription obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
