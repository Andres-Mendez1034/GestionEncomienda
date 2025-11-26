package controller;

import dao.RemitenteDAO;
import dao.RemitenteDAOImpl;
import model.Remitente;

public class RemitenteController {

    private RemitenteDAO remitenteDAO;  // DAO para gestionar remitentes

    public RemitenteController() {
        this.remitenteDAO = new RemitenteDAOImpl();  // Inicializa el DAO
    }

    // Registra un nuevo remitente
    public void registrarRemitente(String nombre, String direccion, String telefono) {
        Remitente r = new Remitente(nombre, direccion, telefono);  // Crea un objeto Remitente
        remitenteDAO.crear(r);  // Llama al DAO para registrar el remitente
    }

    // Obtiene un remitente por su teléfono
    public Remitente obtenerRemitente(String telefono) {
        return remitenteDAO.leer(telefono);  // Llama al DAO para obtener el remitente
    }

    // Actualiza la información de un remitente
    public void actualizarRemitente(String telefonoAntiguo, String nuevoNombre, String nuevaDireccion, String nuevoTelefono) {
        Remitente r = remitenteDAO.leer(telefonoAntiguo);  // Recupera el remitente por teléfono
        if (r != null) {
            r.actualizarInfo(nuevoNombre, nuevaDireccion, nuevoTelefono);  // Actualiza los datos del remitente
            if (!telefonoAntiguo.equals(nuevoTelefono)) {
                remitenteDAO.eliminar(telefonoAntiguo);  // Elimina el viejo teléfono si cambió
            }
            remitenteDAO.actualizar(r);  // Guarda los cambios
        } else {
            System.out.println("Remitente no encontrado.");  // Maneja el caso en que no se encuentra el remitente
        }
    }
}
