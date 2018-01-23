package com.muuyal.escala.billingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.muuyal.escala.billingmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {


        User findByUsername(String username);


}
