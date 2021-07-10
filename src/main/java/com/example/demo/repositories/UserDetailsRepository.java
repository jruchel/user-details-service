package com.example.demo.repositories;

import com.example.demo.models.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsRepository {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public UserDetails save(UserDetails userDetails) {
        logger.info(userDetails.toString());
        return userDetails;
    }

}
