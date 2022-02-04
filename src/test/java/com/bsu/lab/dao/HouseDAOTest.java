package com.bsu.lab.dao;

import com.bsu.lab.creator.HouseCreatorForTest;
import com.bsu.lab.model.House;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class HouseDAOTest extends TestCase {
    private static final House house = HouseCreatorForTest.createHouseForTest();
    HouseDAO dao = HouseDAO.getInstance();

    public void testCreate() {
        int housesCount = HouseDAO.getHousesCount();
        dao.create(house);
        int expectedHousesCount = housesCount + 1;
        int actualHousesCount = HouseDAO.getHousesCount();
        assertEquals(expectedHousesCount, actualHousesCount);
    }

    public void testRead() {
        Set<House> setOfHouses = new HashSet<>();
        setOfHouses.addAll(dao.read());
        int expectedSetSize = HouseDAO.getHousesCount();
        int actualSetSize = setOfHouses.size();
        assertEquals(expectedSetSize, actualSetSize);
    }

    public void testDelete() {
        int housesCount = HouseDAO.getHousesCount();
        dao.delete(house);
        int expectedHousesCount = housesCount - 1;
        int actualHousesCount = HouseDAO.getHousesCount();
        assertEquals(expectedHousesCount, actualHousesCount);
    }

    public void testDeleteAll() {
        dao.deleteAll();
        int expectedHousesCount = 0;
        int actualHousesCount = HouseDAO.getHousesCount();
        assertEquals(expectedHousesCount, actualHousesCount);

    }
}