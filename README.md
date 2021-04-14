# Reto Pokemon parte 1

## Pre-requisitos

* Tener Java 11 instalado
* Comprobar los permisos del fichero gradlew (este fichero se encuentra en la raíz del proyecto, y es lo que se emplea para lanzar la aplicación).
Es muy probable que la terminal muestre que no tenemos permisos para ejecutarlo, por lo que habrá que cambiarlos mediante `chmod u+x gradlew`
* Tener algún programa como Postman (la parte de la API no es necesario probarla con Postman, pero este tipo de programas hace que dicha labor sea más sencilla por la interfaz visual de este tipo de aplicaciones como Postman)

## Puesta en marcha

1) Para asegurar que partimos de un proyecto fresco, ejecutar `./gradlew clean`
2) Para construir la aplicación, ejecutar `./gradlew quarkusBuild`
3) Para lanzar la aplicación, ejecutar `./gradlew quarkusDev`

## Probar la aplicación

La aplicación se puede usar de dos maneras:
1) Desde la consola
2) Mediante peticiones HTTP

### Desde la consola

1) En la misma terminal desde la que se lanzó el proyecto, escribir el nombre de un Pokémon y pulsar Enter
1.1) En caso de que el Pokémon exista, se mostrarán los tipos del Pokémon
1.2) En caso de que el Pokémon no exista, se mostrará un mensaje (en la propia consola) indicando que no hay ningún Pokémon con el nombre introducido
2) Para salir de la consola, escribir "" y pulsar Enter

### Mediante peticiones HTTP

1) Suponiendo que se usa un programa tipo Postman 
1.1) Será necesario crear una nueva petición de tipo GET
1.2) La URL a la que hacer peticiones será la siguiente: http://localhost:8081/pokemon/types?name=charizard
1.3) En lugar de charizard, se puede escribir en nombre de cualquier otro Pokémon
2) Suponiendo que se usan comandos de terminal como "curl"
2.1) Ejecutar curl http://localhost:8081/pokemon/types?name=charizard
2.2) En lugar de charizard, se puede escribir en nombre de cualquier otro Pokémon