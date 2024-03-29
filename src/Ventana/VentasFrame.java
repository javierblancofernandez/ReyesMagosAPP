/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import base_datos.BaseDatos;
import com.sun.glass.events.KeyEvent;
import enviarMail.SendEmail;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import pdf_creator.PdfMaker;
import pojos.DetalleVenta;
import pojos.Nino;
import pojos.Pedido;
import pojos.Producto;
import pojos.ResulNinoRey;
import pojos.Venta;

/**
 *
 * @author FRANCISCO
 */
public class VentasFrame extends javax.swing.JInternalFrame {

    DefaultTableModel modeloTablaProductos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }
    };

    DefaultListModel<Producto> modeloListaProductos = new DefaultListModel<Producto>();
    DefaultListModel<Nino> modeloListaNinos = new DefaultListModel<Nino>();
    Nino nen = new Nino();
    BaseDatos base = new BaseDatos();
    int filaSeleccionada;

    /**
     * Creates new form Ventas
     */
    public VentasFrame() {
        initComponents();
        cargarColumnasTablas();
        cargarListenerModeloTabla();
    }

    private void cargarListenerModeloTabla() {
        modeloTablaProductos.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                int numFilas = modeloTablaProductos.getRowCount();
                //varialbe que sirva para guardar el sumatorio de la venta
                double sumatoria = 0;
                for (int i = 0; i < numFilas; i++) {
                    String importe = (String) modeloTablaProductos.getValueAt(i, 4);//fila,columna
                    sumatoria += Double.parseDouble(importe);//sumatoria=sumatoria+Double.parseDouble(importe)

                }
                //lblSumatoria.setText(String.valueOf(sumatoria));
            }

        });
    }

    private void cargarColumnasTablas() {
        modeloTablaProductos.addColumn("Clave");
        modeloTablaProductos.addColumn("Nombre");
        modeloTablaProductos.addColumn("Precio venta");
        modeloTablaProductos.addColumn("Cantidad");
        modeloTablaProductos.addColumn("Importe");

    }

    private void desplegarFoto(Producto producto) {
        ImageIcon imagenProducto = null;
        try {
            /*obtener imagen*/
            InputStream is = base.buscarFoto(producto);
            BufferedImage bi = ImageIO.read(is);
            imagenProducto = new ImageIcon(bi);

            //lblImagenProd.setIcon(imagenProducto);
            // Redimension de imagen para ajustarla al tamano del JLabel 
            Image imgProd = imagenProducto.getImage();
            lblImagenProd2.setSize(200, 200);
            int anchoEtiqueta = lblImagenProd2.getWidth();//Obtiene el ancho del a imagen
            int altoEtiqueta = lblImagenProd2.getHeight();//Obtiene el alto de la imagen

            //Se crea un nuevo objeto Image con la imagen redimensionada
            Image imgRedimensionada = imgProd.getScaledInstance(anchoEtiqueta, altoEtiqueta, Image.SCALE_DEFAULT);

            // se crea un nuevo objeto ImageIcon a partir de la imagen redimensionada 
            ImageIcon iconRedimensionado = new ImageIcon(imgRedimensionada);

            // Establecemos laimagen redimensioanda como icono de la etiqueta de imagen
            lblImagenProd2.setIcon(iconRedimensionado);
        } catch (IOException ex) {
            ex.printStackTrace();
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

        lblImagenProd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        campoBuscarProd = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnPedirRegalos = new javax.swing.JButton();
        btnQuitarProd = new javax.swing.JButton();
        btnCancelarVenta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblImagenProd2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaBusquedas = new javax.swing.JList<>();
        campoBuscaNino = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listabusquedaNino = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setTitle("Pedir Regalos");
        setPreferredSize(new java.awt.Dimension(577, 558));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(950, 568));

        tablaVentas.setModel(modeloTablaProductos);
        tablaVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaVentasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        jLabel1.setText("Buscar Regalo");

        campoBuscarProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoBuscarProdKeyReleased(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 200));

        btnPedirRegalos.setBackground(new java.awt.Color(255, 204, 51));
        btnPedirRegalos.setText("PEDIR REGALOS");
        btnPedirRegalos.setToolTipText("");
        btnPedirRegalos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedirRegalosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPedirRegalos, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnPedirRegalos, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        btnQuitarProd.setText("QUITAR REGALO");
        btnQuitarProd.setToolTipText("");
        btnQuitarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProdActionPerformed(evt);
            }
        });

        btnCancelarVenta.setText("CANCELAR VENTA");
        btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagenProd2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagenProd2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        listaBusquedas.setModel(modeloListaProductos);
        listaBusquedas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listaBusquedasMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(listaBusquedas);

        campoBuscaNino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoBuscaNinoKeyReleased(evt);
            }
        });

        jLabel2.setText("Busca Niño Registrado");

        listabusquedaNino.setModel(modeloListaNinos);
        listabusquedaNino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listabusquedaNinoMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(listabusquedaNino);

        jLabel3.setText("Tu Nombre Registrado");
        jLabel3.setToolTipText("");

        jLabel4.setText("Tu Regalo elegido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(campoBuscaNino)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnQuitarProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(campoBuscarProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoBuscaNino, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnQuitarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoBuscarProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscarProdKeyReleased
        // TODO add your handling code here:
        limpiarListaProductos();
        String cadenaBusqueda = campoBuscarProd.getText();
        if (cadenaBusqueda.isEmpty()) {
            limpiarListaProductos();

        } else {
            ArrayList<Producto> listaProductos = base.obtenerProductosPorCriterio(cadenaBusqueda);

            for (Producto prod : listaProductos) {
                modeloListaProductos.addElement(prod);

            }
        }

    }//GEN-LAST:event_campoBuscarProdKeyReleased

    private void listaBusquedasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaBusquedasMousePressed
        // con el evento de mouse seleccionamos el indice y el producto
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            System.out.println("el indice en :" + index);
            Producto prod = (Producto) list.getSelectedValue();
            System.out.println("el producto es : " + prod);
            anadirProductoAVenta(prod);

            desplegarFoto(prod);

        }
    }//GEN-LAST:event_listaBusquedasMousePressed

    private void tablaVentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaVentasKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            int filaSeleccionada = tablaVentas.getSelectedRow();
            String cantidad = JOptionPane.showInputDialog("Modificar Cantidad :");
            String precioVenta = (String) modeloTablaProductos.getValueAt(filaSeleccionada, 2);
            double importe = Double.parseDouble(cantidad) * Double.parseDouble(precioVenta);
            String importeString = String.valueOf(importe);
            modeloTablaProductos.setValueAt(cantidad, filaSeleccionada, 3);
            modeloTablaProductos.setValueAt(importeString, filaSeleccionada, 4);

        }
    }//GEN-LAST:event_tablaVentasKeyReleased

    private void btnQuitarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProdActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tablaVentas.getSelectedRow();
        int cantidadFilas = modeloTablaProductos.getRowCount();
        if (cantidadFilas > 0) {
            int opcion = JOptionPane.showConfirmDialog(this, " Esta seguro de borrar el producto? : ");
            if (opcion == 0) {
                modeloTablaProductos.removeRow(filaSeleccionada);

            }

        }


    }//GEN-LAST:event_btnQuitarProdActionPerformed

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
        // TODO add your handling code here:
        int cantidadFilas = modeloTablaProductos.getRowCount();

        if (cantidadFilas > 0) {
            int opcion = JOptionPane.showConfirmDialog(this, " Esta seguro de cancelar la venta ? : ");
            if (opcion == 0) {
                for (int i = cantidadFilas - 1; i >= 0; i--) {
                    modeloTablaProductos.removeRow(i);
                }
            }

        }


    }//GEN-LAST:event_btnCancelarVentaActionPerformed

    private void btnPedirRegalosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedirRegalosActionPerformed
        // TODO add your handling code here:

        ArrayList<DetalleVenta> detalles = new ArrayList<DetalleVenta>();

        //obtenemos la fecha actual y creamos un objeto Date Sql
        Calendar calendarioLocal = Calendar.getInstance();
        java.util.Date fechaActual = calendarioLocal.getTime();
        long fechaMilisegundos = fechaActual.getTime();
        Date fecha = new Date(fechaMilisegundos);
        System.out.println("fecha"+fecha);
        int idnene = this.nen.getId_nino();
        Pedido pedido = new Pedido(idnene, fecha);
        Integer idVenta = base.insertarPedido(pedido);

        int numRows = modeloTablaProductos.getRowCount();

        for (int i = 0; i < numRows; i++) {

            String idProducto = (String) modeloTablaProductos.getValueAt(i, 0);
            String cantidadStr = (String) modeloTablaProductos.getValueAt(i, 3);
            double cantidad = Double.parseDouble(cantidadStr);
            DetalleVenta detalle = new DetalleVenta(idVenta, idProducto, cantidad);
            base.insertarDetalleVenta(detalle);
            detalles.add(detalle);

        }
        for (int i = numRows - 1; i >= 0; i--) {
            modeloTablaProductos.removeRow(i);
        }

        ArrayList<ResulNinoRey> result= base.obtenerNinoResultado();
        System.out.println("nombre"+(result.get(0)).getNombreNino()+"Rey mago"+(result.get(0)).getNombreRey()+"email"+(result.get(0)).getEmailNino());

        JOptionPane.showMessageDialog(this, "<html><p style='font-size:15px; color:blue'><b>"+(result.get(0)).getNombreNino()+"</b>Tus regalos te los llevará tu Rey <b>"+(result.get(0)).getNombreRey()+"</p></html>", "Tus regalos Registrados", 1);
        PdfMaker generatePDFFileIText = new PdfMaker();
        generatePDFFileIText.createPDF(new File("C:/Users/javib/Desktop/CartaReyesMagos.pdf"),(result.get(0)).getNombreNino(),(result.get(0)).getNombreRey());
        SendEmail email = new SendEmail();//
        email.envioDeEmail((result.get(0)).getEmailNino());

    }//GEN-LAST:event_btnPedirRegalosActionPerformed

    private void campoBuscaNinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscaNinoKeyReleased
        // TODO add your handling code here:
        limpiarListaNino();
        String cadenaBusqueda = campoBuscaNino.getText();
        if (cadenaBusqueda.isEmpty()) {
            limpiarListaProductos();

        } else {
            ArrayList<Nino> listaNino = base.obtenerNinoPorCriterio(cadenaBusqueda);

            for (Nino nen : listaNino) {
                modeloListaNinos.addElement(nen);

            }

        }
    }//GEN-LAST:event_campoBuscaNinoKeyReleased

    private void listabusquedaNinoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listabusquedaNinoMousePressed
        // TODO add your handling code here:
        Nino nene = new Nino();
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            System.out.println("el indice en :" + index);
            nene = (Nino) list.getSelectedValue();
            System.out.println("el niño es : " + nene+"edad"+nene.getEdad_nino());
            
        }
        this.nen = nene;
        jTextField1.setText(nene.getNombre_nino()+' '+nene.getApellidos_nino());
    }//GEN-LAST:event_listabusquedaNinoMousePressed
    private void anadirProductoAVenta(Producto prod) {
        String claveProd = prod.getIdProducto();
        String nombreProd = prod.getNombProducto();
        //String precioVenta = String.valueOf(prod.getPrecioVentaProducto());
        //String importe = String.valueOf(prod.getPrecioVentaProducto());

        //String[] datosProducto = {claveProd, nombreProd, precioVenta, "1", importe};
        String[] datosProducto = {claveProd, nombreProd, "0.0", "1", "0.0"};
        modeloTablaProductos.addRow(datosProducto);
    }

    private void limpiarListaProductos() {
        modeloListaProductos.clear();
    }

    private void limpiarListaNino() {
        modeloListaNinos.clear();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnPedirRegalos;
    private javax.swing.JButton btnQuitarProd;
    private javax.swing.JTextField campoBuscaNino;
    private javax.swing.JTextField campoBuscarProd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblImagenProd;
    private javax.swing.JLabel lblImagenProd2;
    private javax.swing.JList<Producto> listaBusquedas;
    private javax.swing.JList<Nino> listabusquedaNino;
    private javax.swing.JTable tablaVentas;
    // End of variables declaration//GEN-END:variables

}
