package com.bsu.lab.util.constants;

public class ConstantsForFlatsComparison {
    public final static String flatAddedInforming =
            "------------------------------------------------------------" +
            "\nКвартира успешно добавлена к сравнению!" +
            "\n------------------------------------------------------------";
    public final static String onlyOneFlatInHouseError =
                                """
                                ------------------------------------------------------------
                                В выбранном доме только одна квартира. Она уже добавлена к сравнению.
                                ------------------------------------------------------------""";
    public final static String questionForAction = """
                                Выберите нужное действие:
                                1. Выбрать другой дом
                                2. Выйти в главное меню
                                Ваш выбор:\s""";
}
