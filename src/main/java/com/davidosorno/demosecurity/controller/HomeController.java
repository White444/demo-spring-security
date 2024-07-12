package com.davidosorno.demosecurity.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.davidosorno.demosecurity.models.User;
import com.davidosorno.demosecurity.services.UserService;

@Controller
public class HomeController {
  
  private final UserService userService;

  public HomeController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public String home(@ModelAttribute("user") User user) {
    return "index.jsp";
  }

  // @GetMapping("/login")
  // public String login(@ModelAttribute("user") User user) {
  //   return "index.jsp";
  // }

  @GetMapping("/events")
  public String events(Principal principal, Model model) {
    model.addAttribute("user", userService.findByEmail(principal.getName()));
    return "events.jsp";
  }

  @GetMapping("/events/{eventId}")
  public String findEventById(@PathVariable("eventId") Long eventId, Model model) {
    model.addAttribute("title", "Título del Evento desde el Backend");
    return "events.jsp";
  }

  @GetMapping("/events/{name}/{eventId}")
  public String details(
    @PathVariable("name") String name, 
    @PathVariable("eventId") Long eventId, 
  Model model) {
    model.addAttribute("title", name + "EN DETAILS Título del Evento desde el Backend");
    return "events.jsp";
  }

  @GetMapping("/admin")
  public String onlyAdmin(Model model) {
    model.addAttribute("title", "Aquí solo puede ingresar un usuario con role ADMIN, FELICITACIONES eres ADMIN!!!");
    return "events.jsp";
  }

  @GetMapping("/admin/{adminId}")
  public String onlyAdminWithId(@PathVariable("adminId") Long adminId, Model model) {
    model.addAttribute("title", "FELICITACIONES eres ADMIN con ID " + adminId );
    return "events.jsp";
  }

}
