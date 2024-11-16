/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.ItemMenu;

import controllers.ItemMenuController;
import controllers.VendedorController;
import helpers.HelpersVista;
import isi.deso.tp.menu.Plato;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import memories.ItemMenuMemory;
import memories.VendedorMemory;

/**
 *
 * @author mariano
 */
public class EditarPlatoVista extends javax.swing.JFrame {

    public Integer itemMenuId;
    public Integer vendedorId; //dueño del item menu
    public ItemMenuController imc;
    public VendedorController vc;

    /**
     * Creates new form EditaClienteVista
     */
    public EditarPlatoVista() {
        initComponents();
    }

    public EditarPlatoVista(Integer id) {
        itemMenuId = id;
        vc = VendedorController.getInstance();
        imc = ItemMenuController.getInstance();
        initComponents();

        Plato p = (Plato) imc.buscarItemMenuPorId(itemMenuId);
        vendedorId = p.getVendedorId();

        //insertar en fields
        nombreInput.setText(p.getNombre());
        descripcionInput.setText(p.getDescripcion());
        precioInput.setText(p.getPrecio().toString());
        pesoInput.setText(p.getPeso().toString());
        caloriasInput.setText(p.getCalorias().toString());
        veganoBox.setSelected(p.aptoVegano());
        celiacoBox.setSelected(p.isAptoCeliaco());

        cargarVendedores();
    }

    public void cargarVendedores() {
        DefaultTableModel Modelotabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que todas las celdas sean editables
            }
        };

        String titulos[] = {"ID", "Nombre"};
        Modelotabla.setColumnIdentifiers(titulos);

        int rowIndexToSelect = -1;
        int currentIndex = 0;

        // Llenar la tabla con los datos de los vendedores
        for (var v : vc.listarVendedores()) {
            Modelotabla.addRow(new Object[]{v.getId(), v.getNombre()});

            if (v.getId() == vendedorId) {
                vendedorText.setText(v.getId() + ", " + v.getNombre());
                rowIndexToSelect = currentIndex;
            }
            currentIndex++;
        }

        // Establecer el modelo en la tabla
        tablaVendedores.setModel(Modelotabla);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(Modelotabla);
        tablaVendedores.setRowSorter(sorter);

        // Seleccionar la fila si rowIndexToSelect tiene un valor válido
        if (rowIndexToSelect != -1) {
            tablaVendedores.setRowSelectionInterval(rowIndexToSelect, rowIndexToSelect);
        }
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
        nombreInput = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        pesoInput = new javax.swing.JTextField();
        cancelarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        descripcionInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        precioInput = new javax.swing.JTextField();
        veganoBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVendedores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        vendedorText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        caloriasInput = new javax.swing.JTextField();
        celiacoBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel3.setText("Peso");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Plato (edit)");

        jLabel6.setText("Descripcion");

        jLabel7.setText("Precio");

        veganoBox.setText("Apto para vegano");

        tablaVendedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaVendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVendedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVendedores);

        jLabel2.setText("Vendedor seleccionado: ");

        jLabel8.setText("Seleccione un vendedor");

        vendedorText.setEditable(false);

        jLabel4.setText("Calorias");

        celiacoBox.setText("Apto para celiaco");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 99, Short.MAX_VALUE)))
                        .addGap(277, 277, 277))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(descripcionInput, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreInput))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(pesoInput)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cancelarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardarBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(precioInput)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vendedorText, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(caloriasInput, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celiacoBox)
                    .addComponent(veganoBox))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcionInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precioInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(veganoBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caloriasInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(celiacoBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(vendedorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarBtn)
                    .addComponent(guardarBtn))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed

        try {

            if (nombreInput.getText().equals("")
                    || descripcionInput.getText().equals("")
                    || precioInput.getText().equals("")
                    || pesoInput.getText().equals("")
                    || caloriasInput.getText().equals("")) {

                throw new Exception("Llena todos los campos");
            }

            //comprobar si selecciono vendedor
            DefaultTableModel model = (DefaultTableModel) tablaVendedores.getModel();
            if (tablaVendedores.getSelectedRows().length < 1) {
                throw new Exception("Seleccina un vendedor");
            }
            Integer vendedorId = 0;
            for (int i : tablaVendedores.getSelectedRows()) {
                try {
                    vendedorId = (Integer) tablaVendedores.getValueAt(tablaVendedores.getSelectedRow(), 0);

                } catch (Exception e) {
                    throw new Exception("Algo salio mal");
                }
            }

            //obtener checkbox
            boolean aptoVegano = veganoBox.isSelected();
            boolean aptoCeliaco = celiacoBox.isSelected();

        
            imc.editarPlato(itemMenuId, nombreInput.getText(), descripcionInput.getText(), precioInput.getText(), aptoVegano, pesoInput.getText(), caloriasInput.getText(), aptoCeliaco, vendedorId);
            //vuelve
            HelpersVista.cambiarVentana(this, ListaItemMenuVista.class);
        } catch (Exception e) {
            HelpersVista.mostrarMensaje(e.getMessage(), "Error", "Alerta");
        }

    }//GEN-LAST:event_guardarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed

        HelpersVista.cambiarVentana(this, ListaItemMenuVista.class);
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void tablaVendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVendedoresMouseClicked
        int row = tablaVendedores.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            // Obtener los datos de la fila clicada
            String id = (String) tablaVendedores.getValueAt(row, 0).toString();
            String nombre = (String) tablaVendedores.getValueAt(row, 1).toString();

            vendedorText.setText(id + ", " + nombre);
        }
    }//GEN-LAST:event_tablaVendedoresMouseClicked

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
            java.util.logging.Logger.getLogger(EditarPlatoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarPlatoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarPlatoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarPlatoVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarPlatoVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField caloriasInput;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JCheckBox celiacoBox;
    private javax.swing.JTextField descripcionInput;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreInput;
    private javax.swing.JTextField pesoInput;
    private javax.swing.JTextField precioInput;
    private javax.swing.JTable tablaVendedores;
    private javax.swing.JCheckBox veganoBox;
    private javax.swing.JTextField vendedorText;
    // End of variables declaration//GEN-END:variables
}
