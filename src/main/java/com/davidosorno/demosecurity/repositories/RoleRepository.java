package com.davidosorno.demosecurity.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.davidosorno.demosecurity.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
  
  Optional<Role> findByName(String roleName);

}
