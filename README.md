# Parcial Segundo Tercio
## Requerimientos
Diseñe, construya y despliegue los siguientes servicios en un microcontenedor docker desplegado en una instancei a EC2 de AWS. Cada estudiante debe seleccionar para desarrollar dos funciones matemáticas de acuerdo a los dos últimos dígitos de su cédula como se especifica en la lista. Todas las funciones reciben un solo parámetro de tipo "Double" y retornan una prámetro sde tipo "Double".
0. log
1. ln
2. sin
3. cos
4. tan
5. acos
6. asin
7. atan
8. sqrt
9. exp (el número de eauler elevado ala potendia del parámetro)

Implemente los servicios para responder al método de solicitud HTTP GET. Deben usar el nombre de la función especificado en la lista y el parámetro debe ser pasado en la variable de query con nombre "value".

Ejemplo de una llamado:

https://amazonxxx.x.xxx.x.xxx:{port}/cos?value=3.141592

Salida. El formato de la salida y la respuesta debe ser un JSON con el siguiente formato

{
 "operation": "cos",
 "input":  3.141592,
 "output":  -0.999999
}

## Prerrequisitos
Para la realización y ejecución tanto del programa como de las pruebas de este, se requieren ser instalados los siguientes programas:
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [Docker](https://www.docker.com/). Programa encargado de crear contenedores ligeros y portables para las aplicaciones software que puedan ejecutarse en cualquier máquina con Docker instalado, independientemente del sistema operativo que la máquina tenga por debajo, facilitando así también los despliegues.
Para asegurar que el usuario cumple con todos los prerrequisitos para poder ejecutar el programa, es necesario disponer de un Shell o Símbolo del Sistema para ejecutar los siguientes comandos para comprobar que todos los programas están instalados correctamente, para así compilar y ejecutar tanto las pruebas como el programa correctamente.

Para asegurar que el usuario cumple con todos los prerrequisitos para poder ejecutar el programa, es necesario disponer de un Shell o Símbolo del Sistema para ejecutar los siguientes comandos para comprobar que todos los programas están instalados correctamente, para así compilar y ejecutar tanto las pruebas como el programa correctamente.

```
mvn -version
git --version
java -version
docker version
```

## Instalación
Para descargar el proyecto de GitHub, primero debemos clonar este repositorio, ejecutando la siguiente línea de comando en GIT.

```
git clone https://github.com/Skullzo/AREP-Parcial2.git
```

## Ejecución
Para compilar el proyecto utilizando la herramienta Maven, nos dirigimos al directorio donde se encuentra alojado el proyecto, y dentro de este ejecutamos en un Shell o Símbolo del Sistema el siguiente comando:

```
mvn package
```
## Pruebas
Para realizar las pruebas correspondientes del proyecto utilizando la herramienta Maven, nos dirigimos al directorio donde se encuentra alojado el proyecto, y dentro de este ejecutamos en un Shell o Símbolo del Sistema el siguiente comando:

```
mvn test
```

A continuación se muestran las pruebas compiladas correctamente para el código fuente.

![img](https://github.com/Skullzo/AREP-Parcial2/blob/main/img/Prueba.PNG)

----------

### Creación de un Rol en Identity and Access Management (IAM)

Para iniciar con el procedimiento de la creación de la función Lambda con su respectiva API, primero se debe crear un rol en Identity and Access Management (IAM). Para crear este rol, primero se accede al Dashboard de AWS, y se selecciona la opción **IAM**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS1.png)

Para seleccionar o crear nuevos roles en AWS, se selecciona la opción **Roles** localizada en la parte izquierda de la página de AWS.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS2.png)

Para crear un nuevo rol, se realiza clic en el botón **Crear un rol** para poder iniciar con la creación de un rol en AWS.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS3.png)

Luego de ingresar a la creación de un rol, se realiza clic en el botón **Lambda** para crear un rol para las funciones Lambda, y luego se realiza clic en el botón **Siguiente: Permisos**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS4.png)

Para crear un rol que tenga todas las políticas de permisos para no tener ningún conflicto de permisos a la hora de manipular las funciones lambda, se seleccionan las políticas **AdministratorAccess** para poder tener un mayor control sobre las funciones lambda. Luego se realiza clic en **Siguiente: Etiquetas**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS5.png)

Ahora se accede a la parte de añadir etiquetas, la cual se deja como está por defecto, y se realiza clic en el botón **Siguiente: Revisar**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS6.png)

