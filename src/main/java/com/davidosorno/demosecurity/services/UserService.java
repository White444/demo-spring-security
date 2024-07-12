package com.davidosorno.demosecurity.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.davidosorno.demosecurity.models.User;
import com.davidosorno.demosecurity.repositories.UserRepository;
import com.davidosorno.demosecurity.security.PasswordEncoder;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User save(User user) {
    encryptUserPassword(user);
    user = repository.save(user);

    return user;
  }

  private void encryptUserPassword(User user) {
    String hashedPassword = PasswordEncoder
        .bCryptPasswordEncoder()
        .encode(user.getPassword());

    user.setPassword(hashedPassword);
  }

  public User findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public User findById(Long userId) {
    Optional<User> user = repository.findById(userId);
    return user.isPresent() ? user.get() : null;
  }

  public boolean emailExists(String email) {
    return repository.existsByEmail(email);
  }

  public long getTotalUsers() {
    return repository.count();
  }

}
