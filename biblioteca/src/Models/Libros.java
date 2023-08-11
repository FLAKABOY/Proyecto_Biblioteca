
package Models;

public class Libros {
    private int id;
    private String titulo;
    private int autor;
    private int editorial;
    private int materia;
    private int cantidad;
    private int num_pag;
    private int anio_edicion;
    private String autor_nombre;
    private String editorial_nombre;
    private String materia_nombre;

    public Libros() {
    }

    public Libros(int id, String titulo, int autor, int editorial, int materia, int cantidad, int num_pag, int anio_edicion, String autor_nombre, String editorial_nombre, String materia_nombre) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.materia = materia;
        this.cantidad = cantidad;
        this.num_pag = num_pag;
        this.anio_edicion = anio_edicion;
        this.autor_nombre = autor_nombre;
        this.editorial_nombre = editorial_nombre;
        this.materia_nombre = materia_nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getEditorial() {
        return editorial;
    }

    public void setEditorial(int editorial) {
        this.editorial = editorial;
    }

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
        this.materia = materia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNum_pag() {
        return num_pag;
    }

    public void setNum_pag(int num_pag) {
        this.num_pag = num_pag;
    }

    public int getAnio_edicion() {
        return anio_edicion;
    }

    public void setAnio_edicion(int anio_edicion) {
        this.anio_edicion = anio_edicion;
    }

    public String getAutor_nombre() {
        return autor_nombre;
    }

    public void setAutor_nombre(String autor_nombre) {
        this.autor_nombre = autor_nombre;
    }

    public String getEditorial_nombre() {
        return editorial_nombre;
    }

    public void setEditorial_nombre(String editorial_nombre) {
        this.editorial_nombre = editorial_nombre;
    }

    public String getMateria_nombre() {
        return materia_nombre;
    }

    public void setMateria_nombre(String materia_nombre) {
        this.materia_nombre = materia_nombre;
    }

    
    
}
