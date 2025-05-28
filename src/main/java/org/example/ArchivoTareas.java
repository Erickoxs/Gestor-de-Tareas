package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ArchivoTareas {
    private List<Tareas> listaDeTareas;
    private static final String NOMBRE_ARCHIVO = "tareas.txt";

    public ArchivoTareas(List<Tareas> listaDeTareas) {
        this.listaDeTareas = listaDeTareas;
    }


    public void mostrarTodasLasTareas() {
        if (listaDeTareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        System.out.println("\n=== LISTA COMPLETA DE TAREAS ===");
        listaDeTareas.forEach(tarea -> {
            System.out.println(tarea.toString());
            System.out.println("-----------------------------");
        });
    }


    public void filtrarPorEstado() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEstados disponibles:");
        System.out.println("1. Por Hacer");
        System.out.println("2. En Progreso");
        System.out.println("3. Completado");
        System.out.print("Seleccione el estado a filtrar (1-3): ");

        try {
            int opcion = scanner.nextInt();
            String estadoSeleccionado;

            switch (opcion) {
                case 1:
                    estadoSeleccionado = "Por Hacer";
                    break;
                case 2:
                    estadoSeleccionado = "En Progreso";
                    break;
                case 3:
                    estadoSeleccionado = "Completado";
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            List<Tareas> tareasFiltradas = listaDeTareas.stream()
                    .filter(t -> t.getEstado().equalsIgnoreCase(estadoSeleccionado))
                    .collect(Collectors.toList());

            if (tareasFiltradas.isEmpty()) {
                System.out.println("\nNo hay tareas con estado: " + estadoSeleccionado);
            } else {
                System.out.println("\n=== TAREAS EN ESTADO: " + estadoSeleccionado.toUpperCase() + " ===");
                tareasFiltradas.forEach(tarea -> {
                    System.out.println(tarea.toString());
                    System.out.println("-----------------------------");
                });
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número del 1 al 3.");
        }
    }


    public void guardarTareas() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Tareas tarea : listaDeTareas) {
                writer.write(tarea.toString());
                writer.newLine();
                writer.write("----------");
                writer.newLine();
            }
        }
    }
}