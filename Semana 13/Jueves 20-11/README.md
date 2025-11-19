# Creación de un Backend con Spring Boot Java

## Pasos para crear un backend con Spring Boot

1. **Requisitos previos**
   - Instalar Java Development Kit (JDK) 17 o superior
   - Instalar Maven o Gradle como herramienta de construcción
   - Instalar un IDE (IntelliJ IDEA, Eclipse, o VS Code con extensiones Java)
   - Tener conocimientos básicos de Java

2. **Inicializar el proyecto Spring Boot**
   - Visitar [Spring Initializr](https://start.spring.io/)
   - Configurar el proyecto:
     - Project: Maven o Gradle
     - Language: Java
     - Spring Boot: Versión estable más reciente
     - Group: com.ejemplo
     - Artifact: nombre-del-proyecto
     - Packaging: Jar
     - Java: 17 o superior
   - Agregar dependencias iniciales:
     - Spring Web
     - Spring Data JPA
     - Spring Boot DevTools
     - H2 Database (para desarrollo) o MySQL/PostgreSQL (para producción)
     - Lombok (opcional, para reducir código boilerplate)
   - Generar y descargar el proyecto

3. **Estructura del proyecto**
   - `src/main/java`: Código fuente Java
     - `controller`: Controladores REST
     - `service`: Lógica de negocio
     - `repository`: Acceso a datos (JPA repositories)
     - `model` o `entity`: Entidades JPA
     - `dto`: Data Transfer Objects
     - `config`: Clases de configuración
   - `src/main/resources`: Archivos de configuración
     - `application.properties` o `application.yml`: Configuración de la aplicación
   - `src/test/java`: Pruebas unitarias y de integración

4. **Configurar application.properties**
   ```properties
   # Puerto del servidor
   server.port=8080
   
   # Configuración de base de datos (H2 para desarrollo)
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   
   # JPA/Hibernate
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   
   # Habilitar consola H2
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console
   ```

5. **Crear una entidad (Entity)**
   ```java
   @Entity
   @Table(name = "usuarios")
   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   public class Usuario {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       
       @Column(nullable = false)
       private String nombre;
       
       @Column(unique = true, nullable = false)
       private String email;
   }
   ```

6. **Crear un Repository (Repositorio)**
   ```java
   @Repository
   public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
       Optional<Usuario> findByEmail(String email);
       List<Usuario> findByNombreContaining(String nombre);
   }
   ```

7. **Crear un Service (Servicio)**
   ```java
   @Service
   public class UsuarioService {
       @Autowired
       private UsuarioRepository usuarioRepository;
       
       public List<Usuario> obtenerTodos() {
           return usuarioRepository.findAll();
       }
       
       public Optional<Usuario> obtenerPorId(Long id) {
           return usuarioRepository.findById(id);
       }
       
       public Usuario guardar(Usuario usuario) {
           return usuarioRepository.save(usuario);
       }
       
       public void eliminar(Long id) {
           usuarioRepository.deleteById(id);
       }
   }
   ```

8. **Crear un Controller (Controlador REST)**
   ```java
   @RestController
   @RequestMapping("/api/usuarios")
   @CrossOrigin(origins = "*")
   public class UsuarioController {
       @Autowired
       private UsuarioService usuarioService;
       
       @GetMapping
       public ResponseEntity<List<Usuario>> obtenerTodos() {
           return ResponseEntity.ok(usuarioService.obtenerTodos());
       }
       
       @GetMapping("/{id}")
       public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
           return usuarioService.obtenerPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
       }
       
       @PostMapping
       public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
           Usuario nuevoUsuario = usuarioService.guardar(usuario);
           return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
       }
       
       @PutMapping("/{id}")
       public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
           return usuarioService.obtenerPorId(id)
               .map(usuarioExistente -> {
                   usuario.setId(id);
                   return ResponseEntity.ok(usuarioService.guardar(usuario));
               })
               .orElse(ResponseEntity.notFound().build());
       }
       
       @DeleteMapping("/{id}")
       public ResponseEntity<Void> eliminar(@PathVariable Long id) {
           usuarioService.eliminar(id);
           return ResponseEntity.noContent().build();
       }
   }
   ```

9. **Manejo de excepciones (opcional pero recomendado)**
   ```java
   @ControllerAdvice
   public class GlobalExceptionHandler {
       @ExceptionHandler(ResourceNotFoundException.class)
       public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
           ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
       }
       
       @ExceptionHandler(Exception.class)
       public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
           ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor");
           return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   ```

10. **Ejecutar la aplicación**
    - Desde la terminal: `mvn spring-boot:run` o `./mvnw spring-boot:run`
    - Desde el IDE: Ejecutar la clase principal con `@SpringBootApplication`
    - La aplicación estará disponible en `http://localhost:8080`

11. **Probar los endpoints**
    - Usar Postman, Insomnia o curl para probar los endpoints
    - Ejemplo con curl:
      ```bash
      # Crear usuario
      curl -X POST http://localhost:8080/api/usuarios \
        -H "Content-Type: application/json" \
        -d '{"nombre":"Juan Pérez","email":"juan@ejemplo.com"}'
      
      # Obtener todos los usuarios
      curl http://localhost:8080/api/usuarios
      
      # Obtener usuario por ID
      curl http://localhost:8080/api/usuarios/1
      
      # Actualizar usuario
      curl -X PUT http://localhost:8080/api/usuarios/1 \
        -H "Content-Type: application/json" \
        -d '{"nombre":"Juan Pérez Actualizado","email":"juan@ejemplo.com"}'
      
      # Eliminar usuario
      curl -X DELETE http://localhost:8080/api/usuarios/1
      ```

12. **Documentación de la API con Swagger (opcional)**
    - Agregar dependencia de Springdoc OpenAPI:
      ```xml
      <dependency>
          <groupId>org.springdoc</groupId>
          <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
          <version>2.2.0</version>
      </dependency>
      ```
    - Acceder a la documentación en: `http://localhost:8080/swagger-ui.html`

13. **Seguridad con Spring Security (opcional)**
    - Agregar dependencia de Spring Security
    - Configurar autenticación y autorización
    - Implementar JWT para APIs RESTful

14. **Testing**
    - Crear pruebas unitarias para servicios usando JUnit y Mockito
    - Crear pruebas de integración para controladores usando MockMvc
    - Ejemplo de prueba unitaria:
      ```java
      @SpringBootTest
      class UsuarioServiceTest {
          @Autowired
          private UsuarioService usuarioService;
          
          @Test
          void testGuardarUsuario() {
              Usuario usuario = new Usuario(null, "Test", "test@ejemplo.com");
              Usuario guardado = usuarioService.guardar(usuario);
              assertNotNull(guardado.getId());
              assertEquals("Test", guardado.getNombre());
          }
      }
      ```

15. **Despliegue**
    - Empaquetar la aplicación: `mvn clean package`
    - El archivo JAR se genera en `target/nombre-del-proyecto.jar`
    - Ejecutar el JAR: `java -jar target/nombre-del-proyecto.jar`
    - Opciones de despliegue:
      - Heroku
      - AWS (Elastic Beanstalk, EC2, ECS)
      - Google Cloud Platform
      - Azure
      - Docker containerization

## Mejores prácticas

- Usar DTO (Data Transfer Objects) para separar la capa de presentación de la capa de dominio
- Implementar validación de datos con Bean Validation (@Valid, @NotNull, @Size, etc.)
- Usar códigos de estado HTTP apropiados
- Implementar logging con SLF4J y Logback
- Usar variables de entorno para configuraciones sensibles
- Implementar paginación para endpoints que retornan listas grandes
- Documentar la API
- Escribir pruebas automatizadas
- Seguir los principios SOLID y Clean Code

## Recursos adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Initializr](https://start.spring.io/)
- [Baeldung Spring Boot Tutorials](https://www.baeldung.com/spring-boot)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
