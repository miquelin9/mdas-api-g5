## Hace todos los puntos pedidos (40%)

#### Permite crear usuarios vía endpoint

OK

#### Permite añadir favoritos vía endpoint

OK

#### Si el pokemon ya está marcado como favorito, ¿se lanza una excepción de dominio?

OK

#### Si el usuario no existe, ¿se lanza una excepción de dominio?

OK

#### Si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

OK

#### Hay tests unitarios

OK, aunque os recomiendo leer las observaciones y para la siguiente, hacerlos más legibles.

**Puntuación: 38/40**

## Se aplican conceptos explicados (40%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

OK

#### Aggregates + VOs

OK

#### No se trabajan con tipos primitivos en dominio

OK

#### Hay servicios de dominio

OK

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK, aunque teneis más métodos de los necesarios. Ejemplo: `search`, `delete`

#### Se utilizan object mothers

No hay. Hay muchos NEWs, y en algunos casos no son necesarios.
Ejemplo: `AddUserUseCaseTest.verify_createUser_throwsUserAlreadyExistsException()`

**Puntuación: 35/40**

## Facilidad setup + README (20%)

#### El README contiene al menos los apartados "cómo ejecutar la aplicación", "cómo user la aplicación"

No viene indicado cómo meter el userId, por defecto coge el id 0. Se explica como advertencia algo sobre el header, pero
no se indica que valor es el que se tiene que introducir como header
(`userId`, `user-id`, o el que corresponda). El resto, OK

#### Es sencillo seguir el apartado "cómo ejecutar la aplicación"

OK

**Puntuación: 15/20**

## Observaciones

- El status code 403 es FORBIDDEN para el caso de añadir un user que ya existe. Debería ser 409 CONFLICT. 403 se utiliza
  cuando no tienes acceso a un recurso
- El status code asociado a la creación es el 201 (CREATED), no el 200 (OK)
- El status code a la hora de añadir un pokemon favorito cuando el user no existe es un 403 FORBIDDEN cuando debería ser
  un 404 NOT FOUND
- Cuando se crea un recurso se utiliza el método POST, NUNCA GET.
- Cuando se quiere actualizar un recurso, podemos utilizar POST, PUT o PATCH (depende del tipo de actualizacion), NUNCA
  GET.
- No nombrar a las variables del tipo `_varX`.
- Tenéis muchos métodos y variables que no usais. Ejemplo: `AddUserUseCaseTest` o `User`, cread únicamente lo necesario.
- Podeis simplificar mucho el código de los métodos de tests. Ejemplo: en `AddFavouritePokemonToUserTest` los mocks
  podrían estan en una variable de clase y reutilizarlos en todos los métodos de este test. Esto los haría más legibles
  y rápidos de leer.
- Si seguimos SRP (principio de responsabilidad única) los controladores, al igual que el resto de las clases, deberían
  tener una única responsabilidad por lo tanto: un controlador = 1 método.

Sé que esta asignatura no trata de cómo hacer una API REST, como aplicar principios SOLID, cómo hacer más o menos CLEAN
CODE o cómo hacer testing y por tanto, no os penalizo mucho por ello, pero deberíais tener en cuenta estas cosas para
próximas prácticas ;-)

**PUNTUACIÓN FINAL: 88/100**
