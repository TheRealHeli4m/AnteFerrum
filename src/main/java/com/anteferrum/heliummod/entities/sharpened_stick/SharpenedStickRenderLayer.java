package com.anteferrum.heliummod.entities.sharpened_stick;

import com.anteferrum.heliummod.AnteFerrum;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class SharpenedStickRenderLayer extends RenderLayer {
    private final SharpenedStickModel model;

    public SharpenedStickRenderLayer(RenderLayerParent renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new SharpenedStickModel(modelSet.bakeLayer(SharpenedStickModel.LAYER_LOCATION));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, EntityRenderState entityRenderState, float v, float v1) {
        submitNodeCollector
                .order(1)
                .submitModel(this.model, entityRenderState, poseStack, RenderTypes.entityCutout(Identifier.fromNamespaceAndPath(AnteFerrum.MODID, "textures/entity/sharpened_stick.png")), entityRenderState.lightCoords, OverlayTexture.NO_OVERLAY, 0, null);
    }
}
