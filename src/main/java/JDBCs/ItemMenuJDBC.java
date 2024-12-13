package JDBCs;

import DAOs.ItemMenuDAO;
import JDBCs.DBConnector;
import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.Plato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemMenuJDBC implements ItemMenuDAO {

    private static final Logger logger = Logger.getLogger(ItemMenuJDBC.class.getName());

    // Instancia estática de la clase ClienteJDBC (Singleton)
    private static ItemMenuJDBC instance;

    // Constructor privado para evitar la creación de instancias externas
    private ItemMenuJDBC() {
    }

    // Método estático para obtener la instancia del Singleton
    public static ItemMenuJDBC getInstance() {
        if (instance == null) {
            synchronized (ItemMenuJDBC.class) {
                if (instance == null) {
                    instance = new ItemMenuJDBC();
                }
            }
        }
        return instance;
    }

    @Override
    public List<ItemMenu> listarItemMenus() {
        List<ItemMenu> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM item_menu";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                Double peso = rs.getDouble("peso");
                int vendedorId = rs.getInt("vendedorId");
                Boolean aptoVegano = rs.getBoolean("apto_Vegano");

                // Datos específicos de comida
                Double calorias = rs.getDouble("calorias"); // Obtener el valor como double
                Boolean aptoCeliaco;

                if (rs.wasNull()) {
                    calorias = null; // Si el valor era NULL en la base de datos, asignamos null
                    aptoCeliaco = null;
                } else {

                    aptoCeliaco = rs.getBoolean("apto_Celiaco");
                }

                // Datos específicos de bebida
                Double graduacionAlcohol = rs.getDouble("graduacion_Alcohol");
                Double volumen;
                if (rs.wasNull()) {
                    graduacionAlcohol = null;
                    volumen = null;
                } else {
                    volumen = rs.getDouble("volumen");
                }

                ItemMenu item = null;

                if (calorias != null && aptoCeliaco != null) {
                    // Crear un objeto Plato si tiene valores en los campos de comida
                    item = new Plato(id, nombre, descripcion, precio, aptoVegano, peso, calorias, aptoCeliaco, vendedorId);
                } else if (graduacionAlcohol != null && volumen != null) {
                    // Crear un objeto Bebida si tiene valores en los campos de bebida
                    item = new Bebida(id, nombre, descripcion, precio, aptoVegano, peso, volumen, graduacionAlcohol, vendedorId);
                }

                lista.add(item);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al listar items de menú", ex);
        }
        return lista;
    }

    @Override
    public void crearItemMenu(ItemMenu im) {
        String query;
        if (im instanceof Plato) {
            query = "INSERT INTO item_menu (nombre, descripcion, precio, vendedorId, apto_Vegano,peso, calorias, apto_Celiaco) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        } else if (im instanceof Bebida) {
            query = "INSERT INTO item_menu (nombre, descripcion, precio, vendedorId, apto_Vegano,peso, graduacion_Alcohol, tamanio) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        } else {
            return;
        }
        Connection conn = DBConnector.getInstance();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, im.getNombre());
            stmt.setString(2, im.getDescripcion());
            stmt.setDouble(3, im.getPrecio());
            stmt.setInt(4, im.getVendedorId());  // Usamos getVendedorId()
            stmt.setBoolean(5, im.isAptoVegano());
            stmt.setDouble(6, im.getPeso());
            if (im instanceof Plato) {
                Plato plato = (Plato) im;
                stmt.setDouble(7, plato.getCalorias());
                stmt.setBoolean(8, plato.isAptoCeliaco());
            } else if (im instanceof Bebida) {
                Bebida bebida = (Bebida) im;
                stmt.setDouble(7, bebida.getGraduacionAlcohol());
                stmt.setString(8, bebida.getVolumen() != null ? bebida.getVolumen() + "ml" : null);  // Convertimos el volumen a String
            }

            stmt.executeUpdate();

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al crear item de menú", ex);
        }
    }

    @Override
    public void editarItemMenu(ItemMenu im) {
        String query;
        if (im instanceof Plato) {
            query = "UPDATE item_menu SET nombre = ?, descripcion = ?, precio = ?, vendedorId = ?, apto_Vegano = ?, peso = ?, calorias = ?, apto_Celiaco = ? WHERE id = ?";
        } else if (im instanceof Bebida) {
            query = "UPDATE item_menu SET nombre = ?, descripcion = ?, precio = ?, vendedorId = ?, apto_Vegano = ?, peso = ?, graduacion_Alcohol = ?, tamanio = ? WHERE id = ?";
        } else {
            return;
        }
        Connection conn = DBConnector.getInstance();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, im.getNombre());
            stmt.setString(2, im.getDescripcion());
            stmt.setDouble(3, im.getPrecio());
            stmt.setInt(4, im.getVendedorId());  // Usamos getVendedorId()
            stmt.setBoolean(5, im.isAptoVegano());
            stmt.setDouble(6, im.getPeso());

            if (im instanceof Plato) {
                Plato plato = (Plato) im;
                stmt.setDouble(7, plato.getCalorias());
                stmt.setBoolean(8, plato.isAptoCeliaco());
            } else if (im instanceof Bebida) {
                Bebida bebida = (Bebida) im;
                stmt.setDouble(7, bebida.getGraduacionAlcohol());
                stmt.setString(8, bebida.getVolumen() != null ? bebida.getVolumen() + "ml" : null);  // Convertimos el volumen a String
            }

            stmt.setInt(9, im.getId());  // Aseguramos que estamos actualizando el item correcto por ID
            stmt.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al editar item de menú", ex);
        }
    }

    @Override
    public void eliminarItemMenu(int id) {
        String query = "DELETE FROM item_menu WHERE id = ?";
        Connection conn = DBConnector.getInstance();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al eliminar item de menú", ex);
        }
    }

    @Override
    public ItemMenu buscarItemMenuPorId(int id) {
        String query = "SELECT * FROM item_menu WHERE id = ?";
        Connection conn = DBConnector.getInstance();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                int vendedorId = rs.getInt("vendedorId");  // Usamos el vendedorId directamente desde la base de datos
                Boolean aptoVegano = rs.getBoolean("apto_Vegano");
                Double peso = rs.getDouble("peso");

                Double calorias = rs.getDouble("calorias");
                Boolean aptoCeliaco = rs.wasNull() ? null : rs.getBoolean("apto_Celiaco");

                Double graduacionAlcohol = rs.getDouble("graduacion_Alcohol");
                Double volumen;
                if (rs.wasNull()) {
                    graduacionAlcohol = null;
                    volumen = null;
                } else {
                    volumen = rs.getDouble("volumen");
                }
               

                if (calorias != null && aptoCeliaco != null) {
                    return new Plato(id, nombre, descripcion, precio, aptoVegano, peso, calorias, aptoCeliaco, vendedorId);
                } else if (graduacionAlcohol != null && volumen != null) {
                    return new Bebida(id, nombre, descripcion, precio, aptoVegano, peso, volumen, graduacionAlcohol, vendedorId);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al buscar item de menú por ID", ex);
        }
        return null;
    }

    @Override
    public List<ItemMenu> buscarItemMenuPorIds(List<Integer> ids) {
        List<ItemMenu> itemsMenus = new ArrayList<ItemMenu>();
        String placeholders = String.join(",", ids.stream().map(id -> "?").toArray(String[]::new));

        String query = "SELECT * FROM item_menu WHERE id IN (" + placeholders + ")";

        Connection conn = DBConnector.getInstance();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < ids.size(); i++) {
                stmt.setInt(i + 1, ids.get(i));
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                int vendedorId = rs.getInt("vendedorId");  // Usamos el vendedorId directamente desde la base de datos
                Boolean aptoVegano = rs.getBoolean("apto_Vegano");
                Double peso = rs.getDouble("peso");

                Double calorias = rs.getDouble("calorias");
                Boolean aptoCeliaco = rs.wasNull() ? null : rs.getBoolean("apto_Celiaco");

                Double graduacionAlcohol = rs.getDouble("graduacion_Alcohol");
                Double volumen;
                if (rs.wasNull()) {
                    graduacionAlcohol = null;
                    volumen = null;
                } else {
                    volumen = rs.getDouble("volumen");
                }

                if (calorias != null && aptoCeliaco != null) {
                    itemsMenus.add(new Plato(id, nombre, descripcion, precio, aptoVegano, peso, calorias, aptoCeliaco, vendedorId));
                } else if (graduacionAlcohol != null && volumen != null) {
                    itemsMenus.add(new Bebida(id, nombre, descripcion, precio, aptoVegano, peso, volumen, graduacionAlcohol, vendedorId));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al buscar item de menú por ID", ex);
        }
        return itemsMenus;
    }

    @Override
    public List<ItemMenu> listarItemMenusPorVendedor(int vendedorId) {
        List<ItemMenu> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM item_menu WHERE vendedorId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vendedorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                Double peso = rs.getDouble("peso");

                Boolean aptoVegano = rs.getBoolean("apto_Vegano");

                // Datos específicos de comida
                Double calorias = rs.getDouble("calorias"); // Obtener el valor como double
                Boolean aptoCeliaco;

                if (rs.wasNull()) {
                    calorias = null; // Si el valor era NULL en la base de datos, asignamos null
                    aptoCeliaco = null;
                } else {

                    aptoCeliaco = rs.getBoolean("apto_Celiaco");
                }

                // Datos específicos de bebida
                Double graduacionAlcohol = rs.getDouble("graduacion_Alcohol");
                Double volumen;
                if (rs.wasNull()) {
                    graduacionAlcohol = null;
                    volumen = null;
                } else {
                    volumen = rs.getDouble("volumen");
                }

                ItemMenu item = null;

                if (calorias != null && aptoCeliaco != null) {
                    // Crear un objeto Plato si tiene valores en los campos de comida
                    item = new Plato(id, nombre, descripcion, precio, aptoVegano, peso, calorias, aptoCeliaco, vendedorId);
                } else if (graduacionAlcohol != null && volumen != null) {
                    // Crear un objeto Bebida si tiene valores en los campos de bebida
                    item = new Bebida(id, nombre, descripcion, precio, aptoVegano, peso, volumen, graduacionAlcohol, vendedorId);
                }

                lista.add(item);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al listar items de menú", ex);
        }
        return lista;
    }

}
