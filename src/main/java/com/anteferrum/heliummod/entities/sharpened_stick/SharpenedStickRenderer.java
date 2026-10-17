package com.anteferrum.heliummod.entities.sharpened_stick;

import com.anteferrum.heliummod.AnteFerrum;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SharpenedStickRenderer extends EntityRenderer<SharpenedStickEntity, SharpenedStickEntityRenderState> {
    private final SharpenedStickModel model;
    public SharpenedStickRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SharpenedStickModel<>(context.bakeLayer(SharpenedStickModel.LAYER_LOCATION));
    }

    @Override
    public SharpenedStickEntityRenderState createRenderState() {
        return new SharpenedStickEntityRenderState();
    }

    @Override
    public void extractRenderState(SharpenedStickEntity entity, SharpenedStickEntityRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);

        state.yaw = Mth.lerp(partialTick, entity.yRotO, entity.getYRot());
        state.pitch = Mth.lerp(partialTick, entity.xRotO, entity.getXRot());
    }

    @Override
    public void submit(SharpenedStickEntityRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.translate(0.0f, -1.25f, 0.0f);
        super.submit(state, poseStack, collector, cameraRenderState);

        collector
                .submitModel(this.model, state, poseStack, RenderTypes.entityCutout(Identifier.fromNamespaceAndPath(AnteFerrum.MODID, "textures/entity/sharpened_stick.png")), state.lightCoords, OverlayTexture.NO_OVERLAY, 0, null);

        poseStack.popPose();
    }
}
