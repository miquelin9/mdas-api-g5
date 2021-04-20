# Evaluación reto 1

## Hace todos los puntos pedidos (40%)
#### Dado un nombre vía argumento, devolver sus tipos
OK, aunque devuelve un JSON y se pedía como un listado de tipos separados por comas:
Input: `charizard`; Output: `fire, flying`

Además tampoco se pedía que devolviese el id :P. 
El modelo en dominio puede que tenga id, pero eso no significa que se tenga que devolver por el output 

#### Dado un nombre vía endpoint, devolver sus tipos
OK, aunque lo del id también aplica a este caso

#### Si no existe el pokemon, ¿se lanza una excepción de dominio?
OK

#### Si la api da timeout, ¿se lanza una excepción de dominio?
La API da un 200 con un mensaje `Unexpected error. null` y la aplicación deja una traza de error,
debería devolver un código de error esperado y que se traduzca
en la capa de infraestructura desde una excepción de dominio.

Ejemplo para reproducirlo: no teniendo conexión a internet

#### Si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP/un error legible en consola?
Por CMD: Sí aunque...entra en un bucle infinito :'(

Por HTTP: Devuelve un 200 y rompe contrato con el cliente, ya que usando el mismo HTTP status code, a veces devuelve "types" y a veces "message".
Quizá sería mejor idea devolver un 404 NOT FOUND cuando no se encuentra el pokemon y un 200 OK cuando todo va bien :)

**Error principal: No diferenciar entre el output de consola y de API http**

**Puntuación: 28/40**

## Se aplican conceptos explicados (40%)
#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)
Están las capas definidas en packages. Sin embargo, hay algunos problemas importantes.

Lo más problemático es que en la capa de aplicación es donde se construye el agregado `PokemonType`. 
En este caso, lo que devuelve la API de pokemons es un JSON que la propia infraestructura debería
apañárselas para cumplir el contrato definido en dominio. No es incorrecto que haya un `PokemonTypesParser`
o algo similar, pero siempre siendo ejecutado en infraestructura. 

El flujo que podríais seguir para mejorarlo es:

Controller [infra] -> Use Case [application] -> Searcher [domain service] 
-> Repository interface [domain] <- Repository implementation [infra]

- Del controller al use case van tipos primitivos y devuelve una respuesta HTTP. 
- Del use case al searcher van Value Objects y devuelve un listado de Agregados.
- Del Searcher al Repository interface van Value Objects y devuelve un listado de Agregados.
- La implementación de la interfaz del repositorio (Dependency Inversion) hace una petición HTTP, 
la cual retornará un JSON y esta misma implementación será la encargada de convertirlo a un 
Agregado.
  
En vuestro caso quedaría algo como
`PokemonController` [infra] -> `GetPokemonTypeUseCase` [application]
-> `PokemonTypesSearcher` [domain] -> `PokemonTypesRepository` [domain interface] 

Y habría una implementación de `PokemonTypeRepository` en infraestructura que podría llamarse `HTTPApiPokemonTypeRepository`. 
Ésta implementación podría hacer uso de un `PokemonTypeParser` para asegurarse que 
el JSON se convierta a un agregado. **Pero debe estar dentro de infra**.

#### Aggregates + VOs
Hay agregados y VOs, aunque en la clase `PokemonType` hay una conversión 
en el método `toString()` a JSON.
JSON es un formato específico de comunicación y, por tanto, es algo que debería estar en la parte de infraestructura
(no en dominio como lo habéis implementado vosotros).

#### No se trabajan con tipos primitivos en dominio
Aunque en el agregado es correcto porque se usan dentro de él Value Objects,
en las interfaces que están en dominio se usan los tipos primitivos `String` y `Object`.
En el servicio de dominio solo debe haber Value Objects y Agregados.

#### Hay servicios de dominio
Explicado en "Separación correcta de capas"

#### Hay use cases en aplicación reutilizables
OK

#### Se aplica el patrón repositorio
Explicado en "Separación correcta de capas". Este reto fue planteado hasta la sesión 3,
y dado que el patrón repository no se explicó hasta la sesión 4, este punto no es penalizable,
únicamente os lo dejo por aquí como anotación

**Error principal: ¡No hay servicios de dominio y se tratan datos de infra en dominio!**

**Puntuación: 15/40**

## Facilidad setup + README (20%)
#### El README contiene al menos los apartados "cómo ejecutar la aplicación", "cómo user la aplicación"
OK

#### Es sencillo seguir el apartado "cómo ejecutar la aplicación"
OK

**Puntuación: 20/20**

## Extra

## Observaciones
- Hay un Dockerfile que no se indica cómo usarse desde el README.
Además, aparentemente no funciona o se ha copy/pasteado de otra app 
  que tiene que ver con sensores ;)
  
- Acordaos de revisar los imports, hay algunos sin usar
- Intentad hacer commits pequeños y legibles (de fácil rollback)
- Mejor si la lista de pokemon types está encapsulada en un objeto 
como podría ser `PokemonTypes`. "First Class Citizens". 
  
- ¿Por qué rabbitmq en el `build.gradle`?

**PUNTUACIÓN FINAL: 63/100**