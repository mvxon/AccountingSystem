package com.bsu.lab.util.constants;

public class ConstantsForFlatsComparison {
    public final static String FLAT_ADDED_INFORMING =
            "------------------------------------------------------------" +
            "\nКвартира успешно добавлена к сравнению!" +
            "\n------------------------------------------------------------";
    public final static String ONLY_ONE_FLAT_IN_HOUSE_ERROR =
                                """
                                ------------------------------------------------------------
                                В выбранном доме только одна квартира. Она уже добавлена к сравнению.
                                ------------------------------------------------------------""";
    public final static String QUESTION_FOR_ACTION = """
                                Выберите нужное действие:
                                1. Выбрать другой дом
                                2. Выйти в главное меню
                                Ваш выбор:\s""";
}
