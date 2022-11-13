package org.example;

public class Main {

    public static final double A = 1.65;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        double[] arguments = createArrayOfArguments(0.7, 2, 0.005);
        double[] values = createArrayOfValues(arguments);
        printResult(arguments, values);
        printMaxValue(values);
        printMinValue(values);
        System.out.printf("> Sum of values array: %.3f\n> Average value: %.3f\n",
                calculateSumOfValues(values), calculateAverageOfValues(values));
    }

    public static double calculateFunction(double x) {
        if(x < 1.3) {
            return Math.PI * Math.pow(x, 2) - 7 / Math.pow(x, 2);
        } else if(x == 1.3) {
            return A * Math.pow(x, 3) + 7 * Math.sqrt(x);
        } else {
            return Math.log(x + 7 * Math.sqrt(Math.abs(x + A)));
        }
    }

    public static int countNumberOfSteps(double start, double end, double delta) {
        return (int)((end - start) / delta) + 1;
    }

    public static double[] createArrayOfArguments(double start, double end, double delta) {
        double[] arguments = new double[countNumberOfSteps(start, end, delta)];
        for(int i = 0; i < arguments.length; i++) {
            arguments[i] = start;
            start += delta;
        }
        return arguments;
    }

    public static double[] createArrayOfValues(double[] arguments) {
        double[] valuesArray = new double[arguments.length];
        for(int i = 0; i < valuesArray.length; i++) {
            valuesArray[i] = calculateFunction(arguments[i]);
        }
        return valuesArray;
    }

    public static int findIndexOfMaxValue(double[] values) {
        int maxIndex = 0;
        for(int i = 1; i < values.length; i++) {
            if(values[i] > values[i - 1]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int findIndexOfMinValue(double[] values) {
        int minIndex = 0;
        for(int i = 1; i < values.length; i++) {
            if(values[i] < values[i - 1]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static double calculateSumOfValues(double[] values) {
        double sum = 0;
        for(double item: values) {
            sum += item;
        }
        return sum;
    }

    public static double calculateAverageOfValues(double[] values) {
        return calculateSumOfValues(values) / values.length;
    }

    public static void printMaxValue(double[] values) {
        double maxValue = values[findIndexOfMaxValue(values)];
        System.out.printf("> Maximum value: %.3f | Index: %d\n", maxValue, findIndexOfMaxValue(values));
    }

    public static void printMinValue(double[] values) {
        double minValue = values[findIndexOfMinValue(values)];
        System.out.printf("> Minimum value: %.3f | Index: %d\n", minValue, findIndexOfMinValue(values));
    }

    public static void printArray(double[] array) {
        for(double item: array) {
            System.out.printf("%.3f\t", item);
        }
    }

    public static void printResult(double[] arguments, double[] values) {
        for(int i = 0; i < arguments.length; i++) {
            System.out.printf("%d. x = %.3f | y = %.3f\n", i + 1, arguments[i], values[i]);
        }
    }
}