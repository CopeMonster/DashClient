package com.copemonster.mixins;

import com.copemonster.DashClient;
import com.copemonster.events.IO.KeyEvent;
import com.copemonster.impl.commands.Commands;
import com.copemonster.impl.commands.client.BindCommand;
import com.copemonster.impl.modules.Modules;
import com.copemonster.utils.IO.KeyAction;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.copemonster.DashClient.mc;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at=@At("HEAD"),cancellable = true)
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci){
        if (key == GLFW_KEY_UNKNOWN) return;

        KeyEvent keyEvent = new KeyEvent(key, KeyAction.get(action));
        DashClient.EVENT_BUS.post(keyEvent);

        if (keyEvent.isCancelled()) ci.cancel();

        BindCommand bindCommand = (BindCommand) Commands.getCommand("BindCommand");
        if (bindCommand == null) return;

        if (!BindCommand.waiting){
            Modules.getModules().forEach(module -> {
                if (module.getKeyBind().getKey() == key &&
                        mc.currentScreen == null &&
                        keyEvent.action == KeyAction.PRESS)
                    module.toggle();
            });
        }
        else if (keyEvent.action == KeyAction.PRESS){
            ci.cancel();

            BindCommand.waiting = false;

            if (key == GLFW_KEY_ESCAPE){
                BindCommand.waitingModule.getKeyBind().reset();
                return;
            }

            BindCommand.waitingModule.getKeyBind().setKey(key);
        }
    }
}