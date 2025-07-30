package Entidades;

public class Videojuego {
    private String titulo;
    private String plataforma;
    private String genero;
    private double precio;
    private int cantidad;
    private int codigoIdentificativo;

    public Videojuego(String titulo, String plataforma, String genero, double precio, int stock, int codigoIdentificativo) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.precio = precio;
        this.cantidad = stock;
        this.codigoIdentificativo = codigoIdentificativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int stock) {
        this.cantidad = stock;
    }

    public int getCodigoIdentificativo() {
        return codigoIdentificativo;
    }

    public void setCodigoIdentificativo(int codigoIdentificativo) {
        this.codigoIdentificativo = codigoIdentificativo;
    }

    @Override
    public String toString() {
        return "DAO.VideojuegoDAO{" +
                "titulo='" + titulo + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", genero='" + genero + '\'' +
                ", precio=" + precio +
                ", stock=" + cantidad +
                ", codigoIdentificativo=" + codigoIdentificativo +
                '}';
    }
}
