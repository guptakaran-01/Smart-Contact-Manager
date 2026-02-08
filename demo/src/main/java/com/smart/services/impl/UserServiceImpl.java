package com.smart.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.helper.AppConstants;
import com.smart.helper.ResourcenotFoundException;
import com.smart.model.User;
import com.smart.repositories.UserRepo;
import com.smart.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
     public User saveUser(User user) {

      //Encode Password 
        user.setPassword(passwordEncoder.encode(user.getPassword()));

       // Setting Manually hardcoding roles 
       user.setRoleList(List.of(AppConstants.ROLE_USER));

       return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(int userId) {
     return userRepo.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
   User user1= userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourcenotFoundException("User not Found"));
  //update
  user1.setName(user.getName());
  user1.setEmail(user.getEmail());
  user1.setPassword(user.getPassword());
  user1.setAbout(user.getAbout());
  user1.setPhoneNumber(user.getPhoneNumber());
  user1.setProfileLink(user.getProfileLink());
  user1.setEnabled(user.isEnabled());
  user1.setEmailVerified(user.isEmailVerified());
  user1.setPhoneVerified(user.isPhoneVerified());
  user1.setProvider(user.getProvider());
  user1.setProviderUserId(user.getProviderUserId());
   
  //save
  User saveUser=userRepo.save(user1);

  return Optional.ofNullable(saveUser);

}

    @Override
    public void deleteUser(int userId) {
     User user1= userRepo.findById(userId).orElseThrow(()-> new ResourcenotFoundException("User not Found"));
     userRepo.delete(user1);
    }

    @Override
    public boolean isUserExist(int userId) {
       User user1= userRepo.findById(userId).orElse(null);
       return user1!=null ? true :false ;
  
    }

    @Override
    public boolean isUserExistByEmail(String email) {
     User user1= userRepo.findByEmail(email).orElse(null);
       return user1!=null ? true :false ;
   
    }

    @Override
    public List<User> getAllUSers() {
   return userRepo.findAll();
   
    }

}
