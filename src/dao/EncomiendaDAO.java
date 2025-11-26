package dao;

import model.Encomienda;

public interface EncomiendaDAO {
    void crear(Encomienda encomienda);
    Encomienda leer(String numeroGuia);
    void actualizar(Encomienda encomienda);
    void eliminar(String numeroGuia);
}
