package com.miapp.biblioteca.ui;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.service.LibrosService;
import com.miapp.biblioteca.service.UsuarioService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ArrayList de libros
        ArrayList<Libro> biblioteca = new ArrayList<>();
        // ArrayList de usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        // Objetos de LibroService y UsuarioService
        LibrosService ls = new LibrosService(biblioteca);
        UsuarioService us = new UsuarioService(usuarios);

        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){

                // Crear usuario
                case 1:
                    crearUsuario(sc, us);
                    break;

                // Crear un libro - titulo, autor, ISBN, genero
                case 2:
                    crearLibro(sc, ls);
                    break;

                // Actualizar libro
                case 3:
                    actualizarLibro(sc, ls);
                    break;

                case 4:
                    buscarLibroPorISBN(sc, ls);
                    break;

                case 5:
                    buscarLibroPorTitulo(sc, ls);
                    break;

                case 6:
                    listarLibros(ls, biblioteca);
                    break;

                case 7:
                    eliminarLibro(sc, ls);
                    break;

                case 8:
                    realizarPrestamo(sc, us, ls);
                    break;

                case 9:
                    realizarDevolucion(sc, us, ls);
                    break;

                default:
                    System.out.println("La opción ingresada no es valida");
                    System.out.println();
                    break;
            }

        } while (opcion > 0  && opcion <= 10);
    }

    public static void mostrarMenu(){
        System.out.println("=== Biblioteca virtual ===");
        System.out.println("1 - Crear un usuario");
        System.out.println("2 - Crear Libro");
        System.out.println("3 - Actualizar libro");
        System.out.println("4 - Buscar libro por ISBN");
        System.out.println("5 - Buscar libro pot título");
        System.out.println("6 - Listar Libros");
        System.out.println("7 - Eliminar libro");
        System.out.println("8 - Prestamos");
        System.out.println("9 - Devoluciones");
        System.out.println("10 - Salir");
        System.out.println("=== Seleccione una opcion ===");
    }

    public static void crearUsuario(Scanner scanner, UsuarioService UsuarioService) {
        System.out.println("Ingrese la id del usuario");
        String idUsuarioCrear = scanner.nextLine();

        if (!idUsuarioCrear.trim().isEmpty()){
            if (UsuarioService.buscarId(idUsuarioCrear) == null){
                System.out.println("Ingrese el nombre del usuario");
                String nombreUsuarioCrear = scanner.nextLine();

                if (!nombreUsuarioCrear.trim().isEmpty()){
                    UsuarioService.create(idUsuarioCrear, nombreUsuarioCrear);
                    System.out.println("El usuario: " + nombreUsuarioCrear + " con id " + idUsuarioCrear + " Ha sido creado correctamente.");
                    System.out.println();
                } else {
                    System.out.println("Ingrese un nombre válido");
                    System.out.println();
                }
            } else {
                System.out.println("Un usuario con la id " + idUsuarioCrear + " ya se encuentra registrado");
                System.out.println();
            }
        } else {
            System.out.println("Ingrese un dato válido para la id del usuario");
            System.out.println();
        }
    }

    public static void crearLibro(Scanner scanner, LibrosService libroService) {
        System.out.println("Ingrese el nombre del libro");
        String tituloDelLibroCrear = scanner.nextLine();

        if(!tituloDelLibroCrear.trim().isEmpty()){
            System.out.println("Ingrese el nombre del autor del libro");
            String nombreAutorCrear = scanner.nextLine();
            if(!nombreAutorCrear.trim().isEmpty()){
                System.out.println("Ingrese el ISBN del libro");
                String ISBNLibroCrear = scanner.nextLine();
                if (!ISBNLibroCrear.trim().isEmpty()){
                    Libro verificarISBNExistente = libroService.readByISBN(ISBNLibroCrear);
                    if (verificarISBNExistente == null){
                        System.out.println("Ingrese el género del libro");
                        String generoLibroCrear = scanner.nextLine();
                        if (!generoLibroCrear.trim().isEmpty()){
                            libroService.create(tituloDelLibroCrear, nombreAutorCrear, ISBNLibroCrear, generoLibroCrear);
                            System.out.println("El libro con el titulo " + tituloDelLibroCrear + " con ISBN " + ISBNLibroCrear + " fue creado correctamente.");
                            System.out.println();
                        } else {
                            System.out.println("Ingrese un género válido para el libro");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Ya existe un libro registrado con ese valor de ISBN");
                        System.out.println();
                    }
                } else {
                    System.out.println("Ingrese un ISBN válido");
                    System.out.println();
                }
            } else {
                System.out.println("Ingrese un nombre de autor válido");
                System.out.println();
            }
        } else {
            System.out.println("Ingrese un nombre válido");
            System.out.println();
        }
    }

    public static void actualizarLibro(Scanner sc, LibrosService ls) {
        // String ISBN, String nuevoAutor, String nuevoTitulo, String nuevoGenero
        System.out.println("Ingrese el ISBN del libro que desea actualizar");
        String ISBNLibroActualizar = sc.nextLine();
        if (!ISBNLibroActualizar.trim().isEmpty()){
            if (ls.readByISBN(ISBNLibroActualizar) != null){
                System.out.println("Ingresa el nuevo autor para el libro");
                String nuevoAutorActualizar = sc.nextLine();
                if (!nuevoAutorActualizar.trim().isEmpty()){
                    System.out.println("Ingresa el nuevo título para el libro");
                    String nuevoTituloActualizar = sc.nextLine();
                    if (!nuevoTituloActualizar.trim().isEmpty()){
                        System.out.println("Ingresa el nuevo género para el libro");
                        String nuevoGeneroActualizar = sc.nextLine();
                        if (!nuevoGeneroActualizar.trim().isEmpty()){
                            ls.update(ISBNLibroActualizar, nuevoAutorActualizar, nuevoTituloActualizar, nuevoGeneroActualizar);
                            System.out.println("El libro fue actualizado correctamente.");
                            System.out.println();
                        } else {
                            System.out.println("Ingrese un género válido");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Ingresa un título válido");
                        System.out.println();
                    }
                } else {
                    System.out.println("Ingresa un autor válido");
                    System.out.println();
                }
            } else {
                System.out.println("No existe ningun libro con ISBN ingresado");
                System.out.println();
            }
        } else {
            System.out.println("Ingrese un ISBN valido");
            System.out.println();
        }
    }

    public static void buscarLibroPorISBN(Scanner sc, LibrosService ls) {
        System.out.println("Ingrese el ISBN del libro que desea buscar");
        String ISBNLibroBusqueda = sc.nextLine();
        if (!ISBNLibroBusqueda.trim().isEmpty()){
            if (ls.readByISBN(ISBNLibroBusqueda) != null){
                System.out.println("Se encontró un libro registrado con ISBN: " + ISBNLibroBusqueda);
                System.out.println(ls.readByISBN(ISBNLibroBusqueda));
                System.out.println();
            } else {
                System.out.println("No se encontró ningun libro con ISBN: " + ISBNLibroBusqueda);
                System.out.println();
            }
        } else{
            System.out.println("Ingresa un ISBN válido");
            System.out.println();
        }
    }

    public static void buscarLibroPorTitulo(Scanner sc, LibrosService ls) {
        System.out.println("Ingrese el título del libro que desea buscar");
        String tituloLibroBusqueda = sc.nextLine();
        if (!tituloLibroBusqueda.trim().isEmpty()){
            if (ls.readByBook(tituloLibroBusqueda) != null){
                System.out.println("Se encontró un libro registrado con título: " + tituloLibroBusqueda);
                System.out.println(ls.readByBook(tituloLibroBusqueda));
                System.out.println();
            } else {
                System.out.println("No se encontró ningun libro con título: " + tituloLibroBusqueda);
                System.out.println();
            }
        } else{
            System.out.println("Ingresa un título válido");
            System.out.println();
        }
    }

    public static void listarLibros(LibrosService ls, ArrayList<Libro> biblioteca) {
        if (biblioteca.size() > 0){
            System.out.println("Registrado hasta el momento: ");
            System.out.println(ls.readAll());
        } else {
            System.out.println("Aún no hay libros registrados");
            System.out.println();
        }
    }

    public static void eliminarLibro(Scanner sc, LibrosService ls) {
        System.out.println("Ingrese el ISBN del libro que desea eliminar");
        String ISBNBorrar = sc.nextLine();
        if (!ISBNBorrar.trim().isEmpty()){
            if (ls.readByISBN(ISBNBorrar) != null){
                ls.delete(ISBNBorrar);
                System.out.println("Se ha borrado el libro con ISBN: " + ISBNBorrar);
                System.out.println();
            } else {
                System.out.println("No se encontró ningun libro con ISBN: " + ISBNBorrar);
                System.out.println();
            }
        } else {
            System.out.println("Ingrese un ISBN válido");
            System.out.println();
        }
    }

    public static void realizarPrestamo(Scanner sc, UsuarioService us, LibrosService ls) {
        System.out.println("Ingrese la id del usuario al cual quiere hacer el prestamo");
        String idUsuarioPrestamo = sc.nextLine();
        if (!idUsuarioPrestamo.trim().isEmpty()){
            if (us.buscarId(idUsuarioPrestamo) != null){
                Usuario usuarioPrestamo = us.buscarId(idUsuarioPrestamo);
                System.out.println("Ingrese ISBN del libro que desea realizar el préstamo");
                String ISBNLibroPrestamo = sc.nextLine();
                if (!ISBNLibroPrestamo.trim().isEmpty()){
                    if (ls.readByISBN(ISBNLibroPrestamo) != null){
                        Libro libroPrestamo = ls.readByISBN(ISBNLibroPrestamo);
                        us.prestamoLibro(usuarioPrestamo, libroPrestamo);
                        System.out.println("El prestamo se hizo correctamente. Libro: " + libroPrestamo + " Usuario: " + usuarioPrestamo);
                        System.out.println();
                    } else {
                        System.out.println("No se encontró el libro con ISBN: " + ISBNLibroPrestamo);
                        System.out.println();
                    }
                } else {
                    System.out.println("Ingrese un ISBN válido");
                    System.out.println();
                }
            } else {
                System.out.println("No se encontro el usuario con id: " + idUsuarioPrestamo);
                System.out.println();
            }
        } else {
            System.out.println("Ingrese una id válida");
            System.out.println();
        }
    }

    public static void realizarDevolucion(Scanner sc, UsuarioService us, LibrosService ls) {
        System.out.println("Ingrese la id del usuario el cual quiere realizar una devolución");
        String idUsuarioDevolucion = sc.nextLine();
        if (!idUsuarioDevolucion.trim().isEmpty()){
            if (us.buscarId(idUsuarioDevolucion) != null){
                Usuario usuarioDevolucion = us.buscarId(idUsuarioDevolucion);
                System.out.println("Ingrese ISBN del libro que desea devolver");
                String ISBNLibroPrestamo = sc.nextLine();
                if (!ISBNLibroPrestamo.trim().isEmpty()){
                    if (ls.readByISBN(ISBNLibroPrestamo) != null){
                        Libro libroDevolucion = ls.readByISBN(ISBNLibroPrestamo);
                        us.devolverLibro(usuarioDevolucion, libroDevolucion);
                        System.out.println("La devolución se hizo correctamente. Libro: " + libroDevolucion + " Usuario: " + usuarioDevolucion);
                        System.out.println();
                    } else {
                        System.out.println("No se encontró el libro con ISBN: " + ISBNLibroPrestamo);
                        System.out.println();
                    }
                } else {
                    System.out.println("Ingrese un ISBN válido");
                    System.out.println();
                }
            } else {
                System.out.println("No se encontro el usuario con id: " + idUsuarioDevolucion);
                System.out.println();
            }
        } else {
            System.out.println("Ingrese una id válida");
            System.out.println();
        }
    }
}
