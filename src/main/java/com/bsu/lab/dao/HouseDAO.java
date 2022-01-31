package com.bsu.lab.dao;


import com.bsu.lab.model.House;
import com.bsu.lab.util.database.HibernateUtil;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;


import java.util.List;

public class HouseDAO implements DAO<House> {
    private static HouseDAO houseDAO;


    private HouseDAO() {

    }

    public static HouseDAO getInstance() {
        if (houseDAO == null) {
            houseDAO = new HouseDAO();
        }
        return houseDAO;
    }

    @Override
    public void create(@NotNull final House house) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(house);
            session.getTransaction().commit();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }

    @Override
    public List<House> read() {
        List<House> result = null;
        try (Session session = HibernateUtil.getSession()) {
            result = session.createQuery("from House").list();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
        return result;
    }


    @Override
    public void update(@NotNull final House house) {
        try (Session session = HibernateUtil.getSession()) {

            session.beginTransaction();

            session.update(house);

            session.getTransaction().commit();
        }

    }

    @Override
    public void delete(@NotNull final House house) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.delete(house);
            session.getTransaction().commit();
        }

    }


    @Override
    public void deleteAll() {
        List<House> listOfAllHouses = houseDAO.read();
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            for (House house : listOfAllHouses) {
                houseDAO.delete(house);
            }
            session.getTransaction().commit();

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }

    public static int getHousesCount() {
        Integer housesCount = 0;
        try (Session session = HibernateUtil.getSession()) {
            housesCount = ((Long) session.createQuery("SELECT count(*) from House")
                    .uniqueResult())
                    .intValue();
        }
        return housesCount;
    }
}
