package house;

import util.SecuredNumbersScanner;
import util.MyNumber;

import java.util.*;

public class House {
    private static int currentHouseNumber;
    private final int houseNumber;
    private int entrancesCount;
    private MyNumber currentFlatNumber = new MyNumber(1);
    private List<Entrance> entrances = new ArrayList<>();

    public House() {
        this.houseNumber = currentHouseNumber + 1;
        String question = "Введите количество подъездов в доме(от 1 до 20): ";
        this.entrancesCount = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.entrancesCount <= 0 || this.entrancesCount > 20)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.entrancesCount = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.entrancesCount <= 0 || this.entrancesCount > 20);
        for (int i = 0; i < this.entrancesCount; i++) {
            if (i == 0) {
                this.entrances.add(new Entrance(i, currentFlatNumber));
            } else {
                this.entrances.add(new Entrance(entrances.get(0), i, currentFlatNumber));
            }

        }
        System.out.println("Дом номер " + (currentHouseNumber + 1) + " успешно добавлен!");
        currentHouseNumber++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        House house = (House) o;
        if (this.entrances.get(0).getFloorsCount() == house.entrances.get(0).getFloorsCount()) {
            if (this.entrances.get(0).getFlatsPerFloor() == house.entrances.get(0).getFlatsPerFloor()) {
                if (this.totalHouseSquare() == house.totalHouseSquare()) {
                    return true;
                }
            }
        }
        return false;

    }

    public double totalHouseSquare() {
        double result = 0;
        for (int i = 1; i < this.currentFlatNumber.getIntValue(); i++) {
            result += this.getFlat(i).getFlatSquare();
        }
        return result;
    }

    public int totalHouseResidentsCount() {
        int result = 0;
        for (int i = 1; i < this.currentFlatNumber.getIntValue(); i++) {
            result += this.getFlat(i).getResidentsCount();
        }
        return result;
    }

    public Entrance getEntranceByFlatNumber(int flatNumber) {
        int result = 0;
        int j = this.entrances.get(0).getFloorsCount() * this.entrances.get(0).getFlatsPerFloor();
        int i = 0;
        for (int k = 0; k < this.entrancesCount; k++) {
            if (flatNumber >= i && flatNumber <= j) {
                result = k;
                return this.entrances.get(result);
            }
            i += this.entrances.get(0).getFloorsCount() * this.entrances.get(0).getFlatsPerFloor() - 1;
            j += this.entrances.get(0).getFloorsCount() * this.entrances.get(0).getFlatsPerFloor();
        }
        return this.entrances.get(result);
    }

    public String toString() {
        String result = "--------------------------------------------------------------------------------";
        result += "\nНомер дома: " + (this.houseNumber) + "\nКоличество подъездов: " + (this.entrancesCount);
        result += "\nКоличество этажей: " + (this.entrances.get(0).getFloorsCount()) + "\nКоличество квартир на одном этаже: ";
        result += this.entrances.get(0).getFlatsPerFloor() + "\nОбщее количество квартир: " + this.getFlatsCount();
        result += "\nОбщая площадь дома: " + (this.totalHouseSquare()) + "\nОбщее количество жильцов: ";
        result += (this.totalHouseResidentsCount());
        result += "\n--------------------------------------------------------------------------------\n";
        return result;
    }

    public int getFlatsCount() {
        int result = 0;
        result = this.entrances.get(0).getFlatsPerFloor();
        result *= this.entrances.get(0).getFloorsCount();
        result *= this.entrancesCount;
        return result;
    }

    public Flat getFlat(int flatNumber) {
        int temp = flatNumber;
        int subtrahend = 0;
        subtrahend = this.getEntranceByFlatNumber(flatNumber).getEntranceNumber();
        subtrahend *= this.getEntranceByFlatNumber(flatNumber).getFloorsCount();
        subtrahend *= this.getEntranceByFlatNumber(flatNumber).getFlatsPerFloor();
        int temp2;
        temp2 = this.getEntranceByFlatNumber(flatNumber).getFloorByFlatNumber(flatNumber).getFloorNumber();
        temp2 *= this.getEntranceByFlatNumber(flatNumber).getFlatsPerFloor();
        subtrahend += temp2;
        temp -= subtrahend;
        temp--;
        return this.getEntranceByFlatNumber(flatNumber).getFloorByFlatNumber(flatNumber).getFlatOnFloor(temp);
    }

}
