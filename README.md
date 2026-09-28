![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠 Semana 7 - Actividad Formativa 5 - Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto

* **Nombre completo:** Mauricio Francisco Valenzuela Fuentes
* **Carrera:** Analista Programador Computacional
* **Sede:** Online

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la **Actividad Formativa N° 5** de la asignatura **Desarrollo Orientado a Objetos II**.

Se trata de la séptima etapa de **SistemaSpeedFast**, una aplicación desarrollada en Java para representar la gestión de pedidos de la empresa de reparto a domicilio **Speed Fast**.

En esta versión se incorpora **persistencia de datos mediante JDBC y MySQL**, permitiendo que la información registrada por medio de la interfaz gráfica permanezca almacenada incluso después de cerrar la aplicación.

El sistema mantiene la interfaz gráfica construida con **Java Swing**, pero reemplaza el almacenamiento temporal en memoria utilizado en la semana anterior por una conexión real con una base de datos MySQL.

Además, se incorporan clases DAO y controladores para separar las responsabilidades entre la interfaz gráfica, la lógica del sistema y el acceso a los datos.

El sistema permite:

* Registrar nuevos pedidos.
* Seleccionar el tipo de pedido.
* Registrar nuevos repartidores.
* Guardar pedidos y repartidores directamente en MySQL.
* Visualizar los pedidos almacenados mediante una tabla.
* Actualizar la información mostrada desde la base de datos.
* Consultar pedidos mediante JDBC y `ResultSet`.
* Asignar un repartidor a un pedido pendiente.
* Registrar una entrega asociando un pedido con un repartidor.
* Cambiar el estado de un pedido de `PENDIENTE` a `EN_REPARTO`.
* Mantener los datos almacenados después de cerrar la aplicación.
* Manejar errores de conexión y operaciones SQL mediante excepciones.

---

## 🧱 Estructura general del proyecto

```text
📁 SistemaSpeedFast_v7/
│
├── 📁 database/
│   └── speedfast_db.sql
│
├── 📁 lib/
│   └── mysql-connector-j-26.7.0.jar
│
├── 📁 src/
│   ├── 📁 main/
│   │   └── Main.java
│   │
│   ├── 📁 modelo/
│   │   ├── EstadoPedido.java
│   │   ├── Pedido.java
│   │   ├── Repartidor.java
│   │   └── Entrega.java
│   │
│   ├── 📁 dao/
│   │   ├── ConexionBD.java
│   │   ├── PedidoDAO.java
│   │   ├── RepartidorDAO.java
│   │   └── EntregaDAO.java
│   │
│   ├── 📁 controlador/
│   │   ├── ControladorPedidos.java
│   │   └── ControladorEntregas.java
│   │
│   └── 📁 vista/
│       ├── VentanaPrincipal.java
│       ├── VentanaRegistroPedido.java
│       ├── VentanaRegistroRepartidor.java
│       ├── VentanaListaPedidos.java
│       └── VentanaRegistroEntrega.java
│
├── 📄 .gitignore
├── 📄 SistemaSpeedFast_v7.iml
└── 📄 README.md
```

---

## 🧩 Organización por paquetes

El proyecto se encuentra organizado en cinco paquetes principales:

### 1. `main`

Contiene la clase encargada de iniciar la aplicación.

#### `Main.java`

Es el punto de entrada del sistema.

Crea una instancia de:

```java
VentanaPrincipal
```

y muestra la interfaz principal al usuario.

---

### 2. `modelo`

Contiene las clases que representan los datos utilizados por el sistema.

#### `EstadoPedido.java`

Enum que representa los posibles estados de un pedido:

```text
PENDIENTE
EN_REPARTO
ENTREGADO
```

Los pedidos nuevos comienzan automáticamente como:

```text
PENDIENTE
```

Cuando se registra una entrega y se asigna un repartidor, el pedido cambia a:

```text
EN_REPARTO
```

---

#### `Pedido.java`

Representa un pedido registrado en SpeedFast.

Contiene los atributos:

```text
id
direccionEntrega
tipo
estado
```

Se utilizan dos constructores:

* Uno para crear pedidos nuevos.
* Otro para reconstruir objetos `Pedido` a partir de los registros recuperados desde MySQL.

El identificador del pedido es generado automáticamente por la base de datos mediante:

```sql
AUTO_INCREMENT
```

---

#### `Repartidor.java`

Representa un repartidor registrado en el sistema.

Contiene:

```text
id
nombre
```

También incluye un método `toString()` que permite mostrar directamente el nombre del repartidor en los componentes gráficos.

---

#### `Entrega.java`

Representa la relación entre un pedido y un repartidor.

Contiene:

```text
id
idPedido
idRepartidor
fecha
hora
```

Cada entrega registra qué pedido fue asignado, qué repartidor lo realizará y la fecha y hora en que comenzó.

---

### 3. `dao`

Contiene las clases encargadas del acceso directo a la base de datos.

DAO corresponde a:

```text
Data Access Object
```

Estas clases utilizan JDBC para ejecutar operaciones SQL.

---

#### `ConexionBD.java`

Centraliza la configuración necesaria para establecer la conexión con MySQL.

Utiliza:

```java
DriverManager.getConnection()
```

La conexión apunta a:

```text
jdbc:mysql://127.0.0.1:3306/speedfast_db
```

Las credenciales deben configurarse localmente antes de ejecutar el proyecto.

---

#### `PedidoDAO.java`

Gestiona las operaciones relacionadas con la tabla:

```text
pedido
```

Implementa:

```text
guardar()
listarTodos()
actualizarEstado()
```

Estas operaciones permiten ejecutar:

```text
INSERT
SELECT
UPDATE
```

mediante `PreparedStatement`.

---

#### `RepartidorDAO.java`

Gestiona las operaciones relacionadas con la tabla:

```text
repartidor
```

Implementa:

```text
guardar()
listarTodos()
```

La consulta de repartidores utiliza:

```java
ResultSet
```

para recuperar las filas almacenadas y convertirlas nuevamente en objetos `Repartidor`.

---

#### `EntregaDAO.java`

Gestiona el registro de entregas.

Su método:

```text
guardar()
```

inserta en MySQL la relación entre:

```text
Pedido
+
Repartidor
```

junto con la fecha y hora correspondiente.

---

### 4. `controlador`

Contiene las clases que coordinan las operaciones entre la interfaz gráfica y los DAO.

Esto permite evitar que las ventanas sean responsables directamente de la lógica del sistema o del acceso a la base de datos.

El flujo utilizado es:

```text
Vista
  ↓
Controlador
  ↓
DAO
  ↓
MySQL
```

---

#### `ControladorPedidos.java`

Coordina las operaciones relacionadas con los pedidos.

Permite:

```text
Registrar pedidos
Listar pedidos
```

Al registrar un nuevo pedido, crea un objeto `Pedido` con estado:

```text
PENDIENTE
```

y solicita al `PedidoDAO` que lo guarde en la base de datos.

---

#### `ControladorEntregas.java`

Coordina las operaciones relacionadas con repartidores y entregas.

Permite:

```text
Registrar repartidores
Listar repartidores
Listar pedidos pendientes
Iniciar entregas
```

Cuando se inicia una entrega:

```text
Se crea una Entrega
        ↓
EntregaDAO la guarda
        ↓
PedidoDAO cambia el estado
        ↓
PENDIENTE → EN_REPARTO
```

De esta manera, la lógica de asignación y cambio de estado no queda dentro de la ventana gráfica.

---

### 5. `vista`

Contiene las ventanas gráficas desarrolladas mediante Java Swing.

---

#### `VentanaPrincipal.java`

Es la ventana principal del sistema.

Contiene cuatro botones:

```text
Registrar pedido
Registrar repartidor
Listar pedidos
Asignar repartidor / Iniciar entrega
```

Su función principal es permitir la navegación entre las distintas ventanas.

---

#### `VentanaRegistroPedido.java`

Permite registrar nuevos pedidos.

El usuario ingresa:

```text
Dirección
Tipo
```

Los tipos disponibles son:

```text
COMIDA
ENCOMIENDA
EXPRESS
```

Antes de guardar se valida que la dirección no esté vacía.

Si los datos son correctos, la ventana utiliza:

```text
ControladorPedidos
```

para registrar el pedido directamente en MySQL.

---

#### `VentanaRegistroRepartidor.java`

Permite registrar nuevos repartidores.

El usuario ingresa:

```text
Nombre
```

El sistema valida que el campo no esté vacío y luego guarda el repartidor mediante:

```text
ControladorEntregas
```

---

#### `VentanaListaPedidos.java`

Permite visualizar los pedidos almacenados en MySQL.

Utiliza:

```java
JTable
```

junto con:

```java
DefaultTableModel
```

La tabla contiene:

```text
ID
Dirección
Tipo
Estado
```

El botón:

```text
Actualizar
```

realiza nuevamente la consulta a la base de datos y muestra la información más reciente.

---

#### `VentanaRegistroEntrega.java`

Permite iniciar una nueva entrega.

La ventana carga desde MySQL:

```text
Pedidos pendientes
Repartidores registrados
```

El usuario selecciona un pedido y un repartidor.

Al presionar:

```text
Iniciar entrega
```

el sistema:

```text
Registra la entrega
        ↓
Asocia pedido y repartidor
        ↓
Guarda fecha y hora
        ↓
PENDIENTE → EN_REPARTO
```

Los pedidos que ya se encuentran en estado `EN_REPARTO` no aparecen entre los pedidos disponibles.

---

## ⚙️ Instrucciones para ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/mauvalenzuelaf-oss/SistemaSpeedFast_v7.git
```

---

### 2. Abrir el proyecto

Abre **IntelliJ IDEA** y selecciona:

```text
Open
```

Luego abre:

```text
SistemaSpeedFast_v7
```

---

### 3. Crear la base de datos

Abre **MySQL Workbench** y ejecuta el archivo:

```text
database/speedfast_db.sql
```

Este script crea:

```text
speedfast_db
```

y las tablas:

```text
repartidor
pedido
entrega
```

---

### 4. Configurar MySQL Connector/J

El proyecto incluye el conector JDBC en:

```text
lib/
```

En IntelliJ debe encontrarse agregado mediante:

```text
File
→ Project Structure
→ Modules
→ Dependencies
```

con:

```text
Scope: Compile
```

---

### 5. Configurar la conexión

Abre:

```text
src/dao/ConexionBD.java
```

y configura las credenciales correspondientes a tu instalación local de MySQL:

```java
private static final String USUARIO = "root";

private static final String CONTRASENA =
        "TU_CONTRASENA";
```

La contraseña real utilizada durante el desarrollo no se incluye en el repositorio.

---

### 6. Ejecutar la aplicación

Abre:

```text
src/main/Main.java
```

y ejecuta:

```java
main()
```

Se abrirá la ventana principal de SpeedFast.

---

### 7. Utilizar el sistema

Desde la ventana principal es posible:

```text
Registrar pedidos
Registrar repartidores
Consultar pedidos
Iniciar entregas
```

Los datos registrados permanecen almacenados en MySQL después de cerrar la aplicación.

---

## 🖥️ Flujo de funcionamiento

```text
Inicio de aplicación
        ↓
VentanaPrincipal
        │
        ├───────────────┬──────────────────┬─────────────────┐
        │               │                  │                 │
        ↓               ↓                  ↓                 ↓
Registrar pedido   Registrar         Listar pedidos    Iniciar entrega
                   repartidor
        │               │                  │                 │
        ↓               ↓                  ↓                 ↓
Validar datos      Validar nombre     Controlador       Consultar pedidos
        │               │             de pedidos        pendientes
        ↓               ↓                  │                 │
Controlador        Controlador             ↓                 ↓
de pedidos         de entregas        PedidoDAO         Seleccionar
        │               │                  │             repartidor
        ↓               ↓                  ↓                 │
PedidoDAO          RepartidorDAO         SELECT              ↓
        │               │                  │            Controlador
        ↓               ↓                  ↓            de entregas
      INSERT          INSERT             ResultSet            │
        │               │                  │                  ↓
        └───────────────┴──────────────┬───┘          EntregaDAO
                                      │                  +
                                      ↓              PedidoDAO
                                    MySQL                │
                                      │                  ↓
                                      │          INSERT entrega
                                      │          UPDATE pedido
                                      │                  │
                                      └──────────────────┘
                                              ↓
                                  PENDIENTE → EN_REPARTO
```

---

## 💾 Persistencia de datos

A diferencia de la versión correspondiente a la Semana 6, esta versión ya no utiliza un `ArrayList` compartido como almacenamiento principal.

La información se almacena de manera persistente en:

```text
MySQL
```

mediante:

```text
Java Swing
    ↓
Controladores
    ↓
DAO
    ↓
JDBC
    ↓
speedfast_db
```

Esto permite cerrar y volver a ejecutar la aplicación sin perder los pedidos, repartidores y entregas registrados.

---

**Repositorio GitHub:** https://github.com/mauvalenzuelaf-oss/SistemaSpeedFast_v7

**Fecha de Entrega:** 28/09/2026

