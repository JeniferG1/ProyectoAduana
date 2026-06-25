<html>
<body>
<!--StartFragment--><html><head></head><body><h1>ProyectoAduana </h1>
<h2>Descripción del Proyecto</h2>
<p>ProyectoAduana es una arquitectura de microservicios desarrollada con Spring Boot que gestiona los procesos aduaneros de control fronterizo. El sistema permite administrar personas, vehículos, trámites fronterizos, permisos de circulación, declaraciones juradas y más, mediante servicios independientes que se comunican entre sí a través de Feign Client y se registran en un servidor Eureka.</p>
<hr>
<h2>Integrantes del Equipo</h2>

Nombre | Rol
-- | --
Jenifer Gáliz | Desarrollador Backend


<hr>
<h2>Comunicación entre Microservicios</h2>
<pre><code>ms-tramitefronterizo ──Feign──► ms-persona
ms-tramitefronterizo ──Feign──► ms-pasofronterizo
ms-permisocirculacion ──Feign──► ms-vehiculo
</code></pre>
<p>Todos los microservicios se registran en:</p>
<pre><code>http://localhost:8096 (Eureka Server)
</code></pre>
<hr>
<h2>Estructura del Proyecto</h2>
<pre><code>ProyectoAduana/
├── Eurekaserver/
├── ms-declaracionjurada/
│    └── src/main/java/com/proyectoaduana/msdeclaracionjurada/
│         ├── Controller/
│         ├── Service/
│         ├── Repository/
│         ├── Model/
│         └── Exception/
├── ms-detalleproducto/
├── ms-pais/
├── ms-pasofronterizo/
├── ms-permisocirculacion/
├── ms-persona/
├── ms-relacionfamiliar/
├── ms-tramitefronterizo/
├── ms-usuariosistema/
├── ms-vehiculo/
└── README.md
</code></pre></body></html><!--EndFragment-->
</body>

## Comunicación entre Microservicios

```
ms-tramitefronterizo ──Feign──► ms-persona
ms-tramitefronterizo ──Feign──► ms-pasofronterizo
ms-permisocirculacion ──Feign──► ms-vehiculo
```

Todos los microservicios se registran en el servidor Eureka:

```
http://localhost:8761
```
 
---

## Rutas Principales del Gateway

Todas las solicitudes pasan por el Gateway en `http://localhost:8080` y se redirigen automáticamente al microservicio correspondiente según el path:

| Ruta del Gateway | Microservicio destino |
|---|---|
| `http://localhost:8080/api/v1/declaracionJurada/**` | ms-declaracionjurada |
| `http://localhost:8080/api/v1/detalleproducto/**` | ms-detalleproducto |
| `http://localhost:8080/api/v1/pais/**` | ms-pais |
| `http://localhost:8080/api/v1/pasofronterizo/**` | ms-pasofronterizo |
| `http://localhost:8080/api/v1/permisocirculacion/**` | ms-permisocirculacion |
| `http://localhost:8080/api/v1/persona/**` | ms-persona |
| `http://localhost:8080/api/v1/relacionfamiliar/**` | ms-relacionfamiliar |
| `http://localhost:8080/api/v1/tramitefronterizo/**` | ms-tramitefronterizo |
| `http://localhost:8080/api/v1/usuariosistema/**` | ms-usuariosistema |
| `http://localhost:8080/api/v1/vehiculo/**` | ms-vehiculo |
 
---

## Documentación Swagger / OpenAPI

Cada microservicio expone su propia documentación interactiva. Acceso directo (sin pasar por el Gateway), reemplazando el puerto según corresponda:

| Microservicio | Swagger UI |
|---|---|
| ms-declaracionjurada | http://localhost:8081/swagger-ui.html |
| ms-detalleproducto | http://localhost:8082/swagger-ui.html |
| ms-pais | http://localhost:8083/swagger-ui.html |
| ms-pasofronterizo | http://localhost:8084/swagger-ui.html |
| ms-permisocirculacion | http://localhost:8085/swagger-ui.html |
| ms-persona | http://localhost:8086/swagger-ui.html |
| ms-relacionfamiliar | http://localhost:8087/swagger-ui.html |
| ms-tramitefronterizo | http://localhost:8088/swagger-ui.html |
| ms-usuariosistema | http://localhost:8089/swagger-ui.html |
| ms-vehiculo | http://localhost:8090/swagger-ui.html |


### Orden de arranque (importante)

Los microservicios dependen de Eureka para registrarse, y el Gateway depende de que los microservicios ya estén registrados. El orden correcto es:

1. **Eureka Server** (`Eurekaserver`) — debe ser el primero en levantarse.
2. **Los 10 microservicios** (`ms-*`) — pueden levantarse en cualquier orden entre ellos, una vez que Eureka esté arriba.
3. **Gateway** — debe levantarse al final, una vez que los microservicios ya están registrados en Eureka.
</html>
