package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.powers_utils.TeleportsUtils;

public class GoldAllomanticHelper {

    public static void teleportToDeathPos (Level level, IInvestedPlayerData capability, ServerPlayer player, boolean lerasium) {
        if (lerasium) {
            //multi tp
            TeleportsUtils.multiTeleport(player, CapabilityUtils.getBubble(player,5), level,
                    player.getLastDeathLocation().get().dimension(), player.getLastDeathLocation().get().pos());
            capability.drainMetals(MetalTagEnum.LERASIUM);
        } else {
            //tp simple
            TeleportsUtils.teleport(player, level, player.getLastDeathLocation().get().dimension(), player.getLastDeathLocation().get().pos());
        }

        capability.drainMetals(MetalTagEnum.DURALUMIN, MetalTagEnum.GOLD);
    }
}
