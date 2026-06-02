package net.kjentytek303.additional_transfurs.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.entity.*;
import net.kjentytek303.additional_transfurs.entity.generated.*;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

//Needed for taurs
import net.ltxprogrammer.changed.client.tfanimations.HelperModel;
import net.ltxprogrammer.changed.client.tfanimations.TransfurHelper;
import net.ltxprogrammer.changed.client.animations.Limb;
import net.ltxprogrammer.changed.client.renderer.animate.tail.WolfTailInitAnimator;

//Needed for legless
import net.ltxprogrammer.changed.client.renderer.model.LeglessModel;
import net.ltxprogrammer.changed.entity.beast.LatexSnake;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

@OnlyIn(Dist.CLIENT)
public class LatexRedTailCatfishModel extends AdvancedHumanoidModel<LatexRedTailCatfish>  {
	@Override
	public HumanoidAnimator<LatexRedTailCatfish, ?> getAnimator(LatexRedTailCatfish entity) { return animator; }

	@Override
	public ModelPart getTorso() { return this.Torso; }
	
	@Override
	public ModelPart getHead() { return this.Head; }

	@Override
	public ModelPart getArm(HumanoidArm humanoidArm) { return humanoidArm == HumanoidArm.LEFT ? this.LeftArm : this.RightArm; }

	
	
