package com.trinketina.client.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name="sun_fade")
public class TimeValuesConfig {
    @ConfigEntry.Gui.PrefixText()
    public static boolean FadeSun = true;
    public static boolean FadeMoon = true;

    @ConfigEntry.Gui.PrefixText()
    public static long SunFadeInStart = 22000;
    public static long SunFadeInEnd = 23000;
    public static long SunFadeOutStart = 13000;
    public static long SunFadeOutEnd = 14000;

    @ConfigEntry.Gui.PrefixText()
    public static long MoonFadeInStart = 13000;
    public static long MoonFadeInEnd = 14000;
    public static long MoonFadeOutStart = 22000;
    public static long MoonFadeOutEnd = 23000;

    @ConfigEntry.Gui.PrefixText()
    public static long DayLength = 24000;
}
