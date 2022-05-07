package com.bsu.lab.AccountingSystem.consolecontrol.comparer;

import com.bsu.lab.AccountingSystem.consolecontrol.constants.GeneralConstants;
import com.bsu.lab.AccountingSystem.domain.House;
import com.bsu.lab.AccountingSystem.service.HouseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Getter
@Service
public class HousesComparer {
    private House firstHouseForCompare;
    private House secondHouseForCompare;
    private boolean entrancesCountDiffering = false;
    private boolean floorsCountDiffering = false;
    private boolean flatsCountDiffering = false;
    private boolean totalHouseSquareDiffering = false;
    private final HouseService houseService;

    @Autowired
    public HousesComparer(HouseService houseService) {
        this.houseService = houseService;
    }

    public void setHousesForComparing(House firstHouseForCompare, House secondHouseForCompare) {
        this.firstHouseForCompare = firstHouseForCompare;
        this.secondHouseForCompare = secondHouseForCompare;
        this.findDifferingParameters();
    }

    private void findDifferingParameters() {

        if (firstHouseForCompare.getEntrancesCount() != secondHouseForCompare.getEntrancesCount()) {
            this.entrancesCountDiffering = true;
        }
        if (houseService.getFloorsCount(firstHouseForCompare) != houseService.getFloorsCount(secondHouseForCompare)) {
            this.floorsCountDiffering = true;
        }

        if (houseService.getFlatsCount(firstHouseForCompare) != houseService.getFlatsCount(secondHouseForCompare)) {
            this.flatsCountDiffering = true;
        }

        if (houseService.findTotalHouseSquare(firstHouseForCompare) != houseService.
                findTotalHouseSquare(secondHouseForCompare)) {
            this.totalHouseSquareDiffering = true;
        }
    }

    public void printDifferingParameters() {
        System.out.println("Отличающиеся параметры:");

        if (this.isEntrancesCountDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("КОЛИЧЕСТВО ПОДЪЕЗДОВ");
            System.out.println("Количество подъездов в первом доме: " + this.
                    getFirstHouseForCompare().getEntrancesCount());
            System.out.println("Количество подъездов во втором доме: " + this.
                    getSecondHouseForCompare().getEntrancesCount());
            System.out.println(GeneralConstants.SEPARATION);
        }
        if (this.isFloorsCountDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("КОЛИЧЕСТВО ЭТАЖЕЙ");
            System.out.println("Количество этажей в первом доме: " + houseService.
                    getFloorsCount(this.getFirstHouseForCompare()));
            System.out.println("Количество этажей во втором доме: " + houseService.
                    getFloorsCount(this.getSecondHouseForCompare()));
            System.out.println(GeneralConstants.SEPARATION);
        }

        if (this.isFlatsCountDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ОБЩЕЕ КОЛИЧЕСТВО КВАРТИР");
            System.out.println("Количество квартир в первом доме: " + houseService.
                    getFlatsCount(this.getFirstHouseForCompare()));
            System.out.println("Количество квартир во втором доме: " + houseService.
                    getFlatsCount(this.getSecondHouseForCompare()));
            System.out.println(GeneralConstants.SEPARATION);
        }

        if (this.isTotalHouseSquareDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ОБЩАЯ ПЛОЩАДЬ ДОМА");
            System.out.println("Общая площадь первого дома: " + houseService.
                    findTotalHouseSquare(this.getFirstHouseForCompare()));
            System.out.println("Общая площадь второго дома: " + houseService.
                    findTotalHouseSquare(this.getSecondHouseForCompare()));
            System.out.println(GeneralConstants.SEPARATION);
        }
    }
}