Para realizar la respectiva revisión del rol a crear, se ingresa el nombre del rol que es **AREPROL**, y por defecto se deja la descripción del rol que se ingresa automáticamente por el sistema. Se revisa también que las políticas estén correctas, y que no haya ningún límite de permisos. Luego se procede a realizar clic en el botón **Crear un rol**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS7.png)

Para finalizar con la creación del rol que se utilizará para la función lambda, se realiza la respectiva verificación comprobando que se encuentre el rol en la lista de roles creados en AWS.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS8.PNG)

----------

### Creación de la Función Lambda

Para iniciar con el procedimiento de la creación de la función lambda, desde el Dashboard de AWS se realiza clic en el botón **Lambda**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS9.png)

Para crear la función lambda que se utilizará para el laboratorio, se realiza clic en el botón **Crear una función**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS10.png)

Ahora se diligencian los espacios en blanco. Como nombre de la función lambda que se utilizará, se estableció **square**. En el tiempo de ejecución se estableció **Java 8**, y por último, se realiza clic en **Cambiar el rol de ejecución predeterminado**, para posteriormente realizar clic en **Uso de un rol existente**, en el cual se selecciona el rol creado anteriormente, llamado **AREPROL**. Para finalizar con la creación de la función lambda, se procede a realizar clic en el botón **Crear una función**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS11.png)

Para cargar el ```.jar``` del proyecto creado en NetBeans y posteriormente compilado y ejecutado con sus respectivas pruebas, se realiza clic en el botón **Cargar desde**, para así seleccionar la opción **Archivo .zip o .jar**. 

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS12.png)

Para cargar el archivo ```.jar```, se realiza clic en el botón **Cargar** y se selecciona el archivo ```.jar``` del proyecto localizado en la carpeta **target** del mismo. Por último, se realiza clic en el botón **Open** o **Abrir** para así cargar el ```.jar``` a la plataforma de AWS. 

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS13.png)

Para guardar el ```.jar``` subido a la plataforma de AWS, se realiza clic en el botón **Guardar**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS14.png)

En el Dashboard de la función lambda ya creada, se realiza clic en el botón **Editar** en la opción **Configuración del tiempo de ejecución**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS15.png)

Ahora se modifica el controlador en el cual se ejecutará la función lambda. Para esto, se escribe en el campo provisto **edu.escuelaing.arep.MathServices::square**, que es la ruta o *path* en donde se encuentra el método encargado de realizar la respectiva conversión de Fahrenheit a Celsius.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS16.png)

Para probar dentro de la función lambda que se está ejecutando dentro del ```.jar``` el método **square**, y que si está retornando resultados correctos, se realiza clic primero en el botón **Probar**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS17.png)

Para probarlo, primero se establece de nombre de la prueba **testSquare**, y se ingresa el valor en Fahrenheit, que en este caso es **32**. Luego, se realiza clic en el botón **Invoke (Invocar)** para realizar la respectiva invocación.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS18.png)

Como se puede observar a continuación, la prueba ha sido compilado exitosamente, en la cual se evidencia que el código está retornando el valor correcto, ya que 32 grados Fahrenheit son 0 grados Celsius, como puede ser observado en pantalla. 

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS19.png)

----------

### Creación de la API REST

Para realizar la respectiva creación de la API REST, primero se ingresa a la Dashboard de AWS, en la cual se realiza clic en el botón **API Gateway**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS20.png)

Para crear la API REST y usar la función lambda correspondiente, se realiza clic en el botón **Crear**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS21.png)

Al ingresar a la interfaz de la API REST de AWS, se despliega un mensaje de bienvenida, en el cual nos indica que se ha rellenado previamente el formulario de importación con una API predefinida. Para continuar, se realiza clic en el botón **Aceptar**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS22.png)

Para crear una API completamente nueva, se realiza clic en el botón **API nueva**, y en la configuración se establece de nombre de la API **mathServices**, y se selecciona el tipo de enlace como **Regional**. Luego se realiza clic en el botón **Crear API**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS23.png)

Para crear el método GET en la API, se realiza clic en el botón **Acciones**, para posteriormente realizar clic en el botón **Crear método**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS24.png)

Luego se selecciona **GET** en el menú desplegable para iniciar a configurar el método **GET** correspondiente.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS25.png)

