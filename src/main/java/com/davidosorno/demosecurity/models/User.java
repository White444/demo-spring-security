package com.davidosorno.demosecurity.models;

import java.time.Instant;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotEmpty
  @Size(min = 3, max = 12)
  private String firstName;

  @NotNull
  @NotEmpty
  @Size(min = 3, max = 12)
  private String lastName;

  @NotNull
  @NotEmpty
  @Email
  @Column(unique = true)
  private String email;

  @NotNull
  @NotEmpty
  @Size(min = 4, max = 20)
  private String city;

  @NotNull
  @NotBlank
  @NotEmpty
  @Size(min = 8, max = 100)
  private String password;

  @Transient
  private String passwordConfirmation;

  @NotNull
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @Column(updatable = false)
  private Instant createdAt;

  private Instant updatedAt;

  @PrePersist
  public void onCreate() {
    createdAt = Instant.now();
  }

  @PreUpdate
  public void onUpdate() {
    updatedAt = Instant.now();
  }

  public String fullName() {
    return firstName.concat(" ").concat(lastName);
  }

}
