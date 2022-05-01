package net.rudahee.metallics_arts.modules.powers;

import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rudahee.metallics_arts.modules.data_player.InvestedCapability;
import net.rudahee.metallics_arts.modules.powers.client.ClientEventHandler;

public class MetallicsPowersSetup {

    public static void clientInit(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new PowersEventHandler());
    }


    public static void register(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    public static void register() {
        MetallicsPowersSetup.register();
    }
}
