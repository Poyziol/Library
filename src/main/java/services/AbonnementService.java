package services;

import models.Abonnement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AbonnementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AbonnementService 
{
    private final AbonnementRepository repo;

    public AbonnementService(AbonnementRepository repo) {
        this.repo = repo;
    }

    public List<Abonnement> listAll() {
        return repo.findAllWithAdherant();
    }

    public Optional<Abonnement> findByAdherantId(Integer idAdherant) {
        return repo.findByAdherant_IdWithAdherant(idAdherant);
    }


    public Abonnement getById(Integer id) 
    {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Abonnement introuvable : " + id));
    }

    public Abonnement save(Abonnement abo) 
    {
        return repo.save(abo);
    }

    public void delete(Integer id) 
    {
        repo.deleteById(id);
    }
}
