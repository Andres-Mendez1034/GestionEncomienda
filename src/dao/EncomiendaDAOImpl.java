package dao;

import model.Encomienda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EncomiendaDAOImpl implements EncomiendaDAO {

    @Override
    public void crear(Encomienda encomienda) {
        String sql = "INSERT INTO encomiendas (numero_guia, peso, destino, estado, remitente_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, encomienda.getNumeroGuia());
            stmt.setDouble(2, encomienda.getPeso());
            stmt.setString(3, encomienda.getDestino());
            stmt.setString(4, encomienda.getEstado());

            // --- Manejo de remitenteId nulo ---
            if (encomienda.getRemitenteId() > 0) {
                stmt.setInt(5, encomienda.getRemitenteId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                // Obtener ID generado automáticamente
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        encomienda.setId(rs.getInt(1));
                    }
                }
                System.out.println("[LOG] Encomienda creada: " + encomienda.getNumeroGuia() + " (ID=" + encomienda.getId() + ")");
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo crear la encomienda: " + e.getMessage());
        }
    }

    @Override
    public Encomienda leer(String numeroGuia) {
        String sql = "SELECT * FROM encomiendas WHERE numero_guia = ?";
        Encomienda encomienda = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroGuia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    encomienda = new Encomienda();
                    encomienda.setId(rs.getInt("id"));
                    encomienda.setNumeroGuia(rs.getString("numero_guia"));
                    encomienda.setPeso((float) rs.getDouble("peso"));
                    encomienda.setDestino(rs.getString("destino"));
                    encomienda.setEstado(rs.getString("estado"));

                    int remitenteId = rs.getInt("remitente_id");
                    if (rs.wasNull()) {
                        encomienda.setRemitenteId(0); // sin remitente
                    } else {
                        encomienda.setRemitenteId(remitenteId);
                    }

                    System.out.println("[LOG] Encomienda encontrada: " + numeroGuia);
                } else {
                    System.out.println("[LOG] Encomienda no encontrada: " + numeroGuia);
                }
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo leer la encomienda: " + e.getMessage());
        }

        return encomienda;
    }

    @Override
    public void actualizar(Encomienda encomienda) {
        String sql = "UPDATE encomiendas SET peso = ?, destino = ?, estado = ?, remitente_id = ? WHERE numero_guia = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, encomienda.getPeso());
            stmt.setString(2, encomienda.getDestino());
            stmt.setString(3, encomienda.getEstado());

            // Manejo de remitenteId nulo
            if (encomienda.getRemitenteId() > 0) {
                stmt.setInt(4, encomienda.getRemitenteId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            stmt.setString(5, encomienda.getNumeroGuia());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("[LOG] Encomienda actualizada: " + encomienda.getNumeroGuia());
            } else {
                System.out.println("[LOG] Encomienda no encontrada para actualizar: " + encomienda.getNumeroGuia());
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo actualizar la encomienda: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String numeroGuia) {
        String sql = "DELETE FROM encomiendas WHERE numero_guia = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroGuia);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("[LOG] Encomienda eliminada: " + numeroGuia);
            } else {
                System.out.println("[LOG] Encomienda no encontrada para eliminar: " + numeroGuia);
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo eliminar la encomienda: " + e.getMessage());
        }
    }
}
