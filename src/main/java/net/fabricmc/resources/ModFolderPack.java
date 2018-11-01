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

import net.fabricmc.loader.ModInfo;
import net.minecraft.resource.DirectoryResourcePack;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ModFolderPack extends DirectoryResourcePack {

	private final ModInfo info;

	public ModFolderPack(File f, ModInfo info) {
		super(f);
		this.info = info;
	}

	@Override
	public InputStream openFilename(String name) throws IOException {
		try {
			return super.openFilename(name);
		} catch (IOException e) {
			if ("pack.mcmeta".equals(name)) {
				return new ByteArrayInputStream(FabricResources.createPackMetaString(info).getBytes());
			} else {
				throw e;
			}
		}
	}

	/* @Override
	public BufferedImage getIcon() throws IOException {
		return ImageIO.read(open(FabricResources.MISSING_TEX));
	} */

}
