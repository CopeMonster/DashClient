package com.copemonster.mixins;

import com.copemonster.DashClient;
import com.copemonster.events.network.PacketEvent;
import com.copemonster.events.player.SendMessageEvent;
import com.copemonster.impl.commands.Commands;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void onPacket(Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof ChatMessageC2SPacket chatMessageC2SPacket) {
            String message = chatMessageC2SPacket.chatMessage();

            if (message.startsWith(Commands.getCommandPrefix())) {
                Commands.executeCommand(message.substring(Commands.getCommandPrefix().length()));
                ci.cancel();
            } else {
                SendMessageEvent sendMessageEvent = new SendMessageEvent(message);
                DashClient.EVENT_BUS.post(sendMessageEvent);
            }
        }
    }

    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo info) {
        PacketEvent.Receive receive = new PacketEvent.Receive(packet);
        DashClient.EVENT_BUS.post(receive);

        if (receive.isCancelled()) info.cancel();
    }

    @Inject(at = @At("HEAD"), method = "send(Lnet/minecraft/network/packet/Packet;)V", cancellable = true)
    private void onSendPacketHead(Packet<?> packet, CallbackInfo info) {
        PacketEvent.Send send = new PacketEvent.Send(packet);
        DashClient.EVENT_BUS.post(send);

        if (send.isCancelled()) info.cancel();
    }

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("TAIL"))
    private void onSendPacketTail(Packet<?> packet, CallbackInfo info) {
        DashClient.EVENT_BUS.post(new PacketEvent.Sent(packet));
    }
}