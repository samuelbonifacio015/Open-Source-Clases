# Guía de Desarrollo Backend - Catch-Up Platform

## Repositorio del proyecto:
Proyecto de práctica utilizando Spring Boot con arquitectura DDD (Domain-Driven Design)

---

# Orden de pasos para crear un Backend con Spring Boot

## Paso 1: Configuración Inicial del Proyecto

### pom.xml - Gestión de Dependencias

Lista de dependencias principales:

```xml
<dependencies>
    <!-- Spring Boot Web para crear APIs REST -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA para persistencia -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Validación de datos -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- Base de datos MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    
    <!-- Lombok para reducir código boilerplate -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

**Comando para instalar dependencias:**
```bash
mvn clean install
```

---

## Paso 2: Setup de Configuración

### application.properties

Archivo de configuración para conectar con la base de datos y configurar Spring Boot:

```properties
# Nombre de la aplicación
spring.application.name=catch-up-platform

# Configuración de Base de Datos MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/catch-up-os-7380?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=12345678
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

# Configuración JPA/Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Estrategia de nomenclatura (snake_case para tablas)
spring.jpa.hibernate.naming.physical-strategy=com.acme.catchup.platform.shared.infrastructure.persistence.jpa.strategy.SnakeCasePhysicalNamingStrategy
```

**Notas importantes:**
- `ddl-auto=update`: Actualiza automáticamente el esquema de la base de datos
- `show-sql=true`: Muestra las consultas SQL en consola (útil para desarrollo)
- La estrategia de nomenclatura convierte nombres de clases Java a snake_case en la BD

---

## Paso 3: Estructura del Proyecto (Bounded Context)

### Organización de carpetas según DDD:

```
|📂 src/main/java/com/acme/catchup/platform/
│
├── 📂 news/                              # Bounded Context (Contexto de Negocio)
│   │
│   ├── 📂 domain/                        # Capa de Dominio (Lógica de Negocio)
│   │   └── 📂 model/
│   │       ├── 📂 aggregates/            # Entidades principales (Aggregate Roots)
│   │       │   └── FavoriteSource.java   # Entidad raíz del agregado
│   │       ├── 📂 commands/              # Comandos (escritura/modificación)
│   │       │   └── CreateFavoriteSourceCommand.java
│   │       └── 📂 queries/               # Consultas (solo lectura)
│   │           └── GetAllFavoriteSourcesByNewsApiKeyQuery.java
│   │           └── GetFavoriteSourceByIdQuery.java
│   │   │
│   │   └── 📂 services/                  # Interfaces de servicios
│   │       └── FavoriteSourceCommandService.java
│   │       └── FavoriteSourceQueryService.java
│   │
│   ├── 📂 application/                   # Capa de Aplicación (Casos de Uso)
│   │   ├── 📂 commandservices/           # Implementación de comandos
│   │   │   └── FavoriteSourceCommandServiceImpl.java
│   │   └── 📂 queryservices/             # Implementación de consultas
│   │       └── FavoriteSourceQueryServiceImpl.java
│   │
│   ├── 📂 infrastructure/                # Capa de Infraestructura (Persistencia)
│   │   └── 📂 persistence/
│   │       └── 📂 jpa/
│   │           └── FavoriteSourceRepository.java  # Repositorio JPA
│   │
│   └── 📂 interfaces/                    # Capa de Presentación (API REST)
│       ├── FavoriteSourcesController.java         # Controlador REST
│       ├── 📂 resources/                 # DTOs de entrada/salida
│       │   └── CreateFavoriteSourceResource.java  # DTO para crear
│       │   └── FavoriteSourceResource.java        # DTO de respuesta
│       └── 📂 transform/                 # Ensambladores (Mappers)
│           └── CreateFavoriteSourceCommandFromResourceAssembler.java
│           └── FavoriteSourceResourceFromEntityAssembler.java
│
├── 📂 shared/                            # Recursos compartidos entre contextos
│   └── 📂 infrastructure/
│       └── 📂 persistence/
│           └── 📂 jpa/
│               └── 📂 strategy/
│                   └── SnakeCasePhysicalNamingStrategy.java
│
└── CatchUpPlatformApplication.java       # Clase principal de la aplicación
```

---

## Paso 4: Crear el Bounded Context

**Un Bounded Context representa un módulo de negocio específico.** En este caso: `news`

Crear la estructura de carpetas dentro de `src/main/java/com/acme/catchup/platform/`:
- `news/` - Contexto de noticias
- `shared/` - Recursos compartidos

Cada Bounded Context debe tener estas capas:
1. **domain** - Lógica de negocio pura
2. **application** - Casos de uso
3. **infrastructure** - Persistencia y servicios externos
4. **interfaces** - API REST y presentación

---

## Paso 5: Domain Layer - Crear Commands y Queries (CQRS)

### 📂 domain/model/commands/

**Los Commands son objetos que representan intenciones de modificar el estado del sistema.**

**Ejemplo: CreateFavoriteSourceCommand.java**
```java
package com.acme.catchup.platform.news.domain.model.commands;

