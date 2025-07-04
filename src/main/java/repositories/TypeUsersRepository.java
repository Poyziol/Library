package repositories;

import models.TypeUsers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeUsersRepository extends JpaRepository<TypeUsers, Integer> {
    Optional<TypeUsers> findByIdTypeUsers(Integer idTypeUsers);
}