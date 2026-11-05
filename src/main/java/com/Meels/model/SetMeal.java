package com.Meels.model;

/**
 * Сет — комбинация напитка и тоста.
 *
 * <p>Наследует от {@link Meal} поля {@code name}, {@code cost},
 * {@code caloricContent}, {@code veganFriendly} и их валидацию.
 * Добавляет префикс {@code [СЕТ]} в строковом представлении.</p>
 *
 * @author  Ваше имя
 * @version 1.0
 * @since   2026-11-04
 * @see     Meal
 */
public class SetMeal extends Meal {

    /**
     * Создаёт сет. Валидация полей выполняется в конструкторе {@link Meal}.
     *
     * @param name            название сета; не {@code null} и не пустое
     * @param cost            стоимость в рублях; {@code >= 0}
     * @param caloricContent  калорийность в ккал; {@code >= 0}
     * @param veganFriendly   {@code true}, если сет веганский
     * @throws IllegalArgumentException если нарушен любой из инвариантов
     */
    public SetMeal(String name, double cost, double caloricContent, boolean veganFriendly) {
        super(name, cost, caloricContent, veganFriendly);
    }

    /**
     * {@inheritDoc}
     *
     * @return строка {@code "Сет"}
     */
    @Override
    public String getCategory() {
        return "Сет";
    }

    /**
     * {@inheritDoc}
     *
     * <p>Добавляет префикс {@code [СЕТ]} к представлению базового класса,
     * переиспользуя его форматирование.</p>
     *
     * @return строка вида {@code [СЕТ] <название> | ... | Vegan: Да}
     */
    @Override
    public String toString() {
        return "[СЕТ] " + super.toString();
    }
}