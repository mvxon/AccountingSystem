package house;
import accounting.Main;
import java.util.*;
public class House {
    private static int currentHouseNumber;
    private final int houseNumber;
    private int entrancesCount;
    private int currentEntrancesCount=0;
    private  int currentFlatNumber = 0;
    private List<Entrance> entrances = new ArrayList<>();
    public class Entrance {
        private int entranceNumber;
        private int floorsCount = 0;
        private int currentFloorsCount = 0;
        private  int flatsPerFloor = 0;
        private List<Floor> floors = new ArrayList<>();
        public class Floor {
            private int floorNumber = 0;
            private List<Flat> flats = new ArrayList<>();
            private int currentFlatsCount = 0;
            public class Flat {
                private double flatSquare;
                private int residentsCount;
                private final int flatNumber;
                private int roomsCount;
                private int currentRoomsCount = 0;
                private int flatUniqueNumber = 0;
                private List<Room> rooms = new ArrayList<>();
                public class Room {
                    private int roomNumber;
                    private final double roomSquare;
                    Room() {
                        this.roomNumber = currentRoomsCount + 1;
                        this.roomSquare = Main.EnteringInfoCheck("Введите площадь " + (roomNumber) + " комнаты(м^2): ");
                        currentRoomsCount++;
                    }
                    Room(Room room) {
                        this.roomNumber = room.roomNumber;
                        this.roomSquare = room.roomSquare;
                    }
                }

                Flat() {
                    currentFlatNumber++;
                    this.flatUniqueNumber = currentFlatNumber;
                    this.flatNumber = currentFlatsCount + 1;
                    if (floorNumber == 0) {
                        this.roomsCount = Main.EnteringInfoCheck("Введите количество комнат " + (flatNumber) + " квартиры на этаже: ");
                    }
                    this.residentsCount = this.roomsCount;
                    for (int i = 0; i < roomsCount; i++) {
                        this.rooms.add(new Room());
                    }

                    this.findFlatSquare();
                    currentFlatsCount++;
                }

                Flat(Flat flat) {
                    currentFlatNumber++;
                    this.flatUniqueNumber = currentFlatNumber;
                    this.flatNumber = currentFlatsCount + 1;
                    this.roomsCount = flat.roomsCount;
                    this.flatSquare = flat.flatSquare;
                    this.residentsCount = flat.residentsCount;
                    for (int i = 0; i < this.roomsCount; i++) {
                        this.rooms.add(new Room(flat.rooms.get(i)));
                    }
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
                    if(this.roomsCount == flat.roomsCount) {
                        return true;
                    }
                    return false;
                }
                public boolean equalsByFlatSquare(Object o) {
                    if (o == null || getClass() != o.getClass()) {
                        return false;
                    }
                    Flat flat = (Flat) o;
                    if(this.flatSquare == flat.flatSquare) {
                        return true;
                    }
                    return false;
                }
                @Override
                public String toString() {
                    String result = "";
                    result += "\n--------------------------------------------------------------------------------\n"+"Номер дома: "+houseNumber+"\n" +"Номер подъезда: "+(entranceNumber+1)+"\n" +"Номер квартиры: " + this.flatUniqueNumber + "\nКоличество комнат: " + this.roomsCount;
                    for (int i = 0; i < this.roomsCount; i++) {
                        result += "\nПлощадь " + Integer.toString(i + 1) + " комнаты: " + this.rooms.get(i).roomSquare;
                    }
                    result += "\nОбщая площадь квартиры: " + this.flatSquare + "\nКоличество жильцов: " + this.residentsCount + "\n--------------------------------------------------------------------------------\n";
                    return result;
                }
            }

            Floor() {
                this.floorNumber = currentFloorsCount;
                if (floorNumber == 0) {
                    for (int i = 0; i < flatsPerFloor; i++) {
                        this.flats.add(new Flat());
                    }
                }
                currentFloorsCount++;
            }

            Floor(Floor floor) {
                this.floorNumber = currentFloorsCount;
                for (int i = 0; i < flatsPerFloor; i++) {
                    this.flats.add(new Flat(floor.flats.get(i)));
                }
                currentFloorsCount++;
            }

