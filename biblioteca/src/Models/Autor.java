
package Models;
public class Autor {
    private int id;
    private String autor;

    public Autor() {
    } 

    public Autor(int id, String autor) {
        this.id = id;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    @Override
    public String toString(){
        return this.getAutor();
    }
}
