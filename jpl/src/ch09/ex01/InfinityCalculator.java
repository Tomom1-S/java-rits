package ch09.ex01;

import javax.swing.*;

public class InfinityCalculator {
    public static void main(String[] args) {
        double posInf = Double.POSITIVE_INFINITY;
        double negInf = Double.NEGATIVE_INFINITY;

        System.out.println("(+∞) + (+∞) = " + (posInf + posInf));
        System.out.println("(-∞) + (-∞) = " + (negInf + negInf));
        System.out.println("(+∞) + (-∞) = " + (posInf + negInf));
        System.out.println("(-∞) + (+∞) = " + (negInf + posInf));

        System.out.println("(+∞) - (+∞) = " + (posInf - posInf));
        System.out.println("(-∞) - (-∞) = " + (negInf - negInf));
        System.out.println("(+∞) - (-∞) = " + (posInf - negInf));
        System.out.println("(-∞) - (+∞) = " + (negInf - posInf));

        System.out.println("(+∞) * (+∞) = " + (posInf * posInf));
        System.out.println("(-∞) * (-∞) = " + (negInf * negInf));
        System.out.println("(+∞) * (-∞) = " + (posInf * negInf));
        System.out.println("(-∞) * (+∞) = " + (negInf * posInf));

        System.out.println("(+∞) / (+∞) = " + (posInf / posInf));
        System.out.println("(-∞) / (-∞) = " + (negInf / negInf));
        System.out.println("(+∞) / (-∞) = " + (posInf / negInf));
        System.out.println("(-∞) / (+∞) = " + (negInf / posInf));
    }
}
