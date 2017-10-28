## Caso de uso: CU-01 Crear nuevo problema

### Descripción del escenario:

Un usuario del sistema con una sesión abierta en el mismo desea crear un nuevo problema en un proyecto del cual es miembro.

### Actores

Usuario autenticado (debe ser miembro del proyecto específico).

#### Secuencia de interacciones entre los actores y el sistema

1. El USUARIO descubre un problema en el proyecto.
2. El USUARIO entra al proyecto en el que descubrió el problema.
3. El SISITEMA le muestra la información del proyecto seleccionado.
4. El USUARIO selecciona la opción que le permite crear un nuevo problema.
5. El SISTEMA le muestra la pantalla que le permite crear un problema.
6. El USUARIO completa las distintas características que tiene el problema.
7. El USUARIO selecciona guardar el nuevo problema.
8. El SISTEMA almacena la información del nuevo problema creado en la base de datos.
9. El SISTEMA notifica al usuario el fin de la acción.
10. El SISTEMA vuelve al estado inicial.

### Extensiones / Flujos secundarios

1. El usuario puede cerrar la sesión en cualquier momento.
2. El usuario puede cancelar la acción en cualquier momento. El sistema no realiza ninguna acción salvo volver al estado inicial.
3. En el caso de que al usuario le falte completar un campo del formulario, se mostrará una una notificación que le avisa al mismo que debe completar ese campo.


### Tabla resumen

| ID | Nombre | Objetivo | Actores | Condiciones iniciales | Condiciones finales |
| --- | --- | --- | --- | --- | --- |
| CU-01 | Crear nuevo problema | Crear un nuevo problema en un proyecto | Usuario autenticado | El usuario debe estar autenticado y con una sesión abierta en el sistema, también debe ser miembro del proyecto | El usuario consigue crear el nuevo problema y almacenarlo |