
import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable triangle with three finite, positive side lengths.
 * Validates the triangle inequality and provides geometric operations.
 */
public final class Triangle {

    private final double a;
    private final double b;
    private final double c;

    /**
     * Creates a triangle with the specified sides.
     *
     * @throws IllegalArgumentException if sides are invalid
     */
    public Triangle(double a, double b, double c) {
        validateSide(a, "a");
        validateSide(b, "b");
        validateSide(c, "c");

        double[] sorted = { a, b, c };
        Arrays.sort(sorted);

        if (!(sorted[0] > sorted[2] - sorted[1])) {
            throw new IllegalArgumentException(
                    "Sides must satisfy the strict triangle inequality");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Validates that a side is finite and greater than zero.
     */
    private static void validateSide(double side, String name) {
        if (Double.isNaN(side)
                || Double.isInfinite(side)
                || side <= 0) {
            throw new IllegalArgumentException(
                    name + " must be finite and greater than zero");
        }
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    /**
     * Calculates the perimeter.
     */
    public double getPerimeter() {
        return a + b + c;
    }

    /**
     * Calculates the area using Kahan's rearrangement
     * of Heron's formula.
     */
    public double getArea() {
        double[] sides = { a, b, c };
        Arrays.sort(sides);

        double x = sides[2];
        double y = sides[1];
        double z = sides[0];

        return Math.sqrt(
                (x + (y + z))
                        * (z - (x - y))
                        * (z + (x - y))
                        * (x + (y - z)))
                / 4.0;
    }

    /**
     * Checks whether all three sides are equal.
     */
    public boolean isEquilateral() {
        return a == b && b == c;
    }

    /**
     * Checks whether at least two sides are equal.
     */
    public boolean isIsosceles() {
        return a == b || a == c || b == c;
    }

    /**
     * Checks whether all sides are different.
     */
    public boolean isScalene() {
        return !isIsosceles();
    }

    /**
     * Compares triangles by their ordered side values.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Triangle)) {
            return false;
        }

        Triangle triangle = (Triangle) other;

        return Double.compare(a, triangle.a) == 0
                && Double.compare(b, triangle.b) == 0
                && Double.compare(c, triangle.c) == 0;
    }

    /**
     * Generates a hash code based on the side values.
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    /**
     * Returns a string representation of the triangle.
     */
    @Override
    public String toString() {
        return "Triangle{"
                + "a=" + a
                + ", b=" + b
                + ", c=" + c
                + '}';
    }
}
