package services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.Exemplaire;
import repositories.ExemplaireRepository;

@Service
@Transactional
public class ExemplaireService {
    private final ExemplaireRepository repo;

    public ExemplaireService(ExemplaireRepository repo) {
        this.repo = repo;
    }

    // Liste tous les exemplaires
    public List<Exemplaire> listAll() {
        return repo.findAll();
    }

    // *** Nouvel ajout : lister uniquement les disponibles ***
    public List<Exemplaire> listDisponibles() {
        return repo.findByDisponibleTrue();
    }

    public Exemplaire get(Integer id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Exemplaire introuvable : " + id));
    }

    public Exemplaire save(Exemplaire obj) {
        return repo.save(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    // Ajouter ce getter dans ExemplaireService
    public boolean isExemplaireDisponible(Integer id) {
        return repo.findById(id)
                  .map(Exemplaire::getDisponible)
                  .orElse(false);
    }
}
