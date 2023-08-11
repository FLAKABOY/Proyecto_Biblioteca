
package Models;

public class Estudiantes {
    private int id;
    private String documento;
    private String codigo;
    private String nombre;
    private String telefono;
    private String carrera;

    public Estudiantes() {
    }

    public Estudiantes(int id, String documento, String codigo, String nombre, String telefono, String carrera) {
        this.id = id;
        this.documento = documento;
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
}
