<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/jumbotron-narrow.css">
    <link rel="stylesheet" type="text/css" href="/css/home.css">
    <link rel="stylesheet" type="text/css" href="/css/jquery.growl.css"/>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/js/jquery.growl.js" type="text/javascript"></script>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li id="qsLogoutBtn"><a href="#">Logout</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">PRUEBA DE CONCEPTO SSO </h3>
    </div>
    <div class="jumbotron">
        <h3>Bienvenido Nicolas ${nickName}!</h3>
    </div>
    <div class="row marketing">
        <div class="col-lg-6">
            <h4>Por que elegimos Auth0 como SSO?</h4>
            <p>Optamos por Auth0 para nuestra prueba de concepto de Single Sign-On (SSO) porque es simple, funcional y facil de integrar.</p>

            <p>Auth0 tiene una interfaz clara y buena documentacion, lo que facilita aprender e implementar SSO sin perder tiempo. Tambien funciona bien con diferentes tecnologias, lo que lo hace muy versatil.</p>
        </div>

        <div class="col-lg-6">
            <h4>Por que no otro SSO?</h4>
            <p>Consideramos otras opciones como Okta o AWS Cognito, pero eran mas complicadas o menos accesibles. Por eso, Auth0 fue la mejor eleccion para nuestro proyecto. Tambien tuvimos en cuenta la recomendacion del profesor.</p>

            <h4>Desventajas de Auth0</h4>
            <p>Algunas desventajas que notamos son las Limitaciones en personalizacion, hay restricciones en cuanto puedes personalizar las pantallas de login o los flujos de autenticacion, pero es entendible considerando que estas son  limitaciones del plan gratuito</p>
        </div>
    </div>

    <footer class="footer">
        <p> DDS UTN FRBA GRUPO 02 </p>
    </footer>

</div>

<script type="text/javascript">
    $("#qsLogoutBtn").click(function(e) {
        e.preventDefault();
        $("#home").removeClass("active");
        $("#password-login").removeClass("active");
        $("#qsLogoutBtn").addClass("active");
        // assumes we are not part of SSO so just logout of local session
        window.location = "${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}/logout";
    });
</script>

</body>
</html>
