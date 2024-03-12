package com.atmx.AtomixMod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec2f;
import org.joml.Matrix4f;


public class atmxModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {

            Vec2f pos = new Vec2f(20, 405);
            Vec2f size = new Vec2f(40, 40);
            int x = (int) pos.x;
            int y = (int) pos.y;
            int sx = (int) size.x;
            int sy = (int) size.y;
            int hsx = sx / 2;
            int hsy = sy / 2;

            MatrixStack matrixStack = drawContext;
            matrixStack.push();

            matrixStack.translate(x, y, 0);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((System.currentTimeMillis() % 5000) / 5000f * 360f));
            matrixStack.translate(-x, -y, 0);

            Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
            buffer.vertex(positionMatrix, x - hsx, y - hsy, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
            buffer.vertex(positionMatrix, x - hsx, y + hsy, 0).color(1f, 0f, 0f, 1f).texture(0f, 1f).next();
            buffer.vertex(positionMatrix, x + hsx, y + hsy, 0).color(0f, 1f, 0f, 1f).texture(1f, 1f).next();
            buffer.vertex(positionMatrix, x + hsx, y - hsy, 0).color(0f, 0f, 1f, 1f).texture(1f, 0f).next();

            RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
            RenderSystem.setShaderTexture(0, new Identifier(atmxMod.MOD_ID, "icon.png"));
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            tessellator.draw();
            matrixStack.pop();
        });
    }
}