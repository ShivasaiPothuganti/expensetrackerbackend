package com.shivasai.expensetracker.controllers;

import com.shivasai.expensetracker.entity.UserEntity;
import com.shivasai.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository Userrepo;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserEntity new_user){
        try{
            Userrepo.save(new_user);
            HttpHeaders headers = new HttpHeaders();
            headers.add("userStatus","created");
            return new ResponseEntity<UserEntity>(new_user,headers,HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e);
           return new ResponseEntity<String>("exception thrown",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user_deatils){
        try{
           UserEntity user =  Userrepo.findByUserName(user_deatils.getUserName());
           if(user==null){
               return new ResponseEntity<String>("user doesnot exists",HttpStatus.NOT_FOUND);
           }
           else{
               if(user.getPassword().equals(user_deatils.getPassword())){
                   return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
               }
               else {
                   return new ResponseEntity<String>("user credentials are woring", HttpStatus.FORBIDDEN);
               }
           }
        }
        catch (Exception e){
            System.out.println("some error ");
            return new ResponseEntity<>("internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
