package com.copemonster.events.network;

import com.copemonster.events.Event;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.packet.Packet;

@Setter
@Getter
public class PacketEvent extends Event {
    private Packet<?> packet;

    public static class Receive extends PacketEvent {
        public Receive(Packet<?> packet) {
            this.setCancelled(false);
            this.setPacket(packet);
        }
    }

    public static class Send extends PacketEvent {
        public Send(Packet<?> packet) {
            this.setCancelled(false);
            this.setPacket(packet);
        }
    }

    public static class Sent extends PacketEvent {
        public Sent(Packet<?> packet) {
            this.setPacket(packet);
        }
    }
}
