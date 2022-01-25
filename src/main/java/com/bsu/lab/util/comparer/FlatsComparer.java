package com.bsu.lab.util.comparer;

import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.model.Flat;
import com.bsu.lab.service.FlatService;
import lombok.Getter;


@Getter
public class FlatsComparer {
    private final Flat firstFlatForCompare;
    private final Flat secondFlatForCompare;
    private boolean roomsCountDiffering;
    private boolean flatSquareDiffering;

    public FlatsComparer(Flat firstFlatForCompare, Flat secondFlatForCompare) {
        this.firstFlatForCompare = firstFlatForCompare;
        this.secondFlatForCompare = secondFlatForCompare;
        this.findDifferingParameters();
    }

    private void findDifferingParameters() {
        if (firstFlatForCompare.getRooms() != secondFlatForCompare) {
            this.roomsCountDiffering = true;
        }
        if (FlatService.findFlatSquare(firstFlatForCompare) != FlatService.findFlatSquare(secondFlatForCompare)) {
            this.flatSquareDiffering = true;
        }
    }

    public void printDifferingParameters() {
        System.out.println("Отличающиеся параметры:");

        if (this.isRoomsCountDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("КОЛИЧЕСТВО КОМНАТ");
            System.out.println("Количество комнат в первой квартире: " + firstFlatForCompare.getRoomsCount());
            System.out.println("Количество комнат во второй квартире: " + secondFlatForCompare.getRoomsCount());
            System.out.println(GeneralConstants.SEPARATION);
        }

        if (this.isFlatSquareDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ПЛОЩАДЬ КВАРТИРЫ");
            System.out.println("Площадь первой квартиры: " + FlatService.findFlatSquare(firstFlatForCompare));
            System.out.println("Площадь второй квартиры: " + FlatService.findFlatSquare(secondFlatForCompare));
            System.out.println(GeneralConstants.SEPARATION);
        }
    }
}



