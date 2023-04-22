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

    const respuesta = await request.text();

    /* Para consultar los datos guardados en el local store:
    1. F12 para abrir inspeccionar
    2. Buscar la ventana Aplicación
    3. En el menú a la izquierda buscar la sección Almacenamient
    4. Click en la URL (en este caso http://localhost:8080*/
    if (respuesta != "FAIL") {
        localStorage.token = respuesta; // guardo el token en el local storage
        localStorage.email = datos.email; // también guardo el email
        window.location.href = "usuarios.html";
    } else {
        alert("El email o la contraseña son incorrectos. Verifica los datos ingresados")
    }
}