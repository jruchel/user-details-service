package com.example.demo.services;

import com.example.demo.models.UserDetails;
import com.example.demo.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserDetailsRepository repository;

    public UserDetails save(UserDetails userDetails) {
        return repository.save(userDetails);
    }

    public List<UserDetails> saveAll(List<UserDetails> userDetails) {
        return repository.saveAll(userDetails);
    }

    public List<UserDetails> getAllByIpAddress(String ipAddress) {
        return repository.findAllByIpAddress(ipAddress);
    }

    public List<UserDetails> getAll() {
        return repository.findAll();
    }

}
