package com.bsu.lab.util.comparer;

import com.bsu.lab.constant.GeneralConstants;
import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;


@Getter
public class HousesComparer {
    private final House firstHouseForCompare;
    private final House secondHouseForCompare;
    private boolean entrancesCountDiffering = false;
    private boolean floorsCountDiffering = false;
    private boolean flatsCountDiffering = false;
    private boolean totalHouseSquareDiffering = false;

    public HousesComparer(@NotNull House houseForCompare1, @NotNull House houseForCompare2) {
        this.firstHouseForCompare = houseForCompare1;
        this.secondHouseForCompare = houseForCompare2;
        this.findDifferingParameters();
    }

    private void findDifferingParameters() {

        if (firstHouseForCompare.getEntrancesCount() != secondHouseForCompare.getEntrancesCount()) {
            this.entrancesCountDiffering = true;
        }
        if (HouseService.getFloorsCount(firstHouseForCompare) != HouseService.getFloorsCount(secondHouseForCompare)) {
            this.floorsCountDiffering = true;
        }

        if (HouseService.getFlatsCount(firstHouseForCompare) != HouseService.getFlatsCount(secondHouseForCompare)) {
            this.flatsCountDiffering = true;
        }

        if (HouseService.findTotalHouseSquare(firstHouseForCompare) != HouseService.
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
            System.out.println("Количество этажей в первом доме: " + HouseService.
                    getFloorsCount(this.getFirstHouseForCompare()));
            System.out.println("Количество этажей во втором доме: " + HouseService.
                    getFloorsCount(this.getSecondHouseForCompare()));
            System.out.println(GeneralConstants.SEPARATION);
        }

        if (this.isFlatsCountDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ОБЩЕЕ КОЛИЧЕСТВО КВАРТИР");
            System.out.println("Количество квартир в первом доме: " + HouseService.
                    getFlatsCount(this.getFirstHouseForCompare()));
            System.out.println("Количество квартир во втором доме: " + HouseService.
                    getFlatsCount(this.getSecondHouseForCompare()));
            System.out.println(GeneralConstants.SEPARATION);
        }

        if (this.isTotalHouseSquareDiffering()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ОБЩАЯ ПЛОЩАДЬ ДОМА");
            System.out.println("Общая площадь первого дома: " + HouseService.
                    findTotalHouseSquare(this.getFirstHouseForCompare()));
            System.out.println("Общая площадь второго дома: " + HouseService.
                    findTotalHouseSquare(this.getSecondHouseForCompare()));
            System.out.println(GeneralConstants.SEPARATION);
        }
    }
}

