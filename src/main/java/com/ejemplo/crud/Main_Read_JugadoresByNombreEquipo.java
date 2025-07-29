package com.ejemplo.crud;

import com.ejemplo.dao.JugadorDAO;
import com.ejemplo.model.Jugador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main_Read_JugadoresByNombreEquipo {

    private static final Logger logger = LoggerFactory.getLogger(Main_Read_JugadoresByNombreEquipo.class);

    public static void main(String[] args) {

        JugadorDAO dao = new JugadorDAO();
        String nombreEquipo = "FC Barcelona";

        List<Jugador> jugadores = dao.getJugadoresByNombreEquipo(nombreEquipo);

        if(jugadores!= null && !jugadores.isEmpty()) {
            logger.info("Jugadores del equipo: {}", nombreEquipo);
            for (Jugador j: jugadores) {
                logger.info("Nombre: {}, Posicion: {}\n----------------", j.getNombreJugador(), j.getPosicion());
            }
        }
        else {
            logger.warn("No se encontraron jugadores para el equipo: {}", nombreEquipo);
        }

    }
}
