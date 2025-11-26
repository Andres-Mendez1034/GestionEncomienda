package model;

import interfaz.NotificacionInterfaz;
import java.util.ArrayList;
import java.util.List;

public class Encomienda {

    private int id;                  // ID en la base de datos
    private String numeroGuia;
    private float peso;
    private String destino;
    private String estado;           // requerido por el DAO
    private int remitenteId;         // relación con remitente
    private List<Observador> observadores; // Lista de observadores

    // --- Constructor vacío ---
    public Encomienda() { 
        observadores = new ArrayList<>();
    }

    // --- Constructor antiguo (3 parámetros) ---
    public Encomienda(String numeroGuia, float peso, String destino) {
        this();
        this.numeroGuia = numeroGuia;
        this.peso = peso;
        this.destino = destino;
        this.estado = "Registrada";
        this.remitenteId = -1; // valor por defecto inválido para prevenir inserciones sin remitente
    }

    // --- Constructor nuevo (4 parámetros: con remitenteId) ---
    public Encomienda(String numeroGuia, float peso, String destino, int remitenteId) {
        this();
        this.numeroGuia = numeroGuia;
        this.peso = peso;
        this.destino = destino;
        this.estado = "Registrada";
        this.remitenteId = remitenteId;
    }

    // --- Constructor alternativo con objeto Remitente ---
    public Encomienda(String numeroGuia, float peso, String destino, Remitente remitente) {
        this();
        this.numeroGuia = numeroGuia;
        this.peso = peso;
        this.destino = destino;
        this.estado = "Registrada";
        this.remitenteId = (remitente != null) ? remitente.getId() : -1;
    }

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumeroGuia() { return numeroGuia; }
    public void setNumeroGuia(String numeroGuia) { this.numeroGuia = numeroGuia; }

    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getRemitenteId() { return remitenteId; }
    public void setRemitenteId(int remitenteId) { this.remitenteId = remitenteId; }

    // --- Métodos de negocio ---
    public void despachar() {
        this.estado = "Despachada";
        System.out.println("La encomienda " + numeroGuia + " ha sido despachada.");
        notificarObservadores();  // Notificar a los observadores cuando cambia el estado
    }

    public String getDescripcion() {
        return "Guía: " + numeroGuia + ", destino: " + destino + ", peso: " + peso;
    }

    public double getValor() {
        return peso * 10.0;
    }

    public void mostrarInformacion() {
        System.out.println("Número de Guía: " + numeroGuia);
        System.out.println("Peso: " + peso);
        System.out.println("Destino: " + destino);
        System.out.println("Estado: " + estado);
        System.out.println("Remitente ID: " + remitenteId);
    }

    // --- Métodos del patrón Observador --- 
    
    // Método para agregar un observador
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    // Método para quitar un observador
    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    // Método para notificar a todos los observadores
    private void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.update(this); // Notifica a cada observador sobre el cambio
        }
    }
public void agregarObservador(NotificacionInterfaz notificacionInterfaz) {
    observadores.add(notificacionInterfaz);
}

}
