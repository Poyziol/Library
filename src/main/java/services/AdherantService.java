package services;

import models.Adherant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AdherantRepository;
import java.util.List;

@Service
@Transactional
public class AdherantService 
{
    private final AdherantRepository repo;

    public AdherantService(AdherantRepository repo) { this.repo = repo; }

    public Adherant getByUserId(Integer userId) 
    {
      return repo.findByUser_IdUsers(userId).orElseGet(() -> {
                Adherant dummy = new Adherant();
                dummy.setIdAdherant(0);
                return dummy;
            });
    }

    public Adherant get(Integer id) 
    {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Adhérent introuvable: " + id));
    }

    public List<Adherant> listAll() 
    {
        return repo.findAll();
    }

    public Adherant save(Adherant adh) 
    {
        return repo.save(adh);
    }

    public void delete(Integer id) 
    {
        repo.deleteById(id);
    }
}
