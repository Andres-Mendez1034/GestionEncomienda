package dao;

import model.Remitente;

public interface RemitenteDAO {

    // Crea un nuevo remitente en la base de datos
    void crear(Remitente remitente);

    // Lee un remitente de la base de datos por su número de teléfono
    Remitente leer(String telefono);

    // Actualiza los datos de un remitente en la base de datos
    void actualizar(Remitente remitente);

    // Elimina un remitente de la base de datos por su número de teléfono
    void eliminar(String telefono);
}
