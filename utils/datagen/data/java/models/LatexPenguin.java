// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class LatexPenguin<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "latexpenguin"), "main");
	private final ModelPart RightLeg;
	private final ModelPart RightPad;
	private final ModelPart LeftLeg;
	private final ModelPart LeftPad;
	private final ModelPart Head;
	private final ModelPart Torso;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;

	public LatexPenguin(ModelPart root) {
		this.RightLeg = root.getChild("RightLeg");
		this.RightPad = this.RightLeg.getChild("RightPad");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftPad = this.LeftLeg.getChild("LeftPad");
		this.Head = root.getChild("Head");
		this.Torso = root.getChild("Torso");
		this.Tail = this.Torso.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 19.85F, 0.5F));

		PartDefinition RightArch_r1 = RightLeg.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(42, 73).addBox(-2.0F, -0.8F, -1.3628F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.7F, -0.525F, -0.2618F, 0.0F, 0.0F));

		PartDefinition RightPad = RightLeg.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(0, 84).addBox(-0.5F, -1.0F, -3.25F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.025F))
		.texOffs(72, 52).addBox(-1.5F, -1.0F, -1.3F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.025F)), PartPose.offset(0.0F, 3.15F, -1.375F));

		PartDefinition RightPad_r1 = RightPad.addOrReplaceChild("RightPad_r1", CubeListBuilder.create().texOffs(27, 82).addBox(-0.5F, -2.5F, -4.75F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.026F)), PartPose.offsetAndRotation(0.0F, 1.5F, 1.475F, 0.0F, -0.3927F, 0.0F));

		PartDefinition RightPad_r2 = RightPad.addOrReplaceChild("RightPad_r2", CubeListBuilder.create().texOffs(18, 82).addBox(-0.5F, -2.5F, -4.75F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.026F)), PartPose.offsetAndRotation(0.0F, 1.5F, 1.475F, 0.0F, 0.3927F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 19.85F, 0.5F));

		PartDefinition LeftArch_r1 = LeftLeg.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(60, 67).addBox(-1.0F, -0.8F, -1.3628F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.7F, -0.525F, -0.2618F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftLeg.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(87, 64).addBox(-0.5F, -1.0F, -3.25F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.025F))
		.texOffs(78, 21).addBox(-1.5F, -1.0F, -1.3F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.025F)), PartPose.offset(0.0F, 3.15F, -1.375F));

		PartDefinition LeftPad_r1 = LeftPad.addOrReplaceChild("LeftPad_r1", CubeListBuilder.create().texOffs(87, 34).addBox(-0.5F, -2.5F, -4.75F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.026F)), PartPose.offsetAndRotation(0.0F, 1.5F, 1.475F, 0.0F, -0.3927F, 0.0F));

		PartDefinition LeftPad_r2 = LeftPad.addOrReplaceChild("LeftPad_r2", CubeListBuilder.create().texOffs(87, 28).addBox(-0.5F, -2.5F, -4.75F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.026F)), PartPose.offsetAndRotation(0.0F, 1.5F, 1.475F, 0.0F, 0.3927F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Beak_r1 = Head.addOrReplaceChild("Beak_r1", CubeListBuilder.create().texOffs(72, 45).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -4.75F, 0.0F, -0.7854F, 0.0F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(20, 67).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(49, 15).addBox(-4.5F, 1.0F, -2.5F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-5.0F, 2.0F, -3.0F, 10.0F, 13.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(39, 23).addBox(-4.5F, 15.0F, -2.5F, 9.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(54, 34).addBox(-4.0F, 18.0F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 10.5F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(39, 42).addBox(-4.0F, 0.75F, -0.5F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.9635F, 0.0F, 3.1416F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 6.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(14, 35).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 6.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}