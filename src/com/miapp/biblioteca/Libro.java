package com.miapp.biblioteca;

public class Libro {

    //Atributos

    private String titulo;
    private String autor;
    private String ISBN;
    private String genero;
    private boolean disponible;

    //Constructores

    // Constructor vacio

    public Libro() {
    }

    // Constructor parametrizado

    public Libro(String titulo, String autor, String ISBN, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.genero = genero;
        this.disponible = true;
    }


    //GETTERS y SETTERS

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }


    //METODO Informacion


    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", genero='" + genero + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
