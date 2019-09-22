# Transformación Digital y a Arquitectura Empresarial.
## Proyecto primer tercio

### Serive and FrameWork 

El servidor **No** concurrente capaz de entregar páginas html e imágenes tipo JPG. Igualmente el servidor provee un framework IoC para la construcción de aplicaciones web a partir de POJOS.  aplicación Web desplegada en Heroku.

### Prerequisitos

* Java 1.8
* Maven 3.6.0 

### Cómo utilizarla
Cada POJOs tendra como link /apps/nombreDelPOJos

nombreDelPOJos es el nombre dentro de la etiquieta @WEB("nombre")

Si el POJOs tiene parametros se deben pasar de la siguiente manera

/apps/nombreDelPOJos:param1,param2,param3..

## Ejecutar
### Descagar proyecto y descargar sus dependencias y compilar
```console
arep@arep:~$ git https://github.com/CrkJohn/PY1AREP.git
arep@arep:~$ cd PY1AREP.git 
arep@arep:~$ mvn package
```
### Ejecutar app Local sin heroku 
```console
arep@arep:~$ java -cp target/classes:target/dependency/* edu.escuelaing.arem.Controler
```
### Ejecutar app Local con heroku 
```console
arep@arep:~$ Heroku local web
```

### Generar documentación
```console
arep@arep:~$ mvn javadoc:jar
```

### Deploy
Para ver la pagina web, [Click aqui](https://py1arep.herokuapp.com/CrkJohn.html)
* Responde a los siguientes links
  * https://py1arep.herokuapp.com/?img=github.jpg
  * https://py1arep.herokuapp.com/CrkJohn.html
  * https://py1arep.herokuapp.com/CrkJohn2.html
  * https://py1arep.herokuapp.com/Escuela.html (NO tienes CSS o JS)
* Responde a la siguientes apps
  * https://py1arep.herokuapp.com/apps/greeting - Resultado -> Hello world! , the POJOs works
  * https://py1arep.herokuapp.com/apps/greetingparam:CrkJohn - Resultado --> Hello CrkJohn! , the POJOs works; Doonde CrkJohn es la variable de la url
* Cualquier otra pagina, será respondido con 404


### Licence 

PY1AREP está licenciado  por GNU General Public License v3.0, ver [licencia](https://github.com/CrkJohn/PY1AREP/blob/master/LICENSE) para más detalles.

### Autor

John David Ibañez - [CrkJohn](https://github.com/CrkJohn)

Escuela colombina de ingenieria Julio Garavito. 
