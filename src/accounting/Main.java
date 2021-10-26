package accounting;

import util.SecuredNumbersScanner;
import house.House;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int mainAction, additionalAction;
        List<House> arrayOfHouses = new ArrayList<>();
        do {
            System.out.println("--------------------------------------------------------------------------------");
            Scanner scannerChoice = new Scanner(System.in);
            String questionOfMainAction;
            questionOfMainAction = "Выберите нужное действие:\n1. Создать новый дом";
            questionOfMainAction += "\n2. Просмотреть информацию о уже существущем доме\n3. Удалить дом\n4. Сравнить дома";
            questionOfMainAction += "\n5. Сравнить квартиры\n6. Выйти из программы\nВаш выбор: ";
            mainAction = SecuredNumbersScanner.EnteringInfoCheck(questionOfMainAction);
            System.out.println("--------------------------------------------------------------------------------");
            switch (mainAction) {
                case 1:
                    arrayOfHouses.add(new House());
                    break;
                case 2:
                    if (arrayOfHouses.isEmpty()) {
                        System.out.println("Домов нет");
                        break;
                    }
                    int houseNumber;
                    houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if (houseNumber > arrayOfHouses.size() || houseNumber <= 0) {
                        do {
                            System.out.println("Введен номер несуществующего дома");
                            houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        } while (houseNumber > arrayOfHouses.size() || houseNumber <= 0);
                    }
                    do {
                        System.out.println("--------------------------------------------------------------------------------");
                        String questionOfAdditionalAction;
                        questionOfAdditionalAction = "Выберите нужное действие: \n1. Просмотреть всю информацию об этом доме";
                        questionOfAdditionalAction += "\n2. Просмотреть информацию об отдельной квартире в этом доме";
                        questionOfAdditionalAction += "\n3. Вернуться в главное меню\nВаш выбор: ";
                        additionalAction = SecuredNumbersScanner.EnteringInfoCheck(questionOfAdditionalAction);
                        switch (additionalAction) {
                            case 1:
                                System.out.println(arrayOfHouses.get(houseNumber - 1));
                                break;
                            case 2:
                                int flatNumber;
                                String flatNumberQuestion;
                                flatNumberQuestion = "Введите номер нужной квартиры(1-";
                                flatNumberQuestion += arrayOfHouses.get(houseNumber - 1).getFlatsCount() + "): ";
                                flatNumber = SecuredNumbersScanner.EnteringInfoCheck(flatNumberQuestion);
                                System.out.println(arrayOfHouses.get(houseNumber - 1).getFlat(flatNumber));
                                break;
                            case 3:
                                break;
                        }
                    } while (additionalAction != 3);
                    break;
                case 3:

                    if (arrayOfHouses.isEmpty()) {
                        System.out.println("Домов нет");
                        break;
                    }
                    do {
                        if (arrayOfHouses.size() != 1)
                            System.out.print("Введите номер нужного дома" + "(1-" + arrayOfHouses.size() + "):");
                        else
                            System.out.print("Введите номер нужного дома(1): ");

                        houseNumber = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        if (arrayOfHouses.size() < houseNumber || houseNumber <= 0) {
                            System.out.println("Дома с таким номером нет/номер введен неверно!");
                        }
                    } while (arrayOfHouses.size() < houseNumber || houseNumber <= 0);
                    arrayOfHouses.remove(houseNumber - 1);
                    System.out.println("Дом успешно удалён!");
                    break;
                case 4:
                    if (arrayOfHouses.size() < 2) {
                        System.out.println("Недостаточно домов в списке для сравнения. Добавьте еще дома");
                        break;
                    }
                    int houseCompareNumber1;
                    int houseCompareNumber2;
                    System.out.println("Выберите первый дом для сравнения: ");
                    houseCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if (houseCompareNumber1 - 1 >= arrayOfHouses.size() || houseCompareNumber1 - 1 < 0) {
                        do {
                            System.out.println("Несуществующий номер дома...Введите еще раз");
                            houseCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        } while (houseCompareNumber1 - 1 >= arrayOfHouses.size() || houseCompareNumber1 - 1 < 0);
                    }
                    System.out.println("Выберите второй дом для сравнения: ");
                    houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if (houseCompareNumber2 == houseCompareNumber1 || houseCompareNumber2 - 1 >= arrayOfHouses.size()) {
                        if (houseCompareNumber2 - 1 < 0) {
                            do {
                                System.out.println("Несуществующий номер дома/Дом с таким номером уже добавлен к сравнению");
                                houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                            } while (houseCompareNumber2 == houseCompareNumber1 || houseCompareNumber2 - 1 >= arrayOfHouses.size() ||
                                    houseCompareNumber2 - 1 < 0);
                        }
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
                    houseCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if (houseCompareNumber1 - 1 >= arrayOfHouses.size() || houseCompareNumber1 - 1 < 0) {
                        do {
                            System.out.println("Несуществующий номер дома...Введите еще раз");
                            houseCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        } while (houseCompareNumber1 - 1 >= arrayOfHouses.size() || houseCompareNumber1 - 1 < 0);
                    }
                    String question = "Введите номер нужной квартиры(1-";
                    question += arrayOfHouses.get(houseCompareNumber1 - 1).getFlatsCount() + "): ";
                    flatCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheck(question);
                    if (flatCompareNumber1 > arrayOfHouses.get(houseCompareNumber1 - 1).getFlatsCount() || flatCompareNumber1 <= 0)
                        do {
                            System.out.println("Несуществующий номер квартиры...Введите еще раз");
                            flatCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheck(question);
                        } while (flatCompareNumber1 > arrayOfHouses.get(houseCompareNumber1 - 1).getFlatsCount()
                                || flatCompareNumber1 <= 0);
                    System.out.print("\n------------------------------------------------------------");
                    System.out.println("Квартира успешно добавлена к сравнению!");
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Выберите второй дом для сравнения: ");
                    houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if (houseCompareNumber2 - 1 >= arrayOfHouses.size() || houseCompareNumber2 - 1 < 0) {
                        do {
                            System.out.println("Несуществующий номер дома...Введите еще раз");
                            houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        } while (houseCompareNumber2 - 1 >= arrayOfHouses.size() || houseCompareNumber2 - 1 < 0);
                    }
                    question = "Введите номер нужной квартиры(1-";
                    question += arrayOfHouses.get(houseCompareNumber2 - 1).getFlatsCount() + "): ";
                    flatCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheck(question);


                    if (flatCompareNumber2 > arrayOfHouses.get(houseCompareNumber2 - 1).getFlatsCount()
                            || flatCompareNumber2 <= 0) {

                        do {
                            System.out.println("Несуществующий номер квартиры(или квартира с таким"
                                    + "номером уже добавлена к сравнению)" +
                                    "...Введите еще раз");
                            flatCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheck(question);
                        } while (flatCompareNumber2 > arrayOfHouses.get(houseCompareNumber2 - 1).getFlatsCount()
                                || flatCompareNumber2 <= 0 || flatCompareNumber2 == flatCompareNumber1);
                    }
                    System.out.print("Квартира 1"
                            + arrayOfHouses.get(houseCompareNumber1 - 1).getFlat(flatCompareNumber1));
                    System.out.println("Квартира 2"
                            + arrayOfHouses.get(houseCompareNumber2 - 1).getFlat(flatCompareNumber2));

                    break;
                case 6:
                    scannerChoice.close();
                    break;
            }
        } while (mainAction != 6);
    }

}
