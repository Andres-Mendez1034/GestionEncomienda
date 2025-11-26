package dao;

import model.Remitente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemitenteDAOImpl implements RemitenteDAO {

@Override
public void crear(Remitente remitente) {
    String sql = "INSERT INTO remitentes (nombre, direccion, movil) VALUES (?, ?, ?)";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, remitente.getNombre());
        stmt.setString(2, remitente.getDireccion());
        stmt.setString(3, remitente.getTelefono());

        int filas = stmt.executeUpdate();

        if (filas > 0) {
            // Obtener el ID generado automáticamente
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    remitente.setId(rs.getInt(1));  // asigna el ID al objeto
                }
            }
            System.out.println("[LOG] Remitente creado: " + remitente.getNombre() + " (ID=" + remitente.getId() + ")");
        }

    } catch (SQLException e) {
        System.out.println("[ERROR] No se pudo crear el remitente: " + e.getMessage());
    }
}


    @Override
    public Remitente leer(String telefono) {
        String sql = "SELECT * FROM remitentes WHERE movil = ?";
        Remitente remitente = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefono);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    remitente = new Remitente();
                    remitente.setId(rs.getInt("id"));
                    remitente.setNombre(rs.getString("nombre"));
                    remitente.setDireccion(rs.getString("direccion"));
                    remitente.setTelefono(rs.getString("movil"));

                    System.out.println("[LOG] Remitente encontrado: " + telefono);
                } else {
                    System.out.println("[LOG] Remitente no encontrado: " + telefono);
                }
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo leer el remitente: " + e.getMessage());
        }

        return remitente;
    }

    @Override
    public void actualizar(Remitente remitente) {
        String sql = "UPDATE remitentes SET nombre = ?, direccion = ? WHERE movil = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, remitente.getNombre());
            stmt.setString(2, remitente.getDireccion());
            stmt.setString(3, remitente.getTelefono());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("[LOG] Remitente actualizado: " + remitente.getNombre());
            } else {
                System.out.println("[LOG] Remitente no encontrado para actualizar: " + remitente.getTelefono());
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo actualizar el remitente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String telefono) {
        String sql = "DELETE FROM remitentes WHERE movil = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefono);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("[LOG] Remitente eliminado: " + telefono);
            } else {
                System.out.println("[LOG] Remitente no encontrado para eliminar: " + telefono);
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] No se pudo eliminar el remitente: " + e.getMessage());
        }
    }
}
