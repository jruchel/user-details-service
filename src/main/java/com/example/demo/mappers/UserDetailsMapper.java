package com.example.demo.mappers;


import com.example.demo.contollers.UserDetailsDTO;
import com.example.demo.models.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsMapper {

    public UserDetails dtoToUserDetails(UserDetailsDTO dto) {
        return UserDetails.builder().ipAddress(dto.getIpAddress()).message(dto.getMessage()).build();
    }

    public UserDetailsDTO userDetailsToDTO(UserDetails userDetails) {
        return UserDetailsDTO.builder().ipAddress(userDetails.getIpAddress()).message(userDetails.getMessage()).build();
    }

    public List<UserDetails> dtoListToDetails(List<UserDetailsDTO> dtos) {
        return dtos.stream().map(this::dtoToUserDetails).collect(Collectors.toList());
    }

    public List<UserDetailsDTO> userDetailsListToDTOs(List<UserDetails> userDetails) {
        return userDetails.stream().map(this::userDetailsToDTO).collect(Collectors.toList());
    }

}
