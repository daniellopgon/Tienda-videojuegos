package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private Long idVenta;
    private Cliente cliente;
    private ArrayList<Videojuego> videojuegos;
    private LocalDate fechaVenta;

    public Venta(Cliente cliente, LocalDate fechaVenta) {
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.videojuegos = new ArrayList<>();
    }

    public void addVideojuego(Videojuego videojuego){
        videojuegos.add(videojuego);
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(ArrayList<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public String toString() {
        return "DAO.VentaDAO{" +
                "cliente=" + cliente +
                ", videojuegos=" + videojuegos +
                ", fechaVenta=" + fechaVenta +
                '}';
    }
}
