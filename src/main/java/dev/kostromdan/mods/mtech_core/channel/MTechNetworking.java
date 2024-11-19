package dev.kostromdan.mods.mtech_core.channel;

import dev.kostromdan.mods.mtech_core.MTechCore;
import dev.kostromdan.mods.mtech_core.utils.ModVersionUtil;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;

public class MTechNetworking {
    private static final String PROTOCOL_VERSION = ModVersionUtil.getModVersion(MTechCore.MODID);
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MTechCore.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private static int packetId = 0;

    private static int nextPacketId() {
        return packetId++;
    }

    public static void registerMessages() {
        CHANNEL.registerMessage(nextPacketId(),
                MTechPacket.class,
                MTechPacket::encode,
                MTechPacket::new,
                MTechPacket::handle
        );
    }
}