package com.shivasai.expensetracker.controllers;

import com.shivasai.expensetracker.entity.TransactionEntity;
import com.shivasai.expensetracker.entity.UserEntity;
import com.shivasai.expensetracker.repository.TransactionRepository;
import com.shivasai.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    private UserRepository userrepo;

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransaction(){
        try{
            List<TransactionEntity> tx = transactionRepo.findAll();
            return new ResponseEntity<>(tx,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("not ok",HttpStatus.OK);
        }

    }

    @PostMapping("/transaction")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionEntity transaction){
        System.out.println(transaction);
        UserEntity tx_user = userrepo.findByUserName("chitra");
        if(tx_user==null){
            return new ResponseEntity<>("user doesnot exist",HttpStatus.NOT_FOUND);
        }
        else{
            transaction.setUser(tx_user);
            transactionRepo.save(transaction);
            return new ResponseEntity<>(transaction,HttpStatus.OK);
        }
    }

}
