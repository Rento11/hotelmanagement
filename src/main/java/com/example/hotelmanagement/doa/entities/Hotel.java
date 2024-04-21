package com.example.hotelmanagement.doa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max=100)
    @NotEmpty
    //@Pattern(regexp = "[a-zA-Z0-9\\s]*")
    private String name;
    @NotNull
    @Size(max=100)
    @NotEmpty
    private String city;
    @NotNull
    @Size(max=100)
    @NotEmpty
    private String address;
    @OneToOne
    private Review review;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private Collection<Room> rooms = new ArrayList<>();
}
