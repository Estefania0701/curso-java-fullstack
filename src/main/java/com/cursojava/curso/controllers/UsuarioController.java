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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    a la URL "localhost:8080/usuario/{id}", este método será ejecutado.
    --- value indica la última parte de la URL a la que este método responderá.
    --- El valor completo de la URL que se debe utilizar para acceder a este
    método será la URL base del servidor (en este caso "localhost:8080")
    más la parte de la URL especificada en la anotación @RequestMapping
    (en este caso, "usuario/{id}")
    VER ABAJO PARA MÁS INFO SOBRE REQUEST MAPPING.*/
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
        return usuarioDao.getUsuarios();
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


/* ------------------- REQUEST MAPPING
También es posible agregar más información a la anotación @RequestMapping,
como el tipo de solicitud HTTP (GET, POST, PUT, DELETE), el tipo de contenido
que se espera (JSON, XML, HTML, etc.) y otros parámetros de configuración. De
esta manera, podemos crear una ruta de acceso muy específica y personalizada
para cada método dentro de nuestra aplicación web.
*/