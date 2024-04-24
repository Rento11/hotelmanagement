package com.example.hotelmanagement.doa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max=100)
    @NotEmpty
    private String username;
    @Size(max=100)
    @NotEmpty
    private String email;
    @Size(max=100)
    @NotEmpty
    private String password;
}