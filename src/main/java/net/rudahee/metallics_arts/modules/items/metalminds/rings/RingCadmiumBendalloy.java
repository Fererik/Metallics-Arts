package net.rudahee.metallics_arts.modules.items.metalminds.rings;

import net.rudahee.metallics_arts.data.enums.implementations.MetalsNBTData;

public class RingCadmiumBendalloy extends RingsMindAbstract{
    public RingCadmiumBendalloy (Properties properties){
        super(properties, MetalsNBTData.CADMIUM,MetalsNBTData.BENDALLOY,MetalsNBTData.CADMIUM.getMaxReserveRing(),MetalsNBTData.BENDALLOY.getMaxReserveRing());
    }

}