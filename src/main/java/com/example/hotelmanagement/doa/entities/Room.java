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
    @Size(max = 900)
    @NotEmpty
    //@Pattern(regexp = "[a-zA-Z0-9\\s]*")
    private String roomNumber;
    @Min(0)
    private double pricePerNight;
    private boolean isAvailable;
    private String imageFileName;
    @Min(0)
    private int capacity;
    @ManyToOne
    private Hotel hotel;
}
