package com.ejemplo.crud;

import com.ejemplo.dao.EquipoDAO;
import com.ejemplo.dao.JugadorDAO;
import com.ejemplo.model.Equipo;
import com.ejemplo.model.Jugador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main_Insert_Jugador {

    private static final Logger logger = LoggerFactory.getLogger(Main_Insert_Jugador.class);

    public static void main(String[] args) {


        EquipoDAO equipoDAO = new EquipoDAO();
        JugadorDAO dao = new JugadorDAO();
        Equipo equipo = equipoDAO.getEquipoByNomebreEquipo("Villareal");
        String nombreJugador = "Dani Parejo";

        if (equipo != null) {

            Jugador j = dao.getJugadorByNombreJugador(nombreJugador);
            if (j == null) {
                j = new Jugador();
                j.setNombreJugador(nombreJugador);
                j.setPosicion("Medio");
                j.setEquipo(equipo);

                dao.addJugador(j);
                logger.info("Jugador añadido correctamente");

            } else {
                logger.warn("El jugador {} ya existe en la BD", nombreJugador);
            }

        } else {
            logger.warn("Este equipo no está en la liga");
        }


    }
}
