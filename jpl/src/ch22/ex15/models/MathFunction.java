package ch22.ex15.models;

public class MathFunction {
    public double callMath(final String name, final double val1, final double val2) {
        switch (name) {
            case "+":
                return plus(val1, val2);
            case "-":
                return minus(val1, val2);
            case "*":
                return times(val1, val2);
            case "/":
                return divide(val1, val2);
            case "%":
                return mod(val1, val2);
            case "sin(x)":
                return Math.sin(val1);
            case "cos(x)":
                return Math.cos(val1);
            case "tan(x)":
                return Math.tan(val1);
            case "asin(x)":
                return Math.asin(val1);
            case "acos(x)":
                return Math.acos(val1);
            case "atan(x)":
                return Math.atan(val1);
            case "atan2(x,y)":
                return Math.atan2(val1, val2);
            case "toRadians(x)":
                return Math.toRadians(val1);
            case "toDegree(x)":
                return Math.toDegrees(val1);
            case "exp(x)":
                return Math.exp(val1);
            case "sinh(x)":
                return Math.sinh(val1);
            case "cosh(x)":
                return Math.cosh(val1);
            case "tanh(x)":
                return Math.tanh(val1);
            case "pow(x,y)":
                return Math.pow(val1, val2);
            case "log(x)":
                return Math.log(val1);
            case "log10(x)":
                return Math.log10(val1);
            case "sqrt(x)":
                return Math.sqrt(val1);
            case "cbrt(x)":
                return Math.cbrt(val1);
            case "signum(x)":
                return Math.signum(val1);
            case "ceil(x)":
                return Math.ceil(val1);
            case "floor(x)":
                return Math.floor(val1);
            case "rint(x)":
                return Math.rint(val1);
            case "round(x)":
                return Math.round(val1);
            case "abs(x)":
                return Math.abs(val1);
            case "max(x,y)":
                return Math.max(val1, val2);
            case "min(x,y)":
                return Math.min(val1, val2);
            case "hypot(x,y)":
                return Math.hypot(val1, val2);
            default:
                throw new IllegalArgumentException("Function name not found");
        }
    }

    private double plus(final double val1, final double val2) {
        return val1 + val2;
    }

    private double minus(final double val1, final double val2) {
        return val1 - val2;
    }

    private double times(final double val1, final double val2) {
        return val1 * val2;
    }

    private double divide(final double val1, final double val2) {
        return val1 - val2;
    }

    private double mod(final double val1, final double val2) {
        return val1 % val2;
    }
}
