package net.rudahee.metallics_arts.modules.items.metal_spike;

import net.minecraft.nbt.CompoundNBT;

public class GoldSpike extends MetalSpikeAbstract{
    CompoundNBT nbt = new CompoundNBT();
    public GoldSpike(Properties properties) {
        super(properties);
        nbt.putBoolean("feruchemy", false);
        nbt.putBoolean("allomancy", false);
        setNbt(nbt);
    }
}
