package house;

import util.SecuredNumbersScanner;
import util.MyNumber;

import java.util.ArrayList;
import java.util.List;
class Flat {
    private double flatSquare;
    private int residentsCount;
    private int roomsCount;
    private int flatUniqueNumber = 0;
    private List<Room> rooms = new ArrayList<>();

    Flat(MyNumber flatUniqueNumber, int floorNumber) {
        this.flatUniqueNumber = flatUniqueNumber.getIntValue();
        if (floorNumber == 0) {
            String question = "Введите количество комнат " + (flatUniqueNumber);
            question += " квартиры на этаже(от 1 до 7): ";
            this.roomsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
            if (this.roomsCount <= 0 || this.roomsCount > 7)
                do {
                    System.out.println("Введено неверное значение...Повторите ввод");
                    this.roomsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
                } while (this.roomsCount <= 0 || this.roomsCount > 7);
        }
        this.residentsCount = (int)(Math.random()*(this.roomsCount-1+1)+1);
        for (int i = 0; i < roomsCount; i++) {
            this.rooms.add(new Room(i + 1));

        }
        flatUniqueNumber.Iteration();
        this.findFlatSquare();
    }

    Flat(Flat flat, MyNumber flatUniqueNumber) {
        this.flatUniqueNumber = flatUniqueNumber.getIntValue();
        flatUniqueNumber.Iteration();
        this.roomsCount = flat.roomsCount;
        this.flatSquare = flat.flatSquare;
        this.residentsCount = (int)(Math.random()*(this.roomsCount-1+1)+1);
        for (int i = 0; i < this.roomsCount; i++) {
            this.rooms.add(new Room(flat.rooms.get(i)));
        }
    }

    private void findFlatSquare() {
        double result = 0;
        for (int i = 0; i < roomsCount; i++) {
            result += rooms.get(i).getRoomSquare();
        }
        this.flatSquare = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        if (this.flatSquare == flat.flatSquare && this.roomsCount == flat.roomsCount) {
            return true;
        }
        return false;
    }

    public boolean equalsByRoomsCount(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Flat flat = (Flat) o;
        return this.roomsCount == flat.roomsCount;
    }

    public boolean equalsByFlatSquare(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Flat flat = (Flat) o;
        return this.flatSquare == flat.flatSquare;
    }

    public int getFlatUniqueNumber() {
        return this.flatUniqueNumber;
    }

    public int getResidentsCount() {
        return this.residentsCount;
    }

    public double getFlatSquare() {
        return flatSquare;
    }

    @Override
    public String toString() {
        String result = "";
        result += "\n--------------------------------------------------------------------------------\n";
        result += "Номер квартиры: " + this.flatUniqueNumber + "\nКоличество комнат: " + this.roomsCount;
        for (int i = 0; i < this.roomsCount; i++) {
            result += "\nПлощадь " + (i + 1) + " комнаты: " + this.rooms.get(i).getRoomSquare();
        }
        result += "\nОбщая площадь квартиры: " + this.flatSquare + "\nКоличество жильцов: " + this.residentsCount;
        result += "\n--------------------------------------------------------------------------------\n";
        return result;
    }
}
