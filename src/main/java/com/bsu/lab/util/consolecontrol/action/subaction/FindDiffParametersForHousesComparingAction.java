package com.bsu.lab.util.consolecontrol.action.subaction;

import com.bsu.lab.model.House;
import com.bsu.lab.service.HouseService;
import com.bsu.lab.constant.GeneralConstants;
import org.jetbrains.annotations.NotNull;

public class FindDiffParametersForHousesComparingAction {
     public static void execute(@NotNull House houseForCompare1, @NotNull House houseForCompare2){

        System.out.println("Отличающиеся параметры:");

        if(houseForCompare1.getEntrancesCount() != houseForCompare2.getEntrancesCount()){
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("КОЛИЧЕСТВО ПОДЪЕЗДОВ");
            System.out.println("Количество подъездов в первом доме: " + houseForCompare1.getEntrancesCount());
            System.out.println("Количество подъездов во втором доме: " + houseForCompare2.getEntrancesCount());
            System.out.println(GeneralConstants.SEPARATION);
        }

        if(HouseService.getFlatsCount(houseForCompare1) != HouseService.getFlatsCount(houseForCompare2)){
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ОБЩЕЕ КОЛИЧЕСТВО КВАРТИР");
            System.out.println("Количество квартир в первом доме: " + HouseService.getFlatsCount(houseForCompare1));
            System.out.println("Количество квартир во втором доме: " + HouseService.getFlatsCount(houseForCompare2));
            System.out.println(GeneralConstants.SEPARATION);
        }

        if(HouseService.findTotalHouseSquare(houseForCompare1)!=HouseService.findTotalHouseSquare(houseForCompare2)){
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ОБЩАЯ ПЛОЩАДЬ ДОМА");
            System.out.println("Общая площадь первого дома: " + HouseService.findTotalHouseSquare(houseForCompare1));
            System.out.println("Общая площадь второго дома: " + HouseService.findTotalHouseSquare(houseForCompare2));
            System.out.println(GeneralConstants.SEPARATION);
        }

    }
}
