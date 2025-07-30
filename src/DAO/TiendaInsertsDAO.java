package DAO;

import Entidades.Cliente;
import Entidades.DetalleVenta;
import Entidades.Venta;
import Entidades.Videojuego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TiendaInsertsDAO {
    private final static String URL = "jdbc:mariadb://localhost:3306/Tienda";
    private final static String USER = "dani";
    private final static String PASSWD = "8232";

    public void insertarVenta(Venta venta) {
        String insertarVenta = "INSERT INTO Ventas(idVenta, fecha, codCliente) VALUES (?, ?, ?)";
        String insertarDetalleVenta = "INSERT INTO DetalleVenta(codVenta, codVideojuego, cantidad) VALUES (?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD)) {
            conexion.setAutoCommit(false);

            try (PreparedStatement psVenta = conexion.prepareStatement(insertarVenta);
                 PreparedStatement psDetalle = conexion.prepareStatement(insertarDetalleVenta)) {

                psVenta.setLong(1, venta.getIdVenta());
                psVenta.setDate(2, java.sql.Date.valueOf(venta.getFechaVenta()));
                psVenta.setLong(3, venta.getCliente().getCodigoDeCliente());
                psVenta.executeUpdate();

                for (Videojuego vj : venta.getVideojuegos()) {
                    psDetalle.setLong(1, venta.getIdVenta());
                    psDetalle.setLong(2, vj.getCodigoIdentificativo());
                    psDetalle.setInt(3, vj.getCantidad());
                    psDetalle.executeUpdate();
                }

                conexion.commit();
                System.out.println("Venta insertada correctamente.");

            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("Error en inserción de venta. Rollback ejecutado: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error en conexión o transacción: " + e.getMessage());
        }
    }

    public void insertarVideojuego(Videojuego videojuego) {
        String insertarVideojuego = "INSERT INTO Videojuegos (idVideojuego,titulo,plataforma,genero,precio,stock) " +
                " VALUES(?,?,?,?,?,?) ";
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWD);
            conexion.setAutoCommit(false);
            System.out.println("Conexión exitosa a la base de datos");

            PreparedStatement psVideojuego = conexion.prepareStatement(insertarVideojuego);
            psVideojuego.setLong(1, videojuego.getCodigoIdentificativo());
            psVideojuego.setString(2, videojuego.getTitulo());
            psVideojuego.setString(3, videojuego.getPlataforma());
            psVideojuego.setString(4, videojuego.getGenero());
            psVideojuego.setDouble(5, videojuego.getPrecio());
            psVideojuego.setInt(6, videojuego.getCantidad());
            int filasAfectadas = psVideojuego.executeUpdate();
            System.out.println("Filas afectadas" + filasAfectadas);

            conexion.commit();
            System.out.println("Filas insertadas correctamente");
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al insertar el videojuego " + e.getMessage());
            try {
                assert conexion != null;
                conexion.rollback();
                conexion.close();
            } catch (SQLException rollBackException) {
                System.out.println("Error al realizar el rollBack del Videojuego");
            }

        }
    }

    public void insertarCliente(Cliente cliente) {
        String insertarCliente = "INSERT INTO Clientes(idCliente, nombre, mail) " +
                " VALUES( ?,?,? ) ";

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWD);
            conexion.setAutoCommit(false);
            PreparedStatement psCliente = conexion.prepareStatement(insertarCliente);

            psCliente.setLong(1, cliente.getCodigoDeCliente());
            psCliente.setString(2, cliente.getNombre());
            psCliente.setString(3, cliente.getEmail());
            int filasAfectadas = psCliente.executeUpdate();
            System.out.println("Filas afectadas" + filasAfectadas);

            conexion.commit();
            System.out.println("Se ha guardado correctamente");
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el cliente" + e.getMessage());
            try {
                assert conexion != null;
                conexion.rollback();
                conexion.close();
            } catch (SQLException RollBackException) {
                System.out.println("Error en el rollback de Cliente");
            }
        }

    }
}
