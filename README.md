# ⚽ HibernateFutbol

Proyecto Java con Maven que gestiona equipos y jugadores de fútbol utilizando **Hibernate ORM** y **MySQL** como base de datos relacional.

---

## 📦 Tecnologías usadas

- Java 17
- Maven
- Hibernate 6.4.9.Final
- MySQL 8
- JPA (Jakarta Persistence API)
- SLF4J + Logback (logging)

---

## ⚙️ Configuración

### 1. Base de datos

Crea la base de datos en tu MySQL (puedes cambiar el puerto si es necesario):

```sql
CREATE DATABASE liga_futbol_db;
```

Ahora, crea las 2 siguiente tablas con relacion 1 - n:
```sql
CREATE TABLE equipo (
	id_equipo INT AUTO_INCREMENT PRIMARY KEY,
	nombre_equipo Varchar(100) NOT NULL,
	ciudad Varchar(100) NOT NULL
);
```

```sql
CREATE TABLE jugador (
	id_jugador INT AUTO_INCREMENT PRIMARY KEY,
	nombre_jugador Varchar(100) NOT NULL,
	posicion Varchar(100) NOT NULL,
	equipo_id INT,
	FOREIGN KEY (equipo_id) REFERENCES equipo(id_equipo)
);
```
---

## 📋 Funcionalidades
**MenuCRUD.java**

Menú por consola con opciones:

0. Salir
1. Recuperar jugador por nombre
2. Recuperar equipo por nombre
3. Ver jugadores de un equipo
4. Insertar un equipo nuevo
5. Insertar un jugador nuevo
6. Actualizar equipo (nombre o ciudad)
7. Actualizar jugador (nombre o posicon)
8. Eliminar equipo (con o sin jugadores asociados)
9. Eliminar jugador

---

## 🚀 Cómo ejecutar

Asegúrate de tener la base de datos liga_futbol_db activa en el puerto 3307.

Importa el proyecto en **IntelliJ IDEA** o **Eclipse**.

Ejecuta la clase **MenuCRUD** para iniciar la aplicación por consola.