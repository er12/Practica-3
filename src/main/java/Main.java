/**
 * Created by Ernesto y Francis on 6/5/2016.
 */

import BaseDeDatos.Manejador;
import freemarker.template.Configuration;
import modelo.Articulo;
import modelo.Comentario;
import modelo.Etiqueta;
import modelo.Usuario;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static void main(String [] args)
    {
        staticFileLocation("recursos");
        Manejador bd = new Manejador();
        bd.eliminarTodo();
        bd.subir();
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine( configuration );

        bd.insertarUsuario(new Usuario("er12","Ernesto Rodriguez","1234",true, false));
        bd.insertarUsuario(new Usuario("francis","Ernesto Rodriguez","mmg",true,true));

        ArrayList<Etiqueta> LE = new ArrayList<Etiqueta>();
        LE.add(new Etiqueta(0,"etetiguere"));
        LE.add(new Etiqueta(0,"francis"));
        LE.add(new Etiqueta(0,"cool"));


        bd.insertarArticulo(new Articulo(11,"Hola soy Francis", "BLABLABLA", new Usuario("francis","","",
                false,false),null, null,LE));


        get("/", (request, response) -> {
        Map<String, Object> attributes = new HashMap<>();

        attributes.put("articulos",bd.getArticulos());


            return new ModelAndView(attributes, "home.ftl");
        }, freeMarkerEngine);

        get("/articulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();



            return new ModelAndView(attributes, "articulo.ftl");
        }, freeMarkerEngine);

        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();



            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

    }
}
