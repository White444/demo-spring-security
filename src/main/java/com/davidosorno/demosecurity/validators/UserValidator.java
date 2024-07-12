package com.davidosorno.demosecurity.validators;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.davidosorno.demosecurity.models.Role;
import com.davidosorno.demosecurity.models.User;
import com.davidosorno.demosecurity.services.RoleService;
import com.davidosorno.demosecurity.services.UserService;

@Component
public class UserValidator implements Validator {

  private final UserService userService;
  private final RoleService roleService;

  public UserValidator(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    User user = (User) target;
    
    assignUserRoles(user);

    if (passwordsMismatch(user)) {
      errors.rejectValue("passwordConfirmation", "USER_PASSWORDS_MISMATCH");
    }

    if (userService.emailExists(user.getEmail())) {
      errors.rejectValue("email", "USER_EMAIL_ALREADY_REGISTERED"); 
    }

    if (user.getRoles().size() == 0) {
      errors.rejectValue("email", "USER_ROLE_MISSING");
    }

    if(passwordNotContainAtLeastOneCharacter(user)){
      errors.rejectValue("password", "USER_PASSWORD_MISSING_SPECIAL_CHARACTER");
      // errors.rejectValue("password", "user.password.specialCharacter"); // Siempre utilizar la misma convención para los mensajes (Mayúsculas con guiones o camelcase con puntos)
    }
  }

  private void assignUserRoles(User user) {
    Set<Role> userRoles = new HashSet<>();
    Role role;
    long totalUsers = userService.getTotalUsers();

    if(totalUsers == 0){ 
      role = roleService.findByName("ROLE_USER");
      userRoles.add(role);
  
      role = roleService.findByName("ROLE_ADMIN");
      userRoles.add(role);
    } else if(totalUsers == 1){
      role = roleService.findByName("ROLE_ADMIN");
      userRoles.add(role);
    } else {
      role = roleService.findByName("ROLE_USER");
      userRoles.add(role);
    }

    user.setRoles(userRoles);
  }

  private boolean passwordNotContainAtLeastOneCharacter(User user) {
    return !user.getPassword().matches(".*[!@#$%^&*()<>?].*");
  }

  public boolean passwordsMismatch(User user) {
    return user.getPassword().equals(user.getPasswordConfirmation()) == false;
  }

}
