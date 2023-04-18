package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class UsuarioDaoImp implements UsuarioDao{
    @Override
    public List<Usuario> getUsuarios() {
        return null;
    }
}

// llegué hasta el 2:06:00
