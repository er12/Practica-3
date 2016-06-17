<!DOCTYPE html>


<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

<!-- jQuery -->
    <script src="js/jquery.js"></script>
    <script src="js/jq.js"></script>

    <script type="text/javascript">
        $(document).ready( function (){
            var variable= "${sesion}";
            $('#administrar').hide();

            if(variable==="true") {

                $('.login').hide();
                $('.logout').show();
                $('.agregarArt').show();
                if("${user.isAdministrador()?c}" === "true") {
                    $('#administrar').show();
                }
            }
            else {
                $('.login').show();
                $('.logout').hide();

                $('.agregarArt').hide();
            }

        });

    </script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <![endif]-->

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top"  role="navigation">
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
                <li class = "agregarArt">
                    <a href="#" data-toggle="modal" data-target="#login-modal"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Articulo</a>
                </li>
                <li class="login">
                    <div class="btn-nav"><a class="btn btn-default navbar-btn " id="button_login"  href="/login"> Entrar</a></div>
                </li>
                <li class="logout">
                    <div class="btn-nav"><a class="btn btn-danger navbar-btn " id="button_logout" href="/clear"> Salir</a></div>
                </li>
                <li>
                    <div class="btn-nav"><a class="btn btn-default navbar-btn" id="administrar" href="/administrarUsuarios">Administrar</a> </div>
                </li>
            </ul>

        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Area para agregar articulo -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="loginmodal-container">
            <h1>Creando Articulo</h1><br>
            <form action="/" method="post">
                <input type="text" name="titulo" placeholder="Titulo">
                <input type = "hidden" name = "crearArt" value = "true">
                <textarea type="text-area" style="height: 150px;" class="form-control" row="4" name="area-articulo" placeholder="Texto..."></textarea>
                <br>
                <textarea type="tags-area" style="height: 50px;" class="form-control" row="4" name="area-etiqueta" placeholder="Etiquetas,..."></textarea>
                <br>
                <input type="submit" name="crearArt" class="crearArt loginmodal-submit" value="Aceptar">
            </form>

        </div>
    </div>
</div>

<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="page-header">
                Articulos
            </h1>

            <!-- First Blog Post -->
          <#list articulos as articulo>
                <h2>
                    <a href="/articulos?id=${articulo.getId()}">${articulo.getTitulo()}</a>
                </h2>
                <p class="lead">
                    by <i>${articulo.getAutor().getUsername()}</i>
                </p>
                <p><span class="glyphicon glyphicon-time"></span> Publicado en ${articulo.getFecha()}</p>
                <hr>
                <p class="parrafoEsp">${articulo.getCuerpo()}</p>
                <a class="btn btn-primary" href="/articulos?id=${articulo.getId()}">Leer más <span class="glyphicon glyphicon-chevron-right"></span></a>
                <hr>
            </#list>
        <div class="col-md-4">

            <!-- Blog Search Well
            <div class="well">
                <h4>Blog Search</h4>
                <div class="input-group">
                    <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                        </button>
                        </span>
                </div>
                <!-- /.input-group -
            </div>-->
        </div>

    </div>
    <!-- /.row -->

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Ernesto y Francis 2016</p>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </footer>

</div>
<!-- /.container -->

</body>

</html>