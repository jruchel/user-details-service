package com.example.demo.repositories;

import com.example.demo.models.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

    List<UserDetails> findAllByIpAddress(String ipAddress);
}
