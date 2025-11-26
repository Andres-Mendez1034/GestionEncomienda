package dao;

import model.NotificacionDTO;
import java.util.List;

public interface NotificacionDAO {

    // Obtiene las notificaciones asociadas a una encomienda por su número de guía
    List<NotificacionDTO> obtenerPorEncomienda(String numeroGuia);

    // Obtiene todas las notificaciones registradas
    List<NotificacionDTO> obtenerTodas();

    // Crea una nueva notificación en la base de datos
    void crear(NotificacionDTO n);

    // Obtiene las notificaciones asociadas a una guía (puede ser redundante con obtenerPorEncomienda)
    List<NotificacionDTO> obtenerPorGuia(String numeroGuia);  // Método adicional para obtener notificaciones por número de guía
}
