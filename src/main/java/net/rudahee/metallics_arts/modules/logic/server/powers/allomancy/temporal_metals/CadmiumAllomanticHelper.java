package net.rudahee.metallics_arts.modules.logic.server.powers.allomancy.temporal_metals;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

public class CadmiumAllomanticHelper {
    public static void CadmiumEffectSelfPlayer(Player player, boolean enhanced) {
        if (enhanced) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 100, true, false));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 4, true, false));
        }
    }

    public static void CadmiumMobEffectsOtherPlayers(Player player, IInvestedPlayerData capability, ServerLevel level, boolean enhanced, boolean lerasium) {
        int radius = 8;
        if (enhanced && lerasium) {
            radius = 13;
        } else if (enhanced) {
            radius = 11;
        } else if (lerasium) {
            radius = 10;
        }

        level.getEntitiesOfClass(LivingEntity.class, CapabilityUtils.getBubble(player,radius)).forEach(entity -> {
            if (capability.isBurning(MetalTagEnum.LERASIUM)) {
                if (capability.getEnhanced()) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 100, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 100, true, false));
                } else {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 2, true, false));
                }

            } else {
                if (capability.getEnhanced()) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 2, true, false));
                } else {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 1, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 1, true, false));
                }
            }
        });
    }

}
