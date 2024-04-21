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
    @NotNull
    @Size(max=100)
    @NotEmpty
    private String username;
    @NotNull
    @Size(max=100)
    @NotEmpty
    private String email;
    @NotNull
    @Size(max=100)
    @NotEmpty
    private String password;
}
