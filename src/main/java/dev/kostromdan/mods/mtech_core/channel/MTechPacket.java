package dev.kostromdan.mods.mtech_core.channel;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MTechPacket {
    private final int data;

    public MTechPacket(int data) {
        this.data = data;
    }

    public MTechPacket(FriendlyByteBuf buf) {
        this.data = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(data);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
        });
        context.setPacketHandled(true);
    }
}



