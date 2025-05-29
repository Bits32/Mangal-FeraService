package com.example.mangalfera.security;

import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
private UserRepository userRepository;

  @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(login)
            .or(() -> userRepository.findByEmail(login))
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + login));

    return new CustomUserDetails(user) {

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
      }
    };
  }
}
