package model;

public class Remitente {

    private int id;            // ID en la base de datos
    private String nombre;
    private String direccion;
    private String telefono;   // mapeado a "movil" en la BD

    // --- Constructor vacío ---
    public Remitente() { }

    // --- Constructor con parámetros (sin id) ---
    public Remitente(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // --- Constructor con todos los parámetros (incluyendo id) ---
    public Remitente(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // --- Métodos de negocio ---
    public void registrar() {
        System.out.println("Remitente registrado: " + nombre);
    }

    public void actualizarInfo(String nuevoNombre, String nuevaDireccion, String nuevoTelefono) {
        this.nombre = nuevoNombre;
        this.direccion = nuevaDireccion;
        this.telefono = nuevoTelefono;
        System.out.println("Información del remitente actualizada: " + nombre);
    }

    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
    }
}
