package JDBCs;

import DAOs.CoordenadaDAO;
import isi.deso.tp.usuarios.Coordenada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoordenadaJDBC implements CoordenadaDAO {

    // Instancia estática de la clase ClienteJDBC (Singleton)
    private static CoordenadaJDBC instance;

    // Constructor privado para evitar la creación de instancias externas
    private CoordenadaJDBC() {
    }

    // Método estático para obtener la instancia del Singleton
    public static CoordenadaJDBC getInstance() {
        if (instance == null) {
            synchronized (CoordenadaJDBC.class) {
                if (instance == null) {
                    instance = new CoordenadaJDBC();
                }
            }
        }
        return instance;
    }

    @Override
    public void crearCoordenada(Coordenada p) {
        Connection conn = DBConnector.getInstance();
        String query = "INSERT INTO coordenada (lat, lng) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, p.getLat());
            stmt.setDouble(2, p.getLng());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoordenadaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editarCoordenada(Coordenada p) {
        Connection conn = DBConnector.getInstance();
        String query = "UPDATE coordenada SET lat = ?, lng = ? WHERE idCoordenada = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, p.getLat());
            stmt.setDouble(2, p.getLng());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoordenadaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Coordenada buscarCoordenadaPorId(int id) {
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM coordenada WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double lat = rs.getDouble("lat");
                double lng = rs.getDouble("lng");
                return new Coordenada(lat, lng); // Ajusta esto si tienes un constructor con el id
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordenadaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
