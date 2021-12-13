package com.bsu.lab.util.consolecontrol.action.subaction;

import com.bsu.lab.model.Flat;
import com.bsu.lab.service.FlatService;
import com.bsu.lab.constant.GeneralConstants;
import org.jetbrains.annotations.NotNull;

public class FindDiffParametersForFlatsComparingAction {
    public static void execute(@NotNull Flat flatForCompare1, @NotNull Flat flatForCompare2) {
        System.out.println("Отличающиеся параметры:");

        if (flatForCompare1.getRoomsCount() != flatForCompare2.getRoomsCount()) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("КОЛИЧЕСТВО КОМНАТ");
            System.out.println("Количество комнат в первой квартире: " + flatForCompare1.getRoomsCount());
            System.out.println("Количество комнат во второй квартире: " + flatForCompare2.getRoomsCount());
            System.out.println(GeneralConstants.SEPARATION);
        }

        if (FlatService.findFlatSquare(flatForCompare1) != FlatService.findFlatSquare(flatForCompare2)) {
            System.out.println(GeneralConstants.SEPARATION);
            System.out.println("ПЛОЩАДЬ КВАРТИРЫ");
            System.out.println("Площадь первой квартиры: " + FlatService.findFlatSquare(flatForCompare1));
            System.out.println("Площадь второй квартиры: " + FlatService.findFlatSquare(flatForCompare2));
            System.out.println(GeneralConstants.SEPARATION);
        }
    }
}
