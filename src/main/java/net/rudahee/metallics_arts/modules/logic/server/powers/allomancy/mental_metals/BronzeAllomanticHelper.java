package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.mental_metals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Objects;

public class BronzeAllomanticHelper {

    public static void BronzeAiEntityManipulation(Player player, Level world, boolean enhanced, boolean lerasium) {
        int radius;

        if (enhanced && lerasium) {
            radius = 16;
        } else if (enhanced) {
            radius = 14;
        } else if (lerasium) {
            radius = 12;
        } else {
            radius = 8;
        }

        world.getEntitiesOfClass(Mob.class, CapabilityUtils.getBubble(player, radius)).forEach(entity -> {
            entity.goalSelector.removeGoal(Objects.requireNonNull(entity.goalSelector.getRunningGoals().findFirst().orElse(null)));
            entity.getLookControl().setLookAt(player.position().x, player.position().y, player.position().z);
            entity.getMoveControl().setWantedPosition(player.position().x-0.5F, player.position().y, player.position().z-0.5F, 1.2f);
        });
    }

}
