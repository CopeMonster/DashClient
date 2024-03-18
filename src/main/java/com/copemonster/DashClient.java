package com.copemonster;

import com.google.common.eventbus.EventBus;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashClient implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("DashClient");
	public static final EventBus EVENT_BUS = new EventBus();
	public static final MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitialize() {
		LOGGER.info("=> DASH!");
	}
}