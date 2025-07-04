package services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 7) TypeUsersService
import models.TypeUsers;
import repositories.TypeUsersRepository;

@Service
@Transactional
public class TypeUsersService {
    private final TypeUsersRepository repo;
    public TypeUsersService(TypeUsersRepository repo) { this.repo = repo; }
    public List<TypeUsers> listAll() { return repo.findAll(); }
    public TypeUsers save(TypeUsers obj) { return repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }

    public TypeUsers get(Integer idTypeUsers) 
    {
        return repo.findByIdTypeUsers(idTypeUsers).orElseThrow(() -> new RuntimeException("TypeUsers introuvable pour l’ID " + idTypeUsers));
    }

}
