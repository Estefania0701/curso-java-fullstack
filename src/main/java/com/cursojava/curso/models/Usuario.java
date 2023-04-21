package com.cursojava.curso.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // Para indicar que es una entidad que hace referencia a la BBDD
@Table(name = "usuarios") // para indicarle a la función getUsuarios a cuál tabla debe ir
public class Usuario {

    // Con @Getter y @Setter no es necesario crear los respectivos métodos
    // Así se ahorra código

    // @Column es para indicarle a getUsuarios cuál columna es cuál

    @Id // para indicar que será la clave primaria, el identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para que se autoincremente
    @Getter @Setter @Column(name = "id")
    private long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;

}