package com.Meels.model;

/**
 * Коллекция блюд фиксированного размера.
 *
 * <p>Хранит объекты {@link Meal} полиморфно — в коллекцию можно добавлять
 * любых наследников ({@link SetMeal}, будущие {@code Drink}, {@code Dessert}
 * и т.д.). Внутренний массив не расширяется: при переполнении выводится
 * предупреждение, а элемент не добавляется.</p>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>{@code
 * MealCollection c = new MealCollection(10);
 * c.addMeal(new SetMeal("Сет: Капучино + тост", 420, 380, true));
 * c.printCollection();
 * }</pre>
 *
 * @author  Ваше имя
 * @version 1.0
 * @since   2026-11-04
 */
public class MealCollection {

    /** Внутренний массив блюд. Размер фиксируется в конструкторе. */
    private final Meal[] meals;

    /** Текущее количество добавленных блюд (не путать с {@code meals.length}). */
    private int count;

    /**
     * Создаёт пустую коллекцию заданного размера.
     *
     * @param size максимальное количество блюд; должно быть {@code > 0}
     * @throws IllegalArgumentException если {@code size <= 0}
     */
    public MealCollection(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер коллекции должен быть положительным");
        }
        this.meals = new Meal[size];
        this.count = 0;
    }

    /**
     * Добавляет блюдо в коллекцию.
     *
     * <p>Если коллекция заполнена, выводит сообщение «Меню переполнено!»
     * и оставляет коллекцию без изменений.</p>
     *
     * @param meal блюдо; не {@code null}
     * @throws IllegalArgumentException если {@code meal == null}
     */
    public void addMeal(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException("Нельзя добавить null в коллекцию");
        }
        if (count < meals.length) {
            meals[count++] = meal;
        } else {
            System.out.println("Меню переполнено!");
        }
    }

    /**
     * Возвращает блюдо по индексу.
     *
     * @param index индекс в диапазоне {@code [0, size())}
     * @return блюдо по указанному индексу
     * @throws IndexOutOfBoundsException если индекс вне диапазона
     */
    public Meal get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        return meals[index];
    }

    /**
     * Возвращает текущее количество блюд в коллекции.
     *
     * @return количество добавленных блюд; {@code >= 0}
     */
    public int size() {
        return count;
    }

    /**
     * Печатает все блюда коллекции в порядке добавления.
     *
     * <p>Использует полиморфный {@link Meal#toString()}, поэтому формат
     * строки зависит от фактического типа элемента ({@link SetMeal} и т.д.).</p>
     */
    public void printCollection() {
        System.out.println("==== [МЕНЮ БЛЮД] ====");
        for (int i = 0; i < count; i++) {
            System.out.println(meals[i]);
        }
    }

    /**
     * Считает среднюю стоимость блюд с калорийностью строго больше 300 ккал.
     *
     * @return средняя стоимость в рублях или {@code 0}, если подходящих блюд нет
     */
    public double calculateAvgCostOver300Cal() {
        double sum = 0;
        int n = 0;
        for (int i = 0; i < count; i++) {
            if (meals[i].caloricContent > 300) {
                sum += meals[i].cost;
                n++;
            }
        }
        return n == 0 ? 0 : sum / n;
    }

    /**
     * Считает количество блюд с калорийностью строго больше 300 ккал.
     *
     * @return количество подходящих блюд; {@code >= 0}
     */
    public int countOver300Cal() {
        int n = 0;
        for (int i = 0; i < count; i++) {
            if (meals[i].caloricContent > 300) n++;
        }
        return n;
    }
}