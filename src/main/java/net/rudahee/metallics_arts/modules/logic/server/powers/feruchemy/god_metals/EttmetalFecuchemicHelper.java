package net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.god_metals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;

import java.util.function.Supplier;

public class EttmetalFecuchemicHelper extends AbstractFechuchemicHelper {

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the discharge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AtiumFecuchemicHelper#calculateDischarge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */
    @Override
    public void tappingPower(Player player) {}

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * This method is not used, because the power logic is applied in the charge methods of this class.
     *
     * @param player to whom the effect will be applied.
     *
     * @see AtiumFecuchemicHelper#calculateCharge(CompoundTag, Player, IInvestedPlayerData, int, String, boolean)
     */
    @Override
    public void storagePower(Player player) {}

    public static Supplier<? extends EttmetalFecuchemicHelper> getInstance() {
        return EttmetalFecuchemicHelper::new;
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, it generates the explosion based on the charge values.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     */
    @Override
    public CompoundTag calculateDischarge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        player.level.explode(player,player.position().x,player.position().y,player.position().z,(float) compoundTag.getInt(metalKey)/683, Explosion.BlockInteraction.NONE);
        player.setHealth((player.getHealth() - ((float) compoundTag.getInt(metalKey)/205)));
        compoundTag.putInt(metalKey,0);
        return compoundTag;
    }

    /**
     * Implementation of the abstract method of the AbstractFechuchemicHelper class.
     * In this specific case, only charge when target player take damage from explosions.
     *
     * @param compoundTag the inside information of the metalmind.
     * @param player with the mindmetal equipped.
     * @param playerCapability capabilities (data) of the player.
     * @param metalReserve present value of the metal reserve.
     * @param metalKey metal key to be modified.
     * @param nicConsume control value of whether it is necessary to store charge or not.
     * @return CompoundTag metalmind information update.
     */
    @Override
    public CompoundTag calculateCharge(CompoundTag compoundTag, Player player, IInvestedPlayerData playerCapability, int metalReserve, String metalKey, boolean nicConsume) {
        if (player.getLastDamageSource() != null){
            if ((player.getLastDamageSource().isExplosion())){
                compoundTag.putInt(metalKey, metalReserve + 1);
            }
        }
        return compoundTag;
    }
}
