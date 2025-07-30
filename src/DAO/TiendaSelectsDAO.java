package DAO;

import Entidades.Cliente;
import Entidades.Videojuego;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TiendaSelectsDAO {
    private final static String URL = "jdbc:mariadb://localhost:3306/Tienda";
    private final static String USER = "dani";
    private final static String PASSWD = "8232";

    public int mostrarStockTitulo(String titulo) {
        String buscarPorId = """
                select v.stock
                from Videojuegos v
                where lower(v.titulo) like ?
                """;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(buscarPorId)) {
            ps.setString(1, "%" + titulo.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("stock");
                return stock;
            }

        } catch (SQLException e) {
            System.out.println("Error al mostrar el stock: " + e.getMessage());
        }
        return 0;
    }

    public Videojuego buscarPorTitulo(String titulo) {
        String buscarPorId = """
                select *
                from Videojuegos v
                where lower(v.titulo) like ?
                """;

        Videojuego videojuego = null;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(buscarPorId)) {
            ps.setString(1, "%" + titulo.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                videojuego = new Videojuego(
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getString("genero"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("idVideojuego")
                );
                return videojuego;
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el videojuego: " + e.getMessage());
        }
        return videojuego;
    }

    public Videojuego buscarPorId(int idVideojuego) {
        String buscarPorId = """
                select *
                from Videojuegos v
                where v.idVideojuego = ?
                """;

        Videojuego videojuego = null;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(buscarPorId)) {
            ps.setInt(1, idVideojuego);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                videojuego = new Videojuego(
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getString("genero"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("idVideojuego")
                );
                return videojuego;
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el videojuego: " + e.getMessage());
        }
        return videojuego;
    }

    public Cliente buscarPorNombre(String nombre) {
        String buscarPorNombre = """
                select *
                from Clientes c
                where lower(c.nombre) like ?
                """;

        Cliente cliente = null;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(buscarPorNombre)) {
            ps.setString(1, "%" + nombre.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("mail"),
                        rs.getLong("idCliente")
                );
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el inventario: " + e.getMessage());
        }
        return cliente;
    }


    public Cliente buscarPorApellido(String apellido) {
        String buscarPorApellido = """
                select *
                from Clientes c
                where lower(c.apellidos) like ?
                """;

        Cliente cliente = null;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(buscarPorApellido)) {
            ps.setString(1, "%" + apellido.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("mail"),
                        rs.getLong("idCliente")
                );
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el cliente: " + e.getMessage());
        }
        return cliente;
    }


    public Cliente buscarPorMail(String email) {
        String buscarPorMail = """
                select *
                from Clientes c
                where lower(c.mail) like ?
                """;

        Cliente cliente = null;

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(buscarPorMail)) {
            ps.setString(1, "%" + email.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("mail"),
                        rs.getLong("idCliente")
                );
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el cliente: " + e.getMessage());
        }
        return cliente;
    }

    public ArrayList<Videojuego> mostrarInventario() {
        String mostrarInventario = "SELECT * FROM Videojuegos";
        ArrayList<Videojuego> inventario = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement psInventario = conexion.prepareStatement(mostrarInventario);
             ResultSet rs = psInventario.executeQuery()) {

            while (rs.next()) {
                Videojuego videojuego = new Videojuego(
                        rs.getString("titulo"),
                        rs.getString("plataforma"),
                        rs.getString("genero"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("idVideojuego")
                );
                inventario.add(videojuego);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el inventario: " + e.getMessage());
        }

        return inventario;
    }

    public ArrayList<Object[]> obtenerHistorialVentas() {
        String mostrarHistorialVentas = """
                    SELECT ve.fecha, c.nombre, de.cantidad, v.titulo
                    FROM
                        DetalleVenta de
                        JOIN Ventas ve ON de.codVenta = ve.idVenta
                        JOIN Clientes c ON c.idCliente = ve.codCliente
                        JOIN Videojuegos v ON v.idVideojuego = de.codVideojuego
                """;

        ArrayList<Object[]> historial = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(mostrarHistorialVentas);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getString("titulo")
                };
                historial.add(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener historial de ventas: " + e.getMessage());
        }

        return historial;
    }

    public ArrayList<Object[]> obtenerHistorialVentasDeUnaFecha(LocalDate fecha) {
        String mostrarHistorialVentas = """
                    SELECT ve.fecha, c.nombre, de.cantidad, v.titulo
                    FROM
                        DetalleVenta de
                        JOIN Ventas ve ON de.codVenta = ve.idVenta
                        JOIN Clientes c ON c.idCliente = ve.codCliente
                        JOIN Videojuegos v ON v.idVideojuego = de.codVideojuego
                    WHERE ve.fecha = ?
                """;

        ArrayList<Object[]> historial = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWD);
             PreparedStatement ps = conexion.prepareStatement(mostrarHistorialVentas)) {

            ps.setDate(1, java.sql.Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = {
                            rs.getDate("fecha").toLocalDate(),
                            rs.getString("nombre"),
                            rs.getInt("cantidad"),
                            rs.getString("titulo")
                    };
                    historial.add(fila);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener historial de ventas: " + e.getMessage());
        }

        return historial;
    }


}

