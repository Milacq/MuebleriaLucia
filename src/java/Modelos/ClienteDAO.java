package Modelos;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    ConexionBD cn=new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Cliente validar(String email,String passcliente){
        Cliente client=new Cliente();
        String sql="select * from cliente where correo=? and password=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,passcliente);
            rs=ps.executeQuery();
            while(rs.next()){
                client.setId_cliente(rs.getInt("id_cliente"));
                client.setDni(rs.getInt("dni"));
                client.setNombre(rs.getString("nombre"));
                client.setDirección(rs.getString("dirección"));
                client.setCorreo(rs.getString("correo"));
                client.setPassword(rs.getString("password"));
            }
        }catch(Exception e){
            
        }
        return client;
    }
    
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cl=new Cliente();
                cl.setId_cliente(rs.getInt(1));
                cl.setDni(rs.getInt(2));
                cl.setNombre(rs.getString(3));
                cl.setDirección(rs.getString(4));
                cl.setCorreo(rs.getString(5));
                cl.setPassword(rs.getString(6));
                lista.add(cl);
            }
        }catch (Exception e){       
        }
        return lista;
    }
    
    public int agregar(Cliente cl){
    String sql = "insert into cliente(dni,nombre,dirección,correo,password) values(?,?,?,?,?)";
        try{
            con=cn.Conexion();
            System.out.println("Conexión establecida correctamente.");
            ps=con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getDirección());
            ps.setString(4, cl.getCorreo());
            ps.setString(5, cl.getPassword());
            r = ps.executeUpdate(); // Esta es la línea que está causando el error
            System.out.println("Filas afectadas al agregar: " + r);
        }catch (SQLException e){  
            e.printStackTrace();
        }
        return r;
    }
    
    public int actualizar(Cliente cl){
        String sql="update cliente set dni=?, nombre=?, dirección=?,correo=?,password=? where id_cliente=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getDirección());
            ps.setString(4, cl.getCorreo());
            ps.setString(5, cl.getPassword());
            ps.setInt(6, cl.getId_cliente());
            r=ps.executeUpdate();
        }catch (SQLException e){
        }
        return r;
    }
    
    public Cliente listarId(int idcl){
        Cliente cli= new Cliente();
        String sql="select * from cliente where id_cliente="+idcl;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                cli.setId_cliente(rs.getInt(1));
                cli.setDni(rs.getInt(2));
                cli.setNombre(rs.getString(3));
                cli.setDirección(rs.getString(4));
                cli.setCorreo(rs.getString(5));
                cli.setPassword(rs.getString(6));
            }
        }catch (SQLException e){
            
        }
        return cli;
    }
    
    public void eliminar(int idcl){
        String sql="delete from cliente where id_cliente="+idcl;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
        }
    }
    
}
