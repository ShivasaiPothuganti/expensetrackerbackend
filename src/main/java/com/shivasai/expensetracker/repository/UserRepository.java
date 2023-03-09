package com.shivasai.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shivasai.expensetracker.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserName(String username);
}

