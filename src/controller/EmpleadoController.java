package controller;

import model.Encomienda;
import model.Empleado;
import dao.EncomiendaDAOImpl;
import dao.EncomiendaDAO;

public class EmpleadoController {

    private EncomiendaDAO encomiendaDAO;

    public EmpleadoController() {
        this.encomiendaDAO = new EncomiendaDAOImpl();
    }

    public void asignarRecogida(Empleado empleado, Encomienda encomienda) {
        empleado.asignarRecogida(encomienda);
    }

    public void despacharEncomienda(Empleado empleado, Encomienda encomienda) {
        empleado.despachar(encomienda);
        if (encomienda != null && encomienda.getNumeroGuia() != null) {
            encomiendaDAO.actualizar(encomienda);
        }
    }

    public Encomienda buscarEncomienda(String numeroGuia) {
        return encomiendaDAO.leer(numeroGuia);
    }
}
