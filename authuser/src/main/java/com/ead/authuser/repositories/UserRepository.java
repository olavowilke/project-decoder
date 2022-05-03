package com.ead.authuser.repositories;

import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

/*
JpaSpecificationExecutor<UserModel> will allow the interface to handle Specification<UserModel>
coming from the service layer in the findAll() method signature
 */
public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
