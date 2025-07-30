package Entidades;

public class DetalleVenta {
    private long codVenta;
    private long codVideojuego;
    private int cantidad;

    public DetalleVenta(long codVenta, long codVideojuego, int cantidad) {
        this.codVenta = codVenta;
        this.codVideojuego = codVideojuego;
        this.cantidad = cantidad;
    }

    public long getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(long codVenta) {
        this.codVenta = codVenta;
    }

    public long getCodVideojuego() {
        return codVideojuego;
    }

    public void setCodVideojuego(long codVideojuego) {
        this.codVideojuego = codVideojuego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetalleVentaDAO{" +
                "codVenta=" + codVenta +
                ", codVideojuego=" + codVideojuego +
                ", cantidad=" + cantidad +
                '}';
    }
}
