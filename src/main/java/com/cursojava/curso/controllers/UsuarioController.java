/* --------------- QUÉ ES INYECCIÓN E INYECCIÓN DE DEPENDENCIAS -----------

El término "inyección" en el contexto de la programación se refiere a
proporcionar una dependencia a un objeto en tiempo de ejecución. En otras
palabras, en lugar de que el objeto cree o busque la dependencia por sí mismo,
se le "inyecta" la dependencia desde fuera.

El término "inyección de dependencias" se refiere específicamente a la técnica
de proporcionar dependencias a un objeto a través de un mecanismo de inyección.
En lugar de que el objeto cree o busque sus dependencias, la inyección de
dependencias se encarga de proporcionar esas dependencias de forma externa.

La inyección de dependencias se utiliza para lograr la inversión de control,
que es una técnica para invertir la relación de control entre las clases.
En lugar de que una clase tenga el control y decida qué otras clases debe usar,
la inversión de control permite que sea el contenedor de inyección de
dependencias el que tenga el control y decida qué clases deben usarse.*/

package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
--- Es una combinación de las anotaciones @Controller y @ResponseBody. Esta
anotación se utiliza para marcar una clase como controlador de Spring MVC,
que se encarga de manejar las solicitudes HTTP y devolver las respuestas
correspondientes.
--- Cuando una clase está marcada con la anotación @RestController, Spring la
trata como un controlador que devuelve directamente objetos en lugar de modelos
y vistas.
--- Los métodos dentro de esta clase que manejan las solicitudes HTTP se
anotan con @RequestMapping y los objetos que se devuelven se convierten
automáticamente a JSON o XML (según la configuración de Spring) y se envían
en la respuesta HTTP.
--- Esta anotación es útil cuando se quiere construir una API RESTful, ya que
permite devolver objetos en formato JSON o XML de manera sencilla, sin tener
que preocuparse por la serialización y deserialización de los mismos.
*/
@RestController
public class UsuarioController {

    /* Es una de las características clave del framework de Spring.
    --- Permite que la instancia de un objeto se injecte automáticamente en
    una clase que lo necesite, sin que sea necesario crear una instancia de
    ese objeto manualmente.
    --- Al usar la anotación @Autowired en una clase, Spring buscará en su
    contexto de aplicación (el contenedor de Spring) un objeto que coincida
    con el tipo de la variable anotada con @Autowired y, si encuentra una
    coincidencia, lo inyectará en esa variable.*/
    @Autowired
    private UsuarioDao usuarioDao;

    /* La anotación @RequestMapping es utilizada en métodos dentro de una
    clase anotada con @Controller o @RestController para mapear URLs a métodos
    específicos que manejan solicitudes HTTP.
    --- value indica que cuando el servidor reciba una solicitud HTTP GET
    a la URL "localhost:8080/api/usuario/{id}", este método será ejecutado.
    --- El valor completo de la URL que se debe utilizar para acceder a este
    método será la URL base del servidor (en este caso "localhost:8080")
    más la parte de la URL especificada en la anotación @RequestMapping
    (en este caso, "api/usuario/{id}")
    VER ABAJO PARA MÁS INFO SOBRE REQUEST MAPPING.*/
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET) // "api" es solo para separar a nivel práctico el frontend del backend
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

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        // devuelve una lista de usuarios
        return usuarioDao.getUsuarios();
    }

    /*
    --- La anotación @RequestBody en Spring indica que un método de controlador
    debe esperar un objeto en el cuerpo de una solicitud HTTP entrante y luego
    intentará convertir ese cuerpo de solicitud en el objeto Java
    correspondiente. Es decir, Spring intentará deserializar el cuerpo de la
    solicitud en el objeto Java que se especifique en el parámetro del método
    anotado con @RequestBody.
    --- El método registrarUsuario() espera un objeto Usuario en el cuerpo
    de la solicitud HTTP entrante. Luego, Spring intentará convertir el cuerpo
    de la solicitud en un objeto Usuario. Si la conversión es exitosa, se
    pasará el objeto Usuario como parámetro al método registrar() del objeto
    usuarioDao.*/
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        /* Creo una instancia de la clase Argon2, que es una biblioteca de
        hash de contraseñas que utiliza un algoritmo seguro y resistente
        a los ataques de fuerza bruta.*/
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        /* Genero un hash seguro de la contraseña del usuario, utilizando
        el método hash(). Este método toma cuatro parámetros: el número de
        iteraciones, la memoria requerida, el número de hilos y la
        contraseña en sí.*/
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());

        // Reasigno la contraseña con el hash generado
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
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

    /* DELETE es una de las operaciones CRUD (Create, Read, Update, Delete)
    que se pueden realizar en una API REST
    Cuando un cliente realiza una solicitud DELETE a una URL en un servidor,
    el servidor elimina el recurso identificado por la URL y devuelve una
    respuesta al cliente indicando que se ha eliminado el recurso o, si no
    se pudo eliminar, una indicación de error.*/
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    /* @PathVariable indica que el valor del parámetro de método (en este caso,
    "id") se obtendrá de la variable en la URL. En otras palabras, este
    parámetro se extrae de la URL y se asigna al parámetro "id" del método.*/
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);
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


/* ------------------- REQUEST MAPPING
También es posible agregar más información a la anotación @RequestMapping,
como el tipo de solicitud HTTP (GET, POST, PUT, DELETE), el tipo de contenido
que se espera (JSON, XML, HTML, etc.) y otros parámetros de configuración. De
esta manera, podemos crear una ruta de acceso muy específica y personalizada
para cada método dentro de nuestra aplicación web.

Por defecto (si no se especifica uno), el tipo de método usado es GET
*/