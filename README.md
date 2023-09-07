# PruebaGlobalLogic
Prueba técnica GlobalLogic




Estructura del Proyecto

Se utilizó Spring Boot con JAVA 8 para todo lo que es el BACKEND, en Base de DATOS se utilizó H2, y se implementó un generador de TOKEN con JWT.


Instrucciones para iniciar el proyecto

Se debe levantar con cualquier IDE que tenga incorporado la suite de Spring Boot, tales como STS, Eclipse, IntelliJ, VSC entre los más conocidos, y simplemente hacer click derecho sobre el proyecto y seleccionar RUN -> Spring Boot APP.

El servicio desplegará de forma local, si necesitan cambiar el puerto, lo pueden hacer en el properties de application.properties, donde pueden seleccionar el puerto que ustedes quieran (recordar que al realizar este cambio, también deben cambiar el puerto en el archivo de postman_collection incorporado, para realizar pruebas).

Se incluye un diagrama de secuencia alojado en la carpeta de diagramas y también una collection de POSTMAN, con la estructura para llamar a los endpoint preparada.


Servicios y estructura de los endpoint.

Este sistema consta de 2 servicios REST de tipo POST y 3 servicios de tipo GET para revisar información insertada en BD.

Los servicios POST son:

/sign-up
Este servicio es para crear un usuario, el cual espera recibir la siguiente estructura Json en el BODY:

{
  "name": "nombre de usuario",
  "email": "correo electrónico",
  "password": "contraseña alfanumérica",
  "phones": [      // Un listado de Teléfonos
    {
      "number": número,
      "citycode": número,
      "countrycode": "alfanumérico"
    }
  ]
}



Respuesta del servicio:

{
    "id": número de ID de usuario creado,
    "created": "fecha de creación",
    "active": campo booleano
}


* Acá se solicitaron campos adicionales, pero al ser el servicio de crear usuario, creo que, por lógica, no aplican. Los campos que se omitieron, son los siguientes:

- lastLogin 
- token


En caso de error, se solicitó la siguiente estructura JSON de respuesta, la cual, omití que sea una lista de detalles asociados a un error, y quedo de la siguiente manera:


{
    "error": {
        "date": "fecha de ejecución del error",
        "codigo": código de error,
        "detail": "Detalle de error personalizado"
    },
    "httpStatus": "HTTP_STATUS"
}


En el segundo servicio POST tenemos lo siguiente:

/login
Este servicio es para realizar una autenticación de un usuario creado, pero solo en un bajo nivel, haciendo MATCH con los parámetros de email y contraseña.

{
  "email": "correo usuario registrado",
  "password": "contraseña creada"
}

* No codifique las contraseñas para que sea más fácil hacer pruebas.

Como respuesta, este servicio entrega un TOKEN de autenticación, y la estructura de respuesta es la siguiente:

{
    "id": id de usuario,
    "created": "fecha de creación de usuario",
    "lastLogin": "fecha de la última conexión",
    "token": "token de autorización",
    "name": "nombre de usuario",
    "email": "email",
    "password": "contraseña",
    "phones": [   // Listado de teléfono si es que aplica.
        {
            "number": número,
            "citycode": "código ciudad",
            "countrycode": "código país"
        }
    ],
    "active": estado booleano de sesión
}

* Cada vez que el usuario ingresa, se actualiza un token diferente y desactiva la sesión anterior (mono sesión).


En caso de error, cumple con la misma estructura de mensaje del servicio /sign-up.

El sistema cuenta con mensajes de errores resumidos, descriptivos y personalizados a nivel de usuario, excepto los de tipo 500 (BAD_REQUEST) que muestra el detalle de la traza completo de la consola.

Cuenta con las validaciones en los campos solicitados.

Adicional se crearon 3 servicios de tipo GET para poder revisar los datos ingresados y así poder realizar validaciones de forma más fácil acerca de la data que se está ingresando.

Los servicios son:

/user
Lista todos los usuarios creados.

/session
Lista todas las sesiones.

/phone
Lista todos los teléfonos ingresados con sus usuarios.








Proyecto realizado por Jonathan Ramirez.
