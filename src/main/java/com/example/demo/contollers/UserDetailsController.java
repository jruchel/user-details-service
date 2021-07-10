package com.example.demo.contollers;

import com.example.demo.mappers.UserDetailsMapper;
import com.example.demo.models.UserDetails;
import com.example.demo.services.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-details")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final UserDetailsMapper detailsMapper;

    @PostMapping
    public ResponseEntity<UserDetailsDTO> postUserDetails(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = detailsMapper.dtoToUserDetails(userDetailsDTO);
        userDetails = userDetailsService.save(userDetails);
        UserDetailsDTO response = detailsMapper.userDetailsToDTO(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
