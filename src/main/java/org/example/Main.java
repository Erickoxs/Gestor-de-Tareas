package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        List<Tareas> tareas = new ArrayList<>( List.of(
                new Tareas("Diseñar arquitectura del sistema", "Definir estructura de carpetas, servicios, módulos y base de datos."),
                new Tareas("Implementar autenticación", "Desarrollar login/registro con JWT y OAuth para Google/Twitch."),
                new Tareas("Crear sistema de perfiles", "Permitir edición de perfil, foto de avatar, y bio."),
                new Tareas("Desarrollar feed de publicaciones", "Agregar publicaciones, comentarios, likes y notificaciones."),
                new Tareas("Crear sistema de eventos", "Permitir a organizadores crear y publicar torneos.")
        ));
        Scanner scanner = new Scanner(System.in);
        GestorTareas gestor = new GestorTareas(tareas);
        ArchivoTareas archivo = new ArchivoTareas(tareas);

        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("Gestor de tareas por consola");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Actualizar Tarea");
            System.out.println("3. Eliminar Tarea");
            System.out.println("4. Mostrar todas las Tareas");
            System.out.println("5. Filtrar tareas por estado");
            System.out.println("6. Salir");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next(); // consumir valor incorrecto
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine(); // consumir salto de línea

            switch (opcion) {
                case 1:
                    gestor.agregarTarea();
                    break;
                case 2:
                    gestor.actualizarTarea();
                    break;
                case 3:
                    gestor.eliminarTareaPorId();
                    break;
                case 4:
                    archivo.mostrarTodasLasTareas();
                    break;
                case 5:
                    archivo.filtrarPorEstado();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    ejecutando = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

        scanner.close();
    }
}
