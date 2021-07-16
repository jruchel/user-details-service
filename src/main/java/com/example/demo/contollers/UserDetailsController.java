package com.example.demo.contollers;

import com.example.demo.aspects.LogMethodCall;
import com.example.demo.mappers.UserDetailsMapper;
import com.example.demo.models.UserDetails;
import com.example.demo.services.UserDetailsService;
import com.example.demo.services.external.UserDetailsFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-details")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final UserDetailsMapper detailsMapper;
    private final UserDetailsFilterService filterService;

    @LogMethodCall
    @PostMapping
    public ResponseEntity<UserDetailsDTO> postUserDetails(@RequestBody UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = detailsMapper.dtoToUserDetails(userDetailsDTO);
        userDetails = userDetailsService.save(userDetails);
        UserDetailsDTO response = detailsMapper.userDetailsToDTO(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @LogMethodCall
    @PostMapping("/list")
    public ResponseEntity<List<UserDetailsDTO>> postUserDetails(@RequestBody List<UserDetailsDTO> userDetailsDTOS) {
        List<UserDetails> userDetails = detailsMapper.dtoListToDetails(userDetailsDTOS);
        List<UserDetails> serviceResult = filterService.filter(userDetails);
        userDetailsService.saveAll(serviceResult);
        List<UserDetailsDTO> response = detailsMapper.userDetailsListToDTOs(serviceResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @LogMethodCall
    @GetMapping
    public ResponseEntity<List<UserDetailsDTO>> getAll() {
        List<UserDetailsDTO> response = userDetailsService.getAll().stream().map(detailsMapper::userDetailsToDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}