public record CreateFavoriteSourceCommand(
    String newsApiKey,
    String sourceId
) {
    // Record de Java - inmutable y con constructor/getters automáticos
}
```

**Características:**
- Usan `record` de Java para inmutabilidad
- Representan acciones de escritura (CREATE, UPDATE, DELETE)
- Contienen solo los datos necesarios para ejecutar la acción

---

### 📂 domain/model/queries/

**Los Queries son objetos que representan consultas al sistema (solo lectura).**

**Ejemplo: GetFavoriteSourceByIdQuery.java**
```java
package com.acme.catchup.platform.news.domain.model.queries;

public record GetFavoriteSourceByIdQuery(Long id) {
}
```

**Ejemplo: GetAllFavoriteSourcesByNewsApiKeyQuery.java**
```java
package com.acme.catchup.platform.news.domain.model.queries;

public record GetAllFavoriteSourcesByNewsApiKeyQuery(String newsApiKey) {
}
```

**Características:**
- También usan `record` para inmutabilidad
- Representan consultas (GET/READ)
- Parámetros de búsqueda o filtrado

---

## Paso 6: Domain Layer - Crear Aggregates (Entidades)

### 📂 domain/model/aggregates/

**Los Aggregates son las entidades principales del dominio (raíz del agregado).**

**Ejemplo: FavoriteSource.java**
```java
package com.acme.catchup.platform.news.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FavoriteSource extends AbstractAggregateRoot<FavoriteSource> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String newsApiKey;

    @Column(nullable = false)
    @Getter
    private String sourceId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    @Getter
    private Date updatedAt;

    // Constructor vacío para JPA
    protected FavoriteSource() {}

    // Constructor con Command
    public FavoriteSource(CreateFavoriteSourceCommand command) {
        this.newsApiKey = command.newsApiKey();
        this.sourceId = command.sourceId();
    }
}
```

**Características importantes:**
- `@Entity` - Marca la clase como entidad JPA
- `@Id` y `@GeneratedValue` - Define la clave primaria autoincremental
- `@Column(nullable = false)` - Define restricciones de BD
- `@CreatedDate` y `@LastModifiedDate` - Auditoría automática
- Constructor que recibe un Command para crear la entidad
- Extiende `AbstractAggregateRoot` para eventos de dominio

---

## Paso 7: Domain Layer - Crear Service Interfaces

### 📂 domain/services/

**Las interfaces de servicios definen el contrato de operaciones del dominio.**

**Ejemplo: FavoriteSourceCommandService.java**
```java
package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;

import java.util.Optional;

