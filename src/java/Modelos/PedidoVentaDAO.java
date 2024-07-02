package Modelos;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoVentaDAO {
    
    ConexionBD cn=new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
 
    public int GenerarVenta(PedidoVenta venta) {
        int idventas;
        String sql = "Insert into pedidoventa(id_cliente, id_pago, fecha, total, estado) values(?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getIdcliente());
            ps.setInt(2, venta.getIdpago());
            ps.setString(3, venta.getFecha());
            ps.setDouble(4, venta.getMonto());
            ps.setString(5, venta.getEstado());
            r = ps.executeUpdate();
            
            sql = "select @@IDENTITY AS id_pedido";
            rs = ps.executeQuery(sql);
            rs.next();
            idventas = rs.getInt("id_pedido");
            rs.close();

            for (Carrito detalle : venta.getDetallecompras()) {
                sql = "insert into detalle_pedidoventa(id_producto, id_pedidoventa, cantidad, preciocompra) values(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, detalle.getIdProducto());
                ps.setInt(2, idventas);
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecioCompra());
                r = ps.executeUpdate();
            }
        } catch (Exception e) {
        }
        return r;
    }

    public String idPago() {
        String idpago = "";
        String sql = "select max(id_pago) from pago";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idpago = rs.getString(1);
                System.out.println("ID de pago obtenido: " + idpago);
            }
        } catch (Exception e) {
        } finally {
        }
        return idpago;
    }
    
    
    public int PagarCarrito(Pago pag){
    String sql = "insert into pago(monto) values(?)";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setDouble(1, pag.getMonto());
            r = ps.executeUpdate();
        }catch (SQLException e){  
        }
        return r;
    }
}

