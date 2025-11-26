package interfaz;

import controller.NotificacionController;
import java.util.List;
import model.NotificacionDTO;
import java.util.Scanner;
import model.Encomienda;
import model.Observador;

public class NotificacionInterfaz implements Observador {

    private NotificacionController notificacionController;
    private Scanner scanner;

    public NotificacionInterfaz() {
        this.notificacionController = new NotificacionController();
        this.scanner = new Scanner(System.in);
    }

    public void update(Encomienda encomienda) {
        // Crear y mostrar una notificación cuando el estado de la encomienda cambia
        String mensaje = "La encomienda con número de guía " + encomienda.getNumeroGuia() + " ha cambiado de estado a: " + encomienda.getEstado();
        NotificacionDTO notificacion = new NotificacionDTO(mensaje, "Estado Actualizado", encomienda.getNumeroGuia());
        notificacionController.crearNotificacion(notificacion);
        System.out.println("Notificación generada: " + mensaje);
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n*** Menú Notificaciones ***");
            System.out.println("1. Ver Notificaciones de una Encomienda");
            System.out.println("2. Ver Todas las Notificaciones");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Para capturar el salto de línea

            switch (opcion) {
                case 1: verNotificacionesEncomienda(); break;
                case 2: verTodasNotificaciones(); break;
                case 3: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida, intenta nuevamente.");
            }
        }
    }

    private void verNotificacionesEncomienda() {
        System.out.print("Número de Guía de la encomienda: ");
        String numeroGuia = scanner.nextLine(); // Captura el número de guía

        // Agregar mensaje de depuración
        System.out.println("Buscando notificaciones para la encomienda con número de guía: " + numeroGuia);

        List<NotificacionDTO> notificaciones = notificacionController.obtenerPorEncomienda(numeroGuia);

        if (notificaciones.isEmpty()) {
            System.out.println("No se encontraron notificaciones para la encomienda con número de guía: " + numeroGuia);
        } else {
            for (NotificacionDTO n : notificaciones) {
                System.out.println("[" + n.getFecha() + "] (" + n.getTipo() + ") " + n.getMensaje());
            }
        }
    }

    private void verTodasNotificaciones() {
        for (NotificacionDTO n : notificacionController.obtenerTodas()) {
            System.out.println("[" + n.getFecha() + "] Encomienda " + n.getNumeroGuia() +
                    " (" + n.getTipo() + ") " + n.getMensaje());
        }
    }
}
