package JDBCs;

import DAOs.PedidoDAO;
import static com.mysql.cj.conf.PropertyKey.logger;
import isi.deso.tp.EstadoPedido;
import isi.deso.tp.Pedido;
import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.RegistroDetalle;
import isi.deso.tp.menu.ItemPedido;
import isi.deso.tp.menu.Plato;
import isi.deso.tp.metodos.pago.Efectivo;
import isi.deso.tp.metodos.pago.MercadoPago;
import isi.deso.tp.metodos.pago.MetodoPago;
import isi.deso.tp.metodos.pago.Transferencia;
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
    private PedidoJDBC() {
    }

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

    public void actualizarEstadoPedido(Integer pedidoId, String estado) {
        Connection conn = DBConnector.getInstance();
        String query = "UPDATE pedido SET estado = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, estado);
            stmt.setInt(2, pedidoId);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Integer crearPedido(Pedido p) {
        Connection conn = DBConnector.getInstance();
        String query = "INSERT INTO pedido (estado,total, fecha, clienteId, metodoPago,alias,cbu,cuit,vendedorId) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getEstado().toString());
            stmt.setDouble(2, p.getTotal());
            stmt.setDate(3, Date.valueOf(p.getFecha()));
            stmt.setInt(4, p.getClienteId());
            MetodoPago metodoPago = p.getMetodoPago();
            stmt.setString(5, metodoPago.getString());

            if (metodoPago instanceof Efectivo) {
                stmt.setString(6, null);
                stmt.setString(7, null);
                stmt.setString(8, null);
            }
            if (metodoPago instanceof MercadoPago) {
                stmt.setString(6, ((MercadoPago) metodoPago).getAlias());
                stmt.setString(7, null);
                stmt.setString(8, null);
            }
            if (metodoPago instanceof Transferencia) {
                stmt.setString(6, null);
                stmt.setString(7, ((Transferencia) metodoPago).getCbu());
                stmt.setString(8, ((Transferencia) metodoPago).getCuit());
            }
            stmt.setInt(9, p.getVendedorId());
            stmt.executeUpdate();

            // Obtener el id generado para el pedido
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    @Override
    public void cargarDetalle(Integer idPedido, List<RegistroDetalle> detalle) {
        Connection conn = DBConnector.getInstance();
        String query = "INSERT INTO pedido_detalle (itemMenuId,pedidoId,cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (RegistroDetalle registro : detalle) {
                stmt.setInt(1, registro.getItemMenuId());
                stmt.setInt(2, idPedido);
                stmt.setInt(3, registro.getCantidad());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Pedido> obtenerPedidosDeCliente(Integer clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT *"
                + " FROM pedido p "
                + " WHERE p.clienteId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Pedido
                int pedidoId = rs.getInt("id");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                double total = rs.getDouble("total");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int clienteID = rs.getInt("clienteId");
                MetodoPago metodoPago = getMetodoPago(rs.getString("metodoPago"));
                int vendedorId = rs.getInt("vendedorId");
                Pedido pedido = new Pedido(pedidoId, estado, total, fecha, clienteID, metodoPago, vendedorId);

                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidos;
    }

    @Override
    public List<RegistroDetalle> obtenerDetalleDePedido(Integer pedidoId) {
        List<RegistroDetalle> detalle = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT *"
                + " FROM pedido_detalle pd "
                + " WHERE pd.pedidoId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int itemMenuId = rs.getInt("itemMenuId");

                int cantidad = rs.getInt("cantidad");

                RegistroDetalle rg = new RegistroDetalle(itemMenuId, cantidad);
                detalle.add(rg);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return detalle;
    }

   

    private MetodoPago getMetodoPago(String metodoPagoString) {
        switch (metodoPagoString) {
            case "efectivo":
                return new Efectivo();
            case "mercadopago":
                return new MercadoPago("defaultAlias");  // Aquí podrías obtener el alias real
            case "transferencia":
                return new Transferencia();  // Aquí podrías pasar los valores reales
            default:
                throw new IllegalArgumentException("Metodo de pago desconocido: " + metodoPagoString);
        }
    }

    //TODO hacer editar pedido
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

    @Override
    public List<Pedido> obtenerPedidosDeVendedor(Integer vendedorId) {
        List<Pedido> pedidos = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT *"
                + " FROM pedido p "
                + " WHERE p.vendedorId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vendedorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Pedido
                int pedidoId = rs.getInt("id");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                double total = rs.getDouble("total");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int clienteID = rs.getInt("clienteId");
                MetodoPago metodoPago = getMetodoPago(rs.getString("metodoPago"));

                Pedido pedido = new Pedido(pedidoId, estado, total, fecha, clienteID, metodoPago, vendedorId);

                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidos;
    }

    @Override
    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT *"
                + " FROM pedido p ";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Pedido
                int pedidoId = rs.getInt("id");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                double total = rs.getDouble("total");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int clienteID = rs.getInt("clienteId");
                MetodoPago metodoPago = getMetodoPago(rs.getString("metodoPago"));
                int vendedorId = rs.getInt("vendedorId");
                Pedido pedido = new Pedido(pedidoId, estado, total, fecha, clienteID, metodoPago, vendedorId);

                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidos;

    }

}
