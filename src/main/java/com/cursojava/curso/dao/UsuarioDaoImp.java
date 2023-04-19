package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // funcionalidad de acceder a la BBDD
@Transactional // forma en que tratará las consultas de SQL (fragmentos de transacción)
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager; // Para hacer la conexión con la BBDD

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";

        // listado de usuarios (getResultList para transformar en lista)
        List<Usuario> resultado = entityManager.createQuery(query).getResultList();

        return resultado;
    }
}

// llegué hasta el 2:06:00
