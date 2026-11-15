
public final class Main {

    private static int passed = 0;

    private Main() {
    }

    public static void main(String[] args) {
        if (args.length > 0 && "--test".equals(args[0])) {
            runTests();
            return;
        }

        Triangle triangle = new Triangle(3, 4, 5);

        System.out.println(triangle);
        System.out.println("Perimeter: " + triangle.getPerimeter());
        System.out.println("Area: " + triangle.getArea());
        System.out.println("Equilateral: " + triangle.isEquilateral());
    }

    private static void runTests() {
        passed = 0;

        Triangle t = new Triangle(3, 4, 5);

        check(t.getA() == 3 && t.getB() == 4 && t.getC() == 5);
        check(t.getPerimeter() == 12);
        check(Math.abs(t.getArea() - 6) < 1e-12);
        check(!t.isEquilateral() && !t.isIsosceles() && t.isScalene());

        Triangle equal = new Triangle(2, 2, 2);

        check(equal.isEquilateral() && equal.isIsosceles()
                && !equal.isScalene());
        check(Math.abs(equal.getArea() - Math.sqrt(3)) < 1e-12);

        check(new Triangle(2, 2, 3).isIsosceles());

        check(t.equals(new Triangle(3, 4, 5)));
        check(t.hashCode() == new Triangle(3, 4, 5).hashCode());
        check(!t.equals(new Triangle(4, 3, 5)));
        check(!t.equals(null) && !t.equals("triangle"));
        check(t.toString().contains("a=3.0"));

        rejects(0, 2, 2);
        rejects(-1, 2, 2);
        rejects(1, 2, 3);
        rejects(1, 2, 4);
        rejects(Double.NaN, 2, 2);
        rejects(Double.POSITIVE_INFINITY, 2, 2);
        rejects(1, Double.NEGATIVE_INFINITY, 2);
        rejects(Double.MIN_VALUE, Double.MIN_VALUE, 1);

        check(new Triangle(1e-10, 1e-10, 1e-10).getArea() > 0);

        System.out.println("PASSED: " + passed + " checks");
    }

    private static void check(boolean condition) {
        if (!condition) {
            throw new AssertionError(
                    "Check " + (passed + 1) + " failed");
        }
        passed++;
    }

    private static void rejects(double a, double b, double c) {
        try {
            new Triangle(a, b, c);
        } catch (IllegalArgumentException exception) {
            passed++;
            return;
        }

        throw new AssertionError(
                "Expected IllegalArgumentException for sides: "
                        + a + ", " + b + ", " + c);
    }
}
