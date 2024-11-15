/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBCs;

/**
 *
 * @author mariano
 */
import DAOs.VendedorDAO;
import isi.deso.tp.usuarios.Coordenada;
import isi.deso.tp.usuarios.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorJDBC implements VendedorDAO {

    // Instancia única para Singleton
    private static VendedorJDBC instance;

    // Constructor privado para evitar instancias externas
    private VendedorJDBC() {}

    // Método para obtener la instancia única (Singleton)
    public static VendedorJDBC getInstance() {
        if (instance == null) {
            synchronized (VendedorJDBC.class) {
                if (instance == null) {
                    instance = new VendedorJDBC();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Vendedor> listarVendedores() {
        List<Vendedor> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM vendedor";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                int idCoordenada = rs.getInt("id_Coordenada");

                // Buscar las coordenadas usando el id_Coordenada
                Coordenada coord = buscarCoordenadaPorId(idCoordenada);

                Vendedor vendedor = new Vendedor(id, nombre, direccion, coord);
                lista.add(vendedor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void crearVendedor(Vendedor vendedor) {
        Connection conn = DBConnector.getInstance();

        // Primero insertamos las coordenadas
        Coordenada coord = vendedor.getCoord();
        String queryCoordenada = "INSERT INTO coordenada (lat, lng) VALUES (?, ?)";
        try (PreparedStatement stmtCoord = conn.prepareStatement(queryCoordenada, Statement.RETURN_GENERATED_KEYS)) {
            stmtCoord.setDouble(1, coord.getLat());
            stmtCoord.setDouble(2, coord.getLng());
            stmtCoord.executeUpdate();

            // Obtener el id generado para la coordenada
            ResultSet generatedKeys = stmtCoord.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCoordenada = generatedKeys.getInt(1);

                // Ahora insertamos el vendedor, usando el idCoordenada
                String queryVendedor = "INSERT INTO vendedor (nombre, direccion, id_Coordenada) VALUES (?, ?, ?)";
                try (PreparedStatement stmtVendedor = conn.prepareStatement(queryVendedor)) {
                    stmtVendedor.setString(1, vendedor.getNombre());
                    stmtVendedor.setString(2, vendedor.getDireccion());
                    stmtVendedor.setInt(3, idCoordenada);
                    stmtVendedor.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editarVendedor(Vendedor vendedor) {
        Connection conn = DBConnector.getInstance();



        // Editar los datos del vendedor
        String query = "UPDATE vendedor SET nombre = ?, direccion = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vendedor.getNombre());
            stmt.setString(2, vendedor.getDireccion());
            stmt.setInt(3, vendedor.getId());
            stmt.executeUpdate();

            // Editar las coordenadas
            CoordenadaJDBC.getInstance().editarCoordenada(vendedor.getCoord());

        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarVendedor(int id) {
        Connection conn = DBConnector.getInstance();

        // Obtener id_Coordenada antes de eliminar
        String querySelect = "SELECT id_Coordenada FROM vendedor WHERE id = ?";
        try (PreparedStatement stmtSelect = conn.prepareStatement(querySelect)) {
            stmtSelect.setInt(1, id);
            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                int idCoordenada = rs.getInt("id_Coordenada");

                // Eliminar el vendedor
                String queryDeleteVendedor = "DELETE FROM vendedor WHERE id = ?";
                try (PreparedStatement stmtDeleteVendedor = conn.prepareStatement(queryDeleteVendedor)) {
                    stmtDeleteVendedor.setInt(1, id);
                    stmtDeleteVendedor.executeUpdate();
                }
                /*
                // Eliminar las coordenadas asociadas
                CoordenadaJDBC.getInstance().eliminarCoordenada(idCoordenada);
                 */
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Vendedor buscarVendedorPorId(int id) {
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM vendedor WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                int idCoordenada = rs.getInt("id_Coordenada");

                // Buscar las coordenadas usando el id_Coordenada
                Coordenada coord = buscarCoordenadaPorId(idCoordenada);

                return new Vendedor(id, nombre, direccion, coord);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
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
}

