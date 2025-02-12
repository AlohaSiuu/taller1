async function fetchReservas() {
    try {
        const response = await fetch('http://localhost:8080/api/reservas'); // Endpoint del backend
        if (!response.ok) throw new Error('Error al obtener las reservas');
        const reservas = await response.json();

        // Seleccionar el cuerpo de la tabla
        const reservasList = document.getElementById('reservas-list');
        reservasList.innerHTML = ''; // Limpiar tabla antes de agregar filas

        // Llenar la tabla con las reservas obtenidas
        reservas.forEach(reserva => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${reserva.cliente ? reserva.cliente.nombre : 'Anónimo'}</td>
                <td>${new Date(reserva.fechaHora).toLocaleString()}</td>
                <td>${reserva.numeroPersonas}</td>
                <td>${reserva.mesa ? reserva.mesa.numeroDeMesa : 'Sin asignar'}</td>
            `;
            reservasList.appendChild(row);
        });
    } catch (error) {
        console.error('Error al obtener las reservas:', error);
    }
}

// Ejecutar al cargar la página
document.addEventListener('DOMContentLoaded', fetchReservas);
