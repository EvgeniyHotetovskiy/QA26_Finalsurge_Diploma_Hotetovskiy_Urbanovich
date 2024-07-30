package pages;

import models.AddWorkout;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class WourkoutDetailsPage extends BasePage {
    private static final String UPDATE_WORKOUT = ".dropdown-toggle";
    private static final String SAVE_UPDATE_WORKOUT = "#saveButton";
    private static final By WORKOUT_NAME = By.xpath(".//div[@class='formSep']//div[3]");
    private static final String WORKOUT_TIME = "#WorkoutTime";
    private static final String NAME = "#Name";
    private static final String DESC = "#Desc";
    private static final String PLANNED_WORKOUT = "#PlannedWorkout";
    private static final String PDISTANCE = "#PDistance";
    private static final String PDISTTYPE = "#PDistType";
    private static final String PDURATION = "#PDuration";
    private static final String DISTANCE = "#Distance";
    private static final String DISTTYPE = "#DistType";
    private static final String DURATION = "#Duration";
    private static final String PACE_TYPE = "#PaceType";
    private static final String PER_EFFORT = "#PerEffort";
    private static final String HR_GOOD = "#hf_good";
    private static final String KCAL = "#kCal";
    private static final String SAVE_LIBRARY = "#SaveLibrary";

    @Override
    public void isOpen() {
        $(UPDATE_WORKOUT).shouldBe(clickable);
    }

    public void clickUpdateDWourkout() {
        $(UPDATE_WORKOUT).click();
    }

    public void clickSaveUpdateDWourkout() {
        $(SAVE_UPDATE_WORKOUT).click();
    }

    public void editWourkout(AddWorkout editWorkout) {
        $(WORKOUT_TIME).setValue(editWorkout.getTimeOfDay());
        $(NAME).setValue(editWorkout.getName());
        $(DESC).setValue(editWorkout.getDescription());
        $(PLANNED_WORKOUT).click();
        $(PDISTANCE).setValue(editWorkout.getpDistance());
        $(PDISTTYPE).selectOption(editWorkout.getpDistanceType());
        $(PDURATION).setValue(editWorkout.getpDuration());
        $(DISTANCE).setValue(editWorkout.getDistance());
        $(DISTTYPE).selectOption(editWorkout.getDistanceType());
        $(DURATION).setValue(editWorkout.getDuration());
        $(PACE_TYPE).selectOption(editWorkout.getPaceType());
        $(PER_EFFORT).selectOption(editWorkout.getPerEffort());
        $(HR_GOOD).click();
        $(KCAL).setValue(editWorkout.getkCal());
        $(SAVE_LIBRARY).click();
    }

    public String EditWourkoutisDisplayed() {
        return $(WORKOUT_NAME).getText();
    }

}
