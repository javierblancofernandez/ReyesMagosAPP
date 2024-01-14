/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import base_datos.BaseDatos;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import pojos.Producto;
import Ventana.ProductoFrame;
import java.awt.Dimension;

/**
 *
 * @author FRANCISCO
 */
public class InventariosFrame extends javax.swing.JInternalFrame {
    BaseDatos base=new BaseDatos();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    Producto productoSeleccionado =null;//esta variable esta en el postint de la tabla
    /**
     * Creates new form Inventarios
     */
    public InventariosFrame() {
        initComponents();
        cargarColumnasTablas();
        cargarModeloTabla();
    }

    /**
     * Este emtodo carga el modelo de la Tabla de inventarios*
     */
    private void cargarColumnasTablas(){
        modeloTabla.addColumn("clave");
        modeloTabla.addColumn("nombre");
        modeloTabla.addColumn("unidad");
        modeloTabla.addColumn("PrcioCompra");
        //modeloTabla.addColumn("precioVenta");
        modeloTabla.addColumn("existencias");
    }
    private void cargarModeloTabla() {

        System.out.println("EStoy en cargar modelo tabla");
        limpiarTabla();
        ArrayList <Producto> listaProductos=base.obtenerProductos();
        int numeroProductos= listaProductos.size();
        System.out.println("EStoy en cargar modelo tabla"+numeroProductos);
        modeloTabla.setNumRows(numeroProductos);
        
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto producto = listaProductos.get(i);
            String clave = producto.getIdProducto();
            String nombre = producto.getNombProducto();
            String unidad = producto.getUnidadProducto();
            Double precioCompra = producto.getPrecioCompraProducto();
            //Double precioVenta = producto.getPrecioVentaProducto();
            Double existencias = producto.getExistenciaProducto();
            modeloTabla.setValueAt(clave, i, 0);
            modeloTabla.setValueAt(nombre, i, 1);
            modeloTabla.setValueAt(unidad, i, 2);
            modeloTabla.setValueAt(precioCompra, i, 3);
            //modeloTabla.setValueAt(precioVenta, i, 4);
            modeloTabla.setValueAt(existencias, i, 4);
        }

    }
    
    private void desplegarFoto ( Producto producto) {
    ImageIcon imagenProducto =null ;
    try{
    /*obtener imagen*/
    InputStream is = base.buscarFoto(producto);
    BufferedImage bi = ImageIO.read(is);
    imagenProducto = new ImageIcon(bi);
    
    //lblImagen.setIcon(imagenProducto);
    // Redimension de imagen para ajustarla al tamano del JLabel 
    Image imgProd = imagenProducto.getImage();
    int anchoEtiqueta = lblImagen.getWidth();//Obtiene el ancho del a imagen
    int anchoEtiquetaa = lblImagen.getHeight();//Obtiene el alto de la imagen
    
    //Se crea un nuevo objeto Image con la imagen redimensionada
    Image imgRedimensionada = imgProd.getScaledInstance(anchoEtiqueta, anchoEtiqueta, Image.SCALE_DEFAULT);
    
    // se crea un nuevo objeto ImageIcon a partir de la imagen redimensionada 
    ImageIcon iconRedimensionado = new ImageIcon(imgRedimensionada);
    
    // Establecemos laimagen redimensioanda como icono de la etiqueta de imagen
    
    lblImagen.setIcon(iconRedimensionado);
    
    }catch(IOException ex){
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

        jButtonNuevoArticulo = new javax.swing.JButton();
        jButtonCategoriaFrame = new javax.swing.JButton();
        jButtonProveedorFrame = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        campoClaveProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoNombreProducto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        tablaProductos.getSelectionModel().addListSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event){
                    if (! event.getValueIsAdjusting() && tablaProductos.getSelectedRow()>=0){
                        int filaSeleccionada=tablaProductos.getSelectedRow();
                        Producto producto=(Producto)modeloTabla.getValueAt(filaSeleccionada, 1);
                        campoNombreProducto.setText(producto.getNombProducto());
                        campoClaveProducto.setText(producto.getIdProducto());
                        campoExistenciaProducto.setText(String.valueOf(producto.getStockProducto()));
                        productoSeleccionado= producto;
                        //desplegar la foto del producto
                        desplegarFoto(producto);
                    }
                }

            }
        );
        jLabel5 = new javax.swing.JLabel();
        campoExistenciaProducto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campoAgregarExistenciaProducto = new javax.swing.JTextField();
        jButonAgregarExistencia = new javax.swing.JButton();
        modificarProducto = new javax.swing.JButton();
        borrarProducto = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        campoBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();

        setTitle("Inventarios");
        setPreferredSize(new java.awt.Dimension(577, 558));

        jButtonNuevoArticulo.setToolTipText("Nuevo Articulo");
        jButtonNuevoArticulo.setPreferredSize(new java.awt.Dimension(127, 127));
        ImageIcon iconBtnNuevoArticulo=new ImageIcon("images/nuevoarticulo.png");
        Image imgBtnNuevoArticulo = iconBtnNuevoArticulo.getImage();

        Dimension prefSizeBtnNuevoArticulo = jButtonNuevoArticulo.getPreferredSize();
        int anchoBtnNuevoArticulo = (int)(prefSizeBtnNuevoArticulo.getWidth()*0.6);
        int altoBtnNuevoArticulo = (int)(prefSizeBtnNuevoArticulo.getHeight()* 0.6);

        Image imgRedimBtnNuevoArticulo = imgBtnNuevoArticulo.getScaledInstance(anchoBtnNuevoArticulo, altoBtnNuevoArticulo, Image.SCALE_DEFAULT);
        ImageIcon iconRedimBtnNuevoArticulo = new ImageIcon(imgRedimBtnNuevoArticulo);

        jButtonNuevoArticulo.setIcon(iconRedimBtnNuevoArticulo);
        jButtonNuevoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoArticuloActionPerformed(evt);
            }
        });

        jButtonCategoriaFrame.setToolTipText("Nueva Categoria");
        jButtonCategoriaFrame.setPreferredSize(new java.awt.Dimension(127, 127));
        ImageIcon iconBtnNuevaCategoria=new ImageIcon("images/nuevacateg.png");
        Image imgBtnNuevaCategoria = iconBtnNuevaCategoria.getImage();

        Dimension prefSizeBtnNuevaCategoria = jButtonCategoriaFrame.getPreferredSize();
        int anchoBtnNuevaCategoria = (int)(prefSizeBtnNuevaCategoria.getWidth()*0.6);
        int altoBtnNuevaCategoria = (int)(prefSizeBtnNuevaCategoria.getHeight()* 0.6);

        Image imgRedimBtnNuevaCategoria = imgBtnNuevaCategoria.getScaledInstance(anchoBtnNuevaCategoria, altoBtnNuevaCategoria, Image.SCALE_DEFAULT);
        ImageIcon iconRedimBtnNuevaCategoria = new ImageIcon(imgRedimBtnNuevaCategoria);

        jButtonCategoriaFrame.setIcon(iconRedimBtnNuevaCategoria);
        jButtonCategoriaFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCategoriaFrameActionPerformed(evt);
            }
        });

        jButtonProveedorFrame.setToolTipText("Nuevo Proveedor");
        jButtonProveedorFrame.setPreferredSize(new java.awt.Dimension(127, 127));
        ImageIcon iconBtnNuevoProveedor=new ImageIcon("images/editar.png");
        Image imgBtnNuevoProveedor = iconBtnNuevoProveedor.getImage();

        Dimension prefSizeBtnNuevoProveedor = jButtonProveedorFrame.getPreferredSize();
        int anchoBtnNuevoProveedor = (int)(prefSizeBtnNuevoProveedor.getWidth()*0.6);
        int altoBtnNuevoProveedor = (int)(prefSizeBtnNuevoProveedor.getHeight()* 0.6);

        Image imgRedimBtnNuevoProveedor = imgBtnNuevoProveedor.getScaledInstance(anchoBtnNuevaCategoria, altoBtnNuevaCategoria, Image.SCALE_DEFAULT);
        ImageIcon iconRedimBtnNuevoProveedor = new ImageIcon(imgRedimBtnNuevoProveedor);

        jButtonProveedorFrame.setIcon(iconRedimBtnNuevoProveedor);
        jButtonProveedorFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProveedorFrameActionPerformed(evt);
            }
        });

        jLabel1.setText("Inventarios :");

        jLabel2.setText("Clave");

        jLabel3.setText("Nombre");

        tablaProductos.setModel(modeloTabla);
        jScrollPane1.setViewportView(tablaProductos);

        jLabel5.setText("Existencia");

        jLabel6.setText("Ingresar al Inventario");

        jButonAgregarExistencia.setText("Agregar");
        jButonAgregarExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButonAgregarExistenciaActionPerformed(evt);
            }
        });

        modificarProducto.setText("modifcar");
        modificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarProductoActionPerformed(evt);
            }
        });

        borrarProducto.setText("Eliminar");
        borrarProducto.setPreferredSize(new java.awt.Dimension(73, 23));
        borrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarProductoActionPerformed(evt);
            }
        });

        jLabel7.setText("Buscar");

        campoBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoBuscarKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(borrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modificarProducto)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jButtonNuevoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButtonCategoriaFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButtonProveedorFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(campoClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(campoNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(13, 13, 13))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(181, 181, 181)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoExistenciaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(campoAgregarExistenciaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButonAgregarExistencia)
                                        .addGap(64, 64, 64))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonProveedorFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCategoriaFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonNuevoArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoExistenciaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoAgregarExistenciaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButonAgregarExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(modificarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(borrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(271, 271, 271))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoArticuloActionPerformed
        // TODO add your handling code here:
        ProductoFrame articulo = new ProductoFrame(null, true,null,null,"Nuevo Producto",false);
        articulo.setVisible(true);
        articulo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        articulo.setLocation(600, 150);
        articulo.setAlwaysOnTop(true);
    }//GEN-LAST:event_jButtonNuevoArticuloActionPerformed

    private void jButtonCategoriaFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCategoriaFrameActionPerformed
        // TODO add your handling code here:
        CategoriaFrame categoria = new CategoriaFrame(null, true);
        categoria.setVisible(true);
        categoria.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        categoria.setLocation(600, 150);
        categoria.setAlwaysOnTop(true);
    }//GEN-LAST:event_jButtonCategoriaFrameActionPerformed

    private void jButtonProveedorFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProveedorFrameActionPerformed
        // TODO add your handling code here:
        ProveedorFrame proveedor = new ProveedorFrame(null, true);
        proveedor.setVisible(true);
        proveedor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        proveedor.setLocation(600, 150);
        proveedor.setAlwaysOnTop(true);

    }//GEN-LAST:event_jButtonProveedorFrameActionPerformed

    private void jButonAgregarExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButonAgregarExistenciaActionPerformed
        // TODO add your handling code here:
        double existencia=Double.parseDouble(campoAgregarExistenciaProducto.getText());
        double cantidadActual = productoSeleccionado.getStockProducto();
        double nuevaCantidad=cantidadActual+ existencia;
                
        base.actualizarInventario(productoSeleccionado, existencia);
        limpiarTabla();
        cargarModeloTabla();
    }//GEN-LAST:event_jButonAgregarExistenciaActionPerformed

    private void campoBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscarKeyReleased
        // TODO add your handling code here:
        limpiarTabla();
        String cadenaBusqueda=campoBuscar.getText();
        ArrayList <Producto> listaProductos=base.obtenerProductosPorCriterio(cadenaBusqueda);
        
        int numeroProductos=listaProductos.size();
        modeloTabla.setNumRows(numeroProductos);
        for (int i = 0; i < numeroProductos; i++) {
            Producto producto = listaProductos.get(i);
            String clave=producto.getIdProducto();
            String nombre=producto.getNombProducto();
            String unidad = producto.getUnidadProducto();
            Double precioCompra=producto.getPrecioCompraProducto();
            //Double precioVenta = producto.getPrecioVentaProducto();
            Double existencias = producto.getExistenciaProducto();
            
            //modeloTabla.setValueAt(producto, i, 0);
            modeloTabla.setValueAt(clave, i, 0);
            modeloTabla.setValueAt(producto, i, 1);
            modeloTabla.setValueAt(unidad, i, 2);
            modeloTabla.setValueAt(precioCompra, i, 3);
            //modeloTabla.setValueAt(precioVenta, i, 4);
            modeloTabla.setValueAt(existencias, i, 4);
            
        }
        
        
        
        
    }//GEN-LAST:event_campoBuscarKeyReleased

    private void borrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarProductoActionPerformed
        // TODO add your handling code here:
        int opcion=JOptionPane.showConfirmDialog(this, " ¿Estas seguro de borrar el Producto?");
        
        if (opcion==0) {
            modeloTabla.removeRow(tablaProductos.getSelectedRow());
            base.borrarProducto(productoSeleccionado);
        }
    }//GEN-LAST:event_borrarProductoActionPerformed

    private void modificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarProductoActionPerformed
        // TODO add your handling code here:
        String nombreProducto= productoSeleccionado.getNombProducto();
        ImageIcon imagenProducto = null;
        ProductoFrame frameProd=null;
        int opcion=JOptionPane.showConfirmDialog(this, "¿Estas seguro de modificar el articulo "+nombreProducto+"?");
        
        if (opcion==0) {
            try {
                /* obtengo la imagen*/
                InputStream is=base.buscarFoto(productoSeleccionado);
                BufferedImage bi=ImageIO.read(is);
                imagenProducto=new ImageIcon(bi);
                
                /*creo la ventana*/   
                frameProd = new ProductoFrame(null,true,productoSeleccionado,imagenProducto,"Actualizar producto",true);
                frameProd.setVisible(true);
                if(frameProd!=null){
                if (frameProd.getInformacion() !="") {
                    cargarModeloTabla();
                }
                }
            
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
    }//GEN-LAST:event_modificarProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarProducto;
    private javax.swing.JTextField campoAgregarExistenciaProducto;
    private javax.swing.JTextField campoBuscar;
    private javax.swing.JTextField campoClaveProducto;
    private javax.swing.JTextField campoExistenciaProducto;
    private javax.swing.JTextField campoNombreProducto;
    private javax.swing.JButton jButonAgregarExistencia;
    private javax.swing.JButton jButtonCategoriaFrame;
    private javax.swing.JButton jButtonNuevoArticulo;
    private javax.swing.JButton jButtonProveedorFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JButton modificarProducto;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables

    private void limpiarTabla() {
        int numFilas= modeloTabla.getRowCount();
        System.out.println("nume fillas : "+numFilas);
        if (numFilas>0) {
            for (int i = numFilas-1; i >=0 ; i--) {
            modeloTabla.removeRow(i);
            
        }
        }
        

    }
   
}

