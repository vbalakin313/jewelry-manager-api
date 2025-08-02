package ru.vbalakin.jewelrymanagerapi.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vbalakin.jewelrymanagerapi.entities.UserEntity;
import ru.vbalakin.jewelrymanagerapi.repositories.UserRepository;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> myAppUser = userRepository.findByUsername(username);
        if (myAppUser.isPresent()) {
            var appUser = myAppUser.get();
            return User.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword())
                    .build();
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
