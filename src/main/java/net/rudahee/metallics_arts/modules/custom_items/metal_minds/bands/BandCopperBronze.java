package net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.BandMindAbstract;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.BronzeFecuchemicHelper;
import net.rudahee.metallics_arts.modules.logic.server.powers.feruchemy.cognitive_metals.CopperFecuchemicHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BandCopperBronze extends BandMindAbstract <CopperFecuchemicHelper, BronzeFecuchemicHelper> {

    public BandCopperBronze(Item.Properties properties){
        super(properties, MetalTagEnum.COPPER, MetalTagEnum.BRONZE, CopperFecuchemicHelper.getInstance(), BronzeFecuchemicHelper.getInstance());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTips, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            if (!Screen.hasShiftDown()){
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") +" pts xp"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") / 20 + "s"));
            } else {
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(0).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(0).getNameLower()+"_feruchemic_reserve") * 100)/this.getMetals(0).getMaxReserveBand())+"%"));
                toolTips.add(Component.translatable("metallics_arts.metal_translate."+getMetals(1).getNameLower()).append(": "+ ((stack.getTag().getInt(getMetals(1).getNameLower()+"_feruchemic_reserve") * 100)/this.getMetals(1).getMaxReserveBand())+"%"));
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


}