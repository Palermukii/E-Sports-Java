# 📊 Sistema de Gestión de Torneos de E-Sports (Java)

## 📌 Descripción del Proyecto

Este proyecto en Java implementa un sistema de gestión de torneos de e-sports, permitiendo la administración de hasta **15 equipos** participantes. Para cada equipo se registran y gestionan sus datos, resultados, estadísticas y posición en el torneo.

El sistema cuenta con un menú interactivo y funcionalidades completas para el manejo de información, incluyendo búsquedas, modificaciones y cálculos estadísticos del torneo.

---

## ⚙️ Funcionalidades del Menú

1. Registrar equipo  
2. Consultar equipo  
3. Modificar equipo  
4. Eliminar equipo  
5. Listar todos los equipos  
6. Registrar resultado de partido  
7. Buscar equipos por videojuego  
8. Buscar equipos por país  
9. Ver tabla de posiciones  
10. Calcular estadísticas del torneo  
11. Salir  

---

## 🧠 Métodos Principales

- `ingresarEquipo()`: Registra los datos de un nuevo equipo (verifica unicidad y capacidad).
- `mostrarMenuYElegirOpcion()`: Muestra el menú principal y permite elegir una opción.
- `generarAccion(opcion)`: Ejecuta la acción correspondiente según la opción elegida.
- `validarID()`: Verifica que el ID esté en el rango correcto y no se repita.
- `buscarEquipo()`: Permite buscar equipos por ID o nombre.
- `consultarEquipo()`: Muestra información detallada de un equipo.
- `modificarEquipo()`: Permite editar los datos de un equipo existente.
- `eliminarEquipo()`: Elimina un equipo del sistema.
- `listarEquipos()`: Muestra todos los equipos registrados.
- `registrarResultadoPartido()`: Registra un partido jugado y actualiza estadísticas.
- `buscarEquiposPorVideojuego()`: Filtra equipos por videojuego.
- `buscarEquiposPorPais()`: Filtra equipos por país de origen.
- `mostrarTablaPosiciones()`: Muestra la tabla ordenada por puntos totales.
- `calcularEstadisticas()`: Calcula porcentajes, promedios y ratios de rendimiento.
- `calcularRatio()`: Devuelve el ratio de victorias/derrotas de un equipo.
- `ingresarEntero()`: Valida entradas numéricas dentro de un rango determinado.

---

## 🔐 Validaciones y Buenas Prácticas

- Validación de entradas por teclado con `try-catch`.
- Scanner declarado **solo** en `main()` y correctamente cerrado.
- Atributos definidos **dentro de métodos**, no globales.
- Métodos diseñados para ser reutilizables.
- Comprobación de unicidad de equipos por `ID` y `nombre`.

---

## 🧪 Modo de Prueba

Para facilitar el testeo, se incluye un lote de datos precargados cuando la constante `MODO_PRUEBA` se encuentra en `true`. Este lote es cargado automáticamente al iniciar el programa mediante el método `cargarDatosPrueba()`.

### Ejemplo de precarga:

```java
equipos[0] = new String[] {"1001", "DragonSlayers", "1", "Corea del Sur", "5", "10", "2", "30"};
equipos[1] = new String[] {"1002", "CSMasters", "2", "Argentina", "6", "8", "4", "24"};
...
