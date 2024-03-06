package com.vassilyev.submitapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
    @Column(unique = true)
    private String name;



}
