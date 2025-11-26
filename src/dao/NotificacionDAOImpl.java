package dao;

import model.NotificacionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacionDAOImpl implements NotificacionDAO {

    @Override
    public List<NotificacionDTO> obtenerPorEncomienda(String numeroGuia) {
        List<NotificacionDTO> lista = new ArrayList<>();
        String sql =
            "SELECT n.id, n.mensaje, n.tipo, n.encomienda_id, e.numero_guia, n.fecha " +
            "FROM notificaciones n " +
            "JOIN encomiendas e ON n.encomienda_id = e.id " +
            "WHERE e.numero_guia = ? " +
            "ORDER BY n.fecha DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {  // Se cambia aquí el tipo del ResultSet

            ps.setString(1, numeroGuia); // Usamos String para el número de guía
            ResultSet rs = ps.executeQuery();

            // Verificar si no hay resultados
            if (!rs.next()) {
                System.out.println("[LOG] No se encontraron notificaciones para el número de guía: " + numeroGuia);
            } else {
                // Reposicionar el cursor al principio, ya que `rs.next()` avanzó una vez
                rs.beforeFirst();
                while (rs.next()) {
                    lista.add(new NotificacionDTO(
                        rs.getInt("id"),
                        rs.getString("mensaje"),
                        rs.getString("tipo"),
                        rs.getInt("encomienda_id"),
                        rs.getString("numero_guia"),
                        rs.getString("fecha")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] obtenerPorEncomienda: " + e.getMessage());
        }

        if (lista.isEmpty()) {
            System.out.println("[LOG] Lista vacía para el número de guía: " + numeroGuia);
        }

        return lista;
    }

    @Override
    public List<NotificacionDTO> obtenerTodas() {
        List<NotificacionDTO> lista = new ArrayList<>();
        String sql =
            "SELECT n.id, n.mensaje, n.tipo, n.encomienda_id, e.numero_guia, n.fecha " +
            "FROM notificaciones n " +
            "JOIN encomiendas e ON n.encomienda_id = e.id " +
            "ORDER BY n.fecha DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new NotificacionDTO(
                    rs.getInt("id"),
                    rs.getString("mensaje"),
                    rs.getString("tipo"),
                    rs.getInt("encomienda_id"),
                    rs.getString("numero_guia"),
                    rs.getString("fecha")
                ));
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] obtenerTodas: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public void crear(NotificacionDTO n) {
        String sql =
            "INSERT INTO notificaciones (mensaje, tipo, encomienda_id, fecha) VALUES (?, ?, ?, NOW())";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, n.getMensaje());
            ps.setString(2, n.getTipo());
            ps.setInt(3, n.getEncomiendaId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("[ERROR] crear: " + e.getMessage());
        }
    }

    @Override
    public List<NotificacionDTO> obtenerPorGuia(String numeroGuia) {
        // Reutilizamos el método obtenerPorEncomienda para simplificar
        return obtenerPorEncomienda(numeroGuia); 
    }
}
