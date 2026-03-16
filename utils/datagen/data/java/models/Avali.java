// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Avali<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "avali"), "main");
	private final ModelPart Head;
	private final ModelPart RightUpperEars;
	private final ModelPart LeftUpperEars;
	private final ModelPart RightLowerEar;
	private final ModelPart LeftLowerEars;
	private final ModelPart Torso;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart TailSecondary;
	private final ModelPart TailTertiary;
	private final ModelPart TailQuaternary;
	private final ModelPart RightArm;
	private final ModelPart RightFlight;
	private final ModelPart RightSubFlight;
	private final ModelPart LeftArm;
	private final ModelPart LeftFlight;
	private final ModelPart LeftSubFlight;
	private final ModelPart RightLeg;
	private final ModelPart RightLowerLeg;
	private final ModelPart RightFoot;
	private final ModelPart RightPad;
	private final ModelPart LeftLeg;
	private final ModelPart LeftLowerLeg;
	private final ModelPart LeftFoot;
	private final ModelPart LeftPad;

	public Avali(ModelPart root) {
		this.Head = root.getChild("Head");
		this.RightUpperEars = this.Head.getChild("RightUpperEars");
		this.LeftUpperEars = this.Head.getChild("LeftUpperEars");
		this.RightLowerEar = this.Head.getChild("RightLowerEar");
		this.LeftLowerEars = this.Head.getChild("LeftLowerEars");
		this.Torso = root.getChild("Torso");
		this.Tail = this.Torso.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.TailTertiary = this.TailSecondary.getChild("TailTertiary");
		this.TailQuaternary = this.TailTertiary.getChild("TailQuaternary");
		this.RightArm = root.getChild("RightArm");
		this.RightFlight = this.RightArm.getChild("RightFlight");
		this.RightSubFlight = this.RightFlight.getChild("RightSubFlight");
		this.LeftArm = root.getChild("LeftArm");
		this.LeftFlight = this.LeftArm.getChild("LeftFlight");
		this.LeftSubFlight = this.LeftFlight.getChild("LeftSubFlight");
		this.RightLeg = root.getChild("RightLeg");
		this.RightLowerLeg = this.RightLeg.getChild("RightLowerLeg");
		this.RightFoot = this.RightLowerLeg.getChild("RightFoot");
		this.RightPad = this.RightFoot.getChild("RightPad");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftLowerLeg = this.LeftLeg.getChild("LeftLowerLeg");
		this.LeftFoot = this.LeftLowerLeg.getChild("LeftFoot");
		this.LeftPad = this.LeftFoot.getChild("LeftPad");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(38, 26).addBox(-1.5F, -1.0F, -4.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(23, 32).addBox(-2.0F, -3.0F, -5.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition RightUpperEars = Head.addOrReplaceChild("RightUpperEars", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.1987F, -7.5771F, 3.4555F, -0.1309F, -0.0436F, 0.0F));

		PartDefinition cube_r1 = RightUpperEars.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 78).mirror().addBox(0.5F, -2.5F, -2.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.8515F, 3.8588F, 1.5442F, -0.8724F, -0.22F, -0.1369F));

		PartDefinition cube_r2 = RightUpperEars.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 9).mirror().addBox(0.5F, -3.5F, -2.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.817F, 3.0809F, 0.9167F, -0.8724F, -0.22F, -0.1369F));

		PartDefinition cube_r3 = RightUpperEars.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(46, 74).mirror().addBox(0.5F, -4.5F, -2.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.7825F, 2.303F, 0.2893F, -0.8724F, -0.22F, -0.1369F));

		PartDefinition cube_r4 = RightUpperEars.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 74).mirror().addBox(0.5F, -5.5F, -2.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.748F, 1.5252F, -0.3382F, -0.8724F, -0.22F, -0.1369F));

		PartDefinition cube_r5 = RightUpperEars.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 74).mirror().addBox(0.5F, -6.5F, -2.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.7134F, 0.7473F, -0.9657F, -0.8724F, -0.22F, -0.1369F));

		PartDefinition LeftUpperEars = Head.addOrReplaceChild("LeftUpperEars", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1987F, -7.5771F, 3.4555F, -0.1309F, 0.0436F, 0.0F));

		PartDefinition cube_r6 = LeftUpperEars.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 78).addBox(-1.5F, -2.5F, -2.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8515F, 3.8588F, 1.5442F, -0.8724F, 0.22F, 0.1369F));

		PartDefinition cube_r7 = LeftUpperEars.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 9).addBox(-1.5F, -3.5F, -2.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.817F, 3.0809F, 0.9167F, -0.8724F, 0.22F, 0.1369F));

		PartDefinition cube_r8 = LeftUpperEars.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(46, 74).addBox(-1.5F, -4.5F, -2.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7825F, 2.303F, 0.2893F, -0.8724F, 0.22F, 0.1369F));

		PartDefinition cube_r9 = LeftUpperEars.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(34, 74).addBox(-1.5F, -5.5F, -2.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.748F, 1.5252F, -0.3382F, -0.8724F, 0.22F, 0.1369F));

		PartDefinition cube_r10 = LeftUpperEars.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(30, 74).addBox(-1.5F, -6.5F, -2.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7134F, 0.7473F, -0.9657F, -0.8724F, 0.22F, 0.1369F));

		PartDefinition RightLowerEar = Head.addOrReplaceChild("RightLowerEar", CubeListBuilder.create(), PartPose.offset(-4.3271F, -1.787F, 2.7907F));

		PartDefinition cube_r11 = RightLowerEar.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(76, 22).addBox(-1.5F, 1.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8955F, -3.3311F, 1.1892F, -0.3371F, -0.3194F, 0.0701F));

		PartDefinition cube_r12 = RightLowerEar.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(64, 0).addBox(-1.5F, 1.5F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.933F, -2.3825F, 0.8752F, -0.3371F, -0.3194F, 0.0701F));

		PartDefinition cube_r13 = RightLowerEar.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(28, 61).addBox(-1.5F, 1.5F, -2.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.008F, -0.4852F, 0.247F, -0.3371F, -0.3194F, 0.0701F));

		PartDefinition cube_r14 = RightLowerEar.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(38, 20).addBox(-1.5F, 1.5F, -2.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9705F, -1.4338F, 0.5611F, -0.3371F, -0.3194F, 0.0701F));

		PartDefinition LeftLowerEars = Head.addOrReplaceChild("LeftLowerEars", CubeListBuilder.create(), PartPose.offset(4.3271F, -1.787F, 2.7907F));

		PartDefinition cube_r15 = LeftLowerEars.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(42, 61).addBox(0.5F, 1.5F, -2.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.008F, -0.4852F, 0.247F, -0.3371F, 0.3194F, -0.0701F));

		PartDefinition cube_r16 = LeftLowerEars.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(14, 41).addBox(0.5F, 1.5F, -2.5F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9705F, -1.4338F, 0.5611F, -0.3371F, 0.3194F, -0.0701F));

		PartDefinition cube_r17 = LeftLowerEars.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(64, 25).addBox(0.5F, 1.5F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.933F, -2.3825F, 0.8752F, -0.3371F, 0.3194F, -0.0701F));

		PartDefinition cube_r18 = LeftLowerEars.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(76, 26).addBox(0.5F, 1.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8955F, -3.3311F, 1.1892F, -0.3371F, 0.3194F, -0.0701F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition Base_r1 = Tail.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(2, 51).addBox(-1.0F, -1.9F, 0.4F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.125F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.5F, 2.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition Base_r2 = TailPrimary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(34, 1).addBox(-1.0F, -0.8F, -2.7F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, 1.1F, 2.4F, -0.3927F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.0F, 4.25F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r19 = TailSecondary.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(21, 39).mirror().addBox(-3.0F, 0.001F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.314F, 5.7163F, -0.0859F, -0.0151F, -0.1739F));

		PartDefinition cube_r20 = TailSecondary.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(13, 39).addBox(0.0F, 0.001F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.314F, 5.7163F, -0.0859F, 0.0151F, 0.1739F));

		PartDefinition Base_r3 = TailSecondary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(15, 32).addBox(-0.5F, -12.225F, 6.6F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.9F, -8.4F, -0.1309F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create().texOffs(49, 69).addBox(-0.5F, -0.5175F, -0.244F, 1.0F, 1.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 0.7F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r21 = TailTertiary.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(-4, 0).mirror().addBox(-4.0F, 0.001F, -5.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.2408F, 5.4593F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r22 = TailTertiary.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(-4, 4).addBox(0.0F, 0.001F, -5.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2408F, 5.4593F, 0.0F, 0.0F, 0.0873F));

		PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.2398F, 4.4093F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r23 = TailQuaternary.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(46, 12).mirror().addBox(-4.0F, 0.001F, 0.0F, 4.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.001F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r24 = TailQuaternary.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(43, 20).addBox(0.0F, 0.001F, 0.0F, 4.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.001F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition RightFlight = RightArm.addOrReplaceChild("RightFlight", CubeListBuilder.create().texOffs(0, 59).mirror().addBox(-6.0F, -6.0F, 0.0F, 6.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.05F, 3.0F, -0.8F, 0.0F, 1.309F, 0.0F));

		PartDefinition RightSubFlight = RightFlight.addOrReplaceChild("RightSubFlight", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.0F, -0.1F));

		PartDefinition RightArm_r1 = RightSubFlight.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(12, 59).mirror().addBox(-6.0F, -8.0F, 0.25F, 6.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.5F, 0.0F));

		PartDefinition LeftFlight = LeftArm.addOrReplaceChild("LeftFlight", CubeListBuilder.create().texOffs(50, 53).addBox(0.0F, -6.0F, 0.0F, 6.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.05F, 3.0F, -0.8F, 0.0F, -1.309F, 0.0F));

		PartDefinition LeftSubFlight = LeftFlight.addOrReplaceChild("LeftSubFlight", CubeListBuilder.create(), PartPose.offset(4.0F, -2.0F, -0.1F));

		PartDefinition LeftArm_r1 = LeftSubFlight.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(62, 53).addBox(0.0F, -8.0F, 0.25F, 6.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(32, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(32, 43).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(-0.155F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(48, 39).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.15F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

		PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(38, 9).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(48, 29).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(16, 50).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(-0.155F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(48, 46).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.15F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}