public interface FavoriteSourceCommandService {
    Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command);
}
```

**Ejemplo: FavoriteSourceQueryService.java**
```java
package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface FavoriteSourceQueryService {
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);
}
```

**Características:**
- Definen métodos `handle()` para cada Command o Query
- Retornan `Optional<>` para resultados únicos (puede no existir)
- Retornan `List<>` para múltiples resultados
- Separan Commands (escritura) de Queries (lectura)

---

## Paso 8: Infrastructure Layer - Crear Repository

### 📂 infrastructure/persistence/jpa/

**El Repository es la interfaz para acceder a la base de datos.**

**Ejemplo: FavoriteSourceRepository.java**
```java
package com.acme.catchup.platform.news.infrastructure.persistence.jpa;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long> {
    
    // Consultas personalizadas
    List<FavoriteSource> findAllByNewsApiKey(String newsApiKey);
    
    Optional<FavoriteSource> findByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
    
    boolean existsByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
}
```

**Características:**
- Extiende `JpaRepository<Entidad, TipoDeLaClavePrimaria>`
- Spring Data JPA genera automáticamente las implementaciones
- Métodos personalizados siguen la convención de nombres:
  - `findBy...` - Buscar por campos
  - `existsBy...` - Verificar existencia
  - `countBy...` - Contar registros
  - `deleteBy...` - Eliminar por condición

---

## Paso 9: Application Layer - Implementar Services

### 📂 application/commandservices/

**Implementación de los servicios de comandos (escritura).**

**Ejemplo: FavoriteSourceCommandServiceImpl.java**
```java
package com.acme.catchup.platform.news.application.commandservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteSourceCommandServiceImpl implements FavoriteSourceCommandService {

    private final FavoriteSourceRepository repository;

    public FavoriteSourceCommandServiceImpl(FavoriteSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command) {
        // Validar que no exista ya
        if (repository.existsByNewsApiKeyAndSourceId(command.newsApiKey(), command.sourceId())) {
            throw new IllegalArgumentException("Favorite source already exists");
        }
        
        // Crear y guardar
        var favoriteSource = new FavoriteSource(command);
        var savedSource = repository.save(favoriteSource);
        
        return Optional.of(savedSource);
    }
}
```

---

### 📂 application/queryservices/

**Implementación de los servicios de consultas (lectura).**

**Ejemplo: FavoriteSourceQueryServiceImpl.java**
```java
package com.acme.catchup.platform.news.application.queryservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.*;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteSourceQueryServiceImpl implements FavoriteSourceQueryService {

    private final FavoriteSourceRepository repository;

    public FavoriteSourceQueryServiceImpl(FavoriteSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query) {
        return repository.findAllByNewsApiKey(query.newsApiKey());
    }

    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query) {
        return repository.findById(query.id());
    }
}
```

**Características:**
- `@Service` - Marca la clase como servicio de Spring
- Inyección de dependencias por constructor (recomendado)
- Delegan la lógica de persistencia al Repository
- Implementan la lógica de negocio antes de persistir

---

## Paso 10: Interfaces Layer - Crear Resources (DTOs)

### 📂 interfaces/resources/

**Los Resources son DTOs (Data Transfer Objects) para la API REST.**

**Ejemplo: CreateFavoriteSourceResource.java** (Request DTO)
```java
package com.acme.catchup.platform.news.interfaces.resources;

public record CreateFavoriteSourceResource(
    String newsApiKey,
    String sourceId
) {
}
```

**Ejemplo: FavoriteSourceResource.java** (Response DTO)
```java
package com.acme.catchup.platform.news.interfaces.resources;

public record FavoriteSourceResource(
    Long id,
    String newsApiKey,
    String sourceId,
    String createdAt,
    String updatedAt
) {
}
```

**Características:**
- Son `record` inmutables para transferencia de datos
- Request Resources: reciben datos del cliente
- Response Resources: envían datos al cliente
- No contienen lógica de negocio
- Se mapean desde/hacia entidades usando Assemblers

---

## Paso 11: Interfaces Layer - Crear Assemblers (Mappers)

### 📂 interfaces/transform/

**Los Assemblers transforman entre Resources (DTOs) y Entidades de Dominio.**

**Ejemplo: CreateFavoriteSourceCommandFromResourceAssembler.java**
```java
package com.acme.catchup.platform.news.interfaces.transform;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.interfaces.resources.CreateFavoriteSourceResource;

public class CreateFavoriteSourceCommandFromResourceAssembler {
    
    public static CreateFavoriteSourceCommand toCommandFromResource(CreateFavoriteSourceResource resource) {
        return new CreateFavoriteSourceCommand(
            resource.newsApiKey(),
            resource.sourceId()
        );
    }
}
```

**Ejemplo: FavoriteSourceResourceFromEntityAssembler.java**
```java
package com.acme.catchup.platform.news.interfaces.transform;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.interfaces.resources.FavoriteSourceResource;

public class FavoriteSourceResourceFromEntityAssembler {
    
