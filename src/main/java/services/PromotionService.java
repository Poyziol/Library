package services;

import models.Promotion;
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

    public List<Promotion> listAll() { return repo.findAll(); }

    public Promotion get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Promotion introuvable : " + id)); }

    public Promotion save(Promotion promo) { return repo.save(promo); }

    public void delete(Integer id) { repo.deleteById(id); }
}
