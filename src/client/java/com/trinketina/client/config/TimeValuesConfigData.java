package com.trinketina.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name="sun_fade")
public class TimeValuesConfigData implements ConfigData {
    @ConfigEntry.Gui.PrefixText()
    public boolean FadeSun = true;
    public boolean FadeMoon = true;

    @ConfigEntry.Gui.CollapsibleObject()
    @ConfigEntry.Gui.Tooltip()
    public FadeData SunFadeData = new FadeData(22000, 23000, 13000, 14000);

    @ConfigEntry.Gui.CollapsibleObject()
    @ConfigEntry.Gui.Tooltip()
    public FadeData MoonFadeData = new FadeData(13000, 14000, 22000, 23000);

    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.Tooltip()
    public long DayLength = 24000;

    public static class FadeData {
        public long FadeInStart;
        public long FadeInEnd;
        public long FadeOutStart;
        public long FadeOutEnd;

        FadeData(long fadeInStart, long fadeInEnd, long fadeOutStart, long fadeOutEnd) {
            FadeInStart = fadeInStart;
            FadeInEnd = fadeInEnd;
            FadeOutStart = fadeOutStart;
            FadeOutEnd = fadeOutEnd;
        }
    }

    @Override
    public void validatePostLoad() throws ValidationException {
        SunFadeData.FadeInStart = Math.clamp(SunFadeData.FadeInStart, 0, DayLength);
        SunFadeData.FadeInEnd = Math.clamp(SunFadeData.FadeInEnd, 0, DayLength);
        SunFadeData.FadeOutStart = Math.clamp(SunFadeData.FadeOutStart, 0, DayLength);
        SunFadeData.FadeOutEnd = Math.clamp(SunFadeData.FadeOutEnd, 0, DayLength);

        MoonFadeData.FadeInStart = Math.clamp(MoonFadeData.FadeInStart, 0, DayLength);
        MoonFadeData.FadeInEnd = Math.clamp(MoonFadeData.FadeInEnd, 0, DayLength);
        MoonFadeData.FadeOutStart = Math.clamp(MoonFadeData.FadeOutStart, 0, DayLength);
        MoonFadeData.FadeOutEnd = Math.clamp(MoonFadeData.FadeOutEnd, 0, DayLength);

        ConfigData.super.validatePostLoad();
    }
}