    public static FavoriteSourceResource toResourceFromEntity(FavoriteSource entity) {
        return new FavoriteSourceResource(
            entity.getId(),
            entity.getNewsApiKey(),
            entity.getSourceId(),
            entity.getCreatedAt().toString(),
            entity.getUpdatedAt().toString()
        );
    }
}
```

**Características:**
- Métodos estáticos de conversión
- `toCommandFromResource()` - De Resource a Command
- `toResourceFromEntity()` - De Entity a Resource
- Separan la lógica de transformación del controlador
- **Importante:** Usar siempre después de crear las entidades y DTOs

---

## Paso 12: Interfaces Layer - Crear Controller (API REST)

### 📂 interfaces/

**El Controller expone los endpoints REST de la API.**

**Ejemplo: FavoriteSourcesController.java**
```java
package com.acme.catchup.platform.news.interfaces;

import com.acme.catchup.platform.news.domain.model.queries.*;
import com.acme.catchup.platform.news.domain.services.*;
import com.acme.catchup.platform.news.interfaces.resources.*;
import com.acme.catchup.platform.news.interfaces.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favorite-sources")
@Tag(name = "Favorite Sources", description = "Favorite Sources Management Endpoints")
public class FavoriteSourcesController {

    private final FavoriteSourceCommandService commandService;
    private final FavoriteSourceQueryService queryService;

