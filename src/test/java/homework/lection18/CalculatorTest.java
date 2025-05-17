package homework.lection18;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//Розробити та виконати тести для базових арифметичних операцій (додавання, віднімання, множення, ділення)
// в калькуляторі з використанням фреймворка тестування TestNG

public class CalculatorTest {

    //2. Створення тестового класу `CalculatorTest`:
    //  - Використовуйте анотації TestNG для створення тестового класу.
    //  - Створіть екземпляр класу Calculator у методі з анотацією @BeforeClass
    //  та звільніть ресурси (calculator = null) у методі з анотацією @AfterClass`.

    static Calculator calculator;

    @BeforeClass
    public static void setUpBeforeClass() {
        calculator = new Calculator();
    }

    //3. Реалізація тестів:
    //  - Створіть тестові методи для кожної арифметичної операції, наприклад, testAddition, testSubtraction, testMultiplication, testDivision.
    //  - Додайте анотацію @Test для кожного тестового методу з відповідним описом атрибута description.
    //  Вкажіть атрибут priority для визначення пріоритету виконання тестів.
    //  - Додайте в тест поділу, обробку виключення при розподілі на нуль з використанням атрибуту expectedExceptions.
    //  - У кожному тестовому методі використовуйте методи класу `Calculator` для виконання операції.
    //  Результат виконання вивести у консоль для перевірки і вам слід перевірити ці результати вручну.

    @DataProvider
    public Object[][] dataProviderForAddition() {
        return new Object[][] {
                {10, 20, 30},
                {0, 0, 0},
                {-17, 200, 183},
                {-93, -2049, -2142}
        };
    }

    @Test(
            description = "Addition method provides appropriate result",
            dataProvider = "dataProviderForAddition",
            priority = 1
    )
    public void testAddition(int a, int b, int expected) {
        Assert.assertEquals(calculator.add(a, b), expected);
    }

    @DataProvider
    public Object[][] dataProviderSubtraction() {
        return new Object[][] {
                {10, 20, -10},
                {0, 88, -88},
                {-17, 200, -217},
                {-93, -2049, 1956}
        };
    }

    @Test(
            dataProvider = "dataProviderSubtraction",
            description = "Subtraction method provides appropriate result",
            priority = 1
    )
    public void testSubtraction(int a, int b, int expected) {
        System.out.println("Actual: " + calculator.substract(a, b) + " Expected: " + expected);
        Assert.assertEquals(calculator.substract(a, b), expected);
    }


    @DataProvider
    public Object[][] dataProviderMultiplication() {
        return new Object[][] {
                {10, 20, 200},
                {0, 88, 0},
                {-17, 200, -3400},
                {-93, -2049, 190557}
        };
    }

    @Test(
            dataProvider = "dataProviderMultiplication",
            description = "Multiplication method provides appropriate result",
            priority = 1)
    public void testMultiplication(int a, int b, int expected) {
        System.out.println("Actual: " + calculator.multiply(a, b) + " Expected: " + expected);
        Assert.assertEquals(calculator.multiply(a, b), expected);
    }

    @DataProvider
    public Object[][] dataProviderDivision() {
        return new Object[][] {
                {10, 20, 0.5},
                {0, 88, 0.0},
                {-17, 200, -0.085},
                {-9398, -2049, 4.586627623230845}
        };
    }

    @Test(
            dataProvider = "dataProviderDivision",
            description = "Division method provides appropriate result",
            priority = 1
    )
    public void testDivision(int a, int b, double expected) {
        System.out.println("Actual: " + calculator.divide(a, b) + " Expected: " + expected);
        Assert.assertEquals(calculator.divide(a, b), expected);
    }

    @Test(
            expectedExceptions = ArithmeticException.class,
            description = "Division method throws corresponding exception when division by zero is made",
            priority = 1
    )
    public void testDivisionByZero() {
        int a = 10;
        int b = 0;
        calculator.divide(a, b);
//        Assert.assertThrows(ArithmeticException.class, () -> calculator.divide(a, b));
    }

    @AfterClass
    public static void cleanResourcesAfterClass() {
        calculator = null;
    }
}
