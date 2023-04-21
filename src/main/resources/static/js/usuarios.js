/*
Se ejecuta al cargar el documento en el navegador y llama a dos funciones:
cargarUsuarios() y $('#usuarios').DataTable().
La función, $('#usuarios').DataTable(), es una función de jQuery que transforma
la tabla HTML "#usuarios" en una tabla dinámica que permite buscar, ordenar y
filtrar los datos de manera interactiva.
*/
$(document).ready(function() {
    cargarUsuarios();
    $('#usuarios').DataTable();
});

// ------------------------------------------------------------------------------

/* async indica que la función es asincrónica y puede esperar la respuesta
de una solicitud a un servidor.*/
async function cargarUsuarios () {

    /* Utiliza la API fetch para hacer una solicitud HTTP GET a la URL
    "usuarios". Esta solicitud se envía al servidor para obtener una lista
    de usuarios.
    --- fetch() devuelve una Promesa, que representa un valor que puede
    estar disponible ahora, en el futuro o nunca.
    --- Con await se crea una pausa en la ejecución del código en la línea
    donde se realiza la solicitud hasta que se resuelve la promesa devuelta
    por fetch(). Esto asegura que no se continúe con el resto del código
    hasta que se tenga la información necesaria.
    VER ABAJO MÁS INFO SOBRE ASYNC Y AWAIT*/
    const request = await fetch('api/usuarios', { // URL usuarios
        method: 'GET', // tipo de solicitud (HTTP GET)
        headers: { // encabezados
          'Accept': 'application/json',
          'Content-Type': 'application/json' // tipo de contenido
        }
    });

    /* Espera que la respuesta llegue y se convierta a JSON mediante el
    uso de la función ".json()" en el objeto "request" */
    const usuarios = await request.json();

    /* Registra los datos del objeto usuarios en la consola del navegador
    para fines de depuración.*/
    console.log(usuarios);

    /* Contendrá las filas de la tabla HTML que se creará dinámicamente con
    los datos de los usuarios.*/
    let listadoHTML = "";

    /* Recorre cada objeto de usuario en la lista de usuarios y crear una
    fila de tabla HTML con los datos de cada usuario.*/
    for (let usuario of usuarios) {

        // contiene el código HTML del botón eliminar
        let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        // Expresión ternaria (forma abreviada de escribir if-else)
        // condición ? expresión si verdadero : expresión si falso
        let telefonoTexto = usuario.telefono == null? "-" : usuario.telefono;

        let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido
            +'</td><td>'+usuario.email+'</td><td>'+telefonoTexto
            +'</td><td>'+botonEliminar+'</td></tr>';

    /* La cadena de texto de la fila de usuario se agrega a la cadena
    listadoHTML en cada iteración del bucle. */
    listadoHTML += usuarioHTML;
    }

    /* La cadena listadoHTML se asigna al HTML del elemento tbody de la
    tabla HTML, reemplazando cualquier contenido que ya haya sido generado
    en esa área de la tabla.*/
    document.querySelector("#usuarios tbody").outerHTML = listadoHTML;
}

async function eliminarUsuario(id) {
    // elimina un usuario con base en su ID

    // si el usuario responde "no" (!)
    if (!confirm("¿Desea eliminar este usuario?")){
        return; // se corta el flujo de la función, por lo que no se elimina

    }
    const request = await fetch('api/usuarios/'+id, { // URL usuarios
        method: 'DELETE', // tipo de solicitud (HTTP DELETE)
        headers: { // encabezados
          'Accept': 'application/json',
          'Content-Type': 'application/json' // tipo de contenido
        }
    });

    // se actualiza la página automáticamente
    location.reload();
}




/* ------------------------ ASYNC Y AWAIT -----------------------------

El uso de async y await está relacionado con el manejo de promesas.
Al declarar una función con la palabra clave async, esta automáticamente
devuelve una promesa. Además, cualquier función que tenga una promesa como
resultado puede ser marcada con la palabra clave await para pausar la
ejecución del código hasta que la promesa se resuelva.

Cuando se utiliza await en una función async, el código que sigue
inmediatamente después de await solo se ejecutará una vez que la promesa se
haya resuelto. Mientras tanto, el hilo de ejecución se liberará y se
permitirá que otras operaciones continúen. En otras palabras, await detiene
la ejecución del código en ese punto hasta que se resuelve la promesa, pero
permite que el programa siga avanzando en otros aspectos, lo que lo hace
útil para trabajar con solicitudes asincrónicas y otras operaciones que
pueden tardar un tiempo variable en completarse.
*/