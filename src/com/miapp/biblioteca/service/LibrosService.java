package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Libro;

import java.util.ArrayList;

public class LibrosService {
    private ArrayList<Libro> biblioteca;

    public LibrosService(ArrayList<Libro> biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void create(String titulo, String autor, String ISBN, String genero){
        Libro nuevoLibro = new Libro(titulo, autor, ISBN, genero);
        biblioteca.add(nuevoLibro);
    }

    public ArrayList<Libro> readAll() {
        return biblioteca;
    }

    public Libro readByISBN(String ISBN) {
        for ( Libro libro : biblioteca){
            if (libro.getISBN().equals(ISBN)){
                return libro;
            }
        }
        return null;
    }

    // leer libro por titulo
    public ArrayList<Libro> readByBook(String titulo){
        ArrayList<Libro> libroEncontrado = new ArrayList<>();
        for (Libro libro : biblioteca){
            if (libro.getTitulo().equalsIgnoreCase(titulo)){
                libroEncontrado.add(libro);
            }
        }
        return libroEncontrado;
    }

    public void update(String ISBN, String nuevoAutor, String nuevoTitulo, String nuevoGenero){
        for(Libro libro : biblioteca){
            if (libro.getISBN().equals(ISBN)){
                libro.setAutor(nuevoAutor);
                libro.setTitulo(nuevoTitulo);
                libro.setGenero(nuevoGenero);
            }
        }
    }

    public void delete(String ISBN) {
        biblioteca.removeIf(libro -> libro.getISBN().equals(ISBN));
    }

    // Verifica si el libro se encuentra disponible
    public boolean estaDisponible(Libro libro) {
        return libro.isDisponible();
    }
}
