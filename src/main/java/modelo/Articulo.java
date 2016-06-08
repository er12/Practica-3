package modelo;

import java.util.Date;
import java.util.List;

/**
 * Created by Ernesto y Francis on 6/3/2016.
 */
public class Articulo {
    private long id;
    private String titulo;
    private String Cuerpo;
    private Usuario autor;
    private Date fecha;
    private List<Comentario> listaComentario;
    private List<Etiqueta> listaEtiqueta;

    public Articulo(){

    }

    public Articulo(long id, String titulo, String cuerpo, Usuario autor, Date fecha, List<Comentario> listaComentario, List<Etiqueta> listaEtiqueta) {
        this.id = id;
        this.titulo = titulo;
        Cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
        this.listaComentario = listaComentario;
        this.listaEtiqueta = listaEtiqueta;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCuerpo(String cuerpo) {
        Cuerpo = cuerpo;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setListaComentario(List<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
    }

    public void setListaEtiqueta(List<Etiqueta> listaEtiqueta) {
        this.listaEtiqueta = listaEtiqueta;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCuerpo() {
        return Cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Comentario> getListaComentario() {
        return listaComentario;
    }

    public List<Etiqueta> getListaEtiqueta() {
        return listaEtiqueta;
    }
}
