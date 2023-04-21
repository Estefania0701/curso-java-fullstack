$(document).ready(function() {
    // on ready
});

// ------------------------------------------------------------------------------

async function iniciarSesion () {

    // Obtengo los valores de los elementos HTML con los campos de registro
    let datos = {};
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;

    // API FETCH POST
    const request = await fetch('api/login', { // URL usuarios
        method: 'POST', // tipo de solicitud (HTTP POST)
        headers: { // encabezados
          'Accept': 'application/json',
          'Content-Type': 'application/json' // tipo de contenido
        },
        body: JSON.stringify(datos) // cuerpo (conversión de objeto JS a cadena JSON)
    });

    const respuesta = await request.json();
}