package net.rudahee.metallics_arts.setup.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.rudahee.metallics_arts.data.player.IInvestedPlayerData;
import net.rudahee.metallics_arts.setup.registries.ModBlocksRegister;

import java.util.UUID;
import java.util.function.Supplier;

public class InvestedDataPacket {

    private final CompoundTag nbt;
    private final UUID uuid;

    /**
     * Packet for sending Allomancy player data to a client
     *
     * @param data   the AllomancerCapability data for the player
     * @param player the player
     */
    public InvestedDataPacket(IInvestedPlayerData data, Player player) {
        this.uuid = player.getUUID();
        this.nbt = (data != null && ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP != null) ? data.save() : new CompoundTag();

    }

    private InvestedDataPacket(CompoundTag nbt, UUID uuid) {
        this.nbt = nbt;
        this.uuid = uuid;
    }



    public static InvestedDataPacket decode(FriendlyByteBuf buf) {
        return new InvestedDataPacket(buf.readNbt(), buf.readUUID());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(this.nbt);
        buf.writeUUID(this.uuid);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().level.getPlayerByUUID(this.uuid);

            if (player != null && ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP != null) {
                player.getCapability(ModBlocksRegister.InvestedCapabilityRegister.PLAYER_CAP).ifPresent(cap -> cap.load(this.nbt));
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
