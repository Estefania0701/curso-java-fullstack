package com.cursojava.curso.controllers;

// los controladores sirven para manejar las URL
// para que las URL devuelvan cosas
// manejan las direcciones de URL

import com.cursojava.curso.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping(value = "usuarios")
    public List<Usuario> getUsuarios(){
        // devuelve una lista de usuarios

        // lista de usuarios
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();
        usuario.setId(123L);
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        Usuario usuario2 = new Usuario();
        usuario2.setId(456L);
        usuario2.setNombre("Jassir");
        usuario2.setApellido("Agressoth");
        usuario2.setEmail("jass.ud2015@gmail.com");
        usuario2.setTelefono("3123456789");

        Usuario usuario3 = new Usuario();
        usuario3.setId(789L);
        usuario3.setNombre("Pepito");
        usuario3.setApellido("Pérez");
        usuario3.setEmail("elpepo@gmail.com");
        usuario3.setTelefono("3003887649");

        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        return usuarios;
    }

    @RequestMapping(value = "usuario2")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        return usuario;
    }

    @RequestMapping(value = "usuario3")
    public Usuario eliminar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        return usuario;
    }

    @RequestMapping(value = "usuario4")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Estefanía");
        usuario.setApellido("Aguas");
        usuario.setEmail("estefaniaaguas@gmail.com");
        usuario.setTelefono("3145936328");

        return usuario;
    }
}
