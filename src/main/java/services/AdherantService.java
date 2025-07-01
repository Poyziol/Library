package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 11) AdherantService
import models.Adherant;
import repositories.AdherantRepository;

@Service
@Transactional
public class AdherantService {
    private final AdherantRepository repo;
    public AdherantService(AdherantRepository repo) { this.repo = repo; }
    public List<Adherant> listAll() { return repo.findAll(); }
    public Adherant get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Adhérent introuvable : " + id)); }
    public Adherant save(Adherant obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
