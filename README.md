# test_software_getechnologiesMx
Solucion a la prueba de desarrollo de software

# Parking

Parking es una aplicación de gestión de estacionamiento desarrollada en Java utilizando Spring Boot.

## Descripción

La aplicación Parking permite a los usuarios administrar un estacionamiento, realizar seguimiento de los vehículos estacionados, calcular tarifas de estacionamiento y generar informes.

## Funcionalidades

- Registro de vehículos entrantes y salientes.
- Cálculo automático de tarifas de estacionamiento según la duración de la estancia.
- Generación de informes sobre la ocupación del estacionamiento y los ingresos generados.
- Gestión de empleados y permisos de acceso.
- Integración con sistemas de pago.

## Instalación

1. Clona este repositorio: `git clone https://github.com/tu_usuario/parking.git`
2. Navega al directorio del proyecto: `cd parking`
3. Asegúrate de tener instalado Java JDK y Maven.
4. Compila el proyecto: `mvn clean install`
5. Ejecuta la aplicación: `java -jar target/parking-1.0.jar`

## Base de Datos
El proyecto requiere una base de datos MySQL para su funcionamiento. Asegúrate de tener MySQL instalado y configurado correctamente.

## Conexión a la Base de Datos
Para conectar con la base de datos, se necesita un usuario con privilegios de administrador (root) y la contraseña correspondiente.
Crea automaticamente una base de datos parking si no se encuentra.
Utilizaremos las siguientes credenciales:

- **Usuario:** root
- **Contraseña:** Pa$$w0rd

## Lombok
El proyecto utiliza Lombok para la generación automática de código repetitivo. Asegúrate de tener Lombok instalado en tu entorno de desarrollo.

### Configuración de Lombok
- **Eclipse:** Sigue las instrucciones en este [enlace](https://projectlombok.org/setup/eclipse) para configurar Lombok en Eclipse.
  
- **IntelliJ IDEA:** Para configurar Lombok en IntelliJ IDEA, sigue las instrucciones disponibles en este [enlace](https://projectlombok.org/setup/intellij).

## Uso

1. Accede a la API en tu navegador web: `http://localhost:8080`
2. Accede desde la consola menu de opciones.
2. Explora las diferentes funcionalidades de la aplicación, como registro de vehículos, generación de informes y gestión de empleados.

## Contribuir

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama: `git checkout -b feature/feature_name`
3. Realiza tus cambios y haz commit: `git commit -am 'Agrega una nueva funcionalidad'`
4. Sube tus cambios a tu repositorio: `git push origin feature/feature_name`
5. Envía un pull request.

## Autores

- Bryan Tevillo Betancourt - bryan_tevillo@hotmail.com

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.