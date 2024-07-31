package models;

import java.util.Objects;

public class Calculator {
    private final String hours;
    private final String minuts;
    private final String seconds;

    private Calculator(CalculatorBuilder calculatorBuilder) {
        this.hours = calculatorBuilder.hours;
        this.minuts = calculatorBuilder.minuts;
        this.seconds = calculatorBuilder.seconds;
    }

    public String getHours() {
        return hours;
    }


    public String getMinuts() {
        return minuts;
    }


    public String getSeconds() {
        return seconds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return Objects.equals(hours, that.hours) && Objects.equals(minuts, that.minuts) && Objects.equals(seconds, that.seconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minuts, seconds);
    }

    public static class CalculatorBuilder {
        private String hours;
        private String minuts;
        private String seconds;

        public CalculatorBuilder setHours(String hours) {
            this.hours = hours;
            return this;
        }

        public CalculatorBuilder setMinuts(String minuts) {
            this.minuts = minuts;
            return this;
        }

        public CalculatorBuilder setSeconds(String seconds) {
            this.seconds = seconds;
            return this;
        }

        public Calculator build() {
            return new Calculator(this);
        }
    }

}
