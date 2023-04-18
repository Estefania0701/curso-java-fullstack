// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
    $('#usuarios').DataTable();
});

// async para indicar que es asincrónica
async function cargarUsuarios () {
    const request = await fetch('usuarios', { // utiliza la API fetch para hacer una solicitud a la URL "usuario/1234"
        method: 'GET', // solicitud de tipo HTTP GET
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
    });

    // espera que la respuesta llegue y se convierta a JSON mediante el uso de la función ".json()" en el objeto "request"
    const usuarios = await request.json();

    // se registra en la consola el objeto que contiene los datos del usuario que se obtuvieron en la solicitud GET
    console.log(usuarios);

    // ciclo para crear varias filas de usuarios en la tabla
    let listadoHTML = "";
    for (let usuario of usuarios) {
        let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido
            +'</td><td>'+usuario.email+'</td><td>'+usuario.telefono
            +'</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>';
    listadoHTML += usuarioHTML;
    }


    document.querySelector("#usuarios tbody").outerHTML = listadoHTML;
}
