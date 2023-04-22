package com.cursojava.curso.dao;


import com.cursojava.curso.models.Usuario;

import java.util.List;

/* Es una interfaz que define la operaciones CRUD que se pueden realizar
sobre un objeto Usuario */
public interface UsuarioDao {
    List<Usuario> getUsuarios();

    // para eliminar usuarios
    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
