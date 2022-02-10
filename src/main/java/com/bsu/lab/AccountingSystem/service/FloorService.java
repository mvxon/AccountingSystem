package com.bsu.lab.AccountingSystem.service;



import com.bsu.lab.AccountingSystem.model.Flat;
import com.bsu.lab.AccountingSystem.model.Floor;
import com.bsu.lab.AccountingSystem.util.input.service.InputForFlatsCount;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorService {
    private final InputForFlatsCount inputForFlatsCount;
    private final FlatService flatService;

    @Autowired
    public FloorService(InputForFlatsCount inputForFlatsCount, FlatService flatService) {
        this.inputForFlatsCount = inputForFlatsCount;
        this.flatService = flatService;
    }

    public @NotNull Floor createFloor() {
        Floor floor = new Floor();
        floor.setFloorNumber();
        int flatsCount = inputForFlatsCount.input();
        for (int i = 0; i < flatsCount; i++) {
            this.addFlat(floor, flatService.createFlat()); // flats creating
        }
        return floor;
    }

    public void addFlat(@NotNull Floor floor, @NotNull Flat flat) {
        if (floor.getFlats().add(flat)) {
            floor.setFlatsCount(floor.getFlatsCount() + 1);
        }
    }


}
