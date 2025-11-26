package dao;

import model.Encomienda;

public interface EncomiendaDAO {

    // Crea una nueva encomienda en la base de datos
    void crear(Encomienda encomienda);

    // Lee una encomienda de la base de datos por su número de guía
    Encomienda leer(String numeroGuia);

    // Actualiza los datos de una encomienda en la base de datos
    void actualizar(Encomienda encomienda);

    // Elimina una encomienda de la base de datos por su número de guía
    void eliminar(String numeroGuia);
}
