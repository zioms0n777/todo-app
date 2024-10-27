package net.ziomson.todo_app.security;

import lombok.AllArgsConstructor;
import net.ziomson.todo_app.exception.ResourceNotFoundException;
import net.ziomson.todo_app.model.User;
import net.ziomson.todo_app.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user= userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new ResourceNotFoundException("Username doesnt exists"));

        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(),
                        user.getPassword(),
                        authorities);
    }
}
