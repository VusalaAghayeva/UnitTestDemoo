package com.example.unittestdemoo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doctors")
    @SequenceGenerator(
            name = "seq_doctors", allocationSize = 1
    )
    private Long id;
    private String finCode;
    private String name;
    private String surname;
}
