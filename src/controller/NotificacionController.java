package controller;

import dao.NotificacionDAO;
import dao.NotificacionDAOImpl;
import model.NotificacionDTO;
import java.util.List;

public class NotificacionController {

    private final NotificacionDAO notificacionDAO;  // DAO para interactuar con notificaciones

    public NotificacionController() {
        this.notificacionDAO = new NotificacionDAOImpl();  // Inicializa el DAO
    }

    // Obtiene las notificaciones de una encomienda por número de guía
    public List<NotificacionDTO> obtenerPorEncomienda(String numeroGuia) {
        System.out.println("[LOG] Obteniendo notificaciones para la encomienda: " + numeroGuia);
        List<NotificacionDTO> notificaciones = notificacionDAO.obtenerPorEncomienda(numeroGuia);  // Llama al DAO

        if (notificaciones.isEmpty()) {
            System.out.println("[LOG] No se encontraron notificaciones.");
        }

        return notificaciones;
    }

    // Obtiene todas las notificaciones registradas
    public List<NotificacionDTO> obtenerTodas() {
        return notificacionDAO.obtenerTodas();  // Llama al DAO para obtener todas las notificaciones
    }

    // Crea una nueva notificación
    public void crearNotificacion(NotificacionDTO notificacion) {
        notificacionDAO.crear(notificacion);  // Llama al DAO para guardar la notificación
        System.out.println("Notificación creada: " + notificacion.getMensaje());  // Log de la creación
    }
}
