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

import net.minecraft.client.MinecraftGame;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends Gui {
	private static final Identifier TEX = new Identifier("resourcestest", "test.png");

	@Inject(method = "draw(IIF)V", at = @At("RETURN"))
	public void draw(int mouseX, int mouseY, float delta, CallbackInfo info) {
		MinecraftGame.getInstance().getTextureManager().bindTexture(TEX);
		drawTexturedRect(0, 0, 0, 0, 50, 50);
	}
}
