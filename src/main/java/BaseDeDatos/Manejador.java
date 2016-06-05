package BaseDeDatos;

/**
 * Created by Ernesto on 6/4/2016.
 */


import modelo.Usuario;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Manejador {


    public void startConection()
    {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public void testConection()
    {
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        Connection conn = null;
        try {
            conn = cp.getConnection();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed");
        }
        cp.dispose();
    }
    public void subir() {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql;

            sql = "CREATE TABLE IF NOT  EXISTS  USUARIOS( USERNAME VARCHAR(20) PRIMARY KEY," +
                                                         "NOMBRE VARCHAR(50)," +
                                                         "PASSWORD VARCHAR(20), " +
                                                         "ADMINISTRATOR BOOLEAN," +
                                                         "AUTOR BOOLEAN)";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT  EXISTS  ETIQUETAS( ID INTEGER PRIMARY KEY, ETIQUETA VARCHAR(50))";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS ARTICULOS(ID INTEGER PRIMARY KEY, TITULO VARCHAR(20), " +
                    "CUERPO VARCHAR(800), AUTOR VARCHAR(20), FECHA DATE, FOREIGN KEY AUTOR REFERENCES URUARIOS(USERNAME))";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS COMENTARIOS(ID INTEGER, COMENTARIO VARCHAR(300), " +
                    "AUTOR VARCHAR(20), ARTICULO INTEGER, FOREIGN KEY AUTOR REFERENCES USUARIOS(USERNAME), FOREIGN KEY ARTICULO REFERENCES ARTICULOS(ID))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS ETIQUETA_COMENTARIOS(ETIQUETA INTEGER, COMENTARIO INTEGER, " +
                    "PRIMARY KEY (ETIQUETA,COMENTARIO)" +
                    "FOREIGN KEY ETIQUETA REFERENCES ETIQUETAS(ID), FOREIGN KEY COMENTARIO REFERENCES COMENTARIOS(ID))";
            stmt.executeUpdate(sql);

            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void insertarUsuario(Usuario usuario) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO USUARIOS(USERNAME , NOMBRE ,PASSWORD , ADMINISTRATOR, AUTOR ) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            String username = usuario.getUsername();
            String nombre = usuario.getNombre();
            String password = usuario.getPassword();
            boolean administrador = usuario.isAdministrador();
            boolean autor = usuario.isAutor();


            if(username ==null || nombre ==null || password ==null )
                return;

            prepareStatement.setString(1,username);
            prepareStatement.setString(2, nombre);
            prepareStatement.setString(3, password);
            prepareStatement.setString(4, String.valueOf(administrador));
            prepareStatement.setString(5, String.valueOf(autor));

            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public void insertaretiqueta(Usuario usuario) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Pracica2", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO ETIQUETA(ID, NOMBRE ) VALUES(?, ?)";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            String nombre= usuario.getUsername();
            prepareStatement.setString(1,nombre);



            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
