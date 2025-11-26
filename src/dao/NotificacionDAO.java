package dao;

import model.NotificacionDTO;
import java.util.List;

public interface NotificacionDAO {
    List<NotificacionDTO> obtenerPorEncomienda(String numeroGuia);
    List<NotificacionDTO> obtenerTodas();
    void crear(NotificacionDTO n);
    
    // Asegúrate de que este método esté declarado correctamente
    List<NotificacionDTO> obtenerPorGuia(String numeroGuia);  // <-- Aquí está el método
}
