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
    private NotificacionInterfaz notificacionInterfaz;  // Interfaz para notificaciones

    public EncomiendaController() {
        this.encomiendaDAO = new EncomiendaDAOImpl(); // Inicializa DAO para Encomienda
        this.notificacionService = NotificacionService.getInstance(); // Singleton de servicio de notificaciones
        this.notificacionInterfaz = new NotificacionInterfaz(); // Inicializa la interfaz como observadora
    }

    public void registrarEncomienda(String numeroGuia, float peso, String destino) {
        Encomienda encomienda = new Encomienda(numeroGuia, peso, destino);
        encomienda.agregarObservador(notificacionInterfaz);  // Registra la interfaz como observador
        encomiendaDAO.crear(encomienda);  // Guarda la encomienda
    }

    public Encomienda obtenerEncomienda(String numeroGuia) {
        return encomiendaDAO.leer(numeroGuia);  // Recupera la encomienda por número de guía
    }

    public void despacharEncomienda(String numeroGuia) {
        Encomienda encomienda = encomiendaDAO.leer(numeroGuia);
        if (encomienda != null) {
            encomienda.despachar();  // Despacha la encomienda y notifica a los observadores
            encomiendaDAO.actualizar(encomienda);  // Actualiza el estado de la encomienda

            // Crea una notificación sobre el despacho
            NotificacionDTO notificacion = new NotificacionDTO(
                "Encomienda " + numeroGuia + " despachada",
                "Despacho",
                numeroGuia
            );
            notificacionService.agregarNotificacion(notificacion);  // Agrega la notificación
        } else {
            System.out.println("Encomienda no encontrada.");  // Maneja caso en que no se encuentra la encomienda
        }
    }
}