    public FavoriteSourcesController(
            FavoriteSourceCommandService commandService,
            FavoriteSourceQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    /**
     * POST /api/v1/favorite-sources
     * Crear un nuevo favorite source
     */
    @PostMapping
    public ResponseEntity<FavoriteSourceResource> createFavoriteSource(
            @RequestBody CreateFavoriteSourceResource resource) {
        
        var command = CreateFavoriteSourceCommandFromResourceAssembler
                .toCommandFromResource(resource);
        
        var favoriteSource = commandService.handle(command);
        
        if (favoriteSource.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        var favoriteSourceResource = FavoriteSourceResourceFromEntityAssembler
                .toResourceFromEntity(favoriteSource.get());
        
        return new ResponseEntity<>(favoriteSourceResource, HttpStatus.CREATED);
    }

    /**
     * GET /api/v1/favorite-sources/{id}
     * Obtener un favorite source por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<FavoriteSourceResource> getFavoriteSourceById(@PathVariable Long id) {
        var query = new GetFavoriteSourceByIdQuery(id);
        var favoriteSource = queryService.handle(query);
        
        if (favoriteSource.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var resource = FavoriteSourceResourceFromEntityAssembler
                .toResourceFromEntity(favoriteSource.get());
        
        return ResponseEntity.ok(resource);
    }

    /**
     * GET /api/v1/favorite-sources?newsApiKey=xxx
     * Obtener todos los favorite sources por newsApiKey
     */
    @GetMapping
    public ResponseEntity<List<FavoriteSourceResource>> getAllFavoriteSourcesByNewsApiKey(
            @RequestParam String newsApiKey) {
        
        var query = new GetAllFavoriteSourcesByNewsApiKeyQuery(newsApiKey);
        var favoriteSources = queryService.handle(query);
        
        var resources = favoriteSources.stream()
                .map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(resources);
    }
}
```

**Características del Controller:**
- `@RestController` - Marca la clase como controlador REST
- `@RequestMapping` - Define la ruta base del API
- `@PostMapping`, `@GetMapping` - Definen los métodos HTTP
- `@RequestBody` - Recibe datos JSON del cliente
- `@PathVariable` - Obtiene parámetros de la URL
- `@RequestParam` - Obtiene parámetros query string
- Retorna `ResponseEntity<>` con códigos HTTP apropiados
- Usa los Assemblers para transformar datos
- Inyecta Command y Query Services por constructor

---

## Paso 13: Shared - Recursos Compartidos

### 📂 shared/infrastructure/persistence/jpa/strategy/

**Estrategias y configuraciones compartidas entre todos los bounded contexts.**

**Ejemplo: SnakeCasePhysicalNamingStrategy.java**
```java
package com.acme.catchup.platform.shared.infrastructure.persistence.jpa.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

public class SnakeCasePhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(this.toPlural(identifier));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    private Identifier toSnakeCase(Identifier identifier) {
        if (identifier == null) return null;
        
        String name = identifier.getText();
        String snakeCaseName = name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        
        return Identifier.toIdentifier(snakeCaseName);
    }

    private Identifier toPlural(Identifier identifier) {
        if (identifier == null) return null;
        
        String name = identifier.getText();
        String pluralName = pluralize(name);
        
        return Identifier.toIdentifier(pluralName);
    }
}
```

**Características:**
- Convierte nombres de clases Java (CamelCase) a snake_case en BD
- Pluraliza nombres de tablas automáticamente
- Ejemplo: `FavoriteSource` → `favorite_sources` en la BD

---

## Paso 14: Ejecutar la Aplicación

### Clase Principal

**CatchUpPlatformApplication.java**
```java
package com.acme.catchup.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // Habilita auditoría automática de entidades
public class CatchUpPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatchUpPlatformApplication.class, args);
    }
}
```

**Comando para ejecutar:**
```bash
mvn spring-boot:run
```

O desde el IDE: Ejecutar la clase principal

**La aplicación estará disponible en:**
```
http://localhost:8080
```

---

## Resumen del Orden de Creación

### Orden recomendado para crear un Bounded Context:

1. **Domain Layer (Núcleo del negocio):**
   - ✅ `domain/model/commands/` - Crear Commands
   - ✅ `domain/model/queries/` - Crear Queries
   - ✅ `domain/model/aggregates/` - Crear Entidades (Aggregates)
   - ✅ `domain/services/` - Crear interfaces de servicios

2. **Infrastructure Layer (Persistencia):**
   - ✅ `infrastructure/persistence/jpa/` - Crear Repository

3. **Application Layer (Casos de uso):**
   - ✅ `application/commandservices/` - Implementar Command Services
   - ✅ `application/queryservices/` - Implementar Query Services

4. **Interfaces Layer (API REST):**
   - ✅ `interfaces/resources/` - Crear DTOs (Resources)
   - ✅ `interfaces/transform/` - Crear Assemblers
   - ✅ `interfaces/` - Crear Controller

5. **Shared (Opcional):**
   - ✅ `shared/infrastructure/` - Configuraciones compartidas

---

## Principios Arquitectónicos Aplicados

### 🏛️ DDD (Domain-Driven Design)
- Separación por Bounded Contexts
- Aggregates como raíz de consistencia
- Repositories para persistencia

### 📝 CQRS (Command Query Responsibility Segregation)
- Commands para escritura
- Queries para lectura
- Servicios separados por responsabilidad

### 🔄 Arquitectura en Capas
1. **Domain** - Lógica de negocio pura
2. **Application** - Casos de uso
3. **Infrastructure** - Detalles técnicos
4. **Interfaces** - Puntos de entrada (API)

### 🧩 Dependency Inversion
- Las capas superiores no dependen de las inferiores
- Uso de interfaces para desacoplar
- Inyección de dependencias por constructor

---

## Endpoints de la API

### Base URL: `http://localhost:8080/api/v1`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/favorite-sources` | Crear un nuevo favorite source |
| GET | `/favorite-sources/{id}` | Obtener un favorite source por ID |
| GET | `/favorite-sources?newsApiKey=xxx` | Listar todos los favorite sources de un usuario |

### Ejemplo de Request (POST):
```json
{
  "newsApiKey": "abc123",
  "sourceId": "bbc-news"
}
```

### Ejemplo de Response:
```json
{
  "id": 1,
  "newsApiKey": "abc123",
  "sourceId": "bbc-news",
  "createdAt": "2025-01-19T10:30:00",
  "updatedAt": "2025-01-19T10:30:00"
}
```

---

## Comandos Útiles

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Empaquetar como JAR
mvn clean package

# Ejecutar la aplicación
mvn spring-boot:run

# Instalar dependencias
mvn clean install
```

---

## Notas Finales

- **Siempre crear primero la capa de dominio** (Commands, Queries, Aggregates)
- **Luego la infraestructura** (Repository)
- **Después la aplicación** (Services)
- **Finalmente las interfaces** (Resources, Assemblers, Controllers)
- **Usar records para inmutabilidad** en Commands, Queries y Resources
- **Inyección por constructor** para mejor testeo
- **Separar Commands y Queries** (CQRS)
- **Validar datos en el Controller** antes de enviar a los servicios

---

**📅 Creación de este README: 19/11/2025**

**Sujeto a actualizaciones según evolución del proyecto**

