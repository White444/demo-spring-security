package com.davidosorno.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidosorno.demosecurity.models.User;
import com.davidosorno.demosecurity.services.UserService;
import com.davidosorno.demosecurity.validators.UserValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserValidator userValidator;

  public UserController(UserService userService, UserValidator userValidator) {
    this.userService = userService;
    this.userValidator = userValidator;
  }

  @InitBinder
  private void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(userValidator); // No utilizar setValidator porque elimina las validaciones del modelo.
  }

  @PostMapping("/new")
  public String save(
      @Valid @ModelAttribute("user") User user,
      BindingResult result) {

    if (result.hasErrors()) {
      return "index.jsp";
    }

    userService.save(user); //Con Spring Security no es necesario crear una session para guardar el id del usuario.
    return "redirect:/events";
  }

}
