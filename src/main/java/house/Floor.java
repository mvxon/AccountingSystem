package house;

import util.SecuredNumbersScanner;
import util.MyNumber;

import java.util.ArrayList;
import java.util.List;

class Floor {
    private int floorNumber;
    private List<Flat> flats = new ArrayList<>();
    private int flatsPerFloor;

    Floor(int floorNumber, MyNumber currentFlatNumber) {
        this.floorNumber = floorNumber;
        String question = "Введите количество квартир на одном этаже(от 1 до 20): ";
        this.flatsPerFloor = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.flatsPerFloor <= 0 || this.flatsPerFloor > 20)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.flatsPerFloor = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.flatsPerFloor <= 0 || this.flatsPerFloor > 20);
        if (floorNumber == 0) {
            for (int i = 0; i < flatsPerFloor; i++) {
                this.flats.add(new Flat(currentFlatNumber, this.floorNumber));
            }
        }
    }

    Floor(Floor floor, int floorNumber, MyNumber currentFlatNumber, int flatsPerFloor) {
        this.floorNumber = floorNumber;
        this.flatsPerFloor = flatsPerFloor;
        for (int i = 0; i < flatsPerFloor; i++) {
            this.flats.add(new Flat(floor.flats.get(i), currentFlatNumber));
        }
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public Flat getFlatOnFloor(int flatNumber) {
        return this.flats.get(flatNumber);
    }

    public int getFlatsPerFloor() {
        return flatsPerFloor;
    }
}
