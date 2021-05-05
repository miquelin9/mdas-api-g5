## Hace todos los puntos pedidos (40%)

#### Permite obtener los detalles de un pokemon vía endpoint

Ok

#### Si no existe el pokemon, ¿se lanza una excepción de dominio?

Ok

#### Si la api da timeout, ¿se lanza una excepción de dominio?

Ok, aunque la excepción tiene un nombre muy de "infra": `NetworkConnectionException`, ya que el día que se migre de una
API a una base de datos, esta excepción no tendría mucho sentido

#### Si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

Ok

#### Tests de aceptación

- El test de aceptación hace peticiones directas a la poke-api en vez de mockear esta parte.

#### Tests de integración

- El test de integración hace peticiones directas a la poke-api en vez de mockear esta parte.


**Puntuación: 30/40**

## Se aplican conceptos explicados (40%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

Ok

#### Aggregates + VOs

Ok

#### No se trabajan con tipos primitivos en dominio

Ok

#### Hay servicios de dominio

Ok

#### Hay use cases en aplicación reutilizables

Ok

#### Se aplica el patrón repositorio

Ok

#### Se utilizan object mothers

No

**Puntuación: 35/40**

## Facilidad setup + README (20%)

#### El README contiene al menos los apartados "cómo ejecutar la aplicación", "cómo user la aplicación"

Ok

#### Es sencillo seguir el apartado "cómo ejecutar la aplicación"

Ok

**Puntuación: 20/20**

## Extras

- Habéis añadido tests de aceptación e integración en el contexto de user, ¡bien hecho!

**Puntuación: +5**

## Observaciones
- Tened en cuenta que `valueObjects` no es una notación correcta para los paquetes en java
> Package names are written in all lower case to avoid conflict with the names of classes or interfaces.

[Fuente](https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html#:~:text=Naming%20Conventions,a%20programmer%20at%20example.com%20)

- La url ya lleva un GET, no hace ponerlo en la url en sí, sería mejor: `http://localhost:8081/pokemon/1`

**PUNTUACIÓN FINAL: 90/100**