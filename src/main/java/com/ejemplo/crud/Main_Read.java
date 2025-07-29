package com.ejemplo.crud;

import com.ejemplo.dao.EquipoDAO;
import com.ejemplo.model.Equipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main_Read {

    private static final Logger logger = LoggerFactory.getLogger(Main_Read.class);

    public static void main(String[] args) {

        EquipoDAO dao = new EquipoDAO();
        List<Equipo> equipos = dao.getAllEquipo();

        if(equipos!= null && !equipos.isEmpty()) {
            for (Equipo e: equipos) {
                logger.info("Equipo {} | Nombre: {}, Ciudad: {}\n--------------------------------------------------------"
                        , e.getIdEquipo(), e.getNombreEquipo(), e.getCiudad());

            }
        }
        else {
            logger.warn("No se han encontrado equipos en la BD");
        }

    }
}
