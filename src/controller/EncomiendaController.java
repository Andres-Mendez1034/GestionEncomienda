package controller;

import dao.EncomiendaDAO;
import dao.EncomiendaDAOImpl;
import dao.NotificacionDAO;

import model.Encomienda;
import model.NotificacionDTO;
import model.NotificacionService;
import interfaz.NotificacionInterfaz;

public class EncomiendaController {

    private EncomiendaDAO encomiendaDAO;
    private NotificacionService notificacionService;
    private NotificacionInterfaz notificacionInterfaz;  // Añadido para registrar como observador

    public EncomiendaController() {
        this.encomiendaDAO = new EncomiendaDAOImpl();
        this.notificacionService = NotificacionService.getInstance();
        this.notificacionInterfaz = new NotificacionInterfaz(); // Inicializar la interfaz

  
    }

    public void registrarEncomienda(String numeroGuia, float peso, String destino) {
        Encomienda encomienda = new Encomienda(numeroGuia, peso, destino);

        // Registrar la interfaz como observadora
        encomienda.agregarObservador(notificacionInterfaz); 

        encomiendaDAO.crear(encomienda);
    }

    public Encomienda obtenerEncomienda(String numeroGuia) {
        return encomiendaDAO.leer(numeroGuia);
    }

    public void despacharEncomienda(String numeroGuia) {
        Encomienda encomienda = encomiendaDAO.leer(numeroGuia);
        if (encomienda != null) {
            encomienda.despachar();  // Esto disparará la notificación a los observadores
            encomiendaDAO.actualizar(encomienda);

            NotificacionDTO notificacion = new NotificacionDTO(
                "Encomienda " + numeroGuia + " despachada",
                "Despacho",
                numeroGuia
            );

            notificacionService.agregarNotificacion(notificacion);
        } else {
            System.out.println("Encomienda no encontrada.");
        }
    }
}
