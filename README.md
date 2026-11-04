# snkrlab-ms-notificaciones

Microservicio de notificaciones de SNKRLAB. Procesa solicitudes de correo y consume la cola RabbitMQ `notificaciones.email.queue` mediante `CorreoListener`.

## Endpoints

El controlador `CorreoController` usa la ruta base `/api/v1/notificaciones` e incluye `/enviar`. Comprobar verbo HTTP y permisos correspondientes en el controlador.

## Integraciones

MySQL (`snkrlab_notifications_db`), RabbitMQ, Spring Mail y SMTP. Puerto local: **8084**.

**Importante:** nunca almacenar contraseñas de aplicaciones SMTP en `docker-compose.yml` ni en archivos versionados. Inyectar configuración por variables de entorno o un gestor de secretos y rotar cualquier credencial expuesta.

## Desarrollo y ejecución

Desde esta carpeta, con Java y el wrapper Maven:

```bash
./mvnw spring-boot:run
```

En Windows: `mvnw.cmd spring-boot:run`. Para el entorno integral, iniciar dependencias con `docker compose up -d --build` desde la raíz del proyecto. Ajustar las variables de entorno y servicios dependientes antes de ejecutar.

## Seguridad y configuración

Consultar `src/main/resources/application.yaml` o `application.properties` y la configuración de seguridad del servicio. No publicar contraseñas, secretos de correo, tokens ni valores de producción. Se deben verificar los permisos en el servidor y no solo en React.

## Contexto de documentación

Este microservicio se incorporó después de EV1 y no contaba con README en el ZIP original. Este documento se añade siguiendo el formato de los README existentes.
