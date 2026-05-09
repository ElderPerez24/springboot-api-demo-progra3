# Workshop Spring Boot 3.x + JDK 17 (Maven)

Este proyecto fue evolucionando por incrementos, agregando endpoints, validaciones, manejo global de errores, documentación con Swagger/OpenAPI y, en esta etapa, persistencia con **Spring Data JPA** y **PostgreSQL**.

## Objetivo del taller

- Practicar arquitectura básica de API REST con Spring Boot.
- Aplicar validaciones con `jakarta.validation`.
- Manejar errores de forma global.
- Documentar la API con Swagger/OpenAPI.
- Implementar endpoints de negocio.
- Integrar persistencia con `Spring Data JPA`.
- Conectar y trabajar con `PostgreSQL`.
- Modelar entidades relacionadas y exponer CRUD completo.

---

## Requisitos

- Java `17`
- Maven `3.9+`
- PostgreSQL instalado

### Verificar Java

```bash
java -version
```

Debe mostrar `17.x`.

### Verificar Maven

```bash
mvn -version
```

Debe mostrar `3.9+`.

## Proyecto base del taller

Este proyecto parte del workshop inicial de Spring Boot, donde se trabajó con:

- Endpoint base `/api/v1`
- Endpoint de saludos
- Validaciones con `jakarta.validation`
- Manejo global de errores
- Swagger/OpenAPI
- Endpoint de simulación de préstamo

Posteriormente se agregó un incremento progresivo para trabajar con persistencia usando JPA y PostgreSQL.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Validation
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI
- JUnit 5
- MockMvc

## Configuración de base de datos

Para esta etapa se utilizó una base de datos PostgreSQL llamada:

```text
workshop_jpa
```

### Configuración usada en `application.properties`

```properties
spring.application.name=springboot-api-demo
server.port=8081

spring.datasource.url=jdbc:postgresql://localhost:5432/workshop_jpa
spring.datasource.username=postgres
spring.datasource.password=TU_CONTRASENA
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### Explicación de `ddl-auto`

Se utilizó:

```properties
spring.jpa.hibernate.ddl-auto=update
```

porque permite que Hibernate cree o actualice automáticamente las tablas a partir de las entidades JPA, lo que facilita el desarrollo local.

En un entorno de producción, lo recomendable sería usar `validate` junto con herramientas de migración como Flyway o Liquibase.

## Estructura general del proyecto

```text
src/main/java
└── com.ejemplo.demo
    ├── api
    │   ├── controller
    │   │   ├── CategoriaController.java
    │   │   ├── DemoEstadoController.java
    │   │   ├── ProductoController.java
    │   │   ├── SaludoController.java
    │   │   └── SimulacionController.java
    │   ├── dto
    │   │   ├── CategoriaRequest.java
    │   │   ├── CategoriaResponse.java
    │   │   ├── ProductoRequest.java
    │   │   ├── ProductoResponse.java
    │   │   ├── EstadoResponse.java
    │   │   └── ErrorResponse.java
    │   └── exception
    │       └── GlobalExceptionHandler.java
    ├── domain
    │   ├── exception
    │   │   └── ResourceNotFoundException.java
    │   ├── model
    │   │   ├── Categoria.java
    │   │   └── Producto.java
    │   ├── repository
    │   │   ├── CategoriaRepository.java
    │   │   └── ProductoRepository.java
    │   └── service
    │       ├── CategoriaService.java
    │       ├── ProductoService.java
    │       └── estado
    │           ├── EstadoManual.java
    │           └── EstadoSingletonService.java
    └── SpringbootApiDemoApplication.java

src/test/java
└── com.ejemplo.demo.api.controller
    └── CategoriaControllerTest.java
```

## Funcionalidades del proyecto base

### Endpoint base

```bash
curl http://localhost:8081/api/v1
```

### Saludos

- `GET /api/v1/saludos`
- `POST /api/v1/saludos`

### Simulación de préstamo

- `POST /api/v1/simulaciones/prestamo`

Estas funcionalidades forman parte del trabajo anterior del workshop y se mantienen dentro del proyecto.

## Incremento progresivo: Persistencia con JPA

Como parte del avance actual, se integró persistencia usando Spring Data JPA y PostgreSQL.

### Dependencias agregadas en `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Entidades implementadas

### Categoria

Campos:

- `id`
- `nombre`
- `descripcion`
- `creadoEn`
- `actualizadoEn`

### Producto

Campos:

- `id`
- `nombre`
- `sku`
- `precio`
- `stock`
- `categoria`
- `creadoEn`
- `actualizadoEn`

### Relación

- Una categoría tiene muchos productos.
- Un producto pertenece a una categoría.

Esto se implementó con:

```java
@OneToMany(mappedBy = "categoria")
@ManyToOne
@JoinColumn(name = "categoria_id")
```

## Repositorios implementados

Se crearon repositorios con `JpaRepository` para acceso a datos:

- `CategoriaRepository`
- `ProductoRepository`

Además, se agregaron métodos derivados del nombre para validaciones simples, por ejemplo:

- Verificar si ya existe una categoría con el mismo nombre.
- Verificar si ya existe un producto con el mismo SKU.

## Servicios implementados

Se implementaron servicios de dominio para separar la lógica de negocio de los controladores:

- `CategoriaService`
- `ProductoService`

