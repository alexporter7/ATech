package com.alexp777.atech.screen.metalforge;

import com.alexp777.atech.ATech;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SteelForgeScreen extends AbstractContainerScreen<SteelForgeMenu> {

	//Degrees Label
	private final float DEG_LABEL_X = 114;
	private final float DEG_LABEL_Y = 16;
	//Cooldown Factor Label
	private final float CF_LABEL_X = 114;
	private final float CF_LABEL_Y = 25;
	//Heat Ticks Label
	private final float HEAT_TICK_LABEL_X = 114;
	private final float HEAT_TICK_LABEL_Y = 34;
	//Progress Label
	private final float PROG_LABEL_X = 114;
	private final float PROG_LABEL_Y = 43;

	private final int COLOR = -12829636;
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(ATech.MODID, "textures/gui/steel_forge.png");
	public SteelForgeScreen(SteelForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);

	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);

		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);


		renderLabels(pPoseStack, pMouseX, pMouseY);
	}

	@Override
	protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
		super.renderLabels(pPoseStack, pMouseX, pMouseY);
		//this.font.draw(pPoseStack, String.valueOf(menu.getTemperature()), (float)100, (float)20, 4210752);

		this.font.draw(pPoseStack, menu.getTemperature() + " \u00B0C", 	//Temperature
				DEG_LABEL_X, DEG_LABEL_Y, COLOR);
		this.font.draw(pPoseStack, "CF: " + menu.getCooldownFactor(), 	//Cooldown Factor
				CF_LABEL_X, CF_LABEL_Y, COLOR);
		this.font.draw(pPoseStack, "HT: " + menu.getHeatTicks(), 			//Heat Ticks
				HEAT_TICK_LABEL_X, HEAT_TICK_LABEL_Y, COLOR);
		this.font.draw(pPoseStack, menu.getMaxProgress() + " T", 								//Progress
				PROG_LABEL_X, PROG_LABEL_Y, COLOR);
	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pPoseStack, pMouseX, pMouseY);

	}
}
