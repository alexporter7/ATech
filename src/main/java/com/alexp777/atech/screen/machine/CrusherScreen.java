package com.alexp777.atech.screen.machine;

import com.alexp777.atech.ATech;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrusherScreen extends AbstractContainerScreen<CrusherMenu> {

	//Degrees Label
	private final float RECIPE_LABEL_X = 75;
	private final float RECIPE_LABEL_Y = 7;

	private final float PROGRESS_LABEL_X = 75;
	private final float PROGRESS_LABEL_Y = 16;

	private final float MAX_PROGRESS_LABEL_X = 75;
	private final float MAX_PROGRESS_LABEL_Y = 25;

	private final float MODIFIER_LABEL_X = 75;
	private final float MODIFIER_LABEL_Y = 34;

	private final float TIER_LABEL_X = 75;
	private final float TIER_LABEL_Y = 43;

	private final int COLOR = -12829636;
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(ATech.MODID, "textures/gui/crusher.png");

	/*
	======= Constructor =======
	 */
	public CrusherScreen(CrusherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	//	@Override
	//	protected void init() {
	//		super.init();
	//	}

	/*
	 * ======= Render the Background =======
	 */
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

	/*
	 * ======= Render Labels (Progress, MaxProgress, Modifier) =======
	 */

	@Override
	protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
		super.renderLabels(pPoseStack, pMouseX, pMouseY);
		this.font.draw(pPoseStack, "Recipe: " + (menu.getHasRecipe() == 1 ? "true" : "false"), 	//Has Recipe
				RECIPE_LABEL_X, RECIPE_LABEL_Y, COLOR);
		this.font.draw(pPoseStack, "Progress: " + menu.getProgress(), 							//Progress
				PROGRESS_LABEL_X, PROGRESS_LABEL_Y, COLOR);
		this.font.draw(pPoseStack, "mProg: " + menu.getMaxProgress(), 							//Max Progress
				MAX_PROGRESS_LABEL_X, MAX_PROGRESS_LABEL_Y, COLOR);
		this.font.draw(pPoseStack, "Modifier: " + menu.getModifier(), 							//Modifier
				MODIFIER_LABEL_X, MODIFIER_LABEL_Y, COLOR);
		this.font.draw(pPoseStack, "Tier: " + menu.getRecipeTier(), 								//Tier
				TIER_LABEL_X, TIER_LABEL_Y, COLOR);
	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pPoseStack, pMouseX, pMouseY);
	}
}
