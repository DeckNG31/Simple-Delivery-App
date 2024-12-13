/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pedido;

import controllers.ItemMenuController;
import controllers.VendedorController;
import helpers.HelpersVista;
import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.Plato;
import isi.deso.tp.usuarios.Vendedor;
import java.util.List;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mariano
 */
public class CargarItemMenuVista extends javax.swing.JFrame {

    /**
     * Creates new form CargarItemMenuVista
     */
    public CargarItemMenuVista() {
        initComponents();
    }

    public CrearPedidoVista ventanaPadre;

    public CargarItemMenuVista(CrearPedidoVista padre) {
        initComponents();

        ventanaPadre = padre;

        fieldCantidad.setText("1");
        cargarVendedores();
    }

    private void cargarVendedores() {
        List<Vendedor> vendedores = VendedorController.getInstance().listarVendedores();
        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String titulos[] = {"ID", "Nombre"};
        Modelotabla.setColumnIdentifiers(titulos);
        vendedores.forEach(v -> {
            Modelotabla.addRow(new Object[]{
                v.getId(),
                v.getNombre(),});
        });

        listaVendedores.setModel(Modelotabla);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        listaVendedores.setRowSorter(sorter);
        // Configurar para permitir solo una fila seleccionada a la vez
        listaVendedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //este es el que llena los productos
    public void llenarTabla(List<ItemMenu> ims) {
        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que todas las celdas sean editables
            }
        };

        String titulos[] = {"ID", "Tipo", "Nombre", "Descripcion", "Precio", "Apto para veganos", "Peso", "Calorias", "Apto para celiacos", "Volumen (ml)", "Graduacion Alcohol"};
        Modelotabla.setColumnIdentifiers(titulos);

       
        ims.forEach(im -> {

            if (im instanceof Plato) {
                Plato plato = (Plato) im; // Hacemos un casting a Plato
                Modelotabla.addRow(new Object[]{
                    plato.getId(),
                    "Plato",
                    plato.getNombre(),
                    plato.getDescripcion(),
                    plato.getPrecio(),
                    plato.aptoVegano() ? "Si" : "No",
                    plato.getPeso(),
                    plato.getCalorias(),
                    plato.isAptoCeliaco() ? "Si" : "No",
                    "--",
                    "--"
                });
            }

            if (im instanceof Bebida) {
                Bebida bebida = (Bebida) im; // Hacemos un casting a Plato
                Modelotabla.addRow(new Object[]{
                    bebida.getId(),
                    "Bebida",
                    bebida.getNombre(),
                    bebida.getDescripcion(),
                    bebida.getPrecio(),
                    bebida.aptoVegano() ? "Si" : "No",
                    bebida.getPeso(),
                    "--",
                    "--",
                    bebida.getVolumen(),
                    bebida.getGraduacionAlcohol()
                });
            }
        });
        // Establecer el modelo en la tabla
        listaProductos.setModel(Modelotabla);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        listaProductos.setRowSorter(sorter);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaVendedores = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaProductos = new javax.swing.JTable();
        fieldCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cantidad");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listaVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        listaVendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaVendedoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaVendedores);

        listaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo", "Nombre", "Descripcion", "Precio", "Apto para veganos", "Peso", "Calorias", "Apto para celiacos", "Volumen (ml)", "Graduacion alcohol"
            }
        ));
        jScrollPane1.setViewportView(listaProductos);

        fieldCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCantidadActionPerformed(evt);
            }
        });

        jLabel2.setText("Vendedores");

        jLabel3.setText("Productos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(fieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCantidadActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_fieldCantidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (listaProductos.getSelectedRowCount() > 0) {
            try {
                String cantidad = fieldCantidad.getText();

                if (cantidad.isEmpty() || !cantidad.matches("\\d+")) {
                    HelpersVista.mostrarMensaje("Ingresa una cantidad válida", "Error", "Alerta");
                    return;
                }

                int[] filasSeleccionadas = listaProductos.getSelectedRows();

                for (int fila : filasSeleccionadas) {

                    Object idObj = listaProductos.getValueAt(fila, 0);
                    String itemMenuId = idObj.toString();

                    Object nombreObj = listaProductos.getValueAt(fila, 2);
                    String itemMenuNombre = nombreObj.toString();

                    Object precioObj = listaProductos.getValueAt(fila, 4);
                    String itemMenuPrecio = precioObj.toString();

                    Object[] row = {itemMenuId, itemMenuNombre, itemMenuPrecio, cantidad};

                    ventanaPadre.addRowItemMenu(row);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            HelpersVista.mostrarMensaje("Selecciona al menos una fila", "Error", "Alerta");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void listaVendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaVendedoresMouseClicked
        // TODO add your handling code here:
        ventanaPadre.limpiarCarrito();
        int filaSeleccionada = listaVendedores.getSelectedRow();

        if (filaSeleccionada != -1) {
            int id = (int) listaVendedores.getValueAt(filaSeleccionada, 0);
            ventanaPadre.setVendedorId(id);
            List<ItemMenu> im = ItemMenuController.getInstance().listarItemMenusPorVendedor(id);
            llenarTabla(im);
        }

    }//GEN-LAST:event_listaVendedoresMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CargarItemMenuVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CargarItemMenuVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CargarItemMenuVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CargarItemMenuVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CargarItemMenuVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fieldCantidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listaProductos;
    private javax.swing.JTable listaVendedores;
    // End of variables declaration//GEN-END:variables
}