	@Override
	public ModelPart getLeg(HumanoidArm humanoidArm) { return humanoidArm == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg; }

	
	private final HumanoidAnimator<LatexRedTailCatfish, LatexRedTailCatfishModel> animator;

//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdditionalTransfurs.modResource("entity/latex_red_tail_catfish"), "main");

	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdditionalTransfurs.modResource("entity/latex_red_tail_catfish"), "main");
	private final ModelPart RightLeg;
	private final ModelPart RightLowerLeg;
	private final ModelPart RightFoot;
	private final ModelPart RightPad;
	private final ModelPart LeftLeg;
	private final ModelPart LeftLowerLeg;
	private final ModelPart LeftFoot;
	private final ModelPart LeftPad;
	private final ModelPart Head;
	private final ModelPart Torso;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart TailSecondary;
	private final ModelPart TailTertiary;
	private final ModelPart TailQuarternary;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;

		public LatexRedTailCatfishModel (ModelPart root) {
		super(root);
		this.RightLeg = root.getChild("RightLeg");
		this.RightLowerLeg = this.RightLeg.getChild("RightLowerLeg");
		this.RightFoot = this.RightLowerLeg.getChild("RightFoot");
		this.RightPad = this.RightFoot.getChild("RightPad");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftLowerLeg = this.LeftLeg.getChild("LeftLowerLeg");
		this.LeftFoot = this.LeftLowerLeg.getChild("LeftFoot");
		this.LeftPad = this.LeftFoot.getChild("LeftPad");
		this.Head = root.getChild("Head");
		this.Torso = root.getChild("Torso");
		this.Tail = this.Torso.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.TailTertiary = this.TailSecondary.getChild("TailTertiary");
		this.TailQuarternary = this.TailTertiary.getChild("TailQuarternary");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
			   .addPreset(AnimatorPresets.sharkLike(
					 Head,
					 
					 Torso, LeftArm, RightArm,
					 /*PERL_ABDOMEN_STUFF*/
					 Tail, List.of(TailPrimary, TailSecondary, TailTertiary, TailQuarternary), 
					 LeftLeg, LeftLowerLeg, LeftFoot, LeftPad, RightLeg, RightLowerLeg, RightFoot, RightPad
					 /*PERL_WINGED_STUFF*/
					 ))/*PERL_ADDITIONAL_ANIMATORS*/;

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(74, 0).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(74, 10).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition Legfin_r1 = RightFoot.addOrReplaceChild("Legfin_r1", CubeListBuilder.create().texOffs(76, 28).addBox(0.0F, -2.5F, -4.5F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.5F, -0.7332F, -0.7008F, 2.2177F, -0.4703F, -0.3298F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(74, 19).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

		PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(58, 0).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(60, 10).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition Legfin_r2 = LeftFoot.addOrReplaceChild("Legfin_r2", CubeListBuilder.create().texOffs(76, 33).mirror().addBox(0.0F, -2.5F, -4.5F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(2.5F, -0.7332F, -0.7008F, 2.2177F, 0.4703F, 0.3298F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(56, 19).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-3.0F, -3.0F, -7.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 69).addBox(-2.5F, -1.0F, -6.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition whiskers_right_r1 = Head.addOrReplaceChild("whiskers_right_r1", CubeListBuilder.create().texOffs(26, 60).mirror().addBox(0.0F, -2.5F, -8.5F, 0.0F, 7.0F, 13.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -4.5582F, -6.9758F, 1.5236F, 0.3923F, -0.0181F));

		PartDefinition whiskers_left_r1 = Head.addOrReplaceChild("whiskers_left_r1", CubeListBuilder.create().texOffs(0, 60).addBox(0.0F, -2.5F, -8.5F, 0.0F, 7.0F, 13.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(3.0F, -4.5582F, -6.9758F, 1.5236F, -0.3923F, 0.0181F));

		PartDefinition Nosy_r1 = Head.addOrReplaceChild("Nosy_r1", CubeListBuilder.create().texOffs(34, 67).addBox(-1.0F, -29.625F, -0.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 67).addBox(-5.0F, -29.625F, -0.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 26.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(16, 16).addBox(-3.8F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2F, -0.5F, 0.0F));

		PartDefinition Backfin_r1 = Torso.addOrReplaceChild("Backfin_r1", CubeListBuilder.create().texOffs(52, 66).addBox(0.0F, -2.5F, -4.5F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.2F, 3.4418F, 3.0242F, 2.138F, 0.0F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 9.5F, 0.25F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.2F, -0.6F, 0.25F));

		PartDefinition Base1_r1 = TailPrimary.addOrReplaceChild("Base1_r1", CubeListBuilder.create().texOffs(57, 27).addBox(-2.0F, -3.7F, -0.95F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.8467F, 3.3228F, 1.3526F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.7681F, 3.48F));

		PartDefinition Base2_r1 = TailSecondary.addOrReplaceChild("Base2_r1", CubeListBuilder.create().texOffs(57, 36).addBox(-2.0F, -2.7F, -0.95F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 1.6693F, 2.1261F, 1.309F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, 4.05F, 0.2793F, 0.0F, 0.0F));

		PartDefinition fin4top_r1 = TailTertiary.addOrReplaceChild("fin4top_r1", CubeListBuilder.create().texOffs(88, 22).addBox(0.0F, -1.5F, -5.5F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.2909F, 0.1858F, 2.0071F, 0.0F, 0.0F));

		PartDefinition fin3bot_r1 = TailTertiary.addOrReplaceChild("fin3bot_r1", CubeListBuilder.create().texOffs(88, 27).addBox(0.0F, -0.5F, -3.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.7091F, -0.5642F, 2.0071F, 0.0F, 0.0F));

		PartDefinition Base3_r1 = TailTertiary.addOrReplaceChild("Base3_r1", CubeListBuilder.create().texOffs(56, 45).addBox(-1.0F, 0.4894F, -1.5342F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -0.0327F, -0.2884F, 1.1781F, 0.0F, 0.0F));

		PartDefinition TailQuarternary = TailTertiary.addOrReplaceChild("TailQuarternary", CubeListBuilder.create().texOffs(63, 54).addBox(-0.5F, -3.2595F, 3.3445F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(84, 54).addBox(-0.5F, 3.7405F, 4.3445F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(72, 55).addBox(-0.5F, -4.2595F, 5.3445F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 61).addBox(-0.5F, 2.7405F, 2.3445F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(56, 52).addBox(-0.5F, -1.2595F, 3.3445F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(58, 62).addBox(-0.5F, 1.7405F, 1.3445F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(73, 51).addBox(-0.5F, -2.2595F, 1.3445F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5673F, 3.3616F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Base4_r1 = TailQuarternary.addOrReplaceChild("Base4_r1", CubeListBuilder.create().texOffs(66, 45).addBox(-1.0F, -4.7F, 1.05F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 2.8246F, 4.6122F, 1.5272F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition armfin_r1 = RightArm.addOrReplaceChild("armfin_r1", CubeListBuilder.create().texOffs(76, 24).addBox(0.0F, -2.5F, -3.5F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.0F, 6.4418F, 3.0242F, 2.2177F, -0.4703F, -0.3298F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.5F, 0.0F));

		PartDefinition armfin_r2 = LeftArm.addOrReplaceChild("armfin_r2", CubeListBuilder.create().texOffs(76, 20).mirror().addBox(0.0F, -2.5F, -3.5F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(2.0F, 6.4418F, 3.0242F, 2.2177F, 0.4703F, 0.3298F));

		return LayerDefinition.create(meshdefinition, 96, 96);
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