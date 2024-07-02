package Modelos;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdenCompraDAO {
    
    ConexionBD cn=new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public String GenerarSerie(){
        String numeroserie="";
        String sql="select max(NroSerie) from ordenpedido";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                numeroserie=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return numeroserie;
    }
    
    public String idOrden(){
        String idorden="";
        String sql="select max(id_orden) from ordenpedido";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idorden=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idorden  ;
    }
    
    public int guardarOrden(OrdenCompra orc){
        String sql="insert into ordenpedido(id_empleado,id_proveedor,NroSerie,fecha) values(?,?,?,?)";
        
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, orc.getId_empleado());
            ps.setInt(2, orc.getId_proveedor());
            ps.setString(3, orc.getNroSerie());
            ps.setString(4, orc.getFecha());
            r=ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    
    public int guardarDetalleOrden(OrdenCompra orden){
        
        String sql="insert into detalle_ordenpedido(id_ordenpedido,id_producto,cantidad) values (?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, orden.getId_orden());
            ps.setInt(2, orden.getId_producto());
            ps.setInt(3, orden.getCantidad());
            r=ps.executeUpdate();
            
        } catch (Exception e) {
        }
        return r;
    }
    
}
