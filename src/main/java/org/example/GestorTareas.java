package org.example;

import java.util.*;

public class GestorTareas {
    private Tareas tarea;
    private List<Tareas> listaDeTareas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public GestorTareas(List<Tareas> listaDeTareas) {
        this.listaDeTareas = listaDeTareas;
    }


    public void agregarTarea (){
        // Pedir por teclado los parametros a instanciar
        System.out.println("Agrega el titulo de la tarea");
          String titulo = scanner.nextLine();
        System.out.println("Agrega la descripcion de la tarea");
          String descripcion = scanner.nextLine();


          // Instanciar la tarea
        tarea = new Tareas(titulo, descripcion);
        // Agregar la tarea a la lista de tareas
        listaDeTareas.add(tarea);

        System.out.println(tarea + "Se agrego con exito.");
    }


    public void listarTareas() {

        System.out.println("\n--- 📖 Sección de Listado de Tareas ---");

        if (listaDeTareas.isEmpty()) {
            System.out.println("INFO: No hay tareas registradas para mostrar. 텅 비었어요"); // Empty
            return;
        }

        System.out.println("¿Cómo desea listar las tareas?");
        System.out.println("1. Mostrar todas las tareas.");
        System.out.println("2. Filtrar tareas por estado.");
        System.out.print("Por favor, ingrese su opción (1 o 2): ");

        int opcionPrincipal;
        if (scanner.hasNextInt()) {
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente después de nextInt()
        } else {
            System.out.println("ERROR: Opción inválida. Debe ingresar un número.");
            scanner.nextLine(); // Limpiar el buffer del scanner
            return;
        }


        if (opcionPrincipal == 1) {
            System.out.println("\n--- 📚 Listado de Todas las Tareas ---");
            int contador = 1;
            for (Tareas tarea : listaDeTareas) {
                System.out.println(contador + ". " + tarea);
                contador++;
            }
            if (listaDeTareas.isEmpty()) { // Doble chequeo, aunque el primero debería bastar
                System.out.println("INFO: No hay tareas para mostrar.");
            }
            System.out.println("------------------------------------");

        } else if (opcionPrincipal == 2) {
            System.out.println("\n--- 🔎 Filtrar Tareas por Estado ---");
            System.out.println("Seleccione el estado por el cual desea filtrar:");
            System.out.println("1. Por hacer");
            System.out.println("2. En progreso");
            System.out.println("3. Completado"); // Asegúrate que este String coincida con el que guardas
            System.out.print("Ingrese su opción de estado (1, 2, o 3): ");

            int opcionEstado;
            if (scanner.hasNextInt()) {
                opcionEstado = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
            } else {
                System.out.println("ERROR: Opción de estado inválida. Debe ingresar un número.");
                scanner.nextLine(); // Limpiar el buffer
                return;
            }

            String estadoBuscado = null;
            if (opcionEstado == 1) {
                estadoBuscado = "Por hacer";
            } else if (opcionEstado == 2) {
                estadoBuscado = "En progreso";
            } else if (opcionEstado == 3) {
                // OJO: En tu código original tenías "Completado.". Sé consistente.
                // Usaré "Completado" sin punto para este ejemplo.
                estadoBuscado = "Completado";
            } else {
                System.out.println("ERROR: Opción de estado no válida.");
                return; // Salimos si la opción de estado no es válida
            }

            System.out.println("\n--- Tareas con estado: '" + estadoBuscado + "' ---");
            boolean encontradas = false;
            int contadorFiltrado = 1;
            for (Tareas tarea : listaDeTareas) {
                // Asumiendo que tarea.getEstado() devuelve List<String>
                // Si devuelve un solo String, la comparación sería: estadoBuscado.equals(tarea.getEstado())
                if (tarea.getEstado() != null && tarea.getEstado().contains(estadoBuscado)) {
                    System.out.println(contadorFiltrado + ". " + tarea);
                    encontradas = true;
                    contadorFiltrado++;
                }
            }

            if (!encontradas) {
                System.out.println("INFO: No se encontraron tareas con el estado '" + estadoBuscado + "'.");
            }
            System.out.println("---------------------------------------");

        } else {
            System.out.println("ERROR: Opción principal no reconocida (" + opcionPrincipal + "). Por favor, elija 1 o 2.");
        }

    }

