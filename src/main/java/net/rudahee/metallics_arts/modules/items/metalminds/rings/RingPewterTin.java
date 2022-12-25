package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;

public class RingPewterTin extends RingsMindAbstract{
    public RingPewterTin(Properties properties){
        super(properties, MetalsNBTData.TIN,MetalsNBTData.PEWTER,MetalsNBTData.TIN.getMaxReserveRing(),MetalsNBTData.PEWTER.getMaxReserveRing());
    }
}
