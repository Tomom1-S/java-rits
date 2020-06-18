package ch20.ex06;

public enum Operator {
    PLUS('+') {
        public double apply(double a, double b) {
            return a + b;
        }
    },
    MINUS('-') {
        public double apply(double a, double b) {
            return a - b;
        }
    },
    EQUAL('=') {
        public double apply(double a, double b) {
            return b;
        }
    },
    ;

    private char op;

    Operator(char op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return String.valueOf(op);
    }

    public abstract double apply(double a, double b);
}
