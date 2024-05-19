package com.example.hotelmanagement.doa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Booker{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "booker", fetch = FetchType.LAZY)
    private Collection<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "booker", fetch = FetchType.LAZY)
    private Collection<Booking> bookings = new ArrayList<>();
}