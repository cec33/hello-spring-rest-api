package com.monpremierprojet.hello_spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA génère automatiquement :
    // - findAll()
    // - findById(Long id)
    // - save(User user)
    // - delete(User user)
    // - etc.

    // Tu peux même créer des méthodes personnalisées, comme :
    // List<User> findByName(String name); // Spring sait comment faire !
}
