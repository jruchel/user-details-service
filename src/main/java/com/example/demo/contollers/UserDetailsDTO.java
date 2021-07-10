package com.example.demo.contollers;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsDTO {

    private String ipAddress, message;

}
