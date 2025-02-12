// Validar los datos del formulario
function validarFormulario(cliente) {
    if (!cliente.nombre || !cliente.email || !cliente.telefono || !cliente.password) {
        return { valid: false, message: "Todos los campos son obligatorios." };
    }
    return { valid: true };
}

// Enviar el formulario al API
document.getElementById("registroForm").addEventListener("submit", async function (event) {
    event.preventDefault(); // Evita que el formulario se envíe por defecto

    // Obtener los valores del formulario
    const cliente = {
        nombre: document.getElementById("nombre").value,
        email: document.getElementById("email").value,
        telefono: document.getElementById("telefono").value,
        password: document.getElementById("password").value,
    };

    // Validar el formulario antes de enviarlo
    const validacion = validarFormulario(cliente);
    if (!validacion.valid) {
        document.getElementById("mensaje").innerHTML = `<div class="alert alert-danger">${validacion.message}</div>`;
        return;
    }

    try {
        // Realizar la petición POST al API
        const response = await fetch('/api/clientes/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente)
        });

        if (response.ok) {
            // Si la respuesta es OK (200), mostramos un mensaje de éxito
            document.getElementById('mensaje').innerHTML = `
                <div class="alert alert-success">¡Registro Completado!</div>
            `;
            document.getElementById('registroForm').reset(); // Limpiar el formulario
        } else {
            // Cuando la respuesta no es OK, se debe leer como texto
            const errorMessage = await response.text(); // Leemos el mensaje de error como texto
            document.getElementById('mensaje').innerHTML = `
                <div class="alert alert-danger">${errorMessage}</div>
            `;
        }
    } catch (error) {
        // En caso de un error inesperado (como un fallo en la red)
        document.getElementById('mensaje').innerHTML = `
            <div class="alert alert-danger">Error: ${error.message}</div>
        `;
    }
});