    public void actualizarTarea() {
        System.out.println("\n--- ✏️ Actualizar Tarea ---");

        if (listaDeTareas.isEmpty()) {
            System.out.println("INFO: No hay tareas registradas para actualizar.");
            return;
        }

        System.out.print("Escriba el ID de la tarea que desea actualizar: ");
        int id;
        if (scanner.hasNextInt()) {
            id = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
        } else {
            System.out.println("ERROR: ID inválido. Debe ingresar un número.");
            scanner.nextLine(); // Limpiar el buffer de entrada incorrecta
            return;
        }

        Tareas tareaAActualizar = null;
        for (Tareas tareaActual : listaDeTareas) {
            if (tareaActual.getId() == id) {
                tareaAActualizar = tareaActual;
                break; // Tarea encontrada, no necesitamos seguir buscando
            }
        }

        if (tareaAActualizar == null) {
            System.out.println("ERROR: No se encontró una tarea con el ID " + id + ".");
            return;
        }

        System.out.println("INFO: Tarea encontrada. Datos actuales: " + tareaAActualizar);

        boolean continuarEditando = true;
        while (continuarEditando) {
            System.out.println("\n¿Qué desea editar para la Tarea ID " + id + " ('" + tareaAActualizar.getTitulo() + "')?");
            System.out.println("1. Estado");
            System.out.println("2. Título");
            System.out.println("3. Descripción");
            System.out.println("0. Terminar de editar esta tarea");
            System.out.print("Seleccione una opción: ");

            int opcion;
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente
            } else {
                System.out.println("ERROR: Opción inválida. Debe ingresar un número.");
                scanner.nextLine(); // Limpiar el buffer
                continue; // Vuelve al inicio del bucle while para pedir la opción de nuevo
            }

            switch (opcion) {
                case 1: // Editar Estado
                    System.out.println("--- Seleccione el Nuevo Estado ---");
                    System.out.println("a. Por hacer");
                    System.out.println("b. En progreso");
                    System.out.println("c. Completado");
                    System.out.print("Ingrese la letra de la opción (a, b, o c): ");
                    String opcionEstadoStr = scanner.nextLine().trim().toLowerCase();
                    String nuevoEstadoString = null;

                    if (opcionEstadoStr.equals("a")) nuevoEstadoString = "Por hacer";
                    else if (opcionEstadoStr.equals("b")) nuevoEstadoString = "En progreso";
                    else if (opcionEstadoStr.equals("c")) nuevoEstadoString = "Completado"; // Asegúrate que estos strings coincidan
                    else {
                        System.out.println("ERROR: Opción de estado no reconocida. No se realizaron cambios.");
                        break; // Sale del switch, vuelve al menú de edición
                    }


                    tareaAActualizar.setEstado(nuevoEstadoString);


                    System.out.println("INFO: Estado de la Tarea ID " + id + " actualizado a '" + nuevoEstadoString + "'.");
                    break;

                case 2: // Editar Título
                    System.out.print("Ingrese el nuevo título para la Tarea ID " + id + ": ");
                    String nuevoTitulo = scanner.nextLine();
                    tareaAActualizar.setTitulo(nuevoTitulo);
                    System.out.println("INFO: Título de la Tarea ID " + id + " actualizado.");
                    break;

                case 3: // Editar Descripción
                    System.out.println("Ingrese la nueva descripción para la Tarea ID " + id + " (deje una línea vacía y presione Enter para terminar):");
                    List<String> lineasNuevaDescripcion = new ArrayList<>(); // ArrayList sigue siendo útil aquí para construir la descripción
                    String lineaDesc;
                    while (!(lineaDesc = scanner.nextLine()).isEmpty()) { // Leer hasta línea vacía
                        lineasNuevaDescripcion.add(lineaDesc);
                    }
                    String nuevaDescripcion = String.join(System.lineSeparator(), lineasNuevaDescripcion);
                    tareaAActualizar.setDescripcion(nuevaDescripcion);
                    System.out.println("INFO: Descripción de la Tarea ID " + id + " actualizada.");
                    break;

                case 0: // Salir del bucle de edición
                    continuarEditando = false;
                    System.out.println("INFO: Edición finalizada para la Tarea ID " + id + ".");
                    break;

                default:
                    System.out.println("ERROR: Opción de edición no válida (" + opcion + "). Por favor, intente de nuevo.");
                    break;
            }
            if (continuarEditando) {
                System.out.println("INFO: Datos actualizados de la Tarea: " + tareaAActualizar);
            }
        }
    }

    public void eliminarTareaPorId() { // Nombre con camelCase
        System.out.println("\n--- 🗑️ Eliminar Tarea por ID ---");

        if (listaDeTareas.isEmpty()) {
            System.out.println("INFO: La lista de tareas está vacía. No hay nada que eliminar.");
            return;
        }

        System.out.print("Ingrese el ID de la tarea que desea eliminar: ");
        int idParaEliminar;

        if (scanner.hasNextInt()) {
            idParaEliminar = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
        } else {
            System.out.println("ERROR: ID inválido. Debe ingresar un número.");
            scanner.nextLine(); // Limpiar el buffer de entrada incorrecta
            return;
        }

        boolean tareaEliminada = false;
        // Usar un Iterador para eliminar elementos de forma segura mientras se recorre la lista
        Iterator<Tareas> iterador = listaDeTareas.iterator();
        while (iterador.hasNext()) {
            Tareas tareaActual = iterador.next();
            if (tareaActual.getId() == idParaEliminar) {
                iterador.remove(); // Elimina el elemento actual de forma segura
                tareaEliminada = true;
                System.out.println("INFO: Tarea con ID " + idParaEliminar + " eliminada exitosamente. 👍");
                break; // Salimos del bucle porque asumimos que los IDs son únicos y ya encontramos la tarea
            }
        }

        if (!tareaEliminada) {
            System.out.println("INFO: No se encontró ninguna tarea con el ID " + idParaEliminar + ".");
        }
    }





}
