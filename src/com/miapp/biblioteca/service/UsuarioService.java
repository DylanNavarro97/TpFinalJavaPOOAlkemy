package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;

import java.util.ArrayList;

public class UsuarioService {
    private ArrayList<Usuario> usuarios;

    public UsuarioService(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void create(String id, String nombre) {
        Usuario nuevoUsuario = new Usuario(nombre, id);
        usuarios.add(nuevoUsuario);
    }

    public ArrayList<Usuario> read() {
        return  usuarios;
    }

    public void update (String id, String nuevoNombre){
        for (Usuario usuario : usuarios){
            if (usuario.getId().equals(id)){
                usuario.setNombre(nuevoNombre);
            }
        }
    }

    public void delete (String id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public Usuario buscarId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    // Prestar Libro
    public void prestamoLibro(Usuario usuario, Libro libro){
        if (libro.isDisponible()){
            usuario.getLibrosPrestados().add(libro);
            libro.setDisponible(false);
        } else {
            System.out.println("Lamentablemente el libro no se encuentra disponible");
        }
    }

    // Devolucion de libro
    public void devolverLibro(Usuario usuario, Libro libro) {
        if (usuario.getLibrosPrestados().contains(libro)){
            usuario.getLibrosPrestados().remove(libro);
            libro.setDisponible(true);
        }
    }

    // Obtener libros prestados

    public ArrayList<Libro> obtenerLibrosPrestados(Usuario usuario) {
        return usuario.getLibrosPrestados();
    }
}
