package JDBCs;

import DAOs.PedidoDAO;
import isi.deso.tp.Pedido;
import isi.deso.tp.menu.ItemPedido;
import isi.deso.tp.metodos.pago.MetodoPago;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoJDBC implements PedidoDAO {

    // Instancia estática de la clase PedidoJDBC (Singleton)
    private static PedidoJDBC instance;

    // Constructor privado para evitar la creación de instancias externas
    private PedidoJDBC() {}

    // Método estático para obtener la instancia del Singleton
    public static PedidoJDBC getInstance() {
        if (instance == null) {
            synchronized (PedidoJDBC.class) {
                if (instance == null) {
                    instance = new PedidoJDBC();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM pedido";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                double total = rs.getDouble("total");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int clienteId = rs.getInt("clienteId");
                int metodoPagoId = rs.getInt("metodoPagoId");

                // Aquí deberías obtener el método de pago y el estado del pedido si los usas
                MetodoPago metodoPago = buscarMetodoPagoPorId(metodoPagoId);

                Pedido pedido = new Pedido(id, total, fecha, clienteId, metodoPago);
                lista.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void crearPedido(Pedido p) {
        Connection conn = DBConnector.getInstance();
        String query = "INSERT INTO pedido (total, fecha, clienteId, metodoPago) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, p.getTotal());
            stmt.setDate(2, Date.valueOf(p.getFecha()));
            stmt.setInt(3, p.getClienteId());
            stmt.setString(4, p.getMetodoPago().getString());
            stmt.executeUpdate();

            // Obtener el id generado para el pedido
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                p.setId(id); // Asignar el id generado al objeto Pedido
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editarPedido(Pedido p) {
        Connection conn = DBConnector.getInstance();
        String query = "UPDATE pedido SET total = ?, fecha = ?, clienteId = ?, metodoPagoId = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, p.getTotal());
            stmt.setDate(2, Date.valueOf(p.getFecha()));
            stmt.setInt(3, p.getClienteId());
            stmt.setString(4, p.getMetodoPago().getString());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarPedido(int id) {
        Connection conn = DBConnector.getInstance();
        String query = "DELETE FROM pedido WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Pedido buscarPedidoPorId(int id) {
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM pedido WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("total");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int clienteId = rs.getInt("clienteId");
                int metodoPagoId = rs.getInt("metodoPagoId");

                MetodoPago metodoPago = buscarMetodoPagoPorId(metodoPagoId);

                return new Pedido(id, total, fecha, clienteId, metodoPago);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ItemPedido> ordernarPorPrecio(boolean asc) {
        List<ItemPedido> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM item_pedido ORDER BY precio " + (asc ? "ASC" : "DESC");
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // Aquí deberías crear los objetos ItemPedido con la data obtenida
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<ItemPedido> buscarPorRangoDePrecios(double precioMin, double precioMax) {
        List<ItemPedido> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM item_pedido WHERE precio BETWEEN ? AND ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, precioMin);
            stmt.setDouble(2, precioMax);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Crear los objetos ItemPedido con la data obtenida
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<ItemPedido> buscarPorRestaurante(String restaurante) {
        List<ItemPedido> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM item_pedido WHERE restaurante = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, restaurante);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Crear los objetos ItemPedido con la data obtenida
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    // Método para obtener el método de pago por id
    private MetodoPago buscarMetodoPagoPorId(int metodoPagoId) {
        // Implementar lógica para buscar el metodo de pago en la base de datos usando el metodoPagoId
        return null;
    }
}
