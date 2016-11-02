package net.fabricmc.resources.hooks;

import net.minecraft.client.resource.pack.IResourcePack;

import java.util.List;

public interface IMinecraftGameHooks {

	public List<IResourcePack> getDefaultResourcePacks();
}
