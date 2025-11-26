package model;

import dao.NotificacionDAO;
import dao.NotificacionDAOImpl;
import java.util.List;

public class NotificacionService {

    private final NotificacionDAO notificacionDAO;
    private static NotificacionService instance;

    private NotificacionService() {
        this.notificacionDAO = new NotificacionDAOImpl();
    }

    public static NotificacionService getInstance() {
        if (instance == null) {
            instance = new NotificacionService();
        }
        return instance;
    }

    /**
     * Crear una nueva notificación (pasada desde el Controller).
     */
    public void crearNotificacion(NotificacionDTO n) {
        notificacionDAO.crear(n);
    }

    /**
     * Obtiene todas las notificaciones por número de guía.
     */
    public List<NotificacionDTO> obtenerPorGuia(String numeroGuia) {
        return notificacionDAO.obtenerPorGuia(numeroGuia);
    }

    /**
     * Obtiene todas las notificaciones registradas.
     */
    public List<NotificacionDTO> obtenerTodas() {
        return notificacionDAO.obtenerTodas();
    }

public void agregarNotificacion(NotificacionDTO notificacion) {
    notificacionDAO.crear(notificacion);
}

}
