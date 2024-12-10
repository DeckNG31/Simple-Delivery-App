package JDBCs;

import DAOs.ClienteDAO;
import exceptions.UsuarioNoEncontradoException;
import isi.deso.tp.usuarios.Cliente;
import isi.deso.tp.usuarios.Coordenada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteJDBC implements ClienteDAO {

     // Instancia estática de la clase ClienteJDBC (Singleton)
    private static ClienteJDBC instance;

    // Constructor privado para evitar la creación de instancias externas
    private ClienteJDBC() {}

    // Método estático para obtener la instancia del Singleton
    public static ClienteJDBC getInstance() {
        if (instance == null) {
            synchronized (ClienteJDBC.class) {
                if (instance == null) {
                    instance = new ClienteJDBC();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM cliente";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String cuit = rs.getString("cuit");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                int idCoordenada = rs.getInt("id_Coordenada");

                // Buscar las coordenadas usando el id_Coordenada
                Coordenada coord = buscarCoordenadaPorId(idCoordenada);

                Cliente cliente = new Cliente(id, nombre, cuit, email, direccion, coord);
                lista.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void crearCliente(Cliente p) {
        Connection conn = DBConnector.getInstance();

        // Primero insertamos las coordenadas
        Coordenada coord = p.getCoord();
        String queryCoordenada = "INSERT INTO coordenada (lat, lng) VALUES (?, ?)";
        try (PreparedStatement stmtCoord = conn.prepareStatement(queryCoordenada, Statement.RETURN_GENERATED_KEYS)) {
            stmtCoord.setDouble(1, coord.getLat());
            stmtCoord.setDouble(2, coord.getLng());
            stmtCoord.executeUpdate();

            // Obtener el id generado para la coordenada
            ResultSet generatedKeys = stmtCoord.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCoordenada = generatedKeys.getInt(1);

                // Ahora insertamos el cliente, usando el idCoordenada
                String queryCliente = "INSERT INTO cliente (nombre, cuit, email, direccion, id_Coordenada) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
                    stmtCliente.setString(1, p.getNombre());
                    stmtCliente.setString(2, p.getCuit());
                    stmtCliente.setString(3, p.getEmail());
                    stmtCliente.setString(4, p.getDireccion());
                    stmtCliente.setInt(5, idCoordenada);
                    stmtCliente.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editarCliente(Cliente p) {
        Connection conn = DBConnector.getInstance();
        String query = "UPDATE cliente SET nombre = ?, cuit = ?, email = ?, direccion = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCuit());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getDireccion());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
            
            //Editar coordenadas
            CoordenadaJDBC.getInstance().editarCoordenada(p.getCoord());
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarCliente(int id) {
        Connection conn = DBConnector.getInstance();
        String query = "DELETE FROM cliente WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Cliente buscarClientePorId(int id) {
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM cliente WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String cuit = rs.getString("cuit");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                int idCoordenada = rs.getInt("id_Coordenada");

                // Buscar las coordenadas usando el id_Coordenada
                Coordenada coord = buscarCoordenadaPorId(idCoordenada);

                return new Cliente(id, nombre, cuit, email, direccion, coord);
            }
            else {
            // Lanzar la excepción si no se encuentra el cliente
            throw new UsuarioNoEncontradoException("Cliente con ID " + id + " no encontrado.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Método para obtener las coordenadas por id
    private Coordenada buscarCoordenadaPorId(int idCoordenada) {
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM coordenada WHERE idCoordenada = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCoordenada);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double lat = rs.getDouble("lat");
                double lng = rs.getDouble("lng");
                
                return new Coordenada(idCoordenada,lat, lng);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public ArrayList<Integer> obtenerIds(){
      ArrayList<Integer> listaIds = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT id FROM cliente";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");

                listaIds.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaIds;
    }
}
