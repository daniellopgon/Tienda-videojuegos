import DAO.TiendaInsertsDAO;
import DAO.TiendaSelectsDAO;
import DAO.TiendaSelectsEstadisticaDAO;
import DAO.TiendaUpdateDAO;
import Entidades.Venta;
import Entidades.Videojuego;

import java.time.LocalDate;
import java.util.Scanner;

public class TiendaUI {
    public static void main(String[] args) {
        TiendaInsertsDAO tiendaAñadir = new TiendaInsertsDAO();
        TiendaSelectsDAO tiendaMostrar = new TiendaSelectsDAO();
        TiendaSelectsEstadisticaDAO tiendaEstadistica = new TiendaSelectsEstadisticaDAO();
        TiendaUpdateDAO tiendaActualizar = new TiendaUpdateDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n====== MENÚ TIENDA ======");
            System.out.println("1. Añadir videojuego al inventario");
            System.out.println("2. Mostrar todo el inventario");
            System.out.println("3. Consultar un título (por código o nombre)");
            System.out.println("4. Consultar stock de un título");
            System.out.println("5. Buscar cliente (por código, nombre, apellidos o email)");
            System.out.println("6. Registrar venta (y actualizar stock)");
            System.out.println("7. Mostrar historial de ventas completo");
            System.out.println("8. Mostrar historial de ventas de una fecha");
            System.out.println("9. Estadísticas: Acumulado de ventas por plataforma");
            System.out.println("10. Estadísticas: Acumulado de ventas por género");
            System.out.println("11. Mostrar el título más vendido");
            System.out.println("12. Mostrar el mejor día de ventas");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:

                    tiendaAñadir.insertarVideojuego(new Videojuego("NBA2K2025", "PS5", "Deportes", 89.99, 10, 11));
                    break;
                case 2:
                    tiendaMostrar.mostrarInventario();
                    break;
                case 3:
                    tiendaMostrar.buscarPorTitulo("NBA2K2025");
                    break;
                case 4:
                    tiendaMostrar.mostrarStockTitulo("NBA2K2025");
                    break;
                case 5:
                    tiendaMostrar.buscarPorNombre("Paula".toLowerCase());
                    break;
                case 6:
                    Venta venta = new Venta(tiendaMostrar.buscarPorNombre("Paula".toLowerCase()), LocalDate.now());

                    venta.addVideojuego(tiendaMostrar.buscarPorTitulo("Elden Ring"));
                    venta.addVideojuego(tiendaMostrar.buscarPorTitulo("God of War Ragnarok"));

                    tiendaAñadir.insertarVenta(venta);
                    break;
                case 7:
                    tiendaMostrar.obtenerHistorialVentas();
                    break;
                case 8:
                    tiendaMostrar.obtenerHistorialVentasDeUnaFecha(LocalDate.now());
                    break;
                case 9:
                    tiendaEstadistica.acumuladoDeVentasPorPlataforma();
                    break;
                case 10:
                    tiendaEstadistica.acumuladoDeVentasPorGenero();
                    break;
                case 11:
                    tiendaEstadistica.tituloMasVendido();
                    break;
                case 12:
                    tiendaEstadistica.mejorDiaVentas();
                    break;
                case 0:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}

