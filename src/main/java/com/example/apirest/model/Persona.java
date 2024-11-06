package com.example.apirest.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre", nullable = false, length = 70)
    private String nombre;
    @Column(name="apellido", nullable = false, length = 70)
    private String apellido;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name="telefono", length = 12)
    private String telefono;
}
