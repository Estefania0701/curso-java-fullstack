package com.cursojava.curso.controllers;

// los controladores sirven para manejar las URL
// para que las URL devuelvan cosas
// manejan las direcciones de URL

import com.cursojava.curso.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        // recibe un ID y devuelve el respectivo usuario
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        return usuario;
    }

    @RequestMapping(value = "usuario")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        return usuario;
    }

    @RequestMapping(value = "usuario")
    public Usuario eliminar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        return usuario;
    }

    @RequestMapping(value = "usuario")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        return usuario;
    }
}
