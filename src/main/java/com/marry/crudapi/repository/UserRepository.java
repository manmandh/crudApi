package com.marry.crudapi.repository;

import com.marry.crudapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DBMS, interact directly with the database

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
