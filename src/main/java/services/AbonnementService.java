package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 15) AbonnementService
import models.Abonnement;
import repositories.AbonnementRepository;

@Service
@Transactional
public class AbonnementService {
    private final AbonnementRepository repo;
    public AbonnementService(AbonnementRepository repo) { this.repo = repo; }
    public List<Abonnement> listAll() { return repo.findAll(); }
    public Abonnement get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Abonnement introuvable : " + id)); }
    public Abonnement save(Abonnement obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
