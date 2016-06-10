package modelo;

/**
 * Created by Ernesto on 6/3/2016.
 */
public class Etiqueta {

    private int id;
    private String etiqueta;

    public Etiqueta(){

    }

    public Etiqueta(int id, String etiqueta) {
        this.id = id;
        this.etiqueta = etiqueta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
