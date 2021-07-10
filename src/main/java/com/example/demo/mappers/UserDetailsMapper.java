package com.example.demo.mappers;


import com.example.demo.contollers.UserDetailsDTO;
import com.example.demo.models.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails dtoToUserDetails(UserDetailsDTO dto) {
        return UserDetails.builder().ipAddress(dto.getIpAddress()).message(dto.getMessage()).build();
    }

    public UserDetailsDTO userDetailsToDTO(UserDetails userDetails) {
        return UserDetailsDTO.builder().ipAddress(userDetails.getIpAddress()).message(userDetails.getMessage()).build();
    }

}
