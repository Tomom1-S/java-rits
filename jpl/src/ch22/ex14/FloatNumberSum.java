package ch22.ex14;

import java.util.StringTokenizer;

public class FloatNumberSum {
    public float add(final String in) {
        StringTokenizer tokens = new StringTokenizer(in, " ");
        float sum = 0;
        while (tokens.hasMoreTokens()) {
            sum += Float.parseFloat(tokens.nextToken());
        }
        return sum;
    }
}
