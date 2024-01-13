/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base_datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.CategoriaProd;
import pojos.DetalleVenta;
import pojos.Nino;
import pojos.Pedido;
import pojos.Producto;
import pojos.Proveedor;
import pojos.ResulNinoRey;
import pojos.Reyes;
import pojos.Venta;

/**
 *
 * @author FRANCISCO
 */
public class BaseDatos {

    /*String url = "jdbc:mysql://localhost:3306/inventario?useUnicode="
            + "true&useJDBCCompliantTimezoneShift="
            + "true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";*/
    String url = "jdbc:mysql://localhost:3306/bancoalimentos";
    //String password = "admin";
    String password = "javier";
    //String usuario = "root";
    String usuario = "javier";
    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public BaseDatos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    


    public void insertarProducto(Producto producto) {

        try {
            con = DriverManager.getConnection(url, usuario, password);
            File fileFoto = producto.getFotoProducto();
            FileInputStream fis = new FileInputStream(fileFoto);

            String sql = "INSERT INTO cat_productos(id_producto,nombre_prod,desc_prod,stock_prod,foto_prod,unidad_prod,"
                    + "precio_compra_prod,existencias_prod,id_categoria_prod,id_proveedor)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, producto.getIdProducto());
            pstm.setString(2, producto.getNombProducto());
            pstm.setString(3, producto.getDescProducto());
            pstm.setDouble(4, producto.getStockProducto());
            long tamanoFoto = fileFoto.length();
            pstm.setBinaryStream(5, fis, tamanoFoto);
            pstm.setString(6, producto.getUnidadProducto());
            pstm.setDouble(7, producto.getPrecioCompraProducto());
           // pstm.setDouble(8, producto.getPrecioVentaProducto());
            pstm.setDouble(8, producto.getExistenciaProducto());
            pstm.setInt(9, producto.getIdCategoria());
            pstm.setInt(10, producto.getIdProveedor());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    public void insertarNino(Nino nene) {

        try {
            con = DriverManager.getConnection(url, usuario, password);

            String sql = "INSERT INTO cat_nino(nombre_nino, apellidos_nino, edad_nino, email_nino , id_reyes)"
                    + " VALUES (?,?,?,?,?)";

            pstm = con.prepareStatement(sql);
            //pstm.setInt(1, nene.getId_nino());
            pstm.setString(1, nene.getNombre_nino());
            pstm.setString(2, nene.getApellidos_nino());
            pstm.setDouble(3, nene.getEdad_nino());
            pstm.setString(4, nene.getEmail_nino());
            pstm.setInt(5, nene.getId_rey());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public ArrayList<CategoriaProd> obtenerCategorias() {
        ArrayList<CategoriaProd> listaCategorias = new ArrayList<CategoriaProd>();

        try {
            con = DriverManager.getConnection(url, usuario, password);
            String sql = "SELECT * FROM cat_categorias";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_categoria_prod");
                String nombre = rs.getString("nombre_categoria_prod");
                String descripcion = rs.getString("desc_categoria_prod");

                CategoriaProd categoria = new CategoriaProd(id, nombre, descripcion);
                listaCategorias.add(categoria);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCategorias;
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> listaProveedor = new ArrayList<Proveedor>();

        try {
           con = DriverManager.getConnection(url, usuario, password);
            String sql = "SELECT * FROM cat_proveedores";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre_proveedor");
                String descripcion = rs.getString("dir_proveedor");
                String telefono = rs.getString("telef_proveedor");
                String email = rs.getString("email_proveedor");
                String contacto = rs.getString("contacto_proveedor");
                Proveedor proveedor = new Proveedor(id, nombre, descripcion, telefono, email, contacto);
                listaProveedor.add(proveedor);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaProveedor;
    }
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> listaProducto = new ArrayList<Producto>();
        try {
            
            con = DriverManager.getConnection(url, usuario, password);
            String sql = "SELECT * FROM cat_productos ORDER BY nombre_prod";
            
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id_producto");
                String nombre = rs.getString("nombre_prod");
                String descripcion = rs.getString("desc_prod");
                double stock= rs.getDouble("stock_prod");
                //long foto = rs.getBinaryStream("foto_prod");
                String unidad_prod = rs.getString("unidad_prod");
                double precio_compra = rs.getDouble("precio_compra_prod");
                //double precio_venta = rs.getDouble("precio_venta_prod");
                double existencias = rs.getDouble("existencias_prod");
                int categoria = rs.getInt("id_categoria_prod");
                int proveedor = rs.getInt("id_proveedor");
                Producto producto = new Producto(id, nombre, descripcion, stock,null, unidad_prod,precio_compra,existencias,categoria,proveedor);
                listaProducto.add(producto);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProducto;
    }
    
    public ArrayList<Reyes> obtenerReyes() {
        ArrayList<Reyes> listaReyes = new ArrayList<Reyes>();
        try {
            
            con = DriverManager.getConnection(url, usuario, password);
            String sql = "SELECT * FROM cat_rey ORDER BY nombre_rey";
            
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_rey");
                String nombre = rs.getString("nombre_rey");
                //long foto = rs.getBinaryStream("foto_rey");
                Reyes rey = new Reyes(id, nombre,null);
                listaReyes.add(rey);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaReyes;
    }

    public void insertarCategoriaProducto(CategoriaProd categoria) {
        try {
            con = DriverManager.getConnection(url, usuario, password);
            

            String sql = "INSERT INTO cat_categorias(nombre_categoria_prod,desc_categoria_prod)VALUES(?,?)";
                    

            pstm = con.prepareStatement(sql);
            
            pstm.setString(1, categoria.getNombreCategoriaProd());
            pstm.setString(2, categoria.getDescCategoriaProd());
            
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void insertarProveedor(Proveedor proveedor) {
        try {
            con = DriverManager.getConnection(url, usuario, password);
            

            String sql = "INSERT INTO cat_proveedores(nombre_proveedor,dir_proveedor,telef_proveedor,email_proveedor,contacto_proveedor)VALUES(?,?,?,?,?)";
                    

            pstm = con.prepareStatement(sql);
            
            pstm.setString(1, proveedor.getNom_proveedor());
            pstm.setString(2, proveedor.getDir_proveedor());
            pstm.setString(3, proveedor.getTelef_proveedor());
            pstm.setString(4,proveedor.getEmail_proveedor());
            pstm.setString(5,proveedor.getContacto_proveedor() );
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
       
    public int insertarVenta ( Venta venta ){
        int lastValue = 0;
        try {
            
            con = DriverManager.getConnection(url, usuario, password);
            

            String sql = "INSERT INTO ventas(cantidad_venta,fecha_venta)VALUES(?,?)";
                    

            pstm = con.prepareStatement(sql);
            
            pstm.setDouble(1, venta.getCantidadVenta());
            pstm.setDate(2, (Date) venta.getFechaVenta());
          
            pstm.executeUpdate();
            
            pstm.close();
            
            pstm = con.prepareStatement("select last_insert_id()");
            rs = pstm.executeQuery();
            
            while(rs.next()){
                lastValue = rs.getInt("last_insert_id()");
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    return lastValue;
    }
    
        public int insertarPedido ( Pedido pedido ){
        int lastValue = 0;
        try {
            
            con = DriverManager.getConnection(url, usuario, password);
            

            String sql = "INSERT INTO cat_pedido(id_nino_pedido,fecha)VALUES(?,?)";
                    

            pstm = con.prepareStatement(sql);
            
            pstm.setDouble(1, pedido.getIdNino());
            pstm.setDate(2, (Date) pedido.getFechaPedido());
          
            pstm.executeUpdate();
            
            pstm.close();
            
            pstm = con.prepareStatement("select last_insert_id()");
            rs = pstm.executeQuery();
            
            while(rs.next()){
                lastValue = rs.getInt("last_insert_id()");
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    return lastValue;
    }
    
    /* public void insertarDetalleVenta ( DetalleVenta detalle ){
        
        try {
            
            con = DriverManager.getConnection(url, usuario, password);
            

            String sql = "INSERT INTO detalle_venta(id_venta,id_prod,cantidad_vendida)VALUES(?,?,?)";
                    

            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1,detalle.getIdVenta());
            pstm.setString(2,detalle.getIdProducto());
            pstm.setDouble(3, detalle.getCantidadVendida());
          
            pstm.executeUpdate();
            
                     
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
   
    }*/
     
    public void insertarDetalleVenta(DetalleVenta detalle) {

        try {

            con = DriverManager.getConnection(url, usuario, password);

            String sql = "INSERT INTO cat_lineapedido(pedido_fecha_linpedido, prod_nomb_linpedido, cantidad_linpedido)VALUES(?,?,?)";

            pstm = con.prepareStatement(sql);

            pstm.setInt(1, detalle.getIdVenta());
            pstm.setString(2, detalle.getIdProducto());
            pstm.setDouble(3, detalle.getCantidadVendida());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


    public void actualizarInventario(Producto producto, double cantidad) {
        try {
            con = DriverManager.getConnection(url, usuario, password);

            String sql = "UPDATE cat_productos SET existencias_prod=? WHERE id_prod=?";

            pstm = con.prepareStatement(sql);

            pstm.setDouble(1, cantidad);
            pstm.setString(2, producto.getIdProducto());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public ArrayList<Producto> obtenerProductosPorCriterio(String criterio) {

        ArrayList<Producto> listaProducto = new ArrayList<Producto>();
        try {

            con = DriverManager.getConnection(url, usuario, password);
            String sql = "SELECT * FROM cat_productos"
                    + " WHERE id_producto LIKE '" + criterio + "%'"
                    + "OR nombre_prod LIKE '%" + criterio + "%'"
                    + "ORDER BY nombre_prod";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_producto");
                String nombre = rs.getString("nombre_prod");
                String descripcion = rs.getString("desc_prod");
                double stock = rs.getDouble("stock_prod");
                String unidad = rs.getString("unidad_prod");
                double precioCompra = rs.getDouble("precio_compra_prod");
                //double precioVenta = rs.getDouble("precio_venta_prod");
                double existencias = rs.getDouble("existencias_prod");
                int idCategoria = rs.getInt("id_categoria_prod");
                int idProveedor = rs.getInt("id_proveedor");

                Producto producto = new Producto(id, nombre, descripcion,
                        stock, null, unidad,
                        precioCompra, existencias, idCategoria, idProveedor);
                listaProducto.add(producto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProducto;
    }
    
        public ArrayList<Nino> obtenerNinoPorCriterio(String criterio) {

        ArrayList<Nino> listaNino = new ArrayList<Nino>();
        try {

            con = DriverManager.getConnection(url, usuario, password);
            String sql = "SELECT * FROM cat_nino"
                    + " WHERE nombre_nino LIKE '" + criterio + "%'"
                    + "OR apellidos_nino LIKE '%" + criterio + "%'"
                    + "ORDER BY nombre_nino";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_nino");
                String nombre_nino = rs.getString("nombre_nino");
                String apellidos_nino = rs.getString("apellidos_nino");
                int edad_nino = rs.getInt("edad_nino");
                String email_nino = rs.getString("email_nino");
                int id_reyes = rs.getInt("id_reyes");

                Nino nene = new Nino(id, nombre_nino, apellidos_nino, edad_nino, email_nino, id_reyes);
                listaNino.add(nene);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaNino;
    }

    public void borrarProducto(Producto p) {
        try {
            con = DriverManager.getConnection(url, usuario, password);

            String sql = "DELETE FROM cat_productos WHERE id_producto=?";

            pstm = con.prepareStatement(sql);

            pstm.setString(1, p.getIdProducto());

            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
/*Estemetodo devuelve el stream de bytes de la fotografía de un producto guardada en la base de datos 
    ²param producto es el objeto Producto del cual se necesita obtener la fotografía. 
    */
    public InputStream buscarFoto(Producto producto) {
        InputStream streamFoto= null;
        try {
            
            
            con = DriverManager.getConnection(url, usuario, password);
            
            String sql=" SELECT foto_prod FROM cat_productos WHERE id_producto= ?";
            
            pstm = con.prepareStatement(sql);
            pstm.setString(1, producto.getIdProducto());
            
            rs=pstm.executeQuery();
            while(rs.next()){
                streamFoto=rs.getBinaryStream("foto_prod");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return streamFoto;
        

    }
    
        public InputStream buscarFotoRey(Reyes rey) {
        InputStream streamFoto= null;
        try {
            
            
            con = DriverManager.getConnection(url, usuario, password);
            
            String sql=" SELECT foto_rey FROM cat_rey WHERE id_rey= ?";
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, rey.getId_rey());
            
            rs=pstm.executeQuery();
            while(rs.next()){
                streamFoto=rs.getBinaryStream("foto_rey");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return streamFoto;
        

    }

    public void actualizarProducto(Producto producto, boolean cambiarFoto) {
        try {
            
            
            con = DriverManager.getConnection(url, usuario, password);
            
            if (cambiarFoto==true) {
                File fileFoto = producto.getFotoProducto();
                FileInputStream fis =new FileInputStream(fileFoto);
                
                String sql="UPDATE cat_productos SET desc_prod=? ,stock_prod =?, foto_prod=? ,unidad_prod=? ,"
                        + "precio_compra_prod=?,precio_venta_prod=?,id_categoria_prod=?,id_proveedor=?"
                        + "WHERE id_producto=? ";
                pstm = con.prepareStatement(sql);
                pstm.setString(1,producto.getDescProducto());
                pstm.setDouble(2,producto.getStockProducto());
                long tamanoFoto=fileFoto.length();
                pstm.setBinaryStream(3, fis,tamanoFoto);
                pstm.setString(4,producto.getUnidadProducto());
                pstm.setDouble(5,producto.getPrecioCompraProducto());
                //pstm.setDouble(6,producto.getPrecioVentaProducto());
                pstm.setInt(7,producto.getIdCategoria());
                pstm.setInt(8,producto.getIdProveedor());
                pstm.setString(9,producto.getIdProducto());
            }
            else{
            String sql="UPDATE cat_productos SET desc_prod=? ,stock_prod =?,unidad_prod=? ,"
                        + "precio_compra_prod=?,precio_venta_prod=?,id_categoria_prod=?,id_proveedor=? "
                        + " WHERE id_producto=?";
            
            pstm = con.prepareStatement(sql);
                pstm.setString(1,producto.getDescProducto());
                pstm.setDouble(2,producto.getStockProducto());
                pstm.setString(3,producto.getUnidadProducto());
                pstm.setDouble(4,producto.getPrecioCompraProducto());
                //pstm.setDouble(5,producto.getPrecioVentaProducto());
                pstm.setInt(6,producto.getIdCategoria());
                pstm.setInt(7,producto.getIdProveedor());
                pstm.setString(8,producto.getIdProducto());
            }
            pstm.executeUpdate();
            } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    public ArrayList<ResulNinoRey> obtenerNinoResultado() {

        ArrayList<ResulNinoRey> listaResultado = new ArrayList<ResulNinoRey>();
        try {

            con = DriverManager.getConnection(url, usuario, password);
            String sql = "SELECT * FROM pedidoninorey ORDER BY IdentPedido DESC";

            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int idpedido = rs.getInt("IdentPedido");
                String nombreNino = rs.getString("nombre_nino");
                String nombreRey = rs.getString("nombre_rey");
                String emailNino = rs.getString("email_nino");
                ResulNinoRey result = new ResulNinoRey(idpedido, nombreNino, nombreRey,emailNino);
                listaResultado.add(result);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaResultado;
    } 
    
}
