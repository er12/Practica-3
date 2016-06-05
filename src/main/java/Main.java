/**
 * Created by Ernesto on 6/5/2016.
 */

import BaseDeDatos.Manejador;

import static spark.Spark.*;

public class Main {

    public static void main(String [] args)
    {
        Manejador bd = new Manejador();
        bd.subir();
        get("/hello", (req, res) -> "Hello World");

    }
}
