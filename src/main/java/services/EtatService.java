package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 8) EtatService
import models.Etat;
import repositories.EtatRepository;

@Service
@Transactional
public class EtatService {
    private final EtatRepository repo;
    public EtatService(EtatRepository repo) { this.repo = repo; }
    public List<Etat> listAll() { return repo.findAll(); }
    public Etat get(Boolean id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Etat introuvable : " + id)); }
    public Etat save(Etat obj) { return repo.save(obj); }
    public void delete(Boolean id) { repo.deleteById(id); }
}
