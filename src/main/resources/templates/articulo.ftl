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

<!-- jQuery -->
    <script src="js/jquery.js"></script>
    <!--<script src="js/jq.js"></script>-->
    <script type="text/javascript">
        $(document).ready( function (){
            var variable= "${sesion}";

            $(".elimComent").hide();
            $(".editElim").hide();
            $(".hacerComentario").hide();

            $("#button_login").show();
            $("#button_logout").hide();

            if(variable==="true"){
                console.log("dita vaina!~");
                if ( ("${user.getUsername()}" === "${articulo.getAutor().getUsername()}") ||
                        ("${user.isAdministrador()?c}" === "true") )
                {
                    $(".elimComent").show();
                    $(".editElim").show();
                }
                else
                {
                    $(".editElim").hide();
                }

                $(".hacerComentario").show();

                $("#button_login").hide();
                $("#button_logout").show();
            }
        });

    </script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

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
                   <div class="btn-nav"><a class="btn btn-default navbar-btn" id="button_login" href="/login" > Entrar</a></div>
               </li>
               <li>
                   <div class="btn-nav"><a class="btn btn-danger navbar-btn " id="button_logout" href="/clear" > Salir</a></div>
               </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Post Content Column -->
        <div class="col-lg-8">

            <!-- Blog Post -->

            <!-- Title -->
            <h1>${articulo.getTitulo()}</h1>

            <!-- Author -->
            <p class="lead">
                by <i>${articulo.getAutor().getUsername()} - ${articulo.getAutor().getNombre()}</i>
            </p>
            <hr>

            <!-- Date/Time -->
            <p>
        <form action ="/" method = "post">
            <span class="glyphicon glyphicon-time"></span>  Publicado en ${articulo.getFecha()}
            <input type = "hidden" name = "eliminarArt" value = "true">
            <div class = "editElim">
                <a href="#" data-toggle="modal" data-target="#login-modal" style='margin-left: 20em; font-size: 15px;'>Editar</a>
                <button class="btn btn-link" style='margin-left: 5em; font-size: 15px;' name="elim" value="${articulo.getId()}">Eliminar</button>
            </div>
        </form>
            </p>

            <hr>

            <!-- Preview Image -->
            <img class="img-responsive" src="http://www.caregivingclub.com/wp-content/uploads/2011/03/sunset-and-light-bulb-LG-900x300.jpg" alt="">

            <hr>

            <!-- Post Content -->
            <p class="lead">${articulo.getCuerpo()}</p>
          <!--  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut, tenetur natus doloremque laborum quos iste ipsum rerum obcaecati impedit odit illo dolorum ab tempora nihil dicta earum fugiat. Temporibus, voluptatibus.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos, doloribus, dolorem iusto blanditiis unde eius illum consequuntur neque dicta incidunt ullam ea hic porro optio ratione repellat perspiciatis. Enim, iure!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error, nostrum, aliquid, animi, ut quas placeat totam sunt tempora commodi nihil ullam alias modi dicta saepe minima ab quo voluptatem obcaecati?</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Harum, dolor quis. Sunt, ut, explicabo, aliquam tenetur ratione tempore quidem voluptates cupiditate voluptas illo saepe quaerat numquam recusandae? Qui, necessitatibus, est!</p>
-->
            <hr>

            <!-- Blog Comments -->

            <!-- Comments Form -->
            <div class = "hacerComentario" >
            <div class="well">
                <h4>Deja tu Comentario:</h4>
                <form role="form" action="/articulos" method="post">
                    <div class="form-group">
                        <textarea class="form-control" name="comentario" rows="3"></textarea>
                    </div><input type="hidden" name="idArticulo" value="${id}">
                    <input type="submit" class="btn btn-primary" value = "Comentar!"></input>
                </form>
            </div>
            </div>

            <hr>


            <!-- Area para agregar articulo -->
            <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                <div class="modal-dialog">
                    <div class="loginmodal-container">
                        <h1>Editando Articulo</h1><br>
                        <form action="/articulos" method="post">
                            <input type = "hidden" name = "editarArt" value = "true">
                            <input type = "hidden" name = "idArt" value = ${articulo.getId()}>
                            <input type="text" name="titulo" value="${articulo.getTitulo()}">
                            <textarea type="text-area" style="height: 150px;" class="form-control" row="4" name="area-articulo" >${articulo.getCuerpo()}</textarea>
                            <br>

                            <textarea type="tags-area" style="height: 50px;" class="form-control" row="4" name="area-etiqueta" ><#list etiquetas as etiqs>${etiqs.getEtiqueta()}, </#list></textarea>
                            <br>
                            <input type="submit" name="login" class="login loginmodal-submit" value="Aceptar">
                        </form>

                    </div>
                </div>
            </div>

            <!-- Comment -->
            <div class="well">
            <#list comentarios as coment>
                <div class="media">
                    <div class="media-body">
                        <h4 class="media-heading">${coment.getAutor().getUsername()}</h4>
                        <div class="form-group">
                       <textarea class="form-control" rows="2" readonly> ${coment.getComentario()} </textarea>
                        </div>
                        <div class="elimComent">
                            <form action="/articulos" method="post" >
                                <input type ="hidden" name = "eliminarComentarioV"value = "${coment.getId()}"></input>
                                <input type="hidden" name="idArticulo" value="${id}">
                                <input name = "eliminarComentario" type="submit" class="btn btn-danger" value = "Eliminar"></input>
                            </form>

                        </div>

                    </div>
                </div>
            </#list>
            </div>

        </div>

        <!-- Blog Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Blog Search Well -->
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
                <!-- /.input-group -->
            </div>

            <!-- Blog Categories Well -->

            <div class="well">
                <h4>Blog Categories</h4>
                <div class="row">
                <#list etiquetas as etiqueta>
                    <div class="col-lg-6">
                        <ul class="list-unstyled">
                            <li style="color: #1b6d85;font-size: 15px;">${etiqueta.getEtiqueta()}</li>
                        </ul>
                    </div>
                </#list>
                </div>
                <!-- /.row -->
            </div>
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
        </div>
        <!-- /.row -->
    </footer>

</div>
<!-- /.container -->

</body>

</html>
