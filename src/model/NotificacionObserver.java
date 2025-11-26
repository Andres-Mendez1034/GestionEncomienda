package model;

public class NotificacionObserver implements Observador {

    private NotificacionService notificacionService;  // Servicio encargado de manejar las notificaciones

    // Constructor que recibe el servicio de notificación
    public NotificacionObserver(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;  // Inicializa el servicio
    }

    // Método que se ejecuta cuando la encomienda cambia de estado
    @Override
    public void update(Encomienda encomienda) {
        // Crea un mensaje sobre el cambio de estado de la encomienda
        String mensaje = "La encomienda con número de guía " + encomienda.getNumeroGuia() + 
                         " ha cambiado de estado a: " + encomienda.getEstado();
        
        // Agrega la notificación al servicio de notificaciones
        notificacionService.agregarNotificacion(new NotificacionDTO(mensaje, "Estado Encomienda", encomienda.getNumeroGuia()));
    }
}
