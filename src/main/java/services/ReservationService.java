package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 6) ReservationService
import models.Reservation;
import repositories.ReservationRepository;

@Service
@Transactional
public class ReservationService {
    private final ReservationRepository repo;
    public ReservationService(ReservationRepository repo) { this.repo = repo; }
    public List<Reservation> listAll() { return repo.findAll(); }
    public Reservation get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Reservation introuvable : " + id)); }
    public Reservation save(Reservation obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
