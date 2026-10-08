package com.purpleleft.mod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;

public class PurpleLeftClient implements ClientModInitializer {
    public void onInitializeClient() {
        WorldRenderEvents.LAST.register((matrices, tickDelta, viewMatrix, projectionMatrix, blockOutlines, camera, gameRenderer, lightmapTextureManager, backdropTexture) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;
            
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            int halfWidth = width / 2;
            
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderColor(0.5f, 0.0f, 0.5f, 0.3f);
            
            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            buffer.vertex(0, height, 0).color(0.5f, 0.0f, 0.5f, 0.3f).next();
            buffer.vertex(halfWidth, height, 0).color(0.5f, 0.0f, 0.5f, 0.3f).next();
            buffer.vertex(halfWidth, 0, 0).color(0.5f, 0.0f, 0.5f, 0.3f).next();
            buffer.vertex(0, 0, 0).color(0.5f, 0.0f, 0.5f, 0.3f).next();
            tessellator.draw();
            
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        });
    }
}
