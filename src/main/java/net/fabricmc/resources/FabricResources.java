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

package net.fabricmc.resources;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.FabricLoader;
import net.fabricmc.loader.ModContainer;
import net.fabricmc.loader.ModInfo;
import net.minecraft.client.MinecraftGame;
import net.minecraft.resource.ResourcePack;
import net.minecraft.util.Identifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FabricResources implements ModInitializer  {

	public static final Identifier MISSING_TEX = new Identifier("minecraft", "textures/misc/unknown_server.png");

	static String createPackMetaString(ModInfo info) {
		return String.format("{ \"pack\": { \"description\": \"Dummy resource pack for %s\", \"pack_format\": 1 } }", info.getId());
	}

	@Override
	public void onInitialize() {

	}

	public static List<ResourcePack> getResourcePacks() {
		List<String> packsAdded = new ArrayList<>();
		List<ResourcePack> fabricPacks = new ArrayList<>();

		for (ModContainer mod : FabricLoader.INSTANCE.getMods()) {
			File originFile = mod.getOriginFile();
			if (!packsAdded.contains(originFile.getAbsolutePath())) {
				if (originFile.isDirectory()) {
					fabricPacks.add(new ModFolderPack(originFile, mod.getInfo()));
				} else {
					fabricPacks.add(new ModZipFilePack(originFile, mod.getInfo()));
				}
				packsAdded.add(originFile.getAbsolutePath());
			}
		}

		return fabricPacks;
	}

}
