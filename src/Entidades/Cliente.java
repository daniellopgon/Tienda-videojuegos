package Entidades;

public class Cliente {
    private String nombre;
    private String apellidos;
    private String dirección;
    private String email;
    private Long codigoDeCliente;

    public Cliente(String nombre, String apellidos, String dirección, String email, Long codigoDeCliente) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dirección = dirección;
        this.email = email;
        this.codigoDeCliente = codigoDeCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCodigoDeCliente() {
        return codigoDeCliente;
    }

    public void setCodigoDeCliente(Long codigoDeCliente) {
        this.codigoDeCliente = codigoDeCliente;
    }

    @Override
    public String toString() {
        return "DAO.ClienteDAO{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dirección='" + dirección + '\'' +
                ", email='" + email + '\'' +
                ", codigoDeCliente=" + codigoDeCliente +
                '}';
    }
}
