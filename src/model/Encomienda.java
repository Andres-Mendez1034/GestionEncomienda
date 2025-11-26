package model;

import interfaz.NotificacionInterfaz;
import java.util.ArrayList;
import java.util.List;

public class Encomienda {

    private int id;                  // ID en la base de datos
    private String numeroGuia;       // Número de guía de la encomienda
    private float peso;              // Peso de la encomienda
    private String destino;          // Destino de la encomienda
    private String estado;           // Estado de la encomienda (por ej., "Registrada", "Despachada")
    private int remitenteId;         // ID del remitente (relación con Remitente)
    private List<Observador> observadores; // Lista de observadores (para el patrón Observer)

    // --- Constructores ---
    public Encomienda() {
        observadores = new ArrayList<>();  // Inicializa la lista de observadores
    }

    public Encomienda(String numeroGuia, float peso, String destino) {
        this();
        this.numeroGuia = numeroGuia;
        this.peso = peso;
        this.destino = destino;
        this.estado = "Registrada";  // Estado inicial
        this.remitenteId = -1;      // ID de remitente por defecto (no válido)
    }

    public Encomienda(String numeroGuia, float peso, String destino, int remitenteId) {
        this();
        this.numeroGuia = numeroGuia;
        this.peso = peso;
        this.destino = destino;
        this.estado = "Registrada";
        this.remitenteId = remitenteId;
    }

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
        this.estado = "Despachada";  // Cambia el estado de la encomienda
        System.out.println("La encomienda " + numeroGuia + " ha sido despachada.");
        notificarObservadores();  // Notifica a los observadores
    }

    public String getDescripcion() {
        return "Guía: " + numeroGuia + ", destino: " + destino + ", peso: " + peso;
    }

    public double getValor() {
        return peso * 10.0;  // Calcula el valor basado en el peso
    }

    public void mostrarInformacion() {
        System.out.println("Número de Guía: " + numeroGuia);
        System.out.println("Peso: " + peso);
        System.out.println("Destino: " + destino);
        System.out.println("Estado: " + estado);
        System.out.println("Remitente ID: " + remitenteId);
    }

    // --- Métodos del patrón Observador ---
    public void agregarObservador(Observador observador) {
        observadores.add(observador);  // Agrega un observador
    }

    public void quitarObservador(Observador observador) {
        observadores.remove(observador);  // Quita un observador
    }

    private void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.update(this);  // Notifica a cada observador sobre el cambio
        }
    }

    // Método específico para agregar un observador de tipo NotificacionInterfaz
    public void agregarObservador(NotificacionInterfaz notificacionInterfaz) {
        observadores.add(notificacionInterfaz);  // Agrega una interfaz de notificación como observador
    }
}
