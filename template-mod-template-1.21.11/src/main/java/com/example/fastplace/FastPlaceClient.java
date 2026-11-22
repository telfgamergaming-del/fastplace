package com.example.fastplace;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class FastPlaceClient implements ClientModInitializer {
    public static boolean enabled = true;
    /** Ticks between placements while holding right click. 4 = vanilla, 0 = fastest. */
    public static int cooldownTicks = 0;

    private static KeyBinding toggleKey, slowerKey, fasterKey;

    @Override
    public void onInitializeClient() {
        KeyBinding.Category cat = KeyBinding.Category.create(Identifier.of("fastplace", "main"));
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fastplace.toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, cat));
        slowerKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fastplace.slower", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_BRACKET, cat));
        fasterKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fastplace.faster", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_BRACKET, cat));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                enabled = !enabled;
                msg(client, "Fast Place: " + (enabled ? "ON" : "OFF"));
            }
            while (slowerKey.wasPressed()) {
                cooldownTicks = Math.min(4, cooldownTicks + 1);
                msg(client, "Fast Place delay: " + cooldownTicks + " ticks");
            }
            while (fasterKey.wasPressed()) {
                cooldownTicks = Math.max(0, cooldownTicks - 1);
                msg(client, "Fast Place delay: " + cooldownTicks + " ticks");
            }
        });
    }

    private static void msg(net.minecraft.client.MinecraftClient c, String s) {
        if (c.player != null) c.player.sendMessage(Text.literal(s), true);
    }
}
