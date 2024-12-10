/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.ItemMenu;

import controllers.AutenticacionController;
import controllers.ItemMenuController;
import helpers.HelpersVista;
import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.Plato;
import isi.deso.tp.usuarios.Vendedor;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import memories.ItemMenuMemory;
import view.AdminVista;
import view.VendedorVista;

/**
 *
 * @author mariano
 */
public class ListaItemMenuVista extends javax.swing.JFrame {

    ItemMenuController imc;

    /**
     * Creates new form ListaClienteVista
     */
    
    private String tipoUsuario;

    public ListaItemMenuVista() {
        initComponents();
    tipoUsuario = AutenticacionController.getInstance().getTipoUsuario();
        
    if(tipoUsuario.equals("admin") ){
     ocultarBotones();
        }

        
        
        imc = ItemMenuController.getInstance();

        llenarTabla();

    }

    private void ocultarBotones(){
    editarBtn.setVisible(false);
    eliminarBtn.setVisible(false);
    bebidaBtn.setVisible(false);
    platoBtn.setVisible(false);
    }
    
    private void llenarTabla() {
        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que todas las celdas sean editables
            }
        };

        if(tipoUsuario.equals("admin")){
                String titulos[] = {"ID", "Tipo", "Nombre", "Descripcion", "Precio", "Apto para veganos", "Peso", "Calorias", "Apto para celiacos", "Volumen", "Graduacion Alcohol", "Vendedor"};
Modelotabla.setColumnIdentifiers(titulos);

        // Llenar la tabla con los datos de los clientes
        imc.listarItemsMenu().forEach(im -> {

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
                    "--",
                    plato.getVendedorId()
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
                    bebida.getGraduacionAlcohol(),
                    bebida.getVendedorId()
                });
            }

        });
        }
        else{
         String titulos[] = {"ID", "Tipo", "Nombre", "Descripcion", "Precio", "Apto para veganos", "Peso", "Calorias", "Apto para celiacos", "Volumen", "Graduacion Alcohol"};
Modelotabla.setColumnIdentifiers(titulos);

        // Llenar la tabla con los datos de los clientes
      Vendedor v = AutenticacionController.getInstance().obtenerVendedorAutenticado();
        imc.listarItemMenusPorVendedor(v.getId()).forEach(im -> {

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
        }
        
        

        // Establecer el modelo en la tabla
        tablaClientes.setModel(Modelotabla);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        tablaClientes.setRowSorter(sorter);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        platoBtn = new javax.swing.JButton();
        buscarField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        editarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        buscarBtn = new javax.swing.JButton();
        bebidaBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Direccion", "Coordenadas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaClientes.setAlignmentX(1.0F);
        tablaClientes.setAlignmentY(1.0F);
        jScrollPane1.setViewportView(tablaClientes);
        if (tablaClientes.getColumnModel().getColumnCount() > 0) {
            tablaClientes.getColumnModel().getColumn(0).setResizable(false);
            tablaClientes.getColumnModel().getColumn(1).setResizable(false);
            tablaClientes.getColumnModel().getColumn(2).setResizable(false);
            tablaClientes.getColumnModel().getColumn(3).setResizable(false);
        }

        platoBtn.setText("Agregar Plato");
        platoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                platoBtnActionPerformed(evt);
            }
        });

        buscarField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarFieldKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ItemsMenu");

        jButton2.setText("<- Volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        editarBtn.setText("Editar");
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        bebidaBtn.setText("Agregar Bebida");
        bebidaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bebidaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(platoBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bebidaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buscarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarField, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(202, 202, 202)
                        .addComponent(eliminarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editarBtn)))
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(platoBtn)
                    .addComponent(buscarBtn)
                    .addComponent(buscarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bebidaBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(editarBtn)
                    .addComponent(eliminarBtn)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void platoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_platoBtnActionPerformed

        HelpersVista.cambiarVentana( this,CrearPlatoVista.class);
    }//GEN-LAST:event_platoBtnActionPerformed

    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBtnActionPerformed
        // TODO add your handling code here:
        if (tablaClientes.getSelectedRow() > -1) {
            try {
                Integer itemMenuId = (Integer) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0);

                if (imc.buscarItemMenuPorId(itemMenuId) instanceof Plato) {
                    HelpersVista.abrirVentana( EditarPlatoVista.class, itemMenuId);
                }

                if (imc.buscarItemMenuPorId(itemMenuId) instanceof Bebida) {

                    HelpersVista.cambiarVentana(this,EditarBebidaVista.class, itemMenuId);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            HelpersVista.mostrarMensaje("Selecciona algo !", "Error", "Alerta");
        }

    }//GEN-LAST:event_editarBtnActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
        if (tablaClientes.getSelectedRows().length < 1) {
            HelpersVista.mostrarMensaje("Selecciona una o mas items !", "Error", "Alerta");
        } else {
            for (int i = tablaClientes.getSelectedRowCount() - 1; i >= 0; i--) {
                try {
                    // Obtener el índice de la fila seleccionada
                    int rowIndex = tablaClientes.getSelectedRows()[i];
                    Integer itemMenuId = (Integer) tablaClientes.getValueAt(rowIndex, 0);

                    // Eliminar el item del menú
                    imc.eliminarItemMenu(itemMenuId);

                    // Eliminar la fila de la tabla
                    model.removeRow(rowIndex);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed

        buscar();

    }//GEN-LAST:event_buscarBtnActionPerformed

    private void buscarFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();  // Llama al método buscar solo si se presiona ENTER
        }
    }//GEN-LAST:event_buscarFieldKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         String rol = AutenticacionController.getInstance().getTipoUsuario();
        switch (rol){
          case "admin" -> HelpersVista.cambiarVentana(this, AdminVista.class);
          case "vendedor" -> HelpersVista.cambiarVentana(this, VendedorVista.class);
        } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bebidaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bebidaBtnActionPerformed
        HelpersVista.cambiarVentana(this,CrearBebidaVista.class);
    }//GEN-LAST:event_bebidaBtnActionPerformed

    public void buscar() {
        String texto = buscarField.getText().trim();
        // Asegúrate de que el TableRowSorter esté configurado en la tabla
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tablaClientes.getRowSorter();

        if (texto.isEmpty()) {
            // Si el campo de búsqueda está vacío, elimina el filtro
            sorter.setRowFilter(null);
        } else {
            // Aplica un filtro que busca en todas las columnas
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

    @Override
    public void setVisible(boolean isVisible
    ) {
        super.setVisible(isVisible);
        if (isVisible) {
            llenarTabla();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bebidaBtn;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField buscarField;
    private javax.swing.JButton editarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton platoBtn;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
