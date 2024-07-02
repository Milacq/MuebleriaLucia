package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelos.Empleado;
import Conexion.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    ConexionBD cn=new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Empleado validar(String user,String contraseña){
        Empleado em=new Empleado();
        String sql="select * from empleado where usuario=? and contraseña=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2,contraseña);
            rs=ps.executeQuery();
            while(rs.next()){
                em.setId_empleado(rs.getInt("id_empleado"));
                em.setNombre(rs.getString("nombre"));
                em.setUsuario(rs.getString("usuario"));
                em.setContraseña(rs.getString("contraseña"));
                em.setRol(rs.getString("rol"));
            }
        }catch(Exception e){
            
        }
        return em;
    }
    
    //CRUD
    public List listar(){
        String sql="select * from empleado";
        List<Empleado>lista=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Empleado em=new Empleado();
                em.setId_empleado(rs.getInt(1));
                em.setNombre(rs.getString(2));
                em.setUsuario(rs.getString(3));
                em.setContraseña(rs.getString(4));
                em.setRol(rs.getString(5));
                lista.add(em);
            }
        }catch (Exception e){       
        }
        return lista;
    }
    
    public int agregar(Empleado em){
    String sql = "insert into empleado(nombre,usuario,contraseña,rol) values(?,?,?,?)";
        try{
            con=cn.Conexion();
            System.out.println("Conexión establecida correctamente.");
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getNombre());
            ps.setString(2, em.getUsuario());
            ps.setString(3, em.getContraseña());
            ps.setString(4, em.getRol());
            r = ps.executeUpdate(); // Esta es la línea que está causando el error
            System.out.println("Filas afectadas al agregar: " + r);
        }catch (SQLException e){  
            e.printStackTrace();
            System.out.println("Error al ejecutar la consulta SQL: " + e.getMessage());
        }
        return r;
    }
    
    public int actualizar(Empleado em){
        String sql="update empleado set nombre=?, usuario=?, contraseña=?,rol=? where id_empleado=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getNombre());
            ps.setString(2, em.getUsuario());
            ps.setString(3, em.getContraseña());
            ps.setString(4, em.getRol());
            ps.setInt(5, em.getId_empleado());
            r=ps.executeUpdate();
        }catch (SQLException e){
        }
        return r;
    }
    
    public Empleado listarId(int id){
        Empleado emp= new Empleado();
        String sql="select * from empleado where id_empleado="+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                emp.setId_empleado(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setUsuario(rs.getString(3));
                emp.setContraseña(rs.getString(4));
                emp.setRol(rs.getString(5));
                System.out.println("Filas actualizadas al listarid: " + id);
            }
        }catch (SQLException e){
            
        }
        return emp;
    }
    
    public void eliminar(int id){
        String sql="delete from empleado where id_empleado="+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
        }
    }
}