### Funciones principales

- listar
- obtener por id
- crear
- actualizar
- eliminar

### Validaciones aplicadas

- No permitir categorías duplicadas por nombre.
- No permitir productos duplicados por SKU.
- No permitir crear productos con categorías inexistentes.

## DTOs implementados

Para no exponer directamente las entidades JPA en la API, se crearon DTOs de entrada y salida.

### Categorías

- `CategoriaRequest`
- `CategoriaResponse`

### Productos

- `ProductoRequest`
- `ProductoResponse`

## APIs REST implementadas

### Categorías

Base URL:

```text
/api/v1/categorias
```

Operaciones:

- `GET /api/v1/categorias`
- `GET /api/v1/categorias/{id}`
- `POST /api/v1/categorias`
- `PUT /api/v1/categorias/{id}`
- `DELETE /api/v1/categorias/{id}`

### Productos

Base URL:

```text
/api/v1/productos
```

Operaciones:

- `GET /api/v1/productos`
- `GET /api/v1/productos/{id}`
- `POST /api/v1/productos`
- `PUT /api/v1/productos/{id}`
- `DELETE /api/v1/productos/{id}`

## Validaciones implementadas

### `CategoriaRequest`

- Nombre obligatorio.
- Descripción obligatoria.

### `ProductoRequest`

- Nombre obligatorio.
- SKU obligatorio.
- Precio mayor que cero.
- Stock no negativo.
- `categoriaId` obligatoria.

## Manejo global de errores

Se reutilizó `GlobalExceptionHandler` y se amplió para manejar:

- `400 BAD REQUEST` para validaciones
- `400 BAD REQUEST` para reglas de negocio
- `404 NOT FOUND` para recursos inexistentes
- `500 INTERNAL SERVER ERROR` para errores inesperados

También se creó la excepción:

- `ResourceNotFoundException`

## Swagger / OpenAPI

La documentación de la API se encuentra disponible en:

```text
http://localhost:8081/swagger-ui/index.html
```

Desde Swagger se probaron correctamente los endpoints de:

- categorías
- productos
- endpoints del proyecto base

## Pruebas realizadas

### Pruebas manuales verificadas

- Creación válida de categoría → `201`
- Validación fallida en categoría → `400`
- Categoría inexistente → `404`
- Creación de producto → `201`
- Listado de productos → `200`

### Prueba automatizada implementada

- `CategoriaControllerTest`

### Casos cubiertos

- Creación válida → `201`
- Validación fallida → `400`
- Recurso inexistente → `404`

### Ejecutar pruebas

```bash
mvn test
```

Resultado esperado:

```text
BUILD SUCCESS
```

## Ejemplos de uso

### Crear categoría

```json
{
  "nombre": "Laptops",
  "descripcion": "Equipos portatiles"
}
```

### Crear producto

```json
{
  "nombre": "Lenovo ThinkPad",
  "sku": "LAP-001",
  "precio": 5999.99,
  "stock": 10,
  "categoriaId": 1
}
```

## Ejecución del proyecto

### Desde Eclipse

- Clic derecho al proyecto
- `Run As > Java Application`

### Desde consola

```bash
mvn spring-boot:run
```

## Paso 9: Contract-first con OpenAPI Generator

En este avance se agregó el enfoque **contract-first**. Se creó el contrato `src/main/resources/openapi/openapi.yaml` con las rutas existentes del workshop, saludos, simulación de préstamo, categorías, productos y la demo de estado.

También se configuró `openapi-generator-maven-plugin` en `pom.xml` para generar interfaces Spring desde el YAML durante `generate-sources`, usando los DTOs existentes del proyecto para no duplicar modelos. Los controladores ahora implementan las interfaces generadas y mantienen las mismas rutas y la misma forma del JSON ya probado.

Se agregó la demo `singleton/manual` solicitada para comparar el estado mantenido por un `@Service` de Spring contra una instancia creada manualmente con `new`.

Comando para generar las interfaces:

```bash
mvn generate-sources
```

Verificación final:

```bash
mvn test
```

## Checklist final

- [x] Proyecto corre en local
- [x] `GET /api/v1` responde OK
- [x] `GET /api/v1/saludos` habilitado
- [x] `POST /api/v1/saludos` habilitado y validando
- [x] Reglas de negocio implementadas
- [x] Manejo de errores de negocio implementado
- [x] Swagger/OpenAPI habilitado y accesible
- [x] Endpoint de simulación implementado
- [x] Incremento JPA con PostgreSQL implementado
- [x] Entidades relacionadas creadas
- [x] CRUD de categorías implementado
- [x] CRUD de productos implementado
- [x] Validaciones funcionando
- [x] Manejo de 404 implementado
- [x] Pruebas pasando (`mvn test`)
- [x] Paso 9: contrato YAML agregado
- [x] Paso 9: interfaces Spring generadas desde Maven
- [x] Paso 9: controladores implementan interfaces generadas
- [x] Paso 9: demo singleton/manual agregada

## Conclusión

Con este avance, el proyecto evolucionó desde un workshop base de Spring Boot hacia una aplicación con persistencia real usando JPA y PostgreSQL. Se mantuvo una arquitectura ordenada por capas, con DTOs, repositorios, servicios, controladores, validaciones, manejo global de errores, documentación en Swagger y pruebas automatizadas funcionales.