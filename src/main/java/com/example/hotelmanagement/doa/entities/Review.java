package com.example.hotelmanagement.doa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int rating;
    private String comment;
    private LocalDate date;
    @OneToOne
    private Hotel hotel;
    @ManyToOne
    private Booker booker;
}
