package tests;

import models.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CalculatorTests extends BaseTest {
    @Test(groups = "withSuccessLogin")
    public void positiveIntensityTest() {

        Calculator intensityCalc = new Calculator.CalculatorBuilder()
                .setHours("00")
                .setMinuts("20")
                .setSeconds("10")
                .build();
        dashboardPage.clickCalculator();
        calculatorPage.isOpen();
        calculatorPage.selectEvent();
        calculatorPage.inputIntencityCalcTime(intensityCalc);
        calculatorPage.clickCalcPaces();
        Assert.assertTrue(calculatorPage.workoutSplitdisplayed());
    }

    @Test(groups = "withSuccessLogin")
    public void negativeIntensityTest() {

        Calculator intensityCalc = new Calculator.CalculatorBuilder()
                .setMinuts("20")
                .build();
        dashboardPage.clickCalculator();
        calculatorPage.isOpen();
        calculatorPage.selectEvent();
        calculatorPage.inputIntencityCalcTime(intensityCalc);
        calculatorPage.clickCalcPaces();
        Assert.assertTrue(calculatorPage.intencityCalcError());
    }

    @Test(groups = "withSuccessLogin")
    public void positiveTinmanTest() {
        Calculator intensityCalc = new Calculator.CalculatorBuilder()
                .setHours("00")
                .setMinuts("20")
                .setSeconds("10")
                .build();
        dashboardPage.clickCalculator();
        calculatorPage.isOpen();
        calculatorPage.clickTinmanCalc();
        calculatorPage.selectTinmanRaceDistance();
        calculatorPage.inputIntencityCalcTime(intensityCalc);
        calculatorPage.clickGenderButton();
        calculatorPage.clickCalcPaces();
        Assert.assertTrue(calculatorPage.workoutSplitdisplayed());
    }
}
