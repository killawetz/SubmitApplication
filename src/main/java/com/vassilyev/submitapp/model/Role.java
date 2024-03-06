package com.vassilyev.submitapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;
}
