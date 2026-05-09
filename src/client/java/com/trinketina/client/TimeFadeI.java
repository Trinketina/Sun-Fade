package com.trinketina.client;

import com.trinketina.client.config.TimeValuesConfig;

public interface TimeFadeI {
    default float setFadeFromTime(long dayTime, long fadeInStart, long fadeInEnd, long fadeOutStart, long fadeOutEnd) {
        float fade = getFade(dayTime, fadeInStart,  fadeInEnd);
        if (fade >= 0) { // Fading In
            return fade;
        }
        fade = getFade(dayTime, fadeInEnd,  fadeOutStart);
        if (fade >= 0) { // Faded In, waiting to Fade Out
            return 1.0f;
        }
        fade = getFade(dayTime, fadeOutStart,  fadeOutEnd);
        if  (fade >= 0 ) { // Fading Out
            return 1.0f - fade;
        }
        //fade = getFade(dayTime, fadeOutEnd,  fadeInStart);
        else { // Faded Out, waiting to Fade In
            return 0.0f;
        }

    }

    default float getFade(long time, long start, long end) {
        long duration = (end - start + TimeValuesConfig.DayLength) % TimeValuesConfig.DayLength;
        long elapsedTime = (time - start + TimeValuesConfig.DayLength) % TimeValuesConfig.DayLength;

        if (duration == 0) {
             return 1.0f;
        }
        if (elapsedTime > duration) {
            return -1f;
        }

        return  elapsedTime / (float) duration;
    }
}
