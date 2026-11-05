package com.Meels.model;

/**
 * Абстрактное блюдо — базовый класс иерархии позиций меню.
 *
 * <p>Нельзя создать напрямую ({@code new Meal(...)}), но конструктор
 * вызывается из конструкторов наследников через {@code super(...)}.
 * Это позволяет централизованно валидировать общие поля и делать их
 * {@code final} (иммутабельность).</p>
 *
 * <p><b>Инварианты класса:</b></p>
 * <ul>
 *   <li>{@code name} — не {@code null} и не пустая строка;</li>
 *   <li>{@code cost} — неотрицательное число;</li>
 *   <li>{@code caloricContent} — неотрицательное число.</li>
 * </ul>
 *
 * @author  Ваше имя
 * @version 1.0
 * @since   2026-11-04
 */
public abstract class Meal {

    /** Название блюда (например, «Сет: Капучино + Авокадо-тост»). */
    protected final String name;

    /** Стоимость блюда в рублях. */
    protected final double cost;

    /** Калорийность блюда в килокалориях. */
    protected final double caloricContent;

    /** Признак того, что блюдо подходит веганам. */
    protected final boolean veganFriendly;

    /**
     * Создаёт блюдо с проверкой инвариантов.
     *
     * @param name            название; не {@code null} и не пустое
     * @param cost            стоимость в рублях; {@code >= 0}
     * @param caloricContent  калорийность в ккал; {@code >= 0}
     * @param veganFriendly   {@code true}, если блюдо веганское
     * @throws IllegalArgumentException если нарушен любой из инвариантов
     */
    protected Meal(String name, double cost, double caloricContent, boolean veganFriendly) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название блюда не может быть пустым");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        if (caloricContent < 0) {
            throw new IllegalArgumentException("Калорийность не может быть отрицательной");
        }
        this.name = name;
        this.cost = cost;
        this.caloricContent = caloricContent;
        this.veganFriendly = veganFriendly;
    }

    /**
     * Возвращает название блюда.
     *
     * @return название; никогда не {@code null}
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает стоимость блюда.
     *
     * @return стоимость в рублях; {@code >= 0}
     */
    public double getCost() {
        return cost;
    }

    /**
     * Возвращает калорийность блюда.
     *
     * @return калорийность в ккал; {@code >= 0}
     */
    public double getCaloricContent() {
        return caloricContent;
    }

    /**
     * Сообщает, подходит ли блюдо веганам.
     *
     * @return {@code true}, если блюдо веганское
     */
    public boolean isVeganFriendly() {
        return veganFriendly;
    }

    /**
     * Возвращает категорию блюда (например, «Сет», «Напиток»).
     * Реализуется в наследниках.
     *
     * @return короткое название категории; никогда не {@code null}
     */
    public abstract String getCategory();

    /**
     * Возвращает строковое представление блюда в табличном виде.
     *
     * <p>Формат: {@code <название> | <цена> руб. | <ккал> ккал | Vegan: <Да|Нет>}.
     * Наследники могут переопределять метод, вызывая {@code super.toString()}.</p>
     *
     * @return отформатированная строка
     */
    @Override
    public String toString() {
        return String.format(
                "%-52s | %6.0f руб. | %5.0f ккал | Vegan: %-3s",
                name, cost, caloricContent, veganFriendly ? "Да" : "Нет");
    }
}