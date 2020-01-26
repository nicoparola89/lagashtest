# Ejercicio cache

Proyecto de entreviste Lagash

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo.r._

### Pre-requisitos 📋

_Que cosas necesitas para correr el proyecto_

```
Java 8
Git
Maven 
Docker
Docker Compose
```

### Instalación 🔧
Clonar el repositorio [nicoparola89/lagashtest](https://github.com/nicoparola89/lagashtest.git)
#### Local

Para ejecutar el proyecto localmente:

1- Compilar  el proyecto.

En la consola ejecutar.
```
./mvnw clean package
```
2- Ejecutar el proyecto docker-compose

En la consola ejecutar en la raiz del proyecto 

```
docker-compose up
```
Listo! Al api esta lista para usar.



_Para finalizar, un ejemplo de como utilizar la API_

Recurso para obtener un Customer y registrarlo en nuestra cache

Metodo: GET

URL: localhost:8080/api/v1/customer/random

Listo, ya tenemos nuestro registro en la cache.

Recordar que este va a estar activo por 5 minutos.


### Aclaración, en application.properties del proyecto podemos encontrar 2 claves.

Tiempo de expiración de registro en cache, en unidad de segundos.

app.timeexpire.cache = 300

Endpoint donde vamos a conseguir nuestros datos.

app.url.datasource =   "http://interview.lagash.com.ar/api/customer/byScore/"

Si queremos modificar estos valores, se modifican en application.propeties del proyecto, y luego se vuelven a ejecutar los pasos 1 y 2.


