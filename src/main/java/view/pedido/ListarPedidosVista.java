/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pedido;

import DTOs.PedidoItemMenuDTO;
import JDBCs.PedidoJDBC;
import controllers.AutenticacionController;
import controllers.ClienteController;
import controllers.PedidoController;
import controllers.VendedorController;
import helpers.HelpersVista;
import isi.deso.tp.EstadoPedido;
import isi.deso.tp.Pedido;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.usuarios.Cliente;
import isi.deso.tp.usuarios.Vendedor;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import view.AdminVista;
import view.ClienteVista;
import view.VendedorVista;

/**
 *
 * @author deck
 */
public class ListarPedidosVista extends javax.swing.JFrame {

    /**
     * Creates new form ListarPedidoVista
     */
    private String tipoUsuario;
    private PedidoItemMenuDTO pimDTO;
    private PedidoController pc;
    private Integer pedidoId;

    public ListarPedidosVista() {
        initComponents();
        pc = PedidoController.getInstance();
        buttonGroup1.add(recibidoRb);
        buttonGroup1.add(aceptadoRb);
        buttonGroup1.add(preparacionRb);
        buttonGroup1.add(envioRb);
        buttonGroup1.add(enviadoRb);
        visibilidadRB(false);

        tipoUsuario = AutenticacionController.getInstance().getTipoUsuario();
        mostrarPedidos();
    }

    private void visibilidadRB(Boolean b) {

        recibidoRb.setVisible(b);
        aceptadoRb.setVisible(b);
        preparacionRb.setVisible(b);
        envioRb.setVisible(b);
        enviadoRb.setVisible(b);

    }

    private void mostrarPedidos() {

        if (tipoUsuario.equals("cliente")) {
            Cliente c = AutenticacionController.getInstance().obtenerClienteAutenticado();
            pimDTO = PedidoController.getInstance().obtenerPedidosDeCliente(c.getId());

            llenarTablaPedidosDeCliente();
        }

        if (tipoUsuario.equals("vendedor")) {
            Vendedor v = AutenticacionController.getInstance().obtenerVendedorAutenticado();
            pimDTO = PedidoController.getInstance().obtenerPedidosDeVendedor(v.getId());

            llenarTablaPedidosDeVendedor();
        }

        if (tipoUsuario.equals("admin")) {

            pimDTO = PedidoController.getInstance().obtenerPedidos();

            llenarTablaPedidosAdmin();
        }

    }

