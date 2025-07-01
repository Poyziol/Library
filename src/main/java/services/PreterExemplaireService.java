package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 16) PreterExemplaireService
import models.PreterExemplaire;
import repositories.PreterExemplaireRepository;

@Service
@Transactional
public class PreterExemplaireService {
    private final PreterExemplaireRepository repo;
    public PreterExemplaireService(PreterExemplaireRepository repo) { this.repo = repo; }
    public List<PreterExemplaire> listAll() { return repo.findAll(); }
    public PreterExemplaire get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("PreterExemplaire introuvable : " + id)); }
    public PreterExemplaire save(PreterExemplaire obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
