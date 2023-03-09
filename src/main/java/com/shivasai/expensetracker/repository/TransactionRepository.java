package com.shivasai.expensetracker.repository;

import com.shivasai.expensetracker.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

}
