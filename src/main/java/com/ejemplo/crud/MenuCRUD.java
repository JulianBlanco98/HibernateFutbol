package com.ejemplo.crud;

import com.ejemplo.dao.EquipoDAO;
import com.ejemplo.dao.JugadorDAO;
import com.ejemplo.model.Equipo;
import com.ejemplo.model.Jugador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class MenuCRUD {

    private static final Logger logger = LoggerFactory.getLogger(MenuCRUD.class);

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int opcion;
        EquipoDAO equipoDAO = new EquipoDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();
        String nombreEquipo = "";
        String nombreJugador= "";
        String opcion2 = "";

        do {
            menu();
            System.out.print("Ingresa una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 0:
                    System.out.print("Hasta luego");
                    break;

                case 1:
                    System.out.println("GET JugadorByNombreJugador");
                    System.out.print("Introduce el nombre del jugador a buscar: ");
                    nombreJugador = sc.nextLine();

                    Jugador jugador = jugadorDAO.getJugadorByNombreJugador(nombreJugador);
                    if (jugador != null) {
                        showJugador(jugador);
                    } else {
                        System.out.print("El jugador " + nombreJugador + " no se encuentra en la BD");
                    }

                    break;

                case 2:
                    System.out.println("GET EquipoByNombreEqipo");
                    System.out.print("Introduce el nombre del equipo a buscar: ");
                    nombreEquipo = sc.nextLine();

                    Equipo equipoBuscado = equipoDAO.getEquipoByNomebreEquipo(nombreEquipo);
                    if(equipoBuscado != null) {
                        showEquipo(equipoBuscado);
                    }
                    else {
                        System.out.print("El equipo " + nombreEquipo + " no se encuentra en la BD");
                    }


                    break;

                case 3:
                    System.out.println("GET JugadoresByNombreEqipo");
                    System.out.print("Introduce el nombre del equipo a buscar: ");
                    nombreEquipo = sc.nextLine();

                    Equipo equipoJugadores = equipoDAO.getEquipoByNomebreEquipo(nombreEquipo);
                    if(equipoJugadores != null) {

                        List<Jugador> jugadores = jugadorDAO.getJugadoresByNombreEquipo(nombreEquipo);

                        if(jugadores != null && !jugadores.isEmpty()) {
                            System.out.print("Jugadores del equipo: ");
                            showJugadoresEquipo(jugadores);
                        }
                        else {
                            System.out.println("No existen jugadores de este equipo");
                        }

                    }
                    else{
                        System.out.print("El equipo " + nombreEquipo + " no se encuentra en la BD");
                    }

                    break;

                case 4:
                    System.out.println("CREATE Equipo");
                    System.out.print("Introduce el nombre del equipo nuevo: ");
                    nombreEquipo = sc.nextLine();

                    Equipo equipoNuevo = equipoDAO.getEquipoByNomebreEquipo(nombreEquipo);
                    if(equipoNuevo == null) {
                        String ciudad = "";
                        equipoNuevo = new Equipo();
                        System.out.print("Introduce el nombre de la ciudad de este equipo: ");
                        ciudad = sc.nextLine();
                        equipoNuevo.setNombreEquipo(nombreEquipo);
                        equipoNuevo.setCiudad(ciudad);
                        equipoDAO.addEquipo(equipoNuevo);
                        System.out.println("Equipo añadido correctamente a la BD");

                    }
                    else {
                        System.out.println("Este equipo "+nombreEquipo+ " ya esta en la BD");
                    }

                    break;

                case 5:
                    System.out.println("CREATE Jugador");
                    System.out.print("Introduce el nombre del equipo del cual es el jugador: ");
                    nombreEquipo = sc.nextLine();

                    Equipo equipoJugador = equipoDAO.getEquipoByNomebreEquipo(nombreEquipo);

                    if(equipoJugador != null) {

                        System.out.print("Introduce el nombre del jugador: ");
                        nombreJugador = sc.nextLine();
                        Jugador nuevoJugador = jugadorDAO.getJugadorByNombreJugador(nombreJugador);

                        if(nuevoJugador == null) {
                            String posicion = "";
                            nuevoJugador = new Jugador();
                            System.out.print("Introduce la posicion del jugador: ");
                            posicion = sc.nextLine();
                            nuevoJugador.setNombreJugador(nombreJugador);
                            nuevoJugador.setPosicion(posicion);
                            nuevoJugador.setEquipo(equipoJugador);

                            jugadorDAO.addJugador(nuevoJugador);

                        }
                        else {
                            System.out.println("Este jugador ya existe en la BD");
                        }

                    }
                    else {
                        System.out.println("Este equipo no está en la liga");
                    }


                    break;

                case 6:
                    System.out.println("UPDATE EquipoByNombreEquipo");
                    System.out.print("Introduce el nombre del equipo a actualizar: ");
                    nombreEquipo = sc.nextLine();

                    Equipo equipoActualizado = equipoDAO.getEquipoByNomebreEquipo(nombreEquipo);
                    if(equipoActualizado != null) {
                        String nuevoNombreEquipo = "";
                        String nuevaCiudad = "";

                        System.out.print("¿Quieres actualizar el nommbre del equipo?(s): ");
                        opcion2 = sc.nextLine();

                        // hbaría que mirar que el nombre del equipo no existe ya, pero no voy a especificar tanto
                        if(opcion2.equalsIgnoreCase("s")) {
                            System.out.print("Introduce el nuevo nombre: ");
                            nuevoNombreEquipo = sc.nextLine();
                            equipoActualizado.setNombreEquipo(nuevoNombreEquipo);
                        }

                        System.out.print("¿Quieres actualizar la ciudad del equipo?(s): ");
                        opcion2 = sc.nextLine();

                        if(opcion2.equalsIgnoreCase("s")) {
                            System.out.print("Introduce la nueva ciudad: ");
                            nuevaCiudad = sc.nextLine();
                            equipoActualizado.setCiudad(nuevaCiudad);
                        }

                        equipoDAO.updateEquipo(equipoActualizado);
                        System.out.println("Equipo actualziado correctamente");

                    }
                    else {
                        System.out.println("Este equipo no está en la liga");
                    }

                    break;

                case 7:
                    System.out.println("UPDATE JugadorByNombreJugador");
                    System.out.print("Introduce el nombre del equipo a actualizar: ");
                    nombreJugador = sc.nextLine();

                    Jugador jugadorActualizado = jugadorDAO.getJugadorByNombreJugador(nombreJugador);

                    if(jugadorActualizado != null) {
                        String nuevoNombreJugador = "";
                        String nuevaPosicion = "";

                        System.out.print("¿Quieres actualizar el nommbre del jugador?(s): ");
                        opcion2 = sc.nextLine();

                        if(opcion2.equalsIgnoreCase("s")) {
                            System.out.print("Introduce el nuevo nombre del jugador: ");
                            nuevoNombreJugador = sc.nextLine();
                            jugadorActualizado.setNombreJugador(nuevoNombreJugador);
                        }

                        System.out.print("¿Quieres actualizar la posicion del jugador?(s): ");
                        opcion2 = sc.nextLine();

                        if(opcion2.equalsIgnoreCase("s")) {
                            System.out.print("Introduce la nueva posicion: ");
                            nuevaPosicion = sc.nextLine();
                            jugadorActualizado.setPosicion(nuevaPosicion);
                        }

                        jugadorDAO.updateJugador(jugadorActualizado);
                        System.out.println("Equipo actualziado correctamente");
                    }
                    else {
                        System.out.println("Este jugador no está en la liga");
                    }

                    break;

                case 8:
                    System.out.println("DELETE equipoByNombreEquipo");
                    System.out.print("Introduce el nombre del equipo a borrar: ");
                    nombreEquipo = sc.nextLine();

                    Equipo equipoBorrado = equipoDAO.getEquipoByNomebreEquipo(nombreEquipo);
                    if(equipoBorrado != null) {
                        System.out.print("Quieres borrar al "+equipoBorrado.getNombreEquipo()+" ?(s):");
                        opcion2 = sc.nextLine();

                        if(opcion2.equalsIgnoreCase("s")) {
                            equipoDAO.deleteEquipo(equipoBorrado);
                            System.out.println("Equipo borrado con exito");
                        }
                        else {
                            System.out.println("No se borrara este equipo");
                        }
                    }
                    else {
                        System.out.println("Este equipo no está en la liga");
                    }

                    break;

                case 9:
                    System.out.println("DELETE jugadorByNombreJugador");
                    System.out.println("Introduce el nombre del jugador a borrar: ");
                    nombreJugador = sc.nextLine();

                    Jugador jugadorBorrado = jugadorDAO.getJugadorByNombreJugador(nombreJugador);
                    if(jugadorBorrado != null) {
                        System.out.print("Quieres borrar al "+jugadorBorrado.getNombreJugador()+" ?(s):");
                        opcion2 = sc.nextLine();

                        if(opcion2.equalsIgnoreCase("s")) {
                            jugadorDAO.deleteJugador(jugadorBorrado);
                            System.out.println("Jugador borrado con exito");
                        }
                        else {
                            System.out.println("No se borrara este jugador");
                        }
                    }
                    else {
                        System.out.println("Este jugador no está en la liga");
                    }

                    break;


                default:
                    System.out.print("Opcion incorrecta. Introduce un numero valido");
                    break;


            }


        }
        while (opcion != 0);


    }

    private static void menu() {
        System.out.println("\n\n\n-------------------MENU CRUD--------------------");
        System.out.println("0. Salir de la aplicacion");
        System.out.println("1. Recuperar jugador por su nombre");
        System.out.println("2. Recuperar equipo por su nombre");
        System.out.println("3. Recuperar jugadores de un equipo por su nombre");
        System.out.println("4. Insertar un equipo nuevo");
        System.out.println("5. Insertar un jugador nuevo");
        System.out.println("6. Actualizar un equipo");
        System.out.println("7. Actualizar un jugador");
        System.out.println("8. Eliminar un equipo");
        System.out.println("9. Eliminar un jugador");
        System.out.println("---------------------------------------");


    }

    private static void showJugador(Jugador j) {
        System.out.println("Jugador: " + j.getNombreJugador() + ", Posicion: " + j.getPosicion() + ", Equipo: " + j.getEquipo().getNombreEquipo());
    }
    private static void showEquipo(Equipo e) {
        System.out.print("Equipo: "+e.getNombreEquipo()+", Ciudad: "+e.getCiudad());
    }
    private static void showJugadoresEquipo(List<Jugador> jugadores) {

        for (Jugador j: jugadores) {
            showJugador(j);
        }
    }


}
