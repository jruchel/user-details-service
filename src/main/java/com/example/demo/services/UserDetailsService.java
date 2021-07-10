package com.example.demo.services;

import com.example.demo.models.UserDetails;
import com.example.demo.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserDetailsRepository repository;

    public UserDetails save(UserDetails userDetails) {
        return repository.save(userDetails);
    }

}
