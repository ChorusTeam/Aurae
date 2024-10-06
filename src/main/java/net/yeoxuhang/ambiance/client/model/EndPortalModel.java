package net.yeoxuhang.ambiance.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.yeoxuhang.ambiance.Ambiance;

public class EndPortalModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Ambiance.MOD_ID, "endPortal"), "main");
	public final ModelPart root;
	private final ModelPart endPortal;

	public EndPortalModel(ModelPart modelPart) {
		this.root = modelPart;
		this.endPortal = root.getChild("endPortal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition endPortal = partdefinition.addOrReplaceChild("endPortal", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -16.0F, -24.0F, 48.0F, 16.0F, 48.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 192, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int k) {
		root.render(poseStack, vertexConsumer, i, j);
	}
}