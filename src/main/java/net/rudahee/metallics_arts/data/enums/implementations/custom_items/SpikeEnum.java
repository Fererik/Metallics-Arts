package net.rudahee.metallics_arts.data.enums.implementations.custom_items;


import net.minecraft.world.item.Item;

public enum SpikeEnum {
    IRON("iron",null,true,false),
    STEEL("steel", null,false,false),
    TIN("tin", null,false,false),
    PEWTER("pewter", null,false,false),
    COPPER("copper", null,true,false),
    BRONZE("bronze", null,false,false),
    ZINC("zinc", null,false,false),
    BRASS("brass", null,false,false),
    CHROMIUM("chromium", null,false,false),
    NICROSIL("nicrosil", null,false,false),
    ALUMINUM("aluminum", null,false,false),
    DURALUMIN("duralumin",null,false,false),
    CADMIUM("cadmium", null,false,false),
    BENDALLOY("bendalloy", null,false,false),
    ELECTRUM("electrum", null,false,false),
    GOLD("gold", null,true,false),
    ATIUM("atium", null,false,true),
    MALATIUM("malatium", null,false,true),
    LERASIUM("lerasium", null,false,true),
    ETTMETAL("ettmetal", null,false,true);

    private final String name;
    private Item spike;
    private final boolean vanilla;
    private final boolean gems;


    SpikeEnum(String name, Item spike, boolean vanilla, boolean gems) {
        this.name = name;
        this.spike = spike;
        this.vanilla = vanilla;
        this.gems = gems;
    }

    public String getName() {
        return name;
    }

    public Item getSpike() {
        return spike;
    }

    public boolean isVanilla() {
        return vanilla;
    }

    public boolean isGems() {
        return gems;
    }
    public void setSpike (Item spike){
        this.spike = spike;
    }

}
