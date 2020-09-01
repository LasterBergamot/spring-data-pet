package com.pet.springdata.repository.user;

import com.pet.springdata.repository.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Short> {}