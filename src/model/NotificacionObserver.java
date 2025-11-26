package model;

public class NotificacionObserver implements Observador {

    private NotificacionService notificacionService;

    public NotificacionObserver(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @Override
    public void update(Encomienda encomienda) {
        String mensaje = "La encomienda con número de guía " + encomienda.getNumeroGuia() + 
                         " ha cambiado de estado a: " + encomienda.getEstado();
        notificacionService.agregarNotificacion(new NotificacionDTO(mensaje, "Estado Encomienda", encomienda.getNumeroGuia()));
    }
}
