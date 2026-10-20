package com.anteferrum.heliummod.entities.cow_carcass;

import com.anteferrum.heliummod.entities.sharpened_stick.SharpenedStickEntityRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class CowCarcassModel<T extends Entity> extends EntityModel<CowCarcassEntityRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath("modid", "cow_carcass"), "main");
    private final ModelPart root;
    private final ModelPart legs;
    private final ModelPart torso;
    private final ModelPart head;

    public CowCarcassModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.legs = this.root.getChild("legs");
        this.torso = this.root.getChild("torso");
        this.head = this.root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 18.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(28, 22).addBox(4.0F, -6.0F, -6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-8.0F, -6.0F, -6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 38).addBox(4.0F, -6.0F, 2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 38).addBox(-8.0F, -6.0F, 2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 0.0F));

        PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-17.0F, -10.0F, -1.0F, 18.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(44, 22).addBox(-5.0F, 0.0F, 3.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 2.0F, -5.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-2.6667F, -1.6667F, -4.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(48, 36).addBox(-1.6667F, -2.6667F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 40).addBox(-1.6667F, -2.6667F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 27).addBox(-3.6667F, 3.3333F, -3.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.3333F, -8.3333F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(CowCarcassEntityRenderState renderState) {
        super.setupAnim(renderState);
    }
}
