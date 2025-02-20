package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals.AtiumFecuchemicHelper;

import java.util.function.Supplier;

public class BrassFecuchemicHelper extends AbstractFechuchemicHelper {
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Brass: Burns the player if they are in the desert by day, or in a hot biome.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#tappingPower(Player)
     */
    @Override
    public void tappingPower(Player player) {
        if (player.getLevel().getBiome(player.getOnPos()).is(Biomes.DESERT) && player.getLevel().isDay()) {
            player.setSecondsOnFire(1);
        } else if (player.getLevel().getBiome(player.getOnPos()).is(Tags.Biomes.IS_HOT)) {
            player.setSecondsOnFire(1);
        }
    }
    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, for the power of the Brass: Add frozen ticks to the player in case they are in the desert at night, or in a cold biome
     *
     * @param player to whom the effect will be applied.
     *
     * @see AbstractFechuchemicHelper#storagePower(Player)
     */
    @Override
    public void storagePower(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 1, true, false));

        if (player.getLevel().getBiome(player.getOnPos()).is(Tags.Biomes.IS_COLD) ||
                (player.getLevel().getBiome(player.getOnPos()).is(Biomes.DESERT) && player.getLevel().isNight())) {
            player.setTicksFrozen(player.getTicksFrozen() + 3);
        }
    }

    public static Supplier<? extends BrassFecuchemicHelper> getInstance() {
        return BrassFecuchemicHelper::new;
    }

    /**
     * Redefine of the method of the AbstractFechuchemicHelper class.
     * In this specific case, metalmind only charges when player is burning.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AtiumFecuchemicHelper#calculateCharge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */
    @Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.isOnFire()) {
            if (!playerCapability.isStoring(MetalTagEnum.NICROSIL) || !nicConsume) {
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }
    /**
     * This is a unique method in the helpers, which is responsible for adding a few seconds of fire to the given livingEntity
     *
     * @param livingEntity to whom the seconds of fire will be applied.
     * @param secondsFire seconds the entity will be on fire.
     */
    public static void addFireAspectToPlayer(LivingEntity livingEntity, int secondsFire){
        livingEntity.setSecondsOnFire(secondsFire);
    }
}