    private void llenarTablaPedidosAdmin() {

        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que todas las celdas sean editables
            }
        };

        String titulos[] = {"ID", "Cliente", "Vendedor", "Estado", "Total", "Fecha"};
        Modelotabla.setColumnIdentifiers(titulos);
        // Llenar la tabla con los datos de los clientes

        pimDTO.getPedidos().forEach((p) -> {
            String nombreVendedor = VendedorController.getInstance().buscarVendedorPorId(p.getVendedorId()).getNombre();
            String nombreCliente = ClienteController.getInstance().buscarClientePorId(p.getClienteId()).getNombre();
            Modelotabla.addRow(new Object[]{p.getId(), nombreCliente, nombreVendedor, p.getEstado(), p.getTotal(), p.getFecha()});

        });

        // Establecer el modelo en la tabla
        tablaPedidos.setModel(Modelotabla);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        tablaPedidos.setRowSorter(sorter);
        // Configurar para permitir solo una fila seleccionada a la vez
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void llenarTablaPedidosDeVendedor() {
        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que todas las celdas sean editables
            }
        };

        String titulos[] = {"ID", "Cliente", "Estado", "Total", "Fecha"};
        Modelotabla.setColumnIdentifiers(titulos);
        // Llenar la tabla con los datos de los clientes

        pimDTO.getPedidos().forEach((p) -> {
            String nombreCliente = ClienteController.getInstance().buscarClientePorId(p.getClienteId()).getNombre();
            Modelotabla.addRow(new Object[]{p.getId(), nombreCliente, p.getEstado(), p.getTotal(), p.getFecha()});

        });

        // Establecer el modelo en la tabla
        tablaPedidos.setModel(Modelotabla);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        tablaPedidos.setRowSorter(sorter);
        // Configurar para permitir solo una fila seleccionada a la vez
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void llenarTablaPedidosDeCliente() {
        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que todas las celdas sean editables
            }
        };

        String titulos[] = {"ID", "Vendedor", "Estado", "Total", "Fecha"};
        Modelotabla.setColumnIdentifiers(titulos);
        // Llenar la tabla con los datos de los clientes

        pimDTO.getPedidos().forEach((p) -> {
            String nombreVendedor = VendedorController.getInstance().buscarVendedorPorId(p.getVendedorId()).getNombre();
            Modelotabla.addRow(new Object[]{p.getId(), nombreVendedor, p.getEstado(), p.getTotal(), p.getFecha()});

        });

        // Establecer el modelo en la tabla
        tablaPedidos.setModel(Modelotabla);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        tablaPedidos.setRowSorter(sorter);
        // Configurar para permitir solo una fila seleccionada a la vez
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        volverBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        aceptadoRb = new javax.swing.JRadioButton();
        preparacionRb = new javax.swing.JRadioButton();
        envioRb = new javax.swing.JRadioButton();
        enviadoRb = new javax.swing.JRadioButton();
        recibidoRb = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Estado", "Total", "Fecha"
            }
        ));
        jScrollPane3.setViewportView(tablaCarrito);

        jLabel1.setText("Carrito");

        jLabel2.setText("Informacion del Pedido");

        aceptadoRb.setText("Aceptado");
        aceptadoRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptadoRbActionPerformed(evt);
            }
        });

        preparacionRb.setText("Preparacion");
        preparacionRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preparacionRbActionPerformed(evt);
            }
        });

        envioRb.setText("En Envío");
        envioRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envioRbActionPerformed(evt);
            }
        });

        enviadoRb.setText("Enviado");
        enviadoRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviadoRbActionPerformed(evt);
            }
        });

        recibidoRb.setText("Recibido");
        recibidoRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibidoRbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(volverBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(recibidoRb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aceptadoRb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preparacionRb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(envioRb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enviadoRb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volverBtn)
                    .addComponent(aceptadoRb)
                    .addComponent(preparacionRb)
                    .addComponent(envioRb)
                    .addComponent(enviadoRb)
                    .addComponent(recibidoRb))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPedidosMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tablaPedidos.getSelectedRow();

        if (filaSeleccionada != -1) {
            int pedidoId = (int) tablaPedidos.getValueAt(filaSeleccionada, 0);
            this.pedidoId = pedidoId;
            cargarItems(pedidoId);
            if (tipoUsuario.equals("vendedor")) {
                visibilidadRB(true);
                EstadoPedido ep = pimDTO.getPedidoPorId(pedidoId).getEstado();
                setearEstadoRB(ep.name());
            }
        }
    }//GEN-LAST:event_tablaPedidosMouseClicked

    private void setearEstadoRB(String estado) {

        switch (estado) {
            case "RECIBIDO" ->
                recibidoRb.setSelected(true);
            case "ACEPTADO" ->
                aceptadoRb.setSelected(true);
            case "PREPARACION" ->
                preparacionRb.setSelected(true);
            case "EN_ENVIO" ->
                envioRb.setSelected(true);
            case "ENVIADO" ->
                enviadoRb.setSelected(true);

        }

    }

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        // TODO add your handling code here:
        String rol = AutenticacionController.getInstance().getTipoUsuario();
        switch (rol) {
            case "cliente" ->
                HelpersVista.cambiarVentana(this, ClienteVista.class);
            case "vendedor" ->
                HelpersVista.cambiarVentana(this, VendedorVista.class);
            case "admin" ->
                HelpersVista.cambiarVentana(this, AdminVista.class);
        }
    }//GEN-LAST:event_volverBtnActionPerformed

    private void actualizarEstadoLocal(String estado) {
        DefaultTableModel model = (DefaultTableModel) tablaPedidos.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String idEnTabla = model.getValueAt(i, 0).toString();
            if (idEnTabla.equals(pedidoId.toString())) {
                model.setValueAt(estado, i, 2);
            }
        }
        pimDTO.getPedidoPorId(pedidoId).setEstado(EstadoPedido.valueOf(estado));

    }

    private void recibidoRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibidoRbActionPerformed
        // TODO add your handling code here:
        pc.actualizarEstadoPedido(pedidoId, "RECIBIDO");
        actualizarEstadoLocal("RECIBIDO");
    }//GEN-LAST:event_recibidoRbActionPerformed

    private void aceptadoRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptadoRbActionPerformed
        // TODO add your handling code here:
        pc.actualizarEstadoPedido(pedidoId, "ACEPTADO");
        actualizarEstadoLocal("ACEPTADO");
    }//GEN-LAST:event_aceptadoRbActionPerformed

    private void preparacionRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preparacionRbActionPerformed
        // TODO add your handling code here:
        pc.actualizarEstadoPedido(pedidoId, "PREPARACION");
        actualizarEstadoLocal("PREPARACION");
    }//GEN-LAST:event_preparacionRbActionPerformed

    private void envioRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envioRbActionPerformed
        // TODO add your handling code here:
        pc.actualizarEstadoPedido(pedidoId, "EN_ENVIO");
        actualizarEstadoLocal("EN_ENVIO");
    }//GEN-LAST:event_envioRbActionPerformed

    private void enviadoRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviadoRbActionPerformed
        // TODO add your handling code here:
        pc.actualizarEstadoPedido(pedidoId, "ENVIADO");
        actualizarEstadoLocal("ENVIADO");
    }//GEN-LAST:event_enviadoRbActionPerformed

    private void cargarItems(Integer pedidoId) {

        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que todas las celdas sean editables
            }
        };

        String titulos[] = {"ID", "Producto", "Descripcion", "Cantidad", "Precio Unitario"};
        Modelotabla.setColumnIdentifiers(titulos);
        // Llenar la tabla con los datos de los clientes

        Pedido p = pimDTO.getPedidoPorId(pedidoId);

        p.getDetalle().forEach((d) -> {

            ItemMenu im = pimDTO.getItemMenus().stream()
                    .filter(imenu -> imenu.getId().equals(d.getItemMenuId()))
                    .findFirst()
                    .orElse(null);

            Modelotabla.addRow(new Object[]{im.getId(), im.getNombre(), im.getDescripcion(), d.getCantidad(), im.getPrecio()});

        });

        // Establecer el modelo en la tabla
        tablaCarrito.setModel(Modelotabla);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        tablaCarrito.setRowSorter(sorter);
        // Configurar para permitir solo una fila seleccionada a la vez
        tablaCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

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
            java.util.logging.Logger.getLogger(ListarPedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarPedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarPedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarPedidosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarPedidosVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton aceptadoRb;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton enviadoRb;
    private javax.swing.JRadioButton envioRb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton preparacionRb;
    private javax.swing.JRadioButton recibidoRb;
    private javax.swing.JTable tablaCarrito;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables
}
