package accounting;

import util.SecuredNumbersScanner;
import house.House;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int mainAction, additionalAction;
        List<House> arrayOfHouses = new ArrayList<>();
        do {
            System.out.println("--------------------------------------------------------------------------------");
            String questionOfMainAction = """
                    Выберите нужное действие:
                    1. Создать новый дом
                    2. Просмотреть информацию о уже существущем доме
                    3. Удалить дом
                    4. Сравнить дома
                    5. Сравнить квартиры
                    6. Выйти из программы
                    Ваш выбор:\s""";
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
                        questionOfAdditionalAction = """
                                Выберите нужное действие:\s
                                1. Просмотреть всю информацию об этом доме
                                2. Просмотреть информацию об отдельной квартире в этом доме
                                3. Вернуться в главное меню
                                Ваш выбор:\s""";
                        additionalAction = SecuredNumbersScanner.EnteringInfoCheck(questionOfAdditionalAction);
                        switch (additionalAction) {
                            case 1:
                                System.out.println(arrayOfHouses.get(houseNumber - 1));
                                break;
                            case 2:
                                int flatNumber;
                                String flatNumberQuestion;
                                if (arrayOfHouses.get(houseNumber - 1).getFlatsCount() != 1) {
                                    flatNumberQuestion = "Введите номер нужной квартиры(1-";
                                    flatNumberQuestion += arrayOfHouses.get(houseNumber - 1).getFlatsCount() + "): ";
                                } else {
                                    flatNumberQuestion = "Введите номер нужной квартиры(1): ";
                                }
                                flatNumber = SecuredNumbersScanner.EnteringInfoCheck(flatNumberQuestion);
                                if (flatNumber > arrayOfHouses.get(houseNumber - 1).getFlatsCount() || flatNumber <= 0) {
                                    do {
                                        System.out.println("Введен номер несуществующей квартиры...Повторите ввод");
                                        flatNumber = SecuredNumbersScanner.EnteringInfoCheck(flatNumberQuestion);
                                    } while (flatNumber > arrayOfHouses.get(houseNumber - 1).getFlatsCount() ||
                                            flatNumber <= 0);
                                }
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
                            } while (houseCompareNumber2 == houseCompareNumber1
                                    || houseCompareNumber2 - 1 >= arrayOfHouses.size()
                                    || houseCompareNumber2 - 1 < 0);
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
                    if (arrayOfHouses.size() == 1 && arrayOfHouses.get(0).getFlatsCount() == 1) {
                        System.out.println("Недостаточно квартир для сравнения. Добавьте еще дома");
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
                    if (flatCompareNumber1 > arrayOfHouses.get(houseCompareNumber1 - 1).getFlatsCount()
                            || flatCompareNumber1 <= 0)
                        do {
                            System.out.println("Несуществующий номер квартиры...Введите еще раз");
                            flatCompareNumber1 = SecuredNumbersScanner.EnteringInfoCheck(question);
                        } while (flatCompareNumber1 > arrayOfHouses.get(houseCompareNumber1 - 1).getFlatsCount()
                                || flatCompareNumber1 <= 0);
                    System.out.print("------------------------------------------------------------");
                    System.out.print("\nКвартира успешно добавлена к сравнению!");
                    System.out.print("\n------------------------------------------------------------");
                    System.out.print("\nВыберите второй дом для сравнения: \n");
                    houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                    if (houseCompareNumber2 - 1 >= arrayOfHouses.size() || houseCompareNumber2 - 1 < 0) {
                        do {
                            System.out.println("Несуществующий номер дома...Введите еще раз");
                            houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                        } while (houseCompareNumber2 - 1 >= arrayOfHouses.size() || houseCompareNumber2 - 1 < 0);
                    }
                    if (houseCompareNumber2 == houseCompareNumber1
                            && arrayOfHouses.get(houseCompareNumber1 - 1).getFlatsCount() == 1) {
                        System.out.println("------------------------------------------------------------" +
                                "\nВ выбранном доме только одна квартира. Она уже добавлена к сравнению." +
                                "\n------------------------------------------------------------");
                        question = ("""
                                Выберите нужное действие:
                                1. Выбрать другой дом
                                2. Выйти в главное меню
                                Ваш выбор:\s""");
                        do {
                            additionalAction = SecuredNumbersScanner.EnteringInfoCheck(question);
                            if (additionalAction <= 0 || additionalAction > 2) {
                                System.out.println("Введено неверное значение. Повторите ввод");
                            }
                        } while (additionalAction <= 0 || additionalAction > 2);

                        if (additionalAction == 1) {
                            System.out.println("\nВыберите второй дом для сравнения: ");
                            houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                            if (houseCompareNumber2 - 1 >= arrayOfHouses.size() || houseCompareNumber2 - 1 < 0) {
                                do {
                                    System.out.println("Несуществующий номер дома...Введите еще раз");
                                    houseCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheckForHouseNumber(arrayOfHouses);
                                } while (houseCompareNumber2 - 1 >= arrayOfHouses.size() || houseCompareNumber2 - 1 < 0);
                            }
                        }
                        if (additionalAction == 2) {
                            break;
                        }

                    }
                    question = "Введите номер нужной квартиры(1-";
                    question += arrayOfHouses.get(houseCompareNumber2 - 1).getFlatsCount() + "): ";
                    flatCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheck(question);

                    if (flatCompareNumber2 > arrayOfHouses.get(houseCompareNumber2 - 1).getFlatsCount()
                            || flatCompareNumber2 <= 0
                            || (houseCompareNumber1 == houseCompareNumber2 && flatCompareNumber1 == flatCompareNumber2)) {

                        do {
                            System.out.println("Несуществующий номер квартиры(или квартира с таким"
                                    + "номером уже добавлена к сравнению)" +
                                    "...Введите еще раз");
                            flatCompareNumber2 = SecuredNumbersScanner.EnteringInfoCheck(question);
                        } while (flatCompareNumber2 > arrayOfHouses.get(houseCompareNumber2 - 1).getFlatsCount()
                                || flatCompareNumber2 <= 0
                                || (houseCompareNumber1 == houseCompareNumber2 && flatCompareNumber1 == flatCompareNumber2));
                    }
                    System.out.print("Квартира 1"
                            + arrayOfHouses.get(houseCompareNumber1 - 1).getFlat(flatCompareNumber1));
                    System.out.println("Квартира 2"
                            + arrayOfHouses.get(houseCompareNumber2 - 1).getFlat(flatCompareNumber2));

                    break;
                case 6:
                    break;
                default:
                    System.out.println("Введено неверное значение. Повторите попытку");
                    break;
            }
        } while (mainAction != 6);
    }

}
