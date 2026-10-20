package com.anteferrum.heliummod.entities.cow_carcass;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.entities.sharpened_stick.SharpenedStickModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CowCarcassRenderer extends EntityRenderer<CowCarcassEntity, CowCarcassEntityRenderState> {
    private final CowCarcassModel model;
    public CowCarcassRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new CowCarcassModel(context.bakeLayer(CowCarcassModel.LAYER_LOCATION));
    }

    @Override
    public CowCarcassEntityRenderState createRenderState() {
        return new CowCarcassEntityRenderState();
    }

    @Override
    public void extractRenderState(CowCarcassEntity entity, CowCarcassEntityRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }

    @Override
    public void submit(CowCarcassEntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        poseStack.pushPose();
        poseStack.translate(0.0f, -0.75f, 0.0f);
        super.submit(renderState, poseStack, collector, cameraState);

        collector.submitModel(this.model, renderState, poseStack, RenderTypes.entityCutout(Identifier.fromNamespaceAndPath(AnteFerrum.MODID, "textures/entity/cow.png")), renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0, null);

        poseStack.popPose();
    }
}
