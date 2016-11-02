/*
 * Copyright 2016 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
