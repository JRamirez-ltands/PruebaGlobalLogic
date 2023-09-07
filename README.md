<h2>Prueba técnica GlobalLogic</h2>


<h3>Estructura del Proyecto</h3>
</br>
Se utilizó Spring Boot con JAVA 8 para todo lo que es el BACKEND, en Base de DATOS se utilizó H2, y se implementó un generador de TOKEN con JWT.
</br>

<h3>Instrucciones para iniciar el proyecto</h3>
</br>
Se debe levantar con cualquier IDE que tenga incorporado la suite de Spring Boot, tales como STS, Eclipse, IntelliJ, VSC entre los más conocidos, y simplemente hacer click derecho sobre el proyecto y seleccionar RUN -> Spring Boot APP.
</br>
El servicio desplegará de forma local, si necesitan cambiar el puerto, lo pueden hacer en el properties de application.properties, donde pueden seleccionar el puerto que ustedes quieran (recordar que al realizar este cambio, también deben cambiar el puerto en el archivo de postman_collection incorporado, para realizar pruebas).
</br>
Se incluye un diagrama de secuencia alojado en la carpeta de diagramas y también una collection de POSTMAN, con la estructura para llamar a los endpoint preparada.

</br>
<h3>Servicios y estructura de los endpoint.</h3>
</br>
Este sistema consta de 2 servicios REST de tipo POST y 3 servicios de tipo GET para revisar información insertada en BD.
</br>
<h4>Los servicios POST son:</h4>

<h4>/sign-up</h4>
Este servicio es para crear un usuario, el cual espera recibir la siguiente estructura Json en el BODY:
</br>
{</br>
  "name": "nombre de usuario",</br>
  "email": "correo electrónico",</br>
  "password": "contraseña alfanumérica",</br>
  "phones": [      // Un listado de Teléfonos</br>
    {</br>
      "number": número,</br>
      "citycode": número,</br>
      "countrycode": "alfanumérico"</br>
    }</br>
  ]</br>
}</br>
</br>


<h3>Respuesta del servicio:</h3>
{</br>
    "id": número de ID de usuario creado,</br>
    "created": "fecha de creación",</br>
    "active": campo booleano</br>
}
</br>
* Acá se solicitaron campos adicionales, pero al ser el servicio de crear usuario, creo que, por lógica, no aplican. Los campos que se omitieron, son los siguientes:
</br>
- lastLogin 
</br>
- token
</br>

<h3>Error</h3>
En caso de error, se solicitó la siguiente estructura JSON de respuesta, la cual, omití que sea una lista de detalles asociados a un error, y quedo de la siguiente manera:
</br>
{</br>
    "error": {</br>
        "date": "fecha de ejecución del error",</br>
        "codigo": código de error,</br>
        "detail": "Detalle de error personalizado"</br>
    },</br>
    "httpStatus": "HTTP_STATUS"</br>
}</br>


<h3>En el segundo servicio POST tenemos lo siguiente:</h3>
<h3>/login</h3>
Este servicio es para realizar una autenticación de un usuario creado, pero solo en un bajo nivel, haciendo MATCH con los parámetros de email y contraseña.
</br>
{</br>
  "email": "correo usuario registrado",</br>
  "password": "contraseña creada"</br>
}</br>

* No codifique las contraseñas para que sea más fácil hacer pruebas.
</br>
<h5>Como respuesta, este servicio entrega un TOKEN de autenticación, y la estructura de respuesta es la siguiente:</h5>
{</br>
    "id": id de usuario,</br>
    "created": "fecha de creación de usuario",</br>
    "lastLogin": "fecha de la última conexión",</br>
    "token": "token de autorización",</br>
    "name": "nombre de usuario",</br>
    "email": "email",</br>
    "password": "contraseña",</br>
    "phones": [   // Listado de teléfono si es que aplica.</br>
        {</br>
            "number": número,</br>
            "citycode": "código ciudad",</br>
            "countrycode": "código país"</br>
        }</br>
    ],</br>
    "active": estado booleano de sesión</br>
}</br>

* Cada vez que el usuario ingresa, se actualiza un token diferente y desactiva la sesión anterior (mono sesión).

</br>
En caso de error, cumple con la misma estructura de mensaje del servicio /sign-up.
</br>
El sistema cuenta con mensajes de errores resumidos, descriptivos y personalizados a nivel de usuario, excepto los de tipo 500 (BAD_REQUEST) que muestra el detalle de la traza completo de la consola.
</br></br>
Cuenta con las validaciones en los campos solicitados.
</br></br>
Adicional se crearon 3 servicios de tipo GET para poder revisar los datos ingresados y así poder realizar validaciones de forma más fácil acerca de la data que se está ingresando.
</br></br>
<h4>Los servicios son:</h4>
<h5>/user</h5>
Lista todos los usuarios creados.
</br>
<h5>/session</h5>
Lista todas las sesiones.
</br>
<h5>/phone</h5>
Lista todos los teléfonos ingresados con sus usuarios.

</br>
</br>
</br>
</br>


Proyecto realizado por Jonathan Ramirez.
