package repositories;

import models.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> 
{
    Optional<Users> findByNomAndMotDePasse(String nom, String mot_de_passe);
}