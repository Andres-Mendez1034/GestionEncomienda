package model;

import dao.NotificacionDAO;
import dao.NotificacionDAOImpl;
import java.util.List;

public class NotificacionService {

    private final NotificacionDAO notificacionDAO;  // DAO para gestionar notificaciones
    private static NotificacionService instance;    // Instancia única (Singleton)

    private NotificacionService() {
        this.notificacionDAO = new NotificacionDAOImpl();  // Inicializa el DAO
    }

    // Obtiene la instancia única del servicio
    public static NotificacionService getInstance() {
        if (instance == null) {
            instance = new NotificacionService();
        }
        return instance;
    }

    // Crea una nueva notificación
    public void crearNotificacion(NotificacionDTO n) {
        notificacionDAO.crear(n);
    }

    // Obtiene notificaciones por número de guía
    public List<NotificacionDTO> obtenerPorGuia(String numeroGuia) {
        return notificacionDAO.obtenerPorGuia(numeroGuia);
    }

    // Obtiene todas las notificaciones
    public List<NotificacionDTO> obtenerTodas() {
        return notificacionDAO.obtenerTodas();
    }

    // Agrega una notificación
    public void agregarNotificacion(NotificacionDTO notificacion) {
        notificacionDAO.crear(notificacion);
    }
}
