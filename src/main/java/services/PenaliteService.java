package services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.Penalite;
import repositories.PenaliteRepository;

@Service
@Transactional
public class PenaliteService {
    private final PenaliteRepository repo;

    public PenaliteService(PenaliteRepository repo) {
        this.repo = repo;
    }

    public boolean hasActivePenalites(Integer idAdherant) {
        return repo.countActivePenalitesForAdherant(idAdherant) > 0;
    }

     // Liste toutes les pénalités non réglées
    public List<Penalite> getActivePenalites() {
        return repo.findByEstRegleeFalse();
    }

    // Liste toutes les pénalités
    public List<Penalite> listAll() {
        return repo.findAll();
    }

    // Récupère une pénalité par son ID
    public Penalite get(Integer id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Pénalité introuvable : " + id));
    }

    // Sauvegarde ou met à jour une pénalité
    public Penalite save(Penalite obj) {
        return repo.save(obj);
    }

    // Supprime une pénalité
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    // Liste les pénalités d'un adhérent spécifique
    public List<Penalite> getByAdherant(Integer idAdherant) {
        return repo.findByAdherantIdAdherant(idAdherant);
    }
}