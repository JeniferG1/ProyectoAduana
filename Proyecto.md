# ProyectoAduana
<html>
<body>
<!--StartFragment--><html><head></head><body><h1>ProyectoAduana </h1>
<h2>Descripción del Proyecto</h2>
<p>ProyectoAduana es una arquitectura de microservicios desarrollada con Spring Boot que gestiona los procesos aduaneros de control fronterizo. El sistema permite administrar personas, vehículos, trámites fronterizos, permisos de circulación, declaraciones juradas y más, mediante servicios independientes que se comunican entre sí a través de Feign Client y se registran en un servidor Eureka.</p>
<hr>
<h2>Integrantes del Equipo</h2>

Nombre | Rol
-- | --
Daniel González | Desarrollador Backend
Danyer Cordoba | Desarrollador Backend
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
</html>
