package model;

public class NotificacionDTO {

    private int id;
    private String mensaje;
    private String tipo;
    private int encomiendaId;
    private String numeroGuia;
    private String fecha;

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

        // valores por defecto
        this.id = 0;
        this.encomiendaId = 0;
        this.fecha = null;
    }

    // Método enviar (si lo usas en Service)
    public void enviar(String texto) {
        System.out.println("[NOTIFICACIÓN] " + texto);
    }

    // getters
    public int getId() { return id; }
    public String getMensaje() { return mensaje; }
    public String getTipo() { return tipo; }
    public int getEncomiendaId() { return encomiendaId; }
    public String getNumeroGuia() { return numeroGuia; }
    public String getFecha() { return fecha; }
}
