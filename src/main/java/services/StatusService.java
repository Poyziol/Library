package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 4) StatusService
import models.Status;
import repositories.StatusRepository;

@Service
@Transactional
public class StatusService {
    private final StatusRepository repo;
    public StatusService(StatusRepository repo) { this.repo = repo; }
    public List<Status> listAll() { return repo.findAll(); }
    public Status get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Status introuvable : " + id)); }
    public Status save(Status obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}
