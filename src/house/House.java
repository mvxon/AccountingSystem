package house;
import accounting.Main;
import java.util.*;
public class House {
    private int floorsCount = 0;
    private int flatsPerFloor = 0;
    private static int currentHouseNumber;
    private int houseNumber;
    private List<Floor> floors = new ArrayList<>();



    public class Floor {
        private int floorNumber = 0;
        private List<Flat> flats = new ArrayList<>();
        private int currentFlatsCount = 0;

        public class Flat {
            Scanner scannerFlat = new Scanner(System.in);
            private double flatSquare;
            private int residentsCount;
            private int flatNumber;
            private int roomsCount;
            private int currentRoomsCount = 0;
            private List<Room> rooms = new ArrayList<>();

            public class Room {
                private int roomNumber;
                private double roomSquare;

                Room() {
                    this.roomNumber = currentRoomsCount + 1;
                    this.roomSquare = Main.EnteringInfoCheck("Введите площадь " + Integer.toString(roomNumber) + " комнаты: ");
                    currentRoomsCount++;
                }

            }

            Flat() {
                this.flatNumber = currentFlatsCount + 1;
                this.residentsCount = 2;
                if (floorNumber == 1) {
                    this.roomsCount = Main.EnteringInfoCheck("Введите количество комнат " + Integer.toString(flatNumber) + " квартиры на этаже: ");
                    for (int i = 0; i < roomsCount; i++) {
                        this.rooms.add(new Room());
                    }
                }
                this.findFlatSquare();
                currentFlatsCount++;
            }

            private void findFlatSquare() {
                for (int i = 0; i < roomsCount; i++) {
                    this.flatSquare += rooms.get(i).roomSquare;
                }
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
                if (this.roomsCount == flat.roomsCount) {
                    return true;
                }
                return false;
            }

            public boolean equalsByFlatSquare(Object o) {
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Flat flat = (Flat) o;
                if (this.flatSquare == flat.flatSquare) {
                    return true;
                }
                return false;
            }

            @Override
            public String toString() {
                String result = "";
                result += "Номер квартиры: " + Integer.toString(this.flatNumber) + "\nКоличество комнат: " + Integer.toString(this.roomsCount);
                for (int i = 0; i < this.roomsCount; i++)
                    result += "\nПлощадь " + Integer.toString(i + 1) + " квартиры: " + this.rooms.get(i).roomSquare;
                result += "\nОбщая площадь квартиры: " + Double.toString(this.flatSquare) + "\nКоличество жильцов: " + Integer.toString(this.residentsCount);
                return result;
            }
        }

        Floor(int floorNumber) {
            this.floorNumber = floorNumber;
            for (int i = 0; i < flatsPerFloor; i++) {
                this.flats.add(new Flat());
            }
        }

        public Flat getFlat(int flatNumber) {
            flatNumber -= (this.floorNumber) * flatsPerFloor;
            flatNumber--;
            return this.flats.get(flatNumber);
        }
    }

    public House() {
        this.houseNumber = currentHouseNumber + 1;
        System.out.print("Введите количество квартир на одном этаже: ");
        this.floorsCount = Main.EnteringInfoCheck("Введите количество этажей в доме: ");
        this.flatsPerFloor = Main.EnteringInfoCheck("Введите количество квартир на одном этаже: ");
        for (int i = 0; i <= this.floorsCount; i++) {
            this.floors.add(new Floor(i));
        }
        System.out.println("Дом номер " + Integer.toString(currentHouseNumber + 1) + " успешно добавлен!");
        currentHouseNumber++;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        House house = (House) o;
        if (this.floorsCount == house.floorsCount && this.flatsPerFloor == house.flatsPerFloor && this.totalHouseSquare() == house.totalHouseSquare())
            return true;
        else return false;
    }

    public double totalHouseSquare() {
        double result = 0;
        for (int j = 0; j < flatsPerFloor; j++) {
            result += floors.get(1).flats.get(j).flatSquare;
        }
        return floorsCount * result;
    }

    public int totalHouseResidentsCount() {
        int result = 0;
        for(int i =0; i <this.floorsCount;i++){
            for(int j = 0;j<this.flatsPerFloor;j++){
                result+=this.floors.get(i).flats.get(j).residentsCount;
            }
        }
        return result;
    }
    public Floor getFloor(int flatNumber){
        if(flatNumber%flatsPerFloor!=0){
            do{
                flatNumber++;
            }while(flatNumber%flatsPerFloor!=0);
        }
        return this.floors.get(((flatNumber/flatsPerFloor)-1));
    }
    public int getFlatsPerFloor() {
        return this.flatsPerFloor;
    }
    public int getFloorsCount(){
        return this.floorsCount;
    }
}