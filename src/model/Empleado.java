package model;

public class Empleado {

    private int id;
    private String nombre;
    private String rol;


    public Empleado(int id, String nombre, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }


    public void asignarRecogida(Encomienda encomienda) {
        System.out.println("Empleado " + nombre + " asignado para recoger la encomienda " + encomienda.getNumeroGuia());
    }


    public void despachar(Encomienda encomienda) {
        encomienda.setEstado("Despachada");
        System.out.println("Empleado " + nombre + " ha despachado la encomienda " + encomienda.getNumeroGuia());
    }

    public int getId() { return id; }


    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }


    public String getRol() { return rol; }


    public void setRol(String rol) { this.rol = rol; }
}
