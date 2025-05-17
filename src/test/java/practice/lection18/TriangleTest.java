package practice.lection18;

import org.testng.Assert;
import org.testng.annotations.*;

public class TriangleTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void setUp() {
        System.out.println("Before Test");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @BeforeGroups(groups = "smoke")
    public void beforeGroups() {
        System.out.println("Before Groups");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @Test(
            description = "This test is checking this",
//            groups = {"smoke"},
            priority = 1,
            enabled = true,
            dataProvider = "provideData"
    )
    public void testPerimeter(int side1, int side2, int side3, double expectedPerimeter) {
//        double expectedPerimeter = 9.0;
//        int side1 = 3;
//        int side2 = 3;
//        int side3 = 3;

        Triangle triangle = new Triangle(side1, side2, side3);
        Assert.assertEquals(triangle.perimeter(), expectedPerimeter);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void nullExcept() {
        System.out.println("Test 1");
        int a = 0;
        int b = 10;
        int c = b/a;
//        Assert.assertThrows(ArithmeticException.class, () -> {int c = b/a;});
        System.out.println("Ending");
    }

    @DataProvider
    public Object[][] provideData() {
        return new Object[][] {{3, 3, 3, 9.0}, {4, 4, 4, 12}};
    }

    @Test(groups = "smoke")
    public void testovyitest() {
        System.out.println("Test 2");
        Assert.assertEquals(1, 1);
    }

//    @ParameterizedTest
//    @org.junit.jupiter.api.Test
//    public void testPythonitest() {
//        System.out.println("Test 3");
//    }
}
