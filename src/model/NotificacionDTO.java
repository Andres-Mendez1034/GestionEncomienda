package model;

public class NotificacionDTO {

    private int id;                  // ID de la notificación
    private String mensaje;          // Mensaje de la notificación
    private String tipo;             // Tipo de notificación (por ejemplo, "Despacho")
    private int encomiendaId;        // ID de la encomienda asociada
    private String numeroGuia;       // Número de guía asociado
    private String fecha;            // Fecha de la notificación

    // Constructor COMPLETO (para BD)
    public NotificacionDTO(int id, String mensaje, String tipo, int encomiendaId, String numeroGuia, String fecha) {
        this.id = id;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.encomiendaId = encomiendaId;
        this.numeroGuia = numeroGuia;
        this.fecha = fecha;
    }

    // Constructor SIMPLE (para crear notificaciones nuevas)
    public NotificacionDTO(String mensaje, String tipo, String numeroGuia) {
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.numeroGuia = numeroGuia;

        // Valores por defecto
        this.id = 0;
        this.encomiendaId = 0;
        this.fecha = null;
    }

    // Método para simular el envío de la notificación
    public void enviar(String texto) {
        System.out.println("[NOTIFICACIÓN] " + texto);  // Imprime el mensaje de la notificación
    }

    // Getters
    public int getId() { return id; }
    public String getMensaje() { return mensaje; }
    public String getTipo() { return tipo; }
    public int getEncomiendaId() { return encomiendaId; }
    public String getNumeroGuia() { return numeroGuia; }
    public String getFecha() { return fecha; }
}
