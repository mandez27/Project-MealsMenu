package com.Meels.app;

import com.Meels.model.Meal;
import com.Meels.model.MealCollection;
import com.Meels.model.SetMeal;

import java.util.Random;

/**
 * Точка входа демонстрационного приложения «Меню блюд».
 *
 * <p>Сценарий работы:</p>
 * <ol>
 *   <li>Создаётся коллекция на 10 блюд.</li>
 *   <li>Генерируются 10 сетов с «круглыми» ценой и калорийностью,
 *       привязанными к составу сета.</li>
 *   <li>Печатается полное меню.</li>
 *   <li>Печатаются только веганские сеты.</li>
 *   <li>Считается средняя стоимость сетов с калорийностью {@code > 300} ккал.</li>
 * </ol>
 *
 * @author  Ваше имя
 * @version 1.0
 * @since   2026-11-04
 */
public class Main {

    /**
     * Запускает демонстрацию.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        MealCollection collection = new MealCollection(10);
        Random random = new Random();

        // Состав сетов: [название, базовая цена, базовая калорийность, веган?]
        Object[][] sets = {
                {"Сет: Капучино + Авокадо-тост",              420, 380, true},
                {"Сет: Латте на кокосовом + Тост с джемом",   390, 340, true},
                {"Сет: Клюквенный морс + Тост с сыром",       350, 310, false},
                {"Сет: Эрл Грей + Тост с арахисовой пастой",  370, 400, true},
                {"Сет: Эспрессо + Тост с ветчиной",           410, 360, false},
                {"Сет: Зелёный чай + Тост с томатами",        330, 260, true},
                {"Сет: Горячий шоколад + Тост с бананом",     450, 480, false},
                {"Сет: Чай Латте + Тост с лососем",           520, 420, false},
                {"Сет: Шпинатный смузи + Тост с хумусом",     380, 290, true},
                {"Сет: Томатный сок + Тост со скрамблом",     360, 350, false}
        };

        // 1. Создаём 10 сетов с «круглыми» ценой и калориями
        for (Object[] s : sets) {
            String name     = (String)  s[0];
            double baseCost = (int)     s[1];
            double baseCal  = (int)     s[2];
            boolean vegan   = (boolean) s[3];

            // Разброс: цена ±20 руб. (шаг 10), калории ±30 ккал (шаг 10)
            double cost = roundTo10(baseCost + (random.nextInt(5) - 2) * 10);
            double cal  = roundTo10(baseCal  + (random.nextInt(7) - 3) * 10);

            collection.addMeal(new SetMeal(name, cost, cal, vegan));
        }

        // 2. Полное меню
        collection.printCollection();

        // 3. Только Vegan Friendly сеты
        System.out.println("\n==== [VEGAN FRIENDLY СЕТЫ] ====");
        boolean hasVegan = false;
        for (int i = 0; i < collection.size(); i++) {
            Meal m = collection.get(i);
            if (m.isVeganFriendly()) {
                System.out.println(m);
                hasVegan = true;
            }
        }
        if (!hasVegan) {
            System.out.println("В текущем меню нет веганских сетов.");
        }

        // 4. Средняя стоимость сетов > 300 ккал
        System.out.println("\n==== [СРЕДНЯЯ СТОИМОСТЬ СЕТОВ (> 300 ККАЛ)] ====");
        int n = collection.countOver300Cal();
        if (n > 0) {
            System.out.printf("Количество подходящих сетов: %d%n", n);
            System.out.printf("Средняя стоимость: %.0f руб.%n",
                    collection.calculateAvgCostOver300Cal());
        } else {
            System.out.println("Сеты с калорийностью более 300 ккал отсутствуют.");
        }
    }

    /**
     * Округляет значение до ближайшего кратного 10.
     *
     * <p>Используется, чтобы сгенерированные цены и калорийности выглядели
     * «адекватно» (410, 440, 380), а не как {@code 437.2837…}.</p>
     *
     * @param v исходное значение
     * @return значение, округлённое до 10
     */
    private static double roundTo10(double v) {
        return Math.round(v / 10.0) * 10.0;
    }
}