Ahora en el tipo de integración se selecciona **Función Lambda**, la región se selecciona la misma región en la que se encuentra AWS, que en este caso es **Norte de Virginia**, el cual es **us-east-1**. Luego se selecciona en función lambda la que ha sido creada anteriormente, que es **square**, y se habilita la opción de usar el tiempo de espera predeterminado.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS26.png)

Para concederle el permiso correspondiente a la función lambda, se realiza clic en el botón **Aceptar**. 

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS27.png)

Luego de concederle el permiso correspondiente a la función lambda, se realiza clic en el botón **Solicitud de método**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS28.png)

En la sección parámetros de cadenas de consulta de URL, se agrega un nuevo parámetro, en el cual se establece de nombre **value**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS29.png)

Luego de agregar de nombre **value**, se realiza clic en el botón **Ejecución de método** para devolverse.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS30.png)

Ahora se realiza clic en el botón **Solicitud de integración** para realizar la respectiva integración de la API.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS31.png)

En el nombre, seleccionamos el que se ha creado anteriormente, que es **value**, y a **value** se le agrega su respectivo mapeo, que es **method.request.querystring.value**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS32.png)

Luego en la sección plantillas de mapeo, se selecciona la opción **Cuando no haya definida ninguna plantilla (recomendado)**, y en content type se agrega **application/json**. Posteriormente, se crea la plantilla, en la cual se escribe el valor en Fahrenheit, que en este caso es **41**, para posteriormente probar el funcionamiento de la API. Para guardar la plantilla, se realiza clic en el botón **Guardar**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS33.png)

Luego de guardar la plantilla, se realiza clic en el botón **Ejecución de método**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS34.png)

Para probar el correcto funcionamiento de la API, se realiza clic en el botón **PRUEBAS**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS35.png)

Para probar directamente la API, se realiza clic en el botón **Pruebas** para observar si la API está ejecutando correctamente la función lambda.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS36.png)

Como se observa a continuación, la prueba ha sido exitosa, ya que ha retornado el valor convertido a Celsius, ya que 41 grados Fahrenheit son 5 grados Celsius, demostrando así que la API funciona correctamente.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS37.png)

Luego de realizar la prueba correspondiente, se realiza clic en el botón **Ejecución de método**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS38.png)

Ahora, se vuelve a realizar clic en el botón **Solicitud de integración**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS39.png)

En la plantilla ya anteriormente creada, se cambia el valor anteriormente ingresado (41) por **$input.params("value")**, para que la API ahora funcione con cualquier valor ingresado por el usuario. Para guardar los cambios hechos a la plantilla, se realiza clic en **Guardar**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS40.png)

Para probar la API, se realiza clic en **Pruebas**, para posteriormente en el campo provisto por cadenas de consulta, se escriba **value=32**. Para probar con ese valor ya ingresado, se realiza clic en el botón **Pruebas**. Como se observa a continuación, la API funciona ahora correctamente con cualquier valor que ingrese el usuario, ya que 32 grados Fahrenheit, son 0 grados Celsius.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS41.png)

Para implementar la API como tal, se realiza clic en el botón **Acciones**, para posteriormente realizar clic en el botón **Implementar la API**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS42.png)

En etapa de implementación se selecciona **[Nueva etapa]**. En nombre de la fase se establece como **Beta**, en descripción de la etapa se establece **Beta Stage** y, por último, en descripción de implementación se establece **Beta deployment of math services**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS43.png)

Luego de realizar la implementación, se dejan los valores predeterminados que se encuentran a continuación. Para guardar todo lo anteriormente realizado, se realiza clic en el botón **Guardar los cambios**.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS44.png)

Para probar el funcionamiento de la API con **41** grados Fahrenheit, se ingresa la URL de la API en el navegador, que es: https://sr7u3afts5.execute-api.us-east-1.amazonaws.com/Beta?value=41, añadiéndole al final de la URL **?value=41** para probar con dicho valor. Como se puede observar a continuación, el resultado que retorna la API es correcto, ya que **41 grados Fahrenheit** son **5 grados Celsius**, demostrando que ya la API funciona correctamente.

