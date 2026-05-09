package com.trinketina.client;

import com.trinketina.client.config.FadeSkyConfig;
import com.trinketina.client.config.TimeValuesConfigData;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FadeSkyClient implements ClientModInitializer {
    public static final String MOD_ID = "sun_fade";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
        LOGGER.info("Initializing Fade Sun/Moon Client");
        AutoConfig.register(TimeValuesConfigData.class, GsonConfigSerializer::new);
        FadeSkyConfig.CONFIG = AutoConfig.getConfigHolder(TimeValuesConfigData.class).getConfig();
	}
}