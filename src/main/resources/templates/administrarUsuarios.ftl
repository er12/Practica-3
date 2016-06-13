<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Post - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-post.css" rel="stylesheet">
    <link href="css/blog-home.css" rel="stylesheet">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/" style="font-size: 20px">Blog.EF</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <div class="btn-nav"><a class="btn btn-danger navbar-btn " id="button_logout" href="/" > Salir</a></div>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<div class="container">
<table class="table table-striped">
    <#list usuarios as user>
    <tr>

        <td>${user.getUsername()}</td>
        <td>${user.getNombre()}</td>

            <!--<td><input type="checkbox" name="author">Autor</td>
            <td><input type="checkbox" name="admin">Admin</td>-->
        <form action="/administrarUsuarios" method="post">
            <td><button class="btn btn-link" name="elim" value="${user.getUsername()}" type="submit">Eliminar</button> </td>
        </form>


    </tr>
    </#list>
    </table>


    <button class="btn btn-default " data-toggle="modal" data-target="#nuevoUsuario" name="agregar" id="agregar">Agregar</button>
</div>

<div class="modal fade" id="nuevoUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="loginmodal-container">
            <h1>Agregar Usuario</h1><br>
            <form action="/administrarUsuarios" method="post">
                <input type="text" name="user" placeholder="username">
                <input type="text" name="nombre" placeholder="nombre">
                <input type="text" name="pass" placeholder="contraseña">
                <input type="checkbox" name="admin" value = "Admin">Admin</input>
                <input type="submit" name="crearUser" class="crearArt loginmodal-submit" value="Aceptar">
            </form>

        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="row">
        <div class="col-lg-12">
            <p>Copyright &copy; Ernesto y Francis 2016</p>
        </div>
    </div>
    <!-- /.row -->
</footer>

</div>
<!-- /.container -->

<!-- jQuery -->
<script src="js/jquery.js"></script>
<!--<script src="js/jq.js"></script>-->
<!--<script type="text/javascript">
    $(document).ready( function (){
     <#--   var variable= "${sesion}";
        if(variable==="true") {
            $("#button_login").hide();
            $("#button_logout").show();
        }
        else {
            if(variable==="false") {
                $("#button_login").show();
                $("#button_logout").hide();
            }
        }
    });

</script>-->
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>
