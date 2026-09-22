# 🛍️ WendyEcommerce - Backend Core

¡Bienvenido a **WendyEcommerce**! Este proyecto constituye el núcleo del backend para una plataforma de comercio electrónico robusta y escalable. Está construido utilizando el framework **Spring Boot** en su versión 3.3.4, diseñado bajo el paradigma de Arquitectura Limpia y Programación Orientada a Objetos para proporcionar servicios REST eficientes y seguros.

---

## 🚀 Tecnologías Utilizadas e Implementación

El ecosistema tecnológico del proyecto fue seleccionado para garantizar la máxima estabilidad y soporte a largo plazo:

*   **Java 21 (LTS):** Utilizado como el lenguaje de programación base. Se optó por una versión de soporte masivo (LTS) para garantizar la compatibilidad absoluta con el ecosistema de librerías de Spring.
*   **Spring Boot 3.3.4:** Framework principal para la gestión de inyección de dependencias, configuración automática y el ciclo de vida de la aplicación.
*   **Spring Data JPA / Hibernate:** Implementado para la capa de persistencia y el mapeo objeto-relacional (ORM), abstrayendo las consultas SQL nativas en repositorios Java orientados a objetos.
*   **Spring Security:** Framework encargado de la capa de seguridad, gestionando el control de accesos a endpoints protegidos, deshabilitación estratégica de CSRF para API REST y la seguridad en las rutas estáticas.
*   **Microsoft SQL Server:** Motor de base de datos relacional para el almacenamiento persistente (`bsEcomerce01`), conectado de forma óptima mediante el driver oficial `mssql-jdbc`.
*   **Apache Tomcat (Embebido):** Servidor de aplicaciones web que corre por defecto en el puerto optimizado `8086`.

---

## 📁 Estructura del Proyecto (Recursos Estáticos)

Para los flujos visuales que no requieren un framework de frontend pesado, la aplicación sirve interfaces directamente desde la raíz del servidor utilizando la siguiente topología de archivos:

```text
src/main/resources/
├── static/                📦 Directorio raíz público expuesto por Tomcat
│   ├── css/               🎨 Estilos personalizados de la interfaz
│   ├── js/                ⚡ Lógica de scripts e interacción con la API
│   ├── assets/            🖼️ Recursos multimedia
│   │   └── images/        ➡️ Ubicación del isotipo (ex: /assets/images/logowendy.png)
│   └── login.html         📄 Interfaz de autenticación de cara al usuario
└── application.properties Configuración centralizada del ecosistema
```

---

## 📸 Capturas de la Aplicación

### Formulario de Autenticación (`login.html`)
> *Coloca aquí la captura de pantalla de tu interfaz de Login en funcionamiento.*
![Pantalla de Login](https://placeholder.com)

---

## 🛠️ Desafíos Enfrentados y Soluciones Técnicas

Durante la fase de integración y arranque de componentes, se presentaron tres desafíos críticos que fueron resueltos con éxito:

### 1. Incompatibilidad de Entorno en el JDK (Java 24 vs Spring Boot 3.3.4)
*   **El Desafío:** Al arrancar el proyecto, la aplicación se congelaba en el paso de escaneo de componentes (`Bootstrapping Spring Data JPA...`). Los logs revelaron que el IDE ejecutaba el binario de **Java 24**, una versión no LTS no soportada oficialmente por las librerías internas de Spring Boot 3.3.4 (CGLIB/ByteBuddy).
*   **La Solución:** Se ajustaron los *Project JDKs* y los Java Runtimes del IDE a **Java 21 (LTS)**. Se actualizó el archivo `pom.xml` para fijar `<java.version>21</java.version>` y se forzó una limpieza extrema del proyecto mediante `./mvnw clean compile` eliminando cualquier residuo en la memoria de ejecución.

### 2. Bloqueo de Red por Protocolo TCP/IP en SQL Server (`Connection Refused`)
*   **El Desafío:** El pool de conexiones de la aplicación (`HikariPool`) fallaba en el arranque con el error `Connection refused: getsockopt`. SQL Server no aceptaba peticiones externas en el puerto `1433`.
*   **La Solución:** Se accedió al **SQL Server Configuration Manager**, se habilitó manualmente el protocolo **TCP/IP** en las configuraciones de red de la instancia y se asignó explícitamente el puerto `1433` a la directiva *IPAll*. Posteriormente, se reinició el servicio en `services.msc` y se configuró la propiedad `encrypt=false` en el `application.properties` para viabilizar el canal de desarrollo local.

### 3. Bucle de Redirecciones Infinitas (`ERR_TOO_MANY_REDIRECTS`)
*   **El Desafío:** Al intentar ingresar a la pantalla de login, el navegador arrojaba un error de demasiadas redirecciones. Esto ocurría porque Spring Security tenía activada la directiva `.loginPage("/login")` buscando un controlador visual (GET), chocando con el archivo físico estático `login.html` y un controlador puramente REST (`@RestController`).
*   **La Solución:** Se rediseñó el archivo **`SecurityConfig.java`**. Se eliminaron los bloques de login por formulario web tradicional y se securizaron los recursos estáticos liberando explícitamente el patrón `/login.html` y usando `PathRequest.toStaticResources().atCommonLocations()`. Se acopló un `ViewController` con redirección interna tipo `forward:/login.html` para un enrutamiento limpio y transparente al navegador.

---

## ⚙️ Instalación y Despliegue Local

1. Clona este repositorio:
   ```bash
   git clone https://github.com
   ```
2. Asegúrate de tener **SQL Server** activo y con una base de datos llamada `bsEcomerce01`.
3. Configura tus credenciales locales en `src/main/resources/application.properties`.
4. Compila y ejecuta el proyecto:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Abre tu navegador e ingresa a: **`http://localhost:8086/`**