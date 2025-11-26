package model;

public class ConsoleNotificacion implements Notificacion {

    // Implementación del método enviar para mostrar un mensaje en la consola
    @Override
    public void enviar(String mensaje) {
        System.out.println("[NOTIFICACION] " + mensaje);  // Imprime el mensaje de notificación en consola
    }
}
