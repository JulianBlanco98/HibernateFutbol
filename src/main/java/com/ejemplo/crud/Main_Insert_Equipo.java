package com.ejemplo.crud;

import com.ejemplo.dao.EquipoDAO;
import com.ejemplo.model.Equipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main_Insert_Equipo {

    private static final Logger logger = LoggerFactory.getLogger(Main_Insert_Equipo.class);

    public static void main(String[] args) {

        Equipo equipo = new Equipo();
        equipo.setNombreEquipo("Villareal");
        equipo.setCiudad("Villareal");

        EquipoDAO dao = new EquipoDAO();
        dao.addEquipo(equipo);

    }
}