![img](https://github.com/Skullzo/AREP-Lab7/blob/main/img/AWS45.PNG)

----------

### Docker

Para verificar que en la aplicación Docker se hayan desplegado con éxito los contenedores en sus respectivos puertos, se abre la aplicación de Docker de escritorio y se hace la verificación que todos los contenedores estén corriendo en sus respectivos puertos. Como se ve en la siguiente imagen, todos los contenedores están corriendo satisfactoriamente.

![img](https://github.com/Skullzo/AREP-Parcial2/blob/main/img/Docker1.PNG)

Para comprobar que la página web ha sido desplegada con éxito de manera local utilizando Docker, y calcular el ArcoTangente del valor ingresado (en este caso será 90) se ingresa en el navegador la siguiente URL: http://localhost:3456/atan?value=90. Como se puede observar, el contenedor ha sido desplegado satisfactoriamente de manera local utilizando Docker.

![img](https://github.com/Skullzo/AREP-Parcial2/blob/main/img/Docker2.PNG)

Para comprobar que la página web ha sido desplegada con éxito de manera local utilizando Docker, y calcular el Logaritmo del valor ingresado (en este caso será 50) se ingresa en el navegador la siguiente URL: http://localhost:3456/log?value=50. Como se puede observar, el contenedor ha sido desplegado satisfactoriamente de manera local utilizando Docker.

![img](https://github.com/Skullzo/AREP-Parcial2/blob/main/img/Docker3.PNG)

----------

### AWS
Para comprobar que la página web ha sido desplegada con éxito utilizando AWS, y calcular el ArcoTangente del valor ingresado (en este caso será 90) se ingresa en el navegador la siguiente URL: http://localhost:3456/atan?value=90. Como se puede observar, el contenedor ha sido desplegado satisfactoriamente en AWS.

![img](https://github.com/Skullzo/AREP-Parcial2/blob/main/img/AWSS1.PNG)

Para comprobar que la página web ha sido desplegada con éxito utilizando AWS, y calcular el Logaritmo del valor ingresado (en este caso será 50) se ingresa en el navegador la siguiente URL: http://localhost:3456/log?value=50. Como se puede observar, el contenedor ha sido desplegado satisfactoriamente en AWS.

![img](https://github.com/Skullzo/AREP-Parcial2/blob/main/img/AWSS2.PNG)

## Construido con
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [JUnit](https://junit.org/junit5/). Framework de Java que permite la realización de la ejecución de clases de manera controlada, para poder comprobar que los métodos realizan su cometido de forma correcta.
* [NetBeans](https://netbeans.apache.org/). Entorno de desarrollo integrado libre, orientado principalmente al desarrollo de aplicaciones Java. La plataforma NetBeans permite el desarrollo de aplicaciones estructuradas mediante un conjunto de componentes denominados “módulos”. Cada uno de estos módulos sería un archivo Java conteniendo un conjunto de clases que interactarán con las APIs de NetBeans. El objetivo de esta arquitectura es favorecer el desarrollo de funcionalidades de forma independiente y la reutilización de componentes.
* [Java](https://www.oracle.com/java/). Lenguaje de programación de propósito general, es decir, que sirve para muchas cosas, para web, servidores, aplicaciones móviles, entre otros. Java también es un lenguaje orientado a objetos, y con un fuerte tipado de variables.
* [Docker](https://www.docker.com/). Programa encargado de crear contenedores ligeros y portables para las aplicaciones software que puedan ejecutarse en cualquier máquina con Docker instalado, independientemente del sistema operativo que la máquina tenga por debajo, facilitando así también los despliegues.
* [AWS](https://aws.amazon.com/es/). Conjunto de herramientas y servicios de cloud computing de Amazon, que engloba una gran cantidad de servicios para poder realizar distintos tipos de actividades en la nube. Desde almacenamiento a la gestión de instancias, imágenes virtuales, desarrollo de aplicaciones móviles, etc.
* [CircleCI](https://circleci.com/). Plataforma moderna de integración continua y entrega continua (CI / CD) que se encarga de automatizar la construcción, pruebas e implementación de software.

     [![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://app.circleci.com/pipelines/github/Skullzo/AREP-Parcial2)

## Autor
[Alejandro Toro Daza](https://github.com/Skullzo)
## Licencia & Derechos de Autor
**©** Alejandro Toro Daza, Estudiante de Ingeniería de Sistemas de la [Escuela Colombiana de Ingeniería Julio Garavito](https://www.escuelaing.edu.co/es/).

Licencia bajo la [GNU General Public License](https://github.com/Skullzo/AREP-Parcial2/blob/main/LICENSE).
