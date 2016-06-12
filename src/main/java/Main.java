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
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

import static spark.Spark.*;

public class Main {

public static void main(String [] args)
{
    staticFileLocation("recursos");
    Manejador bd = new Manejador();
   // bd.eliminarTodo();
    bd.subir();
    Configuration configuration = new Configuration();
    configuration.setClassForTemplateLoading(Main.class, "/templates");
    FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine( configuration );


    //Administradores
    //bd.insertarUsuario(new Usuario("er12","Ernesto Rodriguez","1234", false,true));
    //bd.insertarUsuario(new Usuario("francis","Francis Cáceres","1234",true,true));

    //Usuario francis = new Usuario("","","",false,false);

    //Datos ejemplo
   /* ArrayList<Etiqueta> LE = new ArrayList<Etiqueta>();
    LE.add(new Etiqueta(0,"etetiguere"));
    LE.add(new Etiqueta(0,"francis"));
    LE.add(new Etiqueta(0,"cool"));


    bd.insertarArticulo(new Articulo(11,"Hola soy Francis",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolore, veritatis, " + //Hasta "veri" son 70 caracteres
                    "tempora, necessitatibus inventore nisi quam quia repellat ut tempore laborum possimus " +
                    "eum dicta id animi corrupti debitis ipsum officiis rerum.",
            new Usuario("francis","","",
                    false,false),null, null,LE));*/

    //-------------------------------------------Comentario Prueba
    /*ArrayList<Etiqueta> LE = new ArrayList<Etiqueta>();
    LE.add(new Etiqueta(0,"etetiguere"));
    LE.add(new Etiqueta(0,"francis"));
    LE.add(new Etiqueta(0,"cool"));


    bd.insertarArticulo(new Articulo(0,"Hola soy Francis", "BLABLABLA", new Usuario("francis","","",
            false,false),null, null,LE));
    */


    get("/", (request, response) -> {
        Map<String, Object> attributes = new HashMap<>();
        Session session = request.session(true);
         Boolean usuario = session.attribute("sesion");


        Boolean admin =session.attribute("admin");
        //System.out.println(" "+ session.attribute("usuario"));
        attributes.put("sesion","false");

        if(admin!=null)
        {
            if(admin)
            {
                attributes.put("greetings","Saludos Administardor.");
                attributes.put("sesion","true");
            }
        }
        else
        {
            if(usuario!=null){
                if(usuario)
                {
                    attributes.put("greetings","Saludos usuario mortal.");
                    attributes.put("sesion","true");
                }
            }
            else
            {
                attributes.put("greetings","");
                attributes.put("estado","fuera");
            }
        }

        attributes.put("articulos",bd.getArticulos());


        return new ModelAndView(attributes, "home.ftl");
    }, freeMarkerEngine);

    post("/", (request, response) -> {
        Session sesion = request.session(true);

        Map<String, Object> attributes = new HashMap<>();

        attributes.put("sesion","true");

        String insertArt = request.queryParams("crearArt");
        String elimArt = request.queryParams("eliminarArt");

        if(insertArt != null) {
            String titulo = request.queryParams("titulo");
            String texto = request.queryParams("area-articulo");
            String etiquetas = request.queryParams("area-etiqueta");
            ArrayList<Etiqueta> etiq = new ArrayList<Etiqueta>();
            for (String eti : etiquetas.split(",")) {
                etiq.add(new Etiqueta(0, eti));
               // System.out.println(eti);
            }


            Articulo art = new Articulo(15, titulo, texto, bd.getUsuario(sesion.attribute("currentUser")), null, null, etiq);
            bd.insertarArticulo(art);
        }
        else {
            if (elimArt != null)
            {
                int elim = Integer.parseInt(request.queryParams("elim"));

                //System.out.println(elim);
                bd.eliminarArticulo(elim);


            }

        }
        attributes.put("articulos",bd.getArticulos());
        return new ModelAndView(attributes, "home.ftl");
    }, freeMarkerEngine);

    get("/articulos", (request, response) -> {
        Map<String, Object> attributes = new HashMap<>();
        Session sesion = request.session(true);
        attributes.put("sesion",(sesion.attribute("sesion")==null)?"false":sesion.attribute("sesion").toString());
        int id = Integer.valueOf(request.queryParams("id"));


        attributes.put("comentarios",bd.getComentariosArt(id));
        attributes.put("articulo",bd.getArticulo(id));
        attributes.put("id",request.queryParams("id"));
        attributes.put("etiquetas",bd.getEtiquetasArt(id));



        return new ModelAndView(attributes, "articulo.ftl");
    }, freeMarkerEngine);

    post("/articulos", (request, response) -> {
        Map<String, Object> attributes = new HashMap<>();
        Session sesion = request.session(true);
        attributes.put("sesion","true");

        String editarArt = request.queryParams("editarArt");
        System.out.println("Esto es " + editarArt);

        if(editarArt != null) {//Not yet
            String titulo = request.queryParams("titulo");
            String texto = request.queryParams("area-articulo");
            String etiquetas = request.queryParams("area-etiqueta");
            int idArt = Integer.parseInt(request.queryParams("idArt"));
            ArrayList<Etiqueta> etiq = new ArrayList<Etiqueta>();
            for (String eti : etiquetas.split(",")) {
                etiq.add(new Etiqueta(0, eti));
                //System.out.println(eti);
            }
            Articulo art = new Articulo(idArt, titulo, texto, bd.getUsuario(sesion.attribute("currentUser")), null, null, etiq);
            System.out.println(art.getId()+ " "+art.getTitulo());
            bd.actualizarArticulo(art);
        }


        String comen = request.queryParams("comentario");
        System.out.println("id es " + request.queryParams("idArticulo") + " " + comen + sesion.attribute("currentUser"));

        int id = Integer.parseInt(request.queryParams("idArticulo"));//arregle esta parte pero nose porque no llega a guardar el comentario

        Comentario com = new Comentario(0,comen,new Usuario(sesion.attribute("currentUser"),"","",false,false),bd.getArticulo(id));
        bd.insertarComentario(com,id);//Aqui no llega no se por que!!!!!!!




        attributes.put("articulo",bd.getArticulo(id));
        attributes.put("comentarios",bd.getComentariosArt(id));
        attributes.put("id",id);
        attributes.put("etiquetas",bd.getEtiquetasArt(id));

        /*for(Comentario c : bd.getComentariosArt(id))
        {
            System.out.println(c.getComentario());
        }*/

        return new ModelAndView(attributes, "articulo.ftl");
    }, freeMarkerEngine);

    get("/login", (request, response) -> {
        Map<String, Object> attributes = new HashMap<>();

        return new ModelAndView(attributes, "login.ftl");
    }, freeMarkerEngine);

    post("/validacion", (request, response) -> {
        Map<String, Object> attributes = new HashMap<>();
        Session session=request.session(true);

        if(session.attribute("sesion"))
        {
            Usuario u= bd.getUsuario(request.queryParams("user"));
            if(u.isAdministrador())
                session.attribute("admin",true);
            attributes.put("message","Bienvenido " + u.getNombre());
            attributes.put("redireccionar", "si");
        }
        else
        {
            attributes.put("message", "Username o password incorrectos.");
            attributes.put("redireccionar", "no");

        }

        //response.redirect("/zonaadmin/");
        return new ModelAndView(attributes, "validacion.ftl");
    }, freeMarkerEngine);

    before("/validacion",(request, response) -> {
        Session session=request.session(true);

        String user = request.queryParams("user");
        String pass = request.queryParams("pass");

        if(bd.goodUsernamePassword(user,pass))
        {
            session.attribute("sesion", true);
            session.attribute("currentUser", user);
        }
        else
            session.attribute("sesion", false);
        //response.redirect("/zonaadmin/");



    });
    //
    /*Session session=request.session(true);

    Usuario usuario= null;//FakeServices.getInstancia().autenticarUsuario(request.params("usuario"), request.params("contrasena"));
    if(request.params("usuario").equalsIgnoreCase("barcamp") && request.params("contrasena").equalsIgnoreCase("2014")){
        usuario = new Usuario("Barcamp", "2014");
    }

    if(usuario==null){
        halt(401,"Credenciales no validas...");
    }

    session.attribute("usuario", usuario);
    response.redirect("/");

    return "";
*/
    }
}
