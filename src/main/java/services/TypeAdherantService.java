package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 3) TypeAdherantService
import models.TypeAdherant;
import repositories.TypeAdherantRepository;

@Service
@Transactional
public class TypeAdherantService {
    private final TypeAdherantRepository repo;
    public TypeAdherantService(TypeAdherantRepository repo) { this.repo = repo; }
    public List<TypeAdherant> listAll() { return repo.findAll(); }
    public TypeAdherant get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("TypeAdherant introuvable : " + id)); }
    public TypeAdherant save(TypeAdherant obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
