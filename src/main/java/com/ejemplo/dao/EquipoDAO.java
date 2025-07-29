package com.ejemplo.dao;

import com.ejemplo.model.Equipo;
import com.ejemplo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EquipoDAO {

    private static final Logger logger = LoggerFactory.getLogger(EquipoDAO.class);
    public List<Equipo> getAllEquipo() {

        List<Equipo> lista = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Equipo> query = session.createQuery("FROM Equipo", Equipo.class); // poner el nombre de la clase JAVA, no el de la BD
            lista = query.list();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return lista;

    }

    public void addEquipo(Equipo equipo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(equipo);
            session.getTransaction().commit();
            logger.info("Equipo {} añadido correctamente a la BD", equipo.getNombreEquipo());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public Equipo getEquipoByNomebreEquipo(String nombreEquipo) {
        Equipo equipo = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            String hql = "SELECT e FROM Equipo e WHERE e.nombreEquipo = :nombre";

            Query<Equipo> query = session.createQuery(hql, Equipo.class);
            query.setParameter("nombre", nombreEquipo);
            equipo = query.getSingleResult();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return equipo;
    }

    public void updateEquipo(Equipo equipo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.merge(equipo);
            session.getTransaction().commit();
            logger.info("Equipo {} actualizado con exito", equipo.getNombreEquipo());
        } catch (Exception e) {
            logger.error("Error al actualizar equipo: " + e.getMessage());
        }
    }

    public void deleteEquipo(Equipo equipo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.remove(equipo);
            session.getTransaction().commit();
            logger.info("Equipo {} borrado con exito", equipo.getNombreEquipo());

        } catch (Exception e) {
            logger.error("Error al borrar equipo: " + e.getMessage());
        }
    }
}
