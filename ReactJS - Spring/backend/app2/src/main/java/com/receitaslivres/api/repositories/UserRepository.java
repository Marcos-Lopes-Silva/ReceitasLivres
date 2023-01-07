package com.receitaslivres.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.receitaslivres.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByAtivoTrue(Pageable pageable);

    UserDetails findByEmail(String subject);

}
