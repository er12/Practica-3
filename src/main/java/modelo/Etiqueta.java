package modelo;

/**
 * Created by Ernesto on 6/3/2016.
 */
public class Etiqueta {

    private int id;
    private String etigueta;

    public Etiqueta(){

    }

    public Etiqueta(int id, String etigueta) {
        this.id = id;
        this.etigueta = etigueta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtigueta() {
        return etigueta;
    }

    public void setEtigueta(String etigueta) {
        this.etigueta = etigueta;
    }
}
