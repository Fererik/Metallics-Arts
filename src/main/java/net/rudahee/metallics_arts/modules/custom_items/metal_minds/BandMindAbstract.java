package net.rudahee.metallics_arts.modules.custom_items.metal_minds;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.*;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.AbstractFechuchemicHelper;
import net.rudahee.metallics_arts.setup.network.ModNetwork;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;
import net.rudahee.metallics_arts.utils.CapabilityUtils;
import net.rudahee.metallics_arts.utils.MetalMindsUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class BandMindAbstract <E extends AbstractFechuchemicHelper, T extends AbstractFechuchemicHelper> extends Item implements ICurioItem {

    private final MetalTagEnum[] metals = new MetalTagEnum[2];
    public String unkeyedString = "Nobody";

    private E firstSupplier;
    private T secondSupplier;

    public BandMindAbstract(Properties properties, MetalTagEnum metal1, MetalTagEnum metal2, Supplier<? extends E> firstHelper, Supplier<? extends T> secondHelper) {
        super(properties);
        metals[0]=metal1;
        metals[1]=metal2;

        this.firstSupplier = firstHelper.get();
        this.secondSupplier = secondHelper.get();
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.getWearer();

        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
            data.setMetalMindEquiped(this.metals[0].getGroup(),true);
            data.setMetalMindEquiped(this.metals[1].getGroup(),true);
            ModNetwork.sync(data, player);
        });
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (this instanceof BandElectrumGold) {
            return;
        }
        Player player = (Player) slotContext.getWearer();
        if (stack.getItem() != newStack.getItem()) {
            player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
                data.setMetalMindEquiped(this.metals[0].getGroup(),false);
                data.setMetalMindEquiped(this.metals[1].getGroup(),false);
                data.setStoring(this.metals[0],false);
                data.setStoring(this.metals[1],false);
                data.setTapping(this.metals[0],false);
                data.setTapping(this.metals[1],false);
                ModNetwork.sync(data, player);
            });
        }
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }
    private IInvestedPlayerData cap = null;

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if(!stack.hasTag()) {
            stack.setTag(addBandTags());
        }
        Player player = (Player) slotContext.entity();
        player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(data ->{
            cap = data;
        });
        boolean canEquip = false;

        if (cap != null) {
            canEquip = !(cap.getMetalMindEquiped(this.metals[0].getGroup()));
            //canEquip = (!(cap.getMetalMindEquiped(this.metals[0].getGroup()) && !cap.getMetalMindEquiped(this.metals[1].getGroup())));
        }

        if (canEquip){
            if (!stack.getTag().getString("key").equals(unkeyedString)
                    && !player.getStringUUID().equals(stack.getTag().getString("key"))){
                canEquip = false;
            }
        }
        ICurioItem.super.canEquip(slotContext, stack);
        return canEquip;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if(!stack.hasTag()) {
            stack.setTag(addBandTags());
        }
        if (this instanceof BandLerasiumEttmetal || this instanceof BandAtiumMalatium
                || this instanceof BandCopperBronze || this instanceof BandAluminumDuralumin){
            return;
        }
        if (stack.hasTag()) {
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[0].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[0].getNameLower()+"_feruchemic_reserve") * 100)/this.metals[0].getMaxReserveBand())+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+metals[1].getNameLower()).append(": "+ ((stack.getTag().getInt(metals[1].getNameLower()+"_feruchemic_reserve") * 100)/this.metals[1].getMaxReserveBand())+"%"));
            }
            if (world != null) {
                toolTips.add(Component.translatable("metallics_arts.mental_mind.owner").append(": "+ ((stack.getTag().getString("key").equals("Nobody")) ? Component.translatable("metallics_arts.mental_mind.nobody").getString() : (world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))) == null) ? Component.translatable("metallics_arts.mental_mind.owner_someone") : world.getPlayerByUUID(UUID.fromString((stack.getTag().getString("key")))).getName().getString())));
            }
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable(" "));
                toolTips.add(Component.translatable("metallics_arts.mental_mind_translate.shift_info").withStyle(ChatFormatting.BLUE));

            }
        }
        super.appendHoverText(stack, world, toolTips, flagIn);
    }

    private CompoundTag addBandTags() {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt(this.metals[0].getNameLower()+"_feruchemic_reserve",0);
        nbt.putInt(this.metals[1].getNameLower()+"_feruchemic_reserve",0);
        nbt.putString("key",this.unkeyedString);
        return nbt;
    }


    public static CompoundTag addBandTagsFull(MetalTagEnum metal1, MetalTagEnum metal2) {
        CompoundTag nbt = new CompoundTag();

        if(metal1.equals(MetalTagEnum.ALUMINUM)){
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve",3);

        }else if(metal1.equals(MetalTagEnum.LERASIUM)){
            nbt.putInt(metal1.getNameLower() + "_feruchemic_reserve",1);
            for (MetalTagEnum metal: MetalTagEnum.values()) {
                nbt.putInt(metal.getNameLower()+"inLerasiumBand",metal.getMaxAllomanticTicksStorage());
            }
        }else {
            nbt.putInt(metal1.getNameLower()+"_feruchemic_reserve",metal1.getMaxReserveBand());

        }

        nbt.putInt(metal2.getNameLower()+"_feruchemic_reserve",metal2.getMaxReserveBand());
        nbt.putString("key","Nobody");

        return nbt;
    }

    private boolean nicConsumeMet0 = false;
    private boolean nicConsumeMet1 = false;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();

        if(!stack.hasTag()) {
            stack.setTag(addBandTags());
        }

        if (this instanceof BandAluminumDuralumin || this instanceof BandElectrumGold) {
            return;
        }

        CompoundTag compoundTag = stack.getTag();

        if (livingEntity.level instanceof ServerLevel) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;
                IInvestedPlayerData playerCapability = CapabilityUtils.getCapability(player);
                if (playerCapability.isTapping(MetalTagEnum.ALUMINUM) || playerCapability.isStoring(MetalTagEnum.ALUMINUM)){
                    stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,false,this.metals[0],this.metals[1]));
                }
                String metalKey = this.metals[0].getNameLower()+"_feruchemic_reserve";
                int actualReserve = stack.getTag().getInt(metalKey);
                int maxReserve = this.metals[0].getMaxReserveBand();
                /**
                 DECANT
                 */
                if (playerCapability.isTapping(this.metals[0])) {
                    if (actualReserve>0) {
                        stack.setTag(firstSupplier.calculateDischarge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet0));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,false,this.metals[0],this.metals[1]));
                        playerCapability.setTapping(this.metals[0],false);
                    }
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.metals[0])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,true,this.metals[0],this.metals[1]));
                        stack.setTag(firstSupplier.calculateCharge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet0));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet0 = !nicConsumeMet0;
                        }
                    } else {
                        playerCapability.setStoring(this.metals[0],false);
                    }
                }
                metalKey = this.metals[1].getNameLower()+"_feruchemic_reserve";
                actualReserve = stack.getTag().getInt(metalKey);
                maxReserve = this.metals[1].getMaxReserveBand();
                /**
                 DECANT
                 */
                if (playerCapability.isTapping(this.metals[1])) {
                    if (actualReserve>0) {
                        stack.setTag(secondSupplier.calculateDischarge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet1));
                        if (playerCapability.isTapping(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,false,this.metals[0],this.metals[1]));
                        playerCapability.setTapping(this.metals[1],false);
                    }
                    /**
                     STORAGE
                     */
                } else if (playerCapability.isStoring(this.metals[1])) {
                    if (actualReserve < maxReserve) {
                        stack.setTag(MetalMindsUtils.changeOwner(player, compoundTag,true,this.metals[0],this.metals[1]));
                        stack.setTag(secondSupplier.calculateCharge(compoundTag,player,playerCapability,actualReserve,metalKey,nicConsumeMet1));
                        if (playerCapability.isStoring(MetalTagEnum.NICROSIL)) {
                            nicConsumeMet1 = !nicConsumeMet1;
                        }
                    } else {
                        playerCapability.setStoring(this.metals[1],false);
                    }
                }
                ModNetwork.sync(playerCapability, player);
            }
        }
        ICurioItem.super.curioTick(slotContext, stack);
    }

    public E getFirstSupplier() {
        return firstSupplier;
    }

    public T getSecondSupplier() {
        return secondSupplier;
    }

    public MetalTagEnum getMetals(int pos) {
        return this.metals[pos];
    }

}
