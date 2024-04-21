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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max=100)
    @NotEmpty
    //@Pattern(regexp = "[a-zA-Z0-9\\s]*")
    private String roomNumber;
    @NotNull
    @Min(0)
    private double pricePerNight;
    @NotNull
    private boolean isAvailable;
    @NotNull
    @Min(0)
    private int capacity;
    @ManyToOne
    private Hotel hotel;
}