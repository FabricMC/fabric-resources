package net.fabricmc.resources.mixin;

import net.fabricmc.resources.hooks.IMinecraftGameHooks;
import net.minecraft.client.MinecraftGame;
import net.minecraft.client.resource.pack.IResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(MinecraftGame.class)
public class MixinMinecraftGame implements IMinecraftGameHooks {

	@Shadow
	private List<IResourcePack> defaultResourcePacks;

	public List<IResourcePack> getDefaultResourcePacks() {
		return defaultResourcePacks;
	}
}
