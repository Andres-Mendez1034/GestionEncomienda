package controller;

import dao.RemitenteDAO;
import dao.RemitenteDAOImpl;
import model.Remitente;

public class RemitenteController {

    private RemitenteDAO remitenteDAO;

    public RemitenteController() {
        this.remitenteDAO = new RemitenteDAOImpl();
    }

    public void registrarRemitente(String nombre, String direccion, String telefono) {
        Remitente r = new Remitente(nombre, direccion, telefono);
        remitenteDAO.crear(r);
    }

    public Remitente obtenerRemitente(String telefono) {
        return remitenteDAO.leer(telefono);
    }

    public void actualizarRemitente(String telefonoAntiguo, String nuevoNombre, String nuevaDireccion, String nuevoTelefono) {
        Remitente r = remitenteDAO.leer(telefonoAntiguo);
        if (r != null) {
            r.actualizarInfo(nuevoNombre, nuevaDireccion, nuevoTelefono);
            if (!telefonoAntiguo.equals(nuevoTelefono)) {
                remitenteDAO.eliminar(telefonoAntiguo);
            }
            remitenteDAO.actualizar(r);
        } else {
            System.out.println("Remitente no encontrado.");
        }
    }
}
