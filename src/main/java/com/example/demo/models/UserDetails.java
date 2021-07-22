package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Valid
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@CompoundIndexes(
        @CompoundIndex(def = "{'ipAddress': 1, 'message': 1}", unique = true)
)
public class UserDetails {

    @Id
    private String id;
    @Pattern(regexp = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    private String ipAddress;
    @Size(min = 4, max = 50)
    @Pattern(regexp = "\\w+")
    private String message;

}
