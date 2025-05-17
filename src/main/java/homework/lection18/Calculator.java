package homework.lection18;

public class Calculator {

    //1. Створення класу `Calculator`:
    //
    //  - Реалізуйте клас Calculator з методами для виконання арифметичних операцій: `add` (додавання),
    //  `subtract` (віднімання), `multiply` (множення) та divide (ділення).
    //
    //  - Обробіть випадок ділення на нуль, викидаючи виняток ArithmeticException.

    public int add(int a, int b) {
        return a + b;
    }

    public int substract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("Divide by zero");
        else
            return (double) a / (double) b;
    }
}
