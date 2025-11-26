package controller;

import dao.NotificacionDAO;
import dao.NotificacionDAOImpl;
import model.NotificacionDTO;
import java.util.List;

public class NotificacionController {

    private final NotificacionDAO notificacionDAO;

    // Constructor para inicializar el DAO
    public NotificacionController() {
        this.notificacionDAO = new NotificacionDAOImpl();
    }

    /**
     * Obtiene las notificaciones asociadas a una encomienda, usando el número de guía.
     * Cambiado para que acepte un String (número de guía).
     */
    public List<NotificacionDTO> obtenerPorEncomienda(String numeroGuia) {
        // Mensaje de depuración para verificar que el controlador recibe el número de guía
        System.out.println("[LOG] En controlador: Obteniendo notificaciones para la encomienda con número de guía: " + numeroGuia);

        List<NotificacionDTO> notificaciones = notificacionDAO.obtenerPorEncomienda(numeroGuia); // Llamada al DAO

        if (notificaciones.isEmpty()) {
            System.out.println("[LOG] No se encontraron notificaciones para la encomienda con número de guía: " + numeroGuia);
        }

        return notificaciones;
    }

    /**
     * Obtiene todas las notificaciones registradas.
     */
    public List<NotificacionDTO> obtenerTodas() {
        return notificacionDAO.obtenerTodas();
    }

public void crearNotificacion(NotificacionDTO notificacion) {

    
    NotificacionDAO notificacionDAO = new NotificacionDAOImpl();
    
    notificacionDAO.crear(notificacion);
    

    System.out.println("Notificación creada: " + notificacion.getMensaje());
}

}
