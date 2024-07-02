package Modelos;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdenDAO {
    
    ConexionBD cn=new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<OrdenPedido> obtenerOrdenes() {
    List<OrdenPedido>ordenes = new ArrayList<>();
        try {
            String sql = "SELECT o.NroSerie AS NSerie, e.nombre AS NombreEmpleado, p.nombre AS NombreProveedor, prod.nombre AS NombreProducto, o.fecha AS Fecha, SUM(dop.cantidad) AS CantidadTotal FROM ordenpedido o INNER JOIN empleado e ON o.id_empleado = e.id_empleado INNER JOIN proveedor p ON o.id_proveedor = p.id_proveedor INNER JOIN detalle_ordenpedido dop ON o.id_orden = dop.id_ordenpedido INNER JOIN producto prod ON dop.id_producto = prod.id_producto GROUP BY o.NroSerie, e.nombre, p.nombre, prod.nombre, o.fecha ORDER BY o.NroSerie ASC";
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdenPedido orden = new OrdenPedido();
                orden.setNSerie(rs.getString("NSerie"));
                orden.setNombreEmpleado(rs.getString("NombreEmpleado"));
                orden.setNombreProveedor(rs.getString("NombreProveedor"));
                orden.setNombreProducto(rs.getString("NombreProducto"));
                orden.setFecha(rs.getDate("Fecha"));
                orden.setCantidadTotal(rs.getInt("CantidadTotal"));
                ordenes.add(orden);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordenes;
    }
}
