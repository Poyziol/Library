package services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 10) UsersService
import models.Users;
import repositories.UsersRepository;

@Service
@Transactional
public class UsersService 
{
    private final UsersRepository repo;
    public UsersService(UsersRepository repo) { this.repo = repo; }
    public List<Users> listAll() { return repo.findAll(); }
    public Users get(Integer id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("User introuvable : " + id)); }
    public Users save(Users obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }

    public Optional<Users> login(String nom, String mot_de_passe) 
    {
        return repo.findByNomAndMotDePasse(nom, mot_de_passe);
    }
}
