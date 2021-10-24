package accounting;
import house.House;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice1, choice2;
        List<House> arrayOfHouses = new ArrayList<>();
        do {
            System.out.println("--------------------------------------------------------------------------------");
            Scanner scannerChoice = new Scanner(System.in);
            choice1 = EnteringInfoCheck("Выберите нужное действие:\n1. Создать новый дом\n2. Просмотреть информацию о уже существущем доме\n3. Удалить дом\n4. Сравнить дома\n5. Сравнить квартиры\n6. Выйти из программы\nВаш выбор: ");
            System.out.println("--------------------------------------------------------------------------------");
            switch (choice1) {
                case 1:
                    arrayOfHouses.add(new House());
                    break;
                case 2:
                    if (arrayOfHouses.isEmpty()) {
                        System.out.println("Домов нет");
                        break;
                    }
                    int houseNumber;
                    houseNumber = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if(houseNumber> arrayOfHouses.size() || houseNumber <=0){
                        System.out.println("Введен номер несуществующего лома");
                        do {
                            houseNumber = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        }while(houseNumber> arrayOfHouses.size() || houseNumber <=0);
                    }
                    do {
                        System.out.println("--------------------------------------------------------------------------------");
                        choice2 = EnteringInfoCheck("Выберите нужное действие: \n1. Просмотреть всю информацию об этом доме\n2. Просмотреть информацию об отдельной квартире в этом доме\n3. Вернуться в главное меню\nВаш выбор: ");
                        switch (choice2) {
                            case 1:
                                System.out.println(arrayOfHouses.get(houseNumber-1));
                                break;
                            case 2:
                                int flatNumber;
                                flatNumber = EnteringInfoCheck("Введите номер нужной квартиры(1-"+arrayOfHouses.get(houseNumber-1).getFlatsCount() +"): ");
                                System.out.println(arrayOfHouses.get(houseNumber - 1).getEntranceByFlatNumber(flatNumber).getFloorByFlatNumber(flatNumber).getFlat(flatNumber));
                                break;
                            case 3:
                                break;
                        }
                    } while (choice2 != 3);
                    break;
                case 3:

                    if (arrayOfHouses.isEmpty()) {
                        System.out.println("Домов нет");
                        break;
                    }
                    do {
                        if (arrayOfHouses.size() != 1)
                            System.out.print("Введите номер нужного дома" + "(1-" + arrayOfHouses.size()+ "):");
                        else
                            System.out.print("Введите номер нужного дома(1): ");

                        houseNumber = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        if (arrayOfHouses.size() < houseNumber || houseNumber <= 0) {
                            System.out.println("Дома с таким номером нет/номер введен неверно!");
                        }
                    } while (arrayOfHouses.size() < houseNumber || houseNumber <= 0);
                    arrayOfHouses.remove(houseNumber - 1);
                    System.out.println("Дом успешно удалён!");
                    break;
                case 4:
                    if(arrayOfHouses.size()<2){
                        System.out.println("Недостаточно домов в списке для сравнения. Добавьте еще дома");
                        break;
                    }
                    int houseCompareNumber1;
                    int houseCompareNumber2;
                    System.out.println("Выберите первый дом для сравнения: ");
                    houseCompareNumber1 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if(houseCompareNumber1-1 >= arrayOfHouses.size() || houseCompareNumber1-1<0){
                        do {
                            System.out.println("Несуществующий номер дома...Введите еще раз");
                            houseCompareNumber1 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        }while(houseCompareNumber1-1 >= arrayOfHouses.size() || houseCompareNumber1-1<0);
                    }
                    System.out.println("Выберите второй дом для сравнения: ");
                    houseCompareNumber2 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if(houseCompareNumber2 == houseCompareNumber1 || houseCompareNumber2-1 >= arrayOfHouses.size() || houseCompareNumber2-1<0){
                        do {
                            System.out.println("Несуществующий номер дома/Дом с таким номером уже добавлен к сравнению");
                            houseCompareNumber2 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        }while(houseCompareNumber2 == houseCompareNumber1 || houseCompareNumber2-1 >= arrayOfHouses.size() || houseCompareNumber2-1<0);
                    }
                    System.out.println(arrayOfHouses.get(houseCompareNumber1 - 1));
                    System.out.println(arrayOfHouses.get(houseCompareNumber2 - 1));
                    if (arrayOfHouses.get(houseCompareNumber1 - 1).equals(arrayOfHouses.get(houseCompareNumber2 - 1)))
                        System.out.println("Дома одинаковы!");
                    break;
                case 5:
                    if (arrayOfHouses.isEmpty()) {
                        System.out.println("Домов нет");
                        break;
                    }
                    int flatCompareNumber1;
                    int flatCompareNumber2 = 0;
                    System.out.println("Выберите первый дом для сравнения: ");
                    houseCompareNumber1 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if(houseCompareNumber1-1 >= arrayOfHouses.size() || houseCompareNumber1-1<0){
                        do {
                            System.out.println("Несуществующий номер дома...Введите еще раз");
                            houseCompareNumber1 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        }while(houseCompareNumber1-1 >= arrayOfHouses.size() || houseCompareNumber1-1<0);
                    }
                    flatCompareNumber1 = EnteringInfoCheck("Введите номер нужной квартиры(1-"+arrayOfHouses.get(houseCompareNumber1-1).getFlatsCount() +"): ");
                    if(flatCompareNumber1 > arrayOfHouses.get(houseCompareNumber1-1).getFlatsCount() ||flatCompareNumber1<=0 )
                        do{
                            System.out.println("Несуществующий номер квартиры...Введите еще раз");
                            flatCompareNumber1 = EnteringInfoCheck("Введите номер нужной квартиры(1-"+arrayOfHouses.get(houseCompareNumber1-1).getFlatsCount() +"): ");
                        }while(flatCompareNumber1 > arrayOfHouses.get(houseCompareNumber1-1).getFlatsCount() ||flatCompareNumber1<=0 );
                    System.out.println("------------------------------------------------------------\nКвартира успешно добавлена к сравнению!\n------------------------------------------------------------");
                    System.out.println("Выберите второй дом для сравнения: ");
                    houseCompareNumber2 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if(houseCompareNumber2-1 >= arrayOfHouses.size() || houseCompareNumber2-1<0){
                        do {
                            System.out.println("Несуществующий номер дома...Введите еще раз");
                            houseCompareNumber2 = EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        }while(houseCompareNumber2-1 >= arrayOfHouses.size() || houseCompareNumber2-1<0|| flatCompareNumber2 == flatCompareNumber1);
                    }
                    flatCompareNumber2 = EnteringInfoCheck("Введите номер нужной квартиры(1-"+arrayOfHouses.get(houseCompareNumber2-1).getFlatsCount() +"): ");
                    if(flatCompareNumber2 > arrayOfHouses.get(houseCompareNumber2-1).getFlatsCount() ||flatCompareNumber2<=0 )

                        do{
                            System.out.println("Несуществующий номер квартиры(или квартира с таким номером уже добавлена к сравнению)...Введите еще раз");
                            flatCompareNumber2 = EnteringInfoCheck("Введите номер нужной квартиры(1-"+arrayOfHouses.get(houseCompareNumber2-1).getFlatsCount() +"): ");
                        }while(flatCompareNumber2 > arrayOfHouses.get(houseCompareNumber2-1).getFlatsCount() ||flatCompareNumber2<=0 || flatCompareNumber2 == flatCompareNumber1);
                    System.out.print("Квартира 1"+arrayOfHouses.get(houseCompareNumber1-1).getEntranceByFlatNumber(flatCompareNumber1).getFloorByFlatNumber(flatCompareNumber1).getFlat(flatCompareNumber1));
                    System.out.println("Квартира 2"+arrayOfHouses.get(houseCompareNumber2-1).getEntranceByFlatNumber(flatCompareNumber2).getFloorByFlatNumber(flatCompareNumber2).getFlat(flatCompareNumber2));
                    break;
                case 6:
                    scannerChoice.close();
                    break;
            }
        } while (choice1 != 6);
    }
    public static int EnteringInfoCheck(String question) {
        int result = 0;
        Scanner choiceScanner = new Scanner(System.in);
        boolean numberFormat = true;
        System.out.print(question);
        do{
            if(!numberFormat){
                System.out.print(question);
            }
            try {
                numberFormat = true;
                result = Integer.parseInt(choiceScanner.nextLine());
            }
            catch(NumberFormatException e) {
                System.out.println("Введено неверное значение");
                numberFormat = false;
            }
        }while(!numberFormat);
        return result;
    }
    public static int EnteringInfoCheckForHouseNumber(List<House> arrayOfHouses) {
        int result = 0;
        Scanner choiceScanner = new Scanner(System.in);
        if (arrayOfHouses.size() != 1)
            System.out.print("Введите номер нужного дома" + "(1-" + arrayOfHouses.size() + "): ");
        else
            System.out.print("Введите номер нужного дома(1): ");

        boolean numberFormatHouseCompareNumber = true;
        do {
            if (numberFormatHouseCompareNumber == false) {
                if (arrayOfHouses.size() != 1)
                    System.out.print("Введите номер нужного дома" + "(1-" + arrayOfHouses.size() + "): ");
                else
                    System.out.print("Введите номер нужного дома(1): ");
            }
            try {
                numberFormatHouseCompareNumber = true;
                result = Integer.parseInt(choiceScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение");
                numberFormatHouseCompareNumber = false;
            }
        } while (!numberFormatHouseCompareNumber);
        return result;
    }
}
