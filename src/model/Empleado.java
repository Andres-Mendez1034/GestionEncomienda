package model;

public class Empleado {

    private int id;  // ID del empleado
    private String nombre;  // Nombre del empleado
    private String rol;  // Rol del empleado

    // Constructor para inicializar un empleado
    public Empleado(int id, String nombre, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    // Asigna al empleado una encomienda para recoger
    public void asignarRecogida(Encomienda encomienda) {
        System.out.println("Empleado " + nombre + " asignado para recoger la encomienda " + encomienda.getNumeroGuia());
    }

    // Despacha una encomienda
    public void despachar(Encomienda encomienda) {
        encomienda.setEstado("Despachada");  // Cambia el estado de la encomienda
        System.out.println("Empleado " + nombre + " ha despachado la encomienda " + encomienda.getNumeroGuia());
    }

    // Métodos getter y setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
