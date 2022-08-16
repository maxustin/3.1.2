package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "password")
   public String password;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "users_and_roles",
   joinColumns = @JoinColumn(name = "user_id"),
   inverseJoinColumns = @JoinColumn(name = "role_id"))
   private List<Role> roleSet = new ArrayList<>();

   public void addRole(Role role) {
      roleSet.add(role);
   }

   public void clearRoleSet() {
      roleSet.clear();
   }
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roleSet;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
