package com.cursojava.curso.dao;


import com.cursojava.curso.models.Usuario;

import java.util.List;

/* Es una interfaz que define la operaciones CRUD que se pueden realizar
sobre un objeto Usuario */
public interface UsuarioDao {
    List<Usuario> getUsuarios();
}
