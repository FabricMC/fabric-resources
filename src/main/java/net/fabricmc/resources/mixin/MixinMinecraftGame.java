package net.fabricmc.resources.mixin;

import net.fabricmc.resources.FabricResources;
import net.minecraft.client.MinecraftGame;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(MinecraftGame.class)
public abstract class MixinMinecraftGame {
	@Shadow
	private ReloadableResourceManager resourceManager;

	@Inject(method = "reloadResources()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ReloadableResourceManager;reload(Ljava/util/List;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
	public void reloadResources(CallbackInfo info, List<ResourcePack> list) {
		list.addAll(FabricResources.getResourcePacks());
	}
}
