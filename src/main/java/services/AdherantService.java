package services;

import models.Adherant;
import models.Users;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AdherantRepository;

import java.time.LocalDate;
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
                       // on ne tente PAS de save(…) pour éviter les contraintes NOT NULL
                       // on fixe juste un idAdherant qui indique "pas d'adhérent"
                       dummy.setIdAdherant(0);
                       return dummy;
            });
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
