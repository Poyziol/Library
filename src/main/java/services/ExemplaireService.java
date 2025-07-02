package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 2) ExemplaireService
import models.Exemplaire;
import repositories.ExemplaireRepository;

@Service
@Transactional
public class ExemplaireService {
    private final ExemplaireRepository repo;
    public ExemplaireService(ExemplaireRepository repo) { this.repo = repo; }
    public List<Exemplaire> listAll() { return repo.findAll(); }
    public Exemplaire get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Exemplaire introuvable : " + id)); }
    public Exemplaire save(Exemplaire obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
