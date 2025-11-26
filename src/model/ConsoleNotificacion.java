package model;


public class ConsoleNotificacion implements Notificacion {

    @Override
    public void enviar(String mensaje) {
        System.out.println("[NOTIFICACION] " + mensaje);
    }
}
