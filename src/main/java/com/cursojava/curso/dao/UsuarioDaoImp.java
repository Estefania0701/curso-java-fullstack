/* ------------- NOTA
Este código define una implementación concreta de la interfaz UsuarioDao que
permite acceder a los datos de usuarios almacenados en una base de datos a
través de la biblioteca de persistencia de Java llamada JPA (Java Persistence API).*/

package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/*
--- Se utiliza para marcar una clase como componente de acceso a datos
(DAO, por sus siglas en inglés).
--- Indica que la clase es responsable de interactuar con la capa de
persistencia de la aplicación, es decir, que se encarga de acceder a la
base de datos y realizar operaciones CRUD (crear, leer, actualizar y eliminar)
en las entidades.
--- Cuando se aplica la anotación @Repository a una clase, Spring la convierte
en un componente gestionado y administra su ciclo de vida, lo que significa
que se encarga de crear instancias de la clase y destruirlas cuando ya no son
necesarias.
--- También permite que la clase pueda ser escaneada automáticamente por
Spring y agregada al contexto de la aplicación. Esto significa que la clase
UsuarioDaoImp se puede utilizar en otras partes de la aplicación sin necesidad
de crear una instancia manualmente.
*/
@Repository

/*
--- Al aplicarla a nivel de clase, se asegura que todos los métodos públicos
de la clase se ejecuten dentro de una transacción
--- Una transacción es una unidad lógica de trabajo que agrupa un conjunto de
operaciones de base de datos. Todas las operaciones dentro de una transacción
se ejecutan como una unidad atómica, lo que significa que si una operación
falla, todas las operaciones realizadas dentro de la transacción se deshacen
o "deshacen" y se vuelve al estado anterior a la transacción.
--- En el contexto de esta clase, significa que todos los métodos públicos que
interactúan con la base de datos, incluyendo el método getUsuarios(), se
ejecutarán dentro de una transacción. Esto asegura que todas las operaciones
realizadas dentro de estos métodos se completen correctamente o se deshagan en
caso de errores, lo que garantiza la integridad y coherencia de los datos en
la base de datos.
*/
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    /* Es una anotación de JPA (Java Persistence API) que se utiliza para
    inyectar un objeto de tipo EntityManager en una clase. */
    @PersistenceContext
    /* El EntityManager es una interfaz que proporciona una API para realizar
    operaciones CRUD en la base de datos, así como otras operaciones avanzadas
    como consultas personalizadas y transacciones.
    Está diseñado para ser utilizado en el ámbito de una transacción, por lo
    que se debe garantizar que todas las operaciones realizadas con el
    EntityManager se realicen dentro del contexto de una transacción.*/
    private EntityManager entityManager;

    // Sobrescribe el método getUsuarios() definido en la interfaz UsuarioDao
    @Override
    /* De acuerdo con ChatGPT no es necesario volver a poner la anotación
    aquí si ya la había puesto a nivel de la clase y si además no estoy
    definiendo un comportamiento de transacción diferente para este método en
    particular. Sin embargo, la dejaré solo porque en el curso lo hacen... */
    @Transactional
    public List<Usuario> getUsuarios() {
        // Selecciono todos los usuarios de la tabla Usuario
        String query = "FROM Usuario";

        /* Ejecuta la consulta definida en la variable query utilizando el que
        se ha inyectado en la clase con la anotación @PersistenceContext. La
        consulta se ejecuta utilizando el método createQuery() del EntityManager,
        que devuelve un objeto de tipo Query. A continuación, se llama al
        método getResultList() de ese objeto para obtener una lista de objetos
        Usuario. La lista se almacena en la variable resultado.*/
        List<Usuario> resultado = entityManager.createQuery(query).getResultList();

        // Devuelvo la lista de usuarios previamente obtenida
        return resultado;
    }

    @Override
    public void eliminar(Long id) {
        /* Busca el objeto Usuario correspondiente al id pasado como argumento,
        utilizando el método find del objeto entityManager. Luego, se elimina
        ese objeto Usuario utilizando el método remove del objeto entityManager.*/
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        /* El método merge combina el estado del objeto usuario con el estado
        de la entidad correspondiente en la base de datos y devuelve una nueva
        instancia de la entidad actualizada o una referencia a la entidad
        actualizada si ya existe en la base de datos. Si el objeto usuario no
        está siendo rastreado por el contexto de persistencia, se convierte
        en una nueva entidad persistente. Si el objeto ya está siendo
        rastreado por el contexto de persistencia, sus cambios se sincronizan
        con la entidad persistente correspondiente en la base de datos.*/
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        /* Verifica si las credenciales de un usuario son correctas,
        es decir, si el email y la contraseña ingresados por el usuario
        coinciden con los datos almacenados en la base de datos.*/

        String query = "FROM Usuario WHERE email = :email";

        // La lista quedará vacía si la consulta no devuelve ningún dato
        // Significará que las credenciales no están registradas (nuevo usuario)
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        // Si la lista queda vacía se detiene la función para no generar error con el código siguiente
        if (lista.isEmpty()) {
            return null;
        }

        // Obtengo la contraseña hasheada
        String passwordHash = lista.get(0).getPassword();

        // Creo una instancia de Argon2
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);


        if (argon2.verify(passwordHash, usuario.getPassword())){
            return lista.get(0); // retorno el usuario
        };
        return null;
    }
}

// llegué hasta el 2:23:00