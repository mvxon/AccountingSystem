package com.bsu.lab.util.input.service;

import com.bsu.lab.util.input.SecuredNumbersScanner;

public class InputForRoomsCount {
    private final static int MAX_ROOMS_COUNT = 7;
    private final static int MIN_ROOMS_COUNT = 1;

    public static int input(int flatNumber) {
        String question = "Введите количество комнат " + flatNumber +
                "-ой квартиры на этаже(от " + MIN_ROOMS_COUNT + " до " + MAX_ROOMS_COUNT + "): ";
        int roomsCount = (SecuredNumbersScanner.EnteringInfoCheck(question));
        while (roomsCount < MIN_ROOMS_COUNT || roomsCount > MAX_ROOMS_COUNT) {
                System.out.println("Введено неверное значение...Повторите ввод");
                roomsCount = (SecuredNumbersScanner.EnteringInfoCheck(question));
        }
        return roomsCount;
    }
}
