package Modelos;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Modelos.Producto;
import java.sql.SQLException;

public class ProductoDAO {
    
    ConexionBD cn=new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    
    
    public Producto buscar(int id){
        Producto p= new Producto();
        String sql="select * from producto where id_producto="+id;
            try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                p.setId_producto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return p;
    }
    
    public int actualizarStock(int id, int stock){
        String sql="update producto set stock=? where id_producto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            r=ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    
    public List listar(){
        String sql="select * from producto";
        List<Producto>lista=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto p=new Producto();
                p.setId_producto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                lista.add(p);
            }
        }catch (Exception e){       
        }
        return lista;
    }
    
    
    public int agregar(Producto p){
    String sql = "insert into producto (nombre,foto,descripcion,precio,stock) values(?,?,?,?,?)";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getFoto());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            r = ps.executeUpdate();
            System.out.println("Filas afectadas al agregar: " + r);
        }catch (SQLException e){  
            e.printStackTrace();
            System.out.println("Error al ejecutar la consulta SQL: " + e.getMessage());
        }
        return r;
    }
    
    
    public int actualizar(Producto p){
        String sql="update producto set nombre=?, foto=?, descripcion=?,precio=?,stock=? where id_producto=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getFoto());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getId_producto());
            r=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return r;
    }
    
    public Producto listarId(int idp){
        Producto pro= new Producto();
        String sql="select * from producto where id_producto="+idp;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                pro.setId_producto(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setFoto(rs.getString(3));
                pro.setDescripcion(rs.getString(4));
                pro.setPrecio(rs.getDouble(5));
                pro.setStock(rs.getInt(6));
                System.out.println("Filas actualizadas al listarid: " + idp);
            }
        }catch (SQLException e){
            System.out.println("Error al ejecutar la consulta SQL eliminar: " + e.getMessage());
        }
        return pro;
    }
    
    public void eliminar(int idp){
        String sql="delete from producto where id_producto="+idp;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Filas eliminar al agregar: " + r);
        }catch (Exception e){
            System.out.println("Error al ejecutar la consulta SQL eliminar: " + e.getMessage());
        }
    }
    
    public Producto buscarProductoOC(String nomproducto) {
        Producto productoo = new Producto();
        String sql = "SELECT * FROM producto WHERE nombre = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nomproducto);
            rs = ps.executeQuery();
            while (rs.next()) {
                productoo.setId_producto(rs.getInt("id_producto"));
                productoo.setNombre(rs.getString("nombre"));
                productoo.setStock(rs.getInt("stock"));
                productoo.setDescripcion(rs.getString("descripcion"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productoo;
    }
    
    public List buscarProducto(String texto){
        List<Producto> lista = new ArrayList<>();
        String sql="select * from producto where nombre like '%"+texto+"%'";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto pro = new Producto();
                pro.setId_producto(rs.getInt("id_producto"));
                pro.setNombre(rs.getString("nombre"));
                pro.setFoto(rs.getString("foto"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio(rs.getDouble("precio"));
                lista.add(pro);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public List<String> listarNombreProductos() {
        List<String> nombreProductos = new ArrayList<>();
        String sql = "SELECT nombre FROM producto";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombreProductos.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
        }
        
        return nombreProductos;
    }
}


    