package Modelos;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    
    ConexionBD cn=new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            String sql = "SELECT pv.id_pedido AS idPedido, c.nombre AS nombreCliente, c.correo AS correo, "
                    + "GROUP_CONCAT(CONCAT(pr.nombre, ' x ', dpv.cantidad) SEPARATOR ' - ') AS nombreProductos, "
                    + "pv.fecha AS fecha, pg.monto AS monto, pv.estado AS estado "
                    + "FROM pedidoventa pv "
                    + "INNER JOIN cliente c ON pv.id_cliente = c.id_cliente "
                    + "INNER JOIN pago pg ON pv.id_pago = pg.id_pago "
                    + "INNER JOIN detalle_pedidoventa dpv ON pv.id_pedido = dpv.id_pedidoventa "
                    + "INNER JOIN producto pr ON dpv.id_producto = pr.id_producto "
                    + "GROUP BY pv.id_pedido, c.nombre, c.correo, pv.fecha, pg.monto, pv.estado";
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setNombreCliente(rs.getString("nombreCliente"));
                pedido.setCorreo(rs.getString("correo"));
                pedido.setNombreProductos(rs.getString("nombreProductos"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setMonto(rs.getDouble("monto"));
                pedido.setEstado(rs.getString("estado"));
                pedidos.add(pedido);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedidos;
    }
    
    public List<Pedido> obtenerPedidosPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            String sql = "SELECT pv.id_pedido AS idPedido, c.nombre AS nombreCliente, c.correo AS correo, "
                    + "GROUP_CONCAT(CONCAT(pr.nombre, ' x ', dpv.cantidad) SEPARATOR ' - ') AS nombreProductos, "
                    + "pv.fecha AS fecha, pg.monto AS monto, pv.estado AS estado "
                    + "FROM pedidoventa pv "
                    + "INNER JOIN cliente c ON pv.id_cliente = c.id_cliente "
                    + "INNER JOIN pago pg ON pv.id_pago = pg.id_pago "
                    + "INNER JOIN detalle_pedidoventa dpv ON pv.id_pedido = dpv.id_pedidoventa "
                    + "INNER JOIN producto pr ON dpv.id_producto = pr.id_producto "
                    + "WHERE pv.id_cliente = ? "
                    + "GROUP BY pv.id_pedido, c.nombre, c.correo, pv.fecha, pg.monto, pv.estado";
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setNombreCliente(rs.getString("nombreCliente"));
                pedido.setCorreo(rs.getString("correo"));
                pedido.setNombreProductos(rs.getString("nombreProductos"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setMonto(rs.getDouble("monto"));
                pedido.setEstado(rs.getString("estado"));
                pedidos.add(pedido);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedidos;
    }

}
