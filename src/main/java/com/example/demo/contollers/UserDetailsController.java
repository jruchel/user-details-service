package com.example.demo.contollers;

import com.example.demo.aspects.LogMethodCall;
import com.example.demo.mappers.UserDetailsMapper;
import com.example.demo.models.UserDetails;
import com.example.demo.services.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-details")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final UserDetailsMapper detailsMapper;

    @LogMethodCall
    @PostMapping
    public ResponseEntity<UserDetailsDTO> postUserDetails(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = detailsMapper.dtoToUserDetails(userDetailsDTO);
        userDetails = userDetailsService.save(userDetails);
        UserDetailsDTO response = detailsMapper.userDetailsToDTO(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @LogMethodCall
    @GetMapping
    public ResponseEntity<List<UserDetailsDTO>> getAll() {
        List<UserDetailsDTO> response = userDetailsService.getAll().stream().map(detailsMapper::userDetailsToDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}


