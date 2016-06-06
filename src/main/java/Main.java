/**
 * Created by Ernesto y Francis on 6/5/2016.
 */

import BaseDeDatos.Manejador;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static void main(String [] args)
    {
        staticFileLocation("recursos");
        Manejador bd = new Manejador();
        bd.subir();
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine( configuration );



        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();



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
