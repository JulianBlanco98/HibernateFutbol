package com.ejemplo.dao;

import com.ejemplo.model.Jugador;
import com.ejemplo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JugadorDAO {

    private static final Logger logger = LoggerFactory.getLogger(EquipoDAO.class);

    public List<Jugador> getJugadoresByNombreEquipo(String nombreEquipo) {
        List<Jugador> lista = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            // Usar el nobre de los campos de JAVA (lenguaje HQL, no SQL)
            String hql = "SELECT j FROM Jugador j JOIN j.equipo e WHERE e.nombreEquipo = :nombre";

            Query<Jugador> query = session.createQuery(hql, Jugador.class);
            query.setParameter("nombre", nombreEquipo);
            lista = query.list();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return lista;
    }

    public void addJugador(Jugador j) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(j);
            session.getTransaction().commit();
            logger.info("Jugador {} añadido correctamente a la BD", j.getNombreJugador());
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public Jugador getJugadorByNombreJugador(String nombreJugador) {
        Jugador j =  null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT j FROM Jugador j WHERE j.nombreJugador = :nombre";

            Query<Jugador> query = session.createQuery(hql, Jugador.class);
            query.setParameter("nombre", nombreJugador);
            j = query.getSingleResult();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return j;
    }

    public void updateJugador(Jugador jugador) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(jugador);
            session.getTransaction().commit();
            logger.info("Jugador {} actualizado correctamente en la BD", jugador.getNombreJugador());
        } catch (Exception e) {
            logger.error("Error al actualizar jugador: " + e.getMessage());
        }
    }

    public void deleteJugador(Jugador jugador) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(jugador);
            session.getTransaction().commit();
            logger.info("Jugador {} eliminado correctamente de la BD", jugador.getNombreJugador());
        } catch (Exception e) {
            logger.error("Error al eliminar jugador: " + e.getMessage());
        }
    }


}
