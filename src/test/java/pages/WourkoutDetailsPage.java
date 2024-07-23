package pages;

import models.AddWorkout;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class WourkoutDetailsPage extends BasePage {
    private static final String UPDATE_WORKOUT = ".dropdown-toggle";
    private static final String SAVE_UPDATE_WORKOUT = "#saveButton";

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
        $("#WorkoutTime").setValue(editWorkout.getTimeOfDay());
        $("#Name").setValue(editWorkout.getName());
        $("#Desc").setValue(editWorkout.getDescription());
        $("#PlannedWorkout").click();
        $("#PDistance").setValue(editWorkout.getpDistance());
        $("#PDistType").selectOption(editWorkout.getpDistanceType());
        $("#PDuration").setValue(editWorkout.getpDuration());

        $("#Distance").setValue(editWorkout.getDistance());
        $("#DistType").selectOption(editWorkout.getDistanceType());
        $("#Duration").setValue(editWorkout.getDuration());
        $("#PaceType").selectOption(editWorkout.getPaceType());
        $("#PerEffort").selectOption(editWorkout.getPerEffort());
        $("#hf_good").click();
        $("#kCal").setValue(editWorkout.getkCal());
        $("#SaveLibrary").click();

    }

}
