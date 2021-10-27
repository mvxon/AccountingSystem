package house;

import util.SecuredNumbersScanner;
import util.MyNumber;

import java.util.ArrayList;
import java.util.List;

 class Entrance {
    private int entranceNumber;
    private int floorsCount;
    private  int flatsPerFloor;
    private List<Floor> floors = new ArrayList<>();
    public Floor getFloorByFlatNumber(int flatNumber){
        int temp = flatNumber-entranceNumber*floorsCount*flatsPerFloor;
        if(temp%flatsPerFloor !=0){
            do {
                temp++;
            }while(temp%flatsPerFloor !=0);
        }
        return this.floors.get((temp/flatsPerFloor)-1);
    }

    Entrance(int entranceNumber, MyNumber currentFlatNumber) {
        this.entranceNumber = entranceNumber;
        String question = "Введите количество этажей в доме(от 1 до 163): ";
        this.floorsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
        if (this.floorsCount <= 0 || this.floorsCount > 163)
            do {
                System.out.println("Введено неверное значение...Повторите ввод");
                this.floorsCount = SecuredNumbersScanner.EnteringInfoCheck(question);
            } while (this.floorsCount<= 0 || this.floorsCount > 163);
        for (int i = 0; i < this.floorsCount; i++) {
            if (i == 0) {
                this.floors.add(new Floor(i, currentFlatNumber));
                this.flatsPerFloor = floors.get(0).getFlatsPerFloor();
            } else {
                this.floors.add(new Floor(floors.get(0),i,currentFlatNumber,flatsPerFloor));
            }
        }
    }

    Entrance(Entrance entrance, int entranceNumber, MyNumber currentFlatNumber) {
        this.entranceNumber = entranceNumber;
        this.floorsCount = entrance.floorsCount;
        this.flatsPerFloor = entrance.flatsPerFloor;
        for(int i =0; i <floorsCount;i++){
            this.floors.add(new Floor(entrance.floors.get(0),i,currentFlatNumber,flatsPerFloor));
        }
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public int getFlatsPerFloor() {
        return flatsPerFloor;
    }
    public int getEntranceNumber(){return entranceNumber;}

}

