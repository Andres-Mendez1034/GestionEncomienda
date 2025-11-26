package controller;

import model.Encomienda;  // Clase Encomienda
import model.Empleado;    // Clase Empleado
import dao.EncomiendaDAOImpl; // Implementación DAO Encomienda
import dao.EncomiendaDAO;    // Interfaz DAO Encomienda

public class EmpleadoController {

    private EncomiendaDAO encomiendaDAO;  // DAO para Encomienda

    public EmpleadoController() {
        this.encomiendaDAO = new EncomiendaDAOImpl(); // Inicializa el DAO
    }

    public void asignarRecogida(Empleado empleado, Encomienda encomienda) {
        empleado.asignarRecogida(encomienda);  // Asigna recogida
    }

    public void despacharEncomienda(Empleado empleado, Encomienda encomienda) {
        empleado.despachar(encomienda);  // Despacha encomienda
        if (encomienda != null && encomienda.getNumeroGuia() != null) {
            encomiendaDAO.actualizar(encomienda);  // Actualiza encomienda
        }
    }

    public Encomienda buscarEncomienda(String numeroGuia) {
        return encomiendaDAO.leer(numeroGuia);  // Busca encomienda
    }
}
