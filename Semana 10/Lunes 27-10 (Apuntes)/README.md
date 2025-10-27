## Repo de proyecto catchup-platform:

[repo-github](https://github.com/samuelbonifacio015/Open-Source-Clases/tree/main/Semana%209/Sabado%2025-10/catch-up-platform)

---

# backend notas 

**Actualizacion 27/10 (18:48PM)**


### 1) CORRER BASE DE DATOS

****IMPORTANTE:****

el archivo ***application.properties*** dentro del proyecto es el encargado de generar la conexión con la base de datos y generar las tablas con los datos de la información

#### Ejemplo:

```java
spring.application.name=catch-up-platform


spring.datasource.url=jdbc:mysql://localhost:3306/catch-up-os-7380?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=12345678
spring.datasource.driver-class-name=com.mysql.jdbc.Driver


spring.jpa.show-sql=true

spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.naming.physical-strategy=com.acme.catchup.platform.shared.infrastructure.persistence.jpa.strategy.SnakeCasePhysicalNamingStrategy
```

Este archivo contiene el directorio, contraseña y conexión con mysql workbench.

***url:***
http://localhost:8080/swagger-ui/index.html



