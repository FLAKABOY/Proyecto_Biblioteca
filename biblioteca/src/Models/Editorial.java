
package Models;

public class Editorial {
    private int id;
    private String editorial;

    public Editorial() {
    }

    public Editorial(int id, String editorial) {
        this.id = id;
        this.editorial = editorial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    @Override
    public String toString(){
        return this.getEditorial();
    }
}
