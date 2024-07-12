package com.davidosorno.demosecurity.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.davidosorno.demosecurity.models.Role;
import com.davidosorno.demosecurity.repositories.RoleRepository;

@Service
public class RoleService {

  private final RoleRepository repository;

  public RoleService(RoleRepository repository) {
    this.repository = repository;

    try {
      Role role = new Role();
      role.setName("ROLE_USER");
      repository.save(role);

      role = new Role();
      role.setName("ROLE_ADMIN");
      repository.save(role);
    } catch (Exception e) {
    }
  }

  public Role findByName(String roleName) {
    Optional<Role> role = repository.findByName(roleName);
    return role.isPresent() ? role.get() : null;
  }

}
