package dao;

import model.Remitente;

public interface RemitenteDAO {
    void crear(Remitente remitente);
    Remitente leer(String telefono);
    void actualizar(Remitente remitente);
    void eliminar(String telefono);
}
