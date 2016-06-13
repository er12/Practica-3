package BaseDeDatos;

/**
 * Created by Ernesto on 6/4/2016.
 */


import modelo.*;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            conn = DriverManager.getConnection("jdbc:h2:~/Practica3","sa","");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void testConection() {
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
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
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql;

            sql = "CREATE TABLE IF NOT  EXISTS  USUARIOS( USERNAME VARCHAR(20) PRIMARY KEY," +
                                                         "NOMBRE VARCHAR(50)," +
                                                         "PASSWORD VARCHAR(20), " +
                                                         "ADMINISTRADOR BOOLEAN," +
                                                         "AUTOR BOOLEAN)";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS ETIQUETAS( ID INTEGER PRIMARY KEY auto_increment,"+
                                                        "ETIQUETA VARCHAR(50))";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS ARTICULOS(ID INTEGER PRIMARY KEY auto_increment,"+
                                                       "TITULO VARCHAR(20), " +
                                                       "CUERPO VARCHAR(800),"+
                                                       " AUTOR VARCHAR(20), "+
                                                       " FECHA TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
                                                        " FOREIGN KEY (AUTOR) REFERENCES USUARIOS(USERNAME))";
            stmt.executeUpdate(sql);


            sql = "CREATE TABLE IF NOT EXISTS COMENTARIOS(ID INTEGER PRIMARY KEY auto_increment, COMENTARIO VARCHAR(400), " +
                    "AUTOR VARCHAR(20), ARTICULO INTEGER, FOREIGN KEY (AUTOR) REFERENCES USUARIOS(USERNAME) ON DELETE CASCADE ," +
                    " FOREIGN KEY (ARTICULO) REFERENCES ARTICULOS(ID) ON DELETE CASCADE)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS ETIQUETAS_ARTICULOS(ETIQUETA INTEGER , ARTICULO INTEGER , " +
                    "PRIMARY KEY (ETIQUETA,ARTICULO)," +
                    "FOREIGN KEY (ETIQUETA) REFERENCES ETIQUETAS(ID) ON UPDATE CASCADE , FOREIGN KEY (ARTICULO) REFERENCES ARTICULOS(ID) ON DELETE CASCADE )";
            stmt.executeUpdate(sql);
            //Algo diferente
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertarUsuario(Usuario usuario) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO USUARIOS(USERNAME ,NOMBRE ,PASSWORD , ADMINISTRADOR , AUTOR ) VALUES(?, ?, ?, ?, ?)";
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

    //Actualizar Usuario

    public void actualizarUsuario(Usuario usuario) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String sql = "UPDATE USUARIOS SET NOMBRE = ? ,PASSWORD = ? , ADMINISTRADOR = ? , AUTOR = ? WHERE USERNAME = ? ";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            String username = usuario.getUsername();
            String nombre = usuario.getNombre();
            String password = usuario.getPassword();
            boolean administrador = usuario.isAdministrador();
            boolean autor = usuario.isAutor();


            if(username ==null || nombre ==null || password ==null )
                return;

            prepareStatement.setString(5,username);
            prepareStatement.setString(1, nombre);
            prepareStatement.setString(2, password);
            prepareStatement.setBoolean(3, administrador);
            prepareStatement.setBoolean(4,autor);

            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void eliminarUsuario(String usuario) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String sql = "DELETE FROM USUARIOS WHERE USERNAME = ? ";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            prepareStatement.setString(1,usuario);

            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void eliminarComentario(int id) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String sql = "DELETE FROM COMENTARIOS WHERE ID = ? ";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            prepareStatement.setInt(1,id);

            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void insertarComentario(Comentario comentario, int IDArt) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO COMENTARIOS ( COMENTARIO , AUTOR , ARTICULO )" +
                    " VALUES(?,?,?)";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);

            prepareStatement.setString(1,comentario.getComentario());
            prepareStatement.setString(2,comentario.getAutor().getUsername());
            prepareStatement.setString(3,String.valueOf(IDArt));

            prepareStatement.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void insertarArticulo(Articulo articulo) {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO ARTICULOS( TITULO, CUERPO, AUTOR)" +
                    " VALUES(?,?,?)";
            PreparedStatement prepareStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            prepareStatement.setString(1,articulo.getTitulo());
            prepareStatement.setString(2,articulo.getCuerpo());
            prepareStatement.setString(3,articulo.getAutor().getUsername());


            prepareStatement.executeUpdate();
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    articulo.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }


            sql = "INSERT INTO ETIQUETAS( ETIQUETA) VALUES(?)";

            List<Etiqueta> etiqViejas = getEtiquetas();

            for(Etiqueta et : articulo.getListaEtiqueta())
            {
                if(etiqViejas.contains(et))
                    continue;
                prepareStatement = conn.prepareStatement(sql);
                prepareStatement.setString(1,et.getEtiqueta());
                prepareStatement.executeUpdate();
            }

            sql = "INSERT INTO ETIQUETAS_ARTICULOS( ETIQUETA, ARTICULO) VALUES( ? , ? ) ";

            for(Etiqueta et : articulo.getListaEtiqueta())
            {

                if(etiqViejas.contains(et))
                    continue;
                int eDummy =getIdEtiqueta(et.getEtiqueta());
                int artDummy = (int) articulo.getId();


                prepareStatement = conn.prepareStatement(sql);
                prepareStatement.setInt(1,eDummy);
                prepareStatement.setInt( 2, artDummy);
                prepareStatement.executeUpdate();
            }


            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Etiqueta> getEtiquetas()
    {
        List<Etiqueta> etiquetas = new ArrayList<>();
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String query = "select * from ETIQUETAS";

            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                etiquetas.add(new Etiqueta(rs.getInt("ID"),rs.getString("ETIQUETA")));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return etiquetas;
    }

    public int getIdEtiqueta(String etiq)
    {
        List<Etiqueta> etiquetas = getEtiquetas();

        for (Etiqueta e : etiquetas)
        {
            if ( e.getEtiqueta().equals(etiq))
                return e.getId();
        }

        return 0;
    }

    public List<Etiqueta> getEtiquetasArt(int art)
    {
        List<Etiqueta> etiquetas = new ArrayList<>();
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String query = "select ETIQUETAS.ID AS ID, ETIQUETAS.ETIQUETA AS ETIQUETA " +
                    "from ETIQUETAS, ETIQUETAS_ARTICULOS, ARTICULOS " +
                    "WHERE ETIQUETAS.ID = ETIQUETAS_ARTICULOS.ETIQUETA " +
                    "AND ETIQUETAS_ARTICULOS.ARTICULO = ARTICULOS.ID " +
                    "AND ARTICULOS.ID = ?";

            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setInt(1,art);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                etiquetas.add(new Etiqueta(rs.getInt("ID"),rs.getString("ETIQUETA")));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return etiquetas;
    }

    public Usuario getUsuario(String user) {
        Usuario usuario = null;
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String query = "SELECT * FROM USUARIOS WHERE USERNAME = ?";

            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1,user);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
               usuario = new Usuario(rs.getString("USERNAME"),
                                rs.getString("NOMBRE"),
                                rs.getString("PASSWORD"),
                                rs.getBoolean("ADMINISTRADOR"),
                                rs.getBoolean("AUTOR"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String query = "SELECT * FROM USUARIOS";

            PreparedStatement prepareStatement = conn.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                usuarios.add(new Usuario(rs.getString("USERNAME"),
                                            rs.getString("NOMBRE"),
                                            rs.getString("PASSWORD"),
                                            rs.getBoolean("ADMINISTRADOR"),
                                            rs.getBoolean("AUTOR")
                                        )
                );
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuarios;
    }

    public List<Comentario> getComentariosArt(int artID) {
        List<Comentario> coments = new ArrayList<>();
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String query = "SELECT COMENTARIOS.ID AS ID, COMENTARIOS.COMENTARIO AS COMENTARIO, " +
                    "USUARIOS.USERNAME AS USERNAME , USUARIOS.NOMBRE AS NOMBRE " +
                    "FROM ARTICULOS, COMENTARIOS, USUARIOS " +
                    "WHERE ? = ARTICULOS.ID " +
                    "AND ARTICULOS.ID = COMENTARIOS.ARTICULO " +
                    "AND USUARIOS.USERNAME = COMENTARIOS.AUTOR";

            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1,String.valueOf(artID));
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                coments.add(new Comentario(rs.getInt("ID"),
                                            rs.getString("COMENTARIO"),
                                            new Usuario(rs.getString("USERNAME"),
                                                rs.getString("NOMBRE"),null,false,false),
                                            null
                            )
                );
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return coments;
    }

    public List<Articulo> getArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String query = "SELECT ID, TITULO, CUERPO, " +
                    "USUARIOS.USERNAME AS USERNAME, USUARIOS.NOMBRE AS NOMBRE, FECHA " +
                    "FROM ARTICULOS, USUARIOS " +
                    "WHERE ARTICULOS.AUTOR = USUARIOS.USERNAME " +
                    "ORDER BY FECHA DESC" ;

            PreparedStatement prepareStatement = conn.prepareStatement(query);

            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                articulos.add(new Articulo(
                        rs.getInt("ID"),
                        rs.getString("TITULO"),
                        rs.getString("CUERPO"),
                        new Usuario(rs.getString("USERNAME"),
                                rs.getString("NOMBRE"),null,false,false),
                        rs.getDate("FECHA"),
                        getComentariosArt(rs.getInt("ID")),
                        getEtiquetasArt(rs.getInt("ID"))
                        )
                );
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articulos;
        }
    }


    public  Articulo getArticulo(int id) {
        Articulo articulo = null;
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            String query = "SELECT ID, TITULO, CUERPO, " +
                    "USUARIOS.USERNAME AS USERNAME, USUARIOS.NOMBRE AS NOMBRE" +
                    ", USUARIOS.ADMINISTRADOR AS ADMINISTRADOR , USUARIOS.AUTOR AS AUTOR , FECHA " +
                    "FROM ARTICULOS, USUARIOS " +
                    "WHERE ARTICULOS.AUTOR = USUARIOS.USERNAME " +
                    "AND ID = ?" ;

            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setInt(1, id );

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
                articulo = new Articulo(
                                rs.getInt("ID"),
                                rs.getString("TITULO"),
                                rs.getString("CUERPO"),
                                new Usuario(rs.getString("USERNAME"),
                                        rs.getString("NOMBRE"),null,rs.getBoolean("ADMINISTRADOR"),rs.getBoolean("AUTOR")),
                                rs.getDate("FECHA"),
                                getComentariosArt(rs.getInt("ID")),
                                getEtiquetasArt(rs.getInt("ID"))
                );
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return articulo;
        }


    }


    public long getArticuloId(String user,String titulo) {

        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");

        int id = 0;
        try {
            conn = cp.getConnection();
            String query = "SELECT ID FROM ARTICULOS WHERE ARTICULOS.AUTOR = ?  AND ARTICULOS.TITULO = ?";

            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, user);
            prepareStatement.setString(2, titulo);

            ResultSet rs = prepareStatement.executeQuery();
             id = rs.getInt("ID");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return id;

        }
    }


    public void actualizarArticulo(Articulo art){

        Connection con = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            String query = "UPDATE ARTICULOS SET TITULO=?, CUERPO=? where ID = ?";
            con = cp.getConnection();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, art.getTitulo());//Esto fue cambiado para probar, aun asi no funcionanba
            prepareStatement.setString(2, art.getCuerpo());
            //Indica el where...
            prepareStatement.setString(3,  String.valueOf(art.getId()));
            //
            prepareStatement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean goodUsernamePassword(String username, String password)
    {
        for( Usuario user :getUsuarios())
        {
            if(user.getUsername().equals(username))
            {
                if(user.getPassword().equals(password))
                {
                    return true;
                }
                break;
            }
        }

        return false;

    }

    public void eliminarArticulo(int id)
    {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");

        String query=null;
        PreparedStatement prepareStatement= null;
        try {
            conn = cp.getConnection();


            query = "DELETE FROM ETIQUETAS_ARTICULOS WHERE ARTICULO = ?";

            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setInt(1,id);


            prepareStatement.executeUpdate();


            //-----------------------------------------------------------
            query = "DELETE FROM ARTICULOS WHERE ID = ?";

            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setInt(1,id);


            prepareStatement.executeUpdate();


            conn.commit();
            conn.close();

        }    catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarTodo()
    {
        Connection conn = null;
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Practica3", "sa", "");
        try {
            conn = cp.getConnection();
            Statement stmt = conn.createStatement();

            String sql;

            sql = "DROP TABLE IF EXISTS ETIQUETAS_ARTICULOS";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS COMENTARIOS";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS ARTICULOS";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS ETIQUETAS";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS USUARIOS";
            stmt.executeUpdate(sql);
            conn.commit();
            stmt.close(); // Nuevo
            conn.close();

        }    catch (SQLException e) {
            e.printStackTrace();
        }
    }


}