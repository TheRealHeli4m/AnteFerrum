package com.anteferrum.heliummod.entities.sharpened_stick;

import com.anteferrum.heliummod.AnteFerrum;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class SharpenedStickModel<T extends Entity> extends EntityModel<SharpenedStickEntityRenderState> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(AnteFerrum.MODID, "sharpened_stick"), "main");
    private final ModelPart root;

    public SharpenedStickModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(8, 16).addBox(-7.0F, -1.0F, -7.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -1.0F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-4.0F, -1.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(8, 0).addBox(-2.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(8, 8).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(8, 12).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 0).addBox(3.0F, -1.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 4).addBox(4.0F, -1.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 8).addBox(5.0F, -1.0F, 4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 4).addBox(-3.0F, -1.0F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(SharpenedStickEntityRenderState state) {
        super.setupAnim(state);
    }
}