package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class TiendaSelectsEstadisticaDAO {
    private final static String URL = "jdbc:mariadb://localhost:3306/Tienda";
    private final static String USER = "dani";
    private final static String PASSWD = "8232";

    public String tituloMasVendido() {
        String sql = """
                    SELECT v.titulo
                    FROM Videojuegos v
                    JOIN DetalleVenta de ON v.idVideojuego = de.codVideojuego
                    JOIN Ventas ve ON ve.idVenta = de.codVenta
                    GROUP BY v.idVideojuego
                    HAVING SUM(de.cantidad) = (
                        SELECT MAX(total)
                        FROM (
                            SELECT SUM(de2.cantidad) total
                            FROM DetalleVenta de2
                            JOIN Ventas ve2 ON ve2.idVenta = de2.codVenta
                            GROUP BY de2.codVideojuego
                        ) AS subConsulta
                    )
                """;
        String titulo = "";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                titulo = rs.getString("titulo");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el título más vendido: " + e.getMessage());
        }

        return titulo;
    }

    public LocalDate mejorDiaVentas() {
        String sql = """
                    SELECT v.fecha
                    FROM DetalleVenta de
                    JOIN Ventas v ON v.idVenta = de.codVenta
                    GROUP BY v.fecha
                    HAVING SUM(de.cantidad) = (
                        SELECT MAX(total)
                        FROM (
                            SELECT SUM(de2.cantidad) total
                            FROM DetalleVenta de2
                            JOIN Ventas v2 ON v2.idVenta = de2.codVenta
                            GROUP BY v2.fecha
                        ) AS subConsulta
                    )
                """;
        LocalDate localDate = null;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                localDate = rs.getDate("fecha").toLocalDate();
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el mejor día de ventas: " + e.getMessage());
        }

        return localDate;
    }

    public HashMap<String, Integer> acumuladoDeVentasPorGenero() {
        String acumuladoVentasGenero = """
                select sum(de.cantidad) ventas, v.genero
                from
                    DetalleVenta de
                    join Ventas ve on de.codVenta = ve.idVenta
                    join Videojuegos v on de.codVideojuego = v.idVideojuego
                group by
                    v.genero
                """;
        HashMap<String, Integer> ventasGenero = new HashMap<>();

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement psAcumuladoVentasGenero = conexion.prepareStatement(acumuladoVentasGenero);
             ResultSet rs = psAcumuladoVentasGenero.executeQuery()) {

            while (rs.next()) {
                String genero = rs.getString("genero");
                int ventas = rs.getInt("ventas");
                ventasGenero.put(genero, ventas);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las ventas por género: " + e.getMessage());
        }
        return ventasGenero;
    }

    public HashMap<String, Integer> acumuladoDeVentasPorPlataforma() {
        String acumuladoVentasPlataforma = """
                select sum(de.cantidad) ventas, v.plataforma
                from
                    DetalleVenta de
                    join Ventas ve on de.codVenta = ve.idVenta
                    join Videojuegos v on de.codVideojuego = v.idVideojuego
                group by
                    v.genero
                """;
        HashMap<String, Integer> ventasPlataforma = new HashMap<>();

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement psAcumuladoVentasPlataforma = conexion.prepareStatement(acumuladoVentasPlataforma);
             ResultSet rs = psAcumuladoVentasPlataforma.executeQuery()) {

            while (rs.next()) {
                String genero = rs.getString("plataforma");
                int ventas = rs.getInt("ventas");
                ventasPlataforma.put(genero, ventas);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las ventas por plataforma: " + e.getMessage());
        }
        return ventasPlataforma;
    }

}
