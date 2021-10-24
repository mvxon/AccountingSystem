package house;
import accounting.Main;
import java.util.*;

public class House {
    private int floorsCount = 0;
    private int currentFloorsCount = 0;
    private  int flatsPerFloor = 0;
    private static int currentHouseNumber;
    private final int houseNumber;
    private List<Floor> floors = new ArrayList<>();
    public class Floor {
        private  int floorNumber = 0;
        private List<Flat> flats = new ArrayList<>();
        private int currentFlatsCount = 0;
        private static int currentFlatNumber=0;
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
                    this.roomNumber = currentRoomsCount+1;
                    this.roomSquare = Main.EnteringInfoCheck("Введите площадь " + Integer.toString(roomNumber) + " комнаты(м^2): ");
                    currentRoomsCount++;
                }

                Room(Room room)
                {
                    this.roomNumber = room.roomNumber;
                    this.roomSquare = room.roomSquare;
                }
            }
            Flat() {
                currentFlatNumber++;
                this.flatUniqueNumber = currentFlatNumber;
                this.flatNumber = currentFlatsCount+1;
                if(floorNumber == 0) {
                    this.roomsCount = Main.EnteringInfoCheck("Введите количество комнат " + Integer.toString(flatNumber) + " квартиры на этаже: ");
                }
                this.residentsCount = this.roomsCount;
                for(int i=0;i<roomsCount;i++) {
                    this.rooms.add(new Room());
                }
                this.findFlatSquare();
                currentFlatsCount++;
            }

            Flat(Flat flat)
            {
                currentFlatNumber++;
                this.flatUniqueNumber = currentFlatNumber;
                this.flatNumber = currentFlatsCount+1;
                this.roomsCount = flat.roomsCount;
                this.flatSquare = flat.flatSquare;
                this.residentsCount = flat.residentsCount;
                for(int i =0; i <this.roomsCount;i++){
                    this.rooms.add(new Room(flat.rooms.get(i)));
                }
                this.findFlatSquare();
                currentFlatsCount++;
            }
            private void findFlatSquare() {
                for(int i =0; i <roomsCount;i++) {
                    this.flatSquare += rooms.get(i).roomSquare;
                }
            }
            @Override
            public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Flat flat = (Flat) o;
                if(this.flatSquare == flat.flatSquare && this.roomsCount == flat.roomsCount) {
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
                result+="\n--------------------------------------------------------------------------------\n"+ "Номер квартиры: " + this.flatUniqueNumber +"\nКоличество комнат: "+this.roomsCount;
                for(int i = 0; i <this.roomsCount;i++) {
                    result += "\nПлощадь " + Integer.toString(i + 1) + " комнаты: " + this.rooms.get(i).roomSquare;
                }
                result+="\nОбщая площадь квартиры: " + this.flatSquare +  "\nКоличество жильцов: " + this.residentsCount+"\n--------------------------------------------------------------------------------\n";
                return result;
            }
        }
        Floor() {
            this.floorNumber = currentFloorsCount;
            if(floorNumber == 0){
                for(int i=0;i<flatsPerFloor;i++) {
                    this.flats.add(new Flat());
                }
            }
            currentFloorsCount++;
        }
        Floor(Floor floor)
        {
            this.floorNumber = currentFloorsCount;
            for(int i =0; i<flatsPerFloor;i++) {
                this.flats.add(new Flat(floor.flats.get(i)));
            }
            currentFloorsCount++;
        }
        public Flat getFlat(int flatNumber) {
            flatNumber -= (this.floorNumber)*flatsPerFloor;
            flatNumber--;
            return this.flats.get(flatNumber);
        }
    }
    public House() {
        this.houseNumber = currentHouseNumber+1;
        this.floorsCount = Main.EnteringInfoCheck("Введите количество этажей в доме: ");
        this.flatsPerFloor = Main.EnteringInfoCheck("Введите количество квартир на одном этаже: ");
        for(int i =0; i <=this.floorsCount;i++)
        {
            if(i==0) {
                this.floors.add(new Floor());
            }
            else{
                this.floors.add(new Floor(floors.get(0)));
            }
        }
        System.out.println("Дом номер "+ Integer.toString(currentHouseNumber+1)+ " успешно добавлен!");
        currentHouseNumber++;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        House house = (House)o;
        if(this.floorsCount == house.floorsCount && this.flatsPerFloor == house.flatsPerFloor && this.totalHouseSquare() == house.totalHouseSquare())
            return true;
        else return false;

    }
    public double totalHouseSquare() {
        double result =0;
        for(int j =0;j<flatsPerFloor;j++)
        {
            result+= floors.get(1).flats.get(j).flatSquare;
        }

        return floorsCount*result;
    }
    public int totalHouseResidentsCount() {
        int result =0;
        for(int i =0; i < floorsCount;i++) {
            for(int j =0;j<flatsPerFloor;j++) {
                result+= floors.get(i).flats.get(j).residentsCount;
            }
        }
        return result;
    }
    /* public Floor getFloor(int floorNumber)
     {
         return this.floors.get(floorNumber-1);
     }*/
    public int getFloorsCount()
    {
        return this.floorsCount;
    }
    public int getFlatsPerFloor()
    {
        return this.flatsPerFloor;
    }
    public Floor getFloorByFlatNumber(int flatNumber)
    {
        int temp = 0;
        temp = flatNumber;
        if(temp%this.flatsPerFloor!=0){
            do{
                temp++;
            }while(temp%this.flatsPerFloor!=0);
        }
        return this.floors.get((temp/flatsPerFloor)-1);
    }

    public String toString() {
        return "--------------------------------------------------------------------------------"+"\nНомер дома: "+Integer.toString(this.houseNumber)+"\nКоличество этажей: "+Integer.toString(this.floorsCount)+"\nКоличество квартир на одном этаже: "+Integer.toString(this.floors.get(0).currentFlatsCount)+"\nОбщее количество квартир: "+this.getFlatsCount()+"\nОбщая площадь дома: "+Double.toString(this.totalHouseSquare())+"\nОбщее количество жильцов: "+Integer.toString(this.totalHouseResidentsCount()) + "\n--------------------------------------------------------------------------------";
    }
    public int getFlatsCount(){
        return this.flatsPerFloor*floorsCount;
    }
}