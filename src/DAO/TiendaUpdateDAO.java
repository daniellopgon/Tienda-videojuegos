package DAO;

import java.sql.*;

public class TiendaUpdateDAO {
    private final String URL = "jdbc:mariadb://localhost:3306/Tienda";
    private final String USER = "dani";
    private final String PASSWD = "8232";

    public void actualizarStock(int stock, Long idVideojuego) {
        String actualizarStock = """
                update Videojuegos
                set stock = stock - ?
                where idVideojuego = ?
                """;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD)) {

            PreparedStatement ps = conexion.prepareStatement(actualizarStock);

            ps.setInt(1, stock);
            ps.setLong(2, idVideojuego);

            int filasAfectadasVenta = ps.executeUpdate();

            System.out.println("Filas afectadas en venta: " + filasAfectadasVenta);

        } catch (SQLException e) {
            System.out.println("Error al actualizar el stock" + e.getMessage());
        }

    }
}
