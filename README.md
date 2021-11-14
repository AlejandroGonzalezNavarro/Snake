# Snake
Juego de snake hecha con hilos y java swing

Aplicación (en desarrollo) hecha con java swing e hilos para su funcionamiento.

## Aviso
Este código no está auditado ni es a prueba de fallos, si lo usas es bajo tu propia responsabilidad.

Al iniciar la aplicación se mostrará un panel con las opciones de configuración de la partida:
- Dificultad: la dificultad consta del tamaño del tablero y la velocidad de la serpiente.
Dificultades:
- Fácil:
 - Tamaño: 20x20
 - Velocidad: Baja
- Media:
 - Tamaño: 30x30
 - Velocidad: Media
- Difícil:
 - Tamaño: 40x40
 - Velocidad: Alta
- Personalizada: Cualquier mezcla de opciones que no estén definidas anteriormente.
A continuación se presentan dos botones:
- Comenzar juego: Comienza el juego, mejor que estés preparado para el desafío.
- Información: Información del juego, bonificaciones y sistema de puntuación.
Al comenzar el juego la serpiente se mueve con las teclas o con las flechas direccionales
A o ← (para ir a la izquierda)
W o ↑ (para ir hacia arriba)
S o ↓ (para ir hacia abajo)
D o → (para ir a la derecha)
En el juego se mostrarán los beneficios obtenidos, las manzanas recogidas, la puntuación y
el tiempo.
Como viene explicado en la pantalla de información, cada manzana da 100 puntos, pero por
cada segundo tu puntuación se reducirá en 10.
Una vez se acabe el juego, se mostrará por pantalla una caja de diálogo con la puntuación,
los beneficios y las manzanas obtenidas. Si has superado alguna de las 3 máximas
puntuaciones aparecerá una caja de texto y un botón, para guardar tu nombre puedes
introducir una combinación de 3 caracteres.
Por último al salir de esta caja de diálogo se mostrará la pantalla de clasificaciones final.
Con solo la opción de salir de la aplicación.

## No implementado

Sistema de encriptación para la tabla de puntuaciones.
Poder volver a jugar sin necesidad de cerrar la aplicación.
