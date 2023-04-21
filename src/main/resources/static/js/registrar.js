$(document).ready(function() {
    // on ready
});

// ------------------------------------------------------------------------------

async function registrarUsuario () {

    // Obtengo los valores de los elementos HTML con los campos de registro
    let datos = {};
    datos.nombre = document.getElementById("txtNombre").value;
    datos.apellido = document.getElementById("txtApellido").value;
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;

    // Verifico que las contraseñas coincidan
    let repetirPassword = document.getElementById("txtRepetirPassword").value;
    if (repetirPassword != datos.password) {
        alert("Las contraseñas no coinciden")
        return; // se detiene la función para que no haga el request
    }

    // API FETCH POST
    const request = await fetch('api/usuarios', { // URL usuarios
        method: 'POST', // tipo de solicitud (HTTP POST)
        headers: { // encabezados
          'Accept': 'application/json',
          'Content-Type': 'application/json' // tipo de contenido
        },
        body: JSON.stringify(datos) // cuerpo (conversión de objeto JS a cadena JSON)
    });
    // VER ABAJO EXPLICACIÓN DE body: JSON.stringify

    /* Espera que la respuesta llegue y se convierta a JSON mediante el
    uso de la función ".json()" en el objeto "request" */
    const usuarios = await request.json();
}

/* ---------------- JSON.STRINGIFY()

--- body: JSON.stringify({a: 1, b: 'Textual content'})
Crea una cadena JSON a partir de un objeto JavaScript y la asigna a la variable
body. En este caso, el objeto tiene dos propiedades: a con un valor numérico
de 1 y b con un valor de cadena de texto de "Textual content".
La función JSON.stringify() se utiliza para convertir el objeto en una cadena
JSON válida, lo que permite enviar datos estructurados a través de una
solicitud HTTP.

*/