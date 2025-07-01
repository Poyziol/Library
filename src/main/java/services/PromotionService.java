package services;

import models.Users;
import repositories.PromotionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PromotionService 
{
    private final PromotionRepository repo;

    public PromotionService(PromotionRepository repo) 
    { 
        this.repo = repo; 
    }

    public List<Users> listAll() { return repo.findAll(); }

    public Users get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Promotion introuvable : " + id)); }

    public Users save(Users promo) { return repo.save(promo); }

    public void delete(Integer id) { repo.deleteById(id); }
}
