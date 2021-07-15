package net.silentchaos512.gear.block.press;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.silentchaos512.gear.SilentGear;

public class MetalPressScreen extends ContainerScreen<MetalPressContainer> {
    public static final ResourceLocation TEXTURE = SilentGear.getId("textures/gui/metal_press.png");

    public MetalPressScreen(MetalPressContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (minecraft == null) return;

        RenderSystem.color4f(1, 1, 1, 1);
        minecraft.getTextureManager().bind(TEXTURE);

        int posX = (this.width - this.imageWidth) / 2;
        int posY = (this.height - this.imageHeight) / 2;
        blit(matrixStack, posX, posY, 0, 0, this.imageWidth, this.imageHeight);

        // Progress arrow
        blit(matrixStack, posX + 79, posY + 35, 176, 14, menu.getProgressArrowScale() + 1, 16);
    }
}
