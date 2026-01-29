package com.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Interaction para cambiar al iframe del datepicker.
 */
public class SwitchToFrame implements Interaction {

    private final Target frameTarget;

    public SwitchToFrame(Target frameTarget) {
        this.frameTarget = frameTarget;
    }

    public static SwitchToFrame on(Target frameTarget) {
        return instrumented(SwitchToFrame.class, frameTarget);
    }

    @Override
    @Step("{0} switches to frame #frameTarget")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor)
                .getDriver()
                .switchTo()
                .frame(frameTarget.resolveFor(actor));
    }
}