            public Flat getFlat(int flatNumber) {
                flatNumber -= entranceNumber*floorsCount*flatsPerFloor;
                flatNumber -= this.floorNumber*flatsPerFloor;
                flatNumber--;
                return this.flats.get(flatNumber);
            }

        }
        public Floor getFloorByFlatNumber(int flatNumber){
            int temp = flatNumber-entranceNumber*floorsCount*flatsPerFloor;
            if(temp%flatsPerFloor !=0){
                do {
                    temp++;
                }while(temp%flatsPerFloor !=0);
            }
            return this.floors.get((temp/flatsPerFloor)-1);
        }

        Entrance() {
            this.entranceNumber = currentEntrancesCount;
            this.floorsCount = Main.EnteringInfoCheck("Введите количество этажей в доме: ");
            this.flatsPerFloor = Main.EnteringInfoCheck("Введите количество квартир на одном этаже: ");
            for (int i = 0; i < this.floorsCount; i++) {
                if (i == 0) {
                    this.floors.add(new Floor());
                } else {
                    this.floors.add(new Floor(floors.get(0)));
                }
            }
            currentEntrancesCount++;
        }

        Entrance(Entrance entrance) {
            this.entranceNumber = currentEntrancesCount;
            this.floorsCount = entrance.floorsCount;
            this.flatsPerFloor = entrance.flatsPerFloor;
            for(int i =0; i <floorsCount;i++){
                this.floors.add(new Floor(entrance.floors.get(i)));
            }
            currentEntrancesCount++;
        }
    }
    public House() {
        this.houseNumber = currentHouseNumber + 1;
        this.entrancesCount = Main.EnteringInfoCheck("Введите количество подъездов в доме: ");
        for(int i =0; i <this.entrancesCount;i++){
            if (i == 0) {
                this.entrances.add(new Entrance());
            } else {
                this.entrances.add(new Entrance(entrances.get(0)));
            }
        }
        System.out.println("Дом номер " + (currentHouseNumber + 1) + " успешно добавлен!");
        currentHouseNumber++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        House house = (House) o;
        if (this.entrances.get(0).floorsCount == house.entrances.get(0).floorsCount && this.entrances.get(0).flatsPerFloor == house.entrances.get(0).flatsPerFloor && this.totalHouseSquare() == house.totalHouseSquare())
            return true;
        else return false;

    }

    public double totalHouseSquare() {
        double result = 0;

        for (int j = 0; j < this.entrances.get(0).flatsPerFloor; j++) {
            result += this.entrances.get(0).floors.get(1).flats.get(j).flatSquare;
        }
        return this.entrances.get(0).floorsCount * result*this.entrancesCount;
    }

    public int totalHouseResidentsCount() {
        int result = 0;
        for(int i =0;i<this.entrancesCount;i++){
            for(int j =0; j<this.entrances.get(0).floorsCount;j++){
                for(int k =0;k<this.entrances.get(0).flatsPerFloor;k++){
                    result+=this.entrances.get(0).floors.get(0).flats.get(0).residentsCount;
                }
            }
        }
        return result;
    }
    public Entrance getEntranceByFlatNumber(int flatNumber){
        int result = 0;
        for(int i =1, j = 0, k = (this.entrances.get(0).floorsCount*this.entrances.get(0).flatsPerFloor) ;j<this.entrancesCount;i+=(this.entrances.get(0).floorsCount*this.entrances.get(0).flatsPerFloor)-1,j++, k+=(this.entrances.get(0).floorsCount*this.entrances.get(0).flatsPerFloor)){
            if(flatNumber>=i && flatNumber<=k){
                result = j;
                return this.entrances.get(result);
            }
        }
        return this.entrances.get(result);
    }

    public String toString() {
        return "--------------------------------------------------------------------------------" + "\nНомер дома: " + (this.houseNumber) +"\nКоличество подъездов: " + (this.entrancesCount) + "\nКоличество этажей: " + (this.entrances.get(0).floorsCount) + "\nКоличество квартир на одном этаже: " + (this.entrances.get(0).floors.get(0).currentFlatsCount) + "\nОбщее количество квартир: " + this.getFlatsCount() + "\nОбщая площадь дома: " + (this.totalHouseSquare()) + "\nОбщее количество жильцов: " + (this.totalHouseResidentsCount()) + "\n--------------------------------------------------------------------------------";
    }

    public int getFlatsCount() {
        return this.entrances.get(0).flatsPerFloor*this.entrances.get(0).floorsCount*this.entrancesCount;
    }

}
