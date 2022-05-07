package com.bsu.lab.AccountingSystem.consolecontrol.comparer;

import com.bsu.lab.AccountingSystem.consolecontrol.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.Flat;
import com.bsu.lab.AccountingSystem.service.FlatService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Getter
@Service
public class FlatsComparer {
    private Flat firstFlatForCompare;
    private Flat secondFlatForCompare;
    private boolean roomsCountDiffering;
    private boolean flatSquareDiffering;

    private final FlatService flatService;

    @Autowired
    public FlatsComparer(FlatService flatService) {
        this.flatService = flatService;
    }

    public void setFlatsForComparing(Flat firstFlatForCompare, Flat secondFlatForCompare) {
        this.firstFlatForCompare = firstFlatForCompare;
        this.secondFlatForCompare = secondFlatForCompare;
        this.findDifferingParameters();
    }

    private void findDifferingParameters() {
        if (firstFlatForCompare.getRooms() != secondFlatForCompare) {
            this.roomsCountDiffering = true;
        }
        if (flatService.findFlatSquare(firstFlatForCompare) != flatService.findFlatSquare(secondFlatForCompare)) {
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
            System.out.println("Площадь первой квартиры: " + flatService.findFlatSquare(firstFlatForCompare));
            System.out.println("Площадь второй квартиры: " + flatService.findFlatSquare(secondFlatForCompare));
            System.out.println(GeneralConstants.SEPARATION);
        }
    }
}



