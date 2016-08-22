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

import net.fabricmc.api.Hook;
import net.fabricmc.base.Fabric;
import net.fabricmc.base.loader.Init;
import net.fabricmc.base.loader.Loader;
import net.fabricmc.base.loader.ModContainer;
import net.fabricmc.base.loader.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Identifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FabricResources {

	public static final Identifier MISSING_TEX = new Identifier("minecraft", "textures/misc/unknown_server.png");

	@Init
	public void init() {
		Fabric.getLoadingBus().subscribe(this);
	}

	@Hook(name = "net.fabricmc.fabric-resources:onModsInitialized", before = {}, after = "fabric:modsInitialized")
	public void onModsInitialized() {
		List<String> packsAdded = new ArrayList<>();
		Minecraft mc = Minecraft.getInstance();

		for (ModContainer mod : Loader.INSTANCE.getMods()) {
			File originFile = mod.getOriginFile();
			if (!packsAdded.contains(originFile.getAbsolutePath())) {
				if (originFile.isDirectory()) {
					mc.defaultResourcePacks.add(new ModFolderPack(originFile, mod.getInfo()));
				} else {
					mc.defaultResourcePacks.add(new ModFilePack(originFile, mod.getInfo()));
				}
				packsAdded.add(originFile.getAbsolutePath());
			}
		}
	}

	static String createPackMetaString(ModInfo info) {
		return String.format("{ \"pack\": { \"description\": \"Dummy resource pack for %s.%s\", \"pack_format\": 1 } }", info.getGroup(), info.getId());
	}

}
