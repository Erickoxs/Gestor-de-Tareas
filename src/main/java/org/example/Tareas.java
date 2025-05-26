package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tareas {
    private int id;
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDate fechaCreacion = LocalDate.now();
    private static int contadorId = 0;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Tareas(String titulo, String descripcion) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = "Por Hacer";
        this.fechaCreacion = fechaCreacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString(){
        return  "id: " + id + "\ntitulo: " + titulo + "\n Descripcion: " + descripcion + "\n estado: " + estado ;
    }
}
