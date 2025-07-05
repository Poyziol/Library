package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 5) TypePretService
import models.TypePret;
import repositories.TypePretRepository;

@Service
@Transactional
public class TypePretService {
    private final TypePretRepository repo;
    public TypePretService(TypePretRepository repo) { this.repo = repo; }
    public List<TypePret> listAll() 
    {
        return repo.findAll();
    }

    public TypePret get(Integer id) 
    {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Type de prêt introuvable : " + id));
    }
    public TypePret save(TypePret obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
