#!/usr/bin/perl

use warnings;
use strict;

my $IMPORTS='package net.kjentytek303.additional_transfurs.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kjentytek303.additional_transfurs.AdditionalTransfurs;
import net.kjentytek303.additional_transfurs.entity./*PERL_CAPITALIZED_NAME*/;
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

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
';

my $EXTEND = "Model extends AdvancedHumanoidModel</*PERL_CAPITALIZED_NAME*/> /*PERL_IMPLEMENTS*/";
my $GENERIC_OVERRIDES = '	@Override
	public HumanoidAnimator</*PERL_CAPITALIZED_NAME*/, ?> getAnimator(/*PERL_CAPITALIZED_NAME*/ entity) { return animator; }

	@Override
	public ModelPart getTorso() { return this.Torso; }
	
	@Override
	public ModelPart getHead() { return this.Head; }

	/*PERL_LEG_OVERRIDES*/
	
';

my $LEGLESS_IMPLEMENTS = "implements LeglessModel ";

my $CENTAUR_IMPLEMENTS = "implements LowerTorsoedModel ";

my $LEGLESS_LEG_OVERRIDES ='

	public ModelPart getLeg(HumanoidArm p_102852_) { return null; }

	@Override
	public ModelPart getAbdomen() { return Abdomen; }

	@Override
	public HelperModel getTransfurHelperModel(Limb limb) {
		if (limb == Limb.ABDOMEN) { return TransfurHelper.getLegless(); }

	return super.getTransfurHelperModel(limb);
	}

	@Override
	public boolean shouldModelSit(LatexSnake entity) { return super.shouldModelSit(entity) || LeglessModel.shouldLeglessSit(entity); }
';

my $CENTAUR_LEG_OVERRIDES = '

	public ModelPart getLeg(HumanoidArm p_102852_) {
		return null;
	}

	@Override
	public HelperModel getTransfurHelperModel(Limb limb) {
		if (limb == Limb.LOWER_TORSO)
			return TransfurHelper.getTaurTorso();
		else if (limb == Limb.TORSO)
			return TransfurHelper.getFeminineTorsoAlt();
		return super.getTransfurHelperModel(limb);
	}

	@Override
	public boolean shouldPartTransfur(ModelPart part) {
		return super.shouldPartTransfur(part) && part != this.Saddle;
	}

	@Override
	public ModelPart getLowerTorso() {
		return LowerTorso;
	}

';

my $MASKED = '	public boolean isPartNotMask(ModelPart part) { return Mask.getAllParts().noneMatch(part::equals); }
	';

my $BIPED_LEG_OVERRIDES = '
	@Override
	public ModelPart getArm(HumanoidArm humanoidArm) { return humanoidArm == HumanoidArm.LEFT ? this.LeftArm : this.RightArm; }
	
	@Override
	public ModelPart getLeg(HumanoidArm humanoidArm) { return humanoidArm == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg; }
';

my $DECLARATIONS = "	private final HumanoidAnimator</*PERL_CAPITALIZED_NAME*/, /*PERL_CAPITALIZED_NAME*/Model> animator;

//	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdditionalTransfurs.modResource(\"entity//*PERL_NAME_LOWERCASE*/\"), \"main\");

";

my $ANIMATOR_INIT = "		animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
			   .addPreset(AnimatorPresets./*PERL_ANIMATOR_PRESET*/(
					 Head,
					 /*PERL_ANIMATED_EARS*/
					 Torso, LeftArm, RightArm,
					 /*PERL_ABDOMEN_STUFF*/
					 /*PERL_NORMAL_TAIL_STUFF*/ 
					 /*PERL_LEG_STUFF*/
					 /*PERL_WINGED_STUFF*/
					 ));

";

my $ears = "LeftEar, RightEar,"

my $normal_tail_stuff = "Tail, List.of(/*PERL_TAIL_PARTS_ARRAY*/),"

my $legless_abdomen = "Abdomen, LowerAbdomen,"
my $centaur_legs = '
                LowerTorso, FrontLeftLeg, leftLowerLeg, LeftFoot, FrontRightLeg, rightLowerLeg, RightFoot,
                BackLeftLeg, leftLowerLeg2, leftFoot2, LeftPad2, BackRightLeg, rightLowerLeg2, rightFoot2, RightPad2))
                .addAnimator(new WolfTailInitAnimator<>(Tail, List.of(/*PERL_TAIL_PARTS_ARRAY*/)))
                .forwardOffset(-7.0f).hipOffset(-1.5f).legLength(13.5f).torsoLength(11.05f);
'

my $dragon_wings =
my $bird_wings =

my $input_file_name = '';
my $name_capitalized ;
my $name_lowercase;
my $output_file_name = '';
my $preset = '';
my @tail_parts = ();
my $is_masked = 0;
my $leg_type = "";

getlopt(@ARGV);

$name_capitalized = $input_file_name;
$name_capitalized =~ /([a-zA-z]+)\.java$/;
$name_capitalized = $1;

$name_lowercase = $name_capitalized;
$name_lowercase =~ s/([A-Z])/_$1/g;
$name_lowercase =~ tr/[A-Z]/[a-z]/;
$name_lowercase =~ s/^_//;

my $leg_type = "biped";

if ( $preset eq "taur" ) {
	$leg_type = "centaur";
}

if ( $preset eq "snake" || $preset eq "leglessShark" || $preset eq "leglessMantaRay" ) {
	$leg_type = "legless";
}

$output_file_name = $name_capitalized . 'Model.java';


open(my $IFILE, '<', $input_file_name) or die "Couldn't open file '$input_file_name': $!";
my @mapped_file = <$IFILE>;
close($IFILE);

splice(@mapped_file, 0, 0, ($IMPORTS) );

my $doneExtend = 0;
my $inModelParts = 0;
my $inConstruct = 0;


for( my $i = 1; $i<scalar(@mapped_file); $i++) {# {{{

	if ( $doneExtend == 0 && $mapped_file[$i] =~ /public class/ ) {
		$mapped_file[$i] =~ s/<T extends Entity> extends EntityModel<T>/$EXTEND/;
		splice(@mapped_file, $i-1, 0, '@OnlyIn(Dist.CLIENT)');

		$i++;
		splice(@mapped_file, $i, 0, $GENERIC_OVERRIDES);
		$doneExtend = 1;
		next;
	}

	$mapped_file[$i] =~ s/new ResourceLocation\("modid", "[a-z]+"\),/AdditionalTransfurs.modResource\("entity\/$name_lowercase"\),/;
	if ( $inModelParts == 0 && $mapped_file[$i] =~ /private final ModelPart;/ ) {
		$inModelParts = 1;
		next;
	}

	if ( $inModelParts == 1 ) {
		splice(@mapped_file, $i, 0, $DECLARATIONS);
		$inModelParts = -1;
		next;
	}

	if ( $mapped_file[$i] =~ /private final ModelPart (Tail[a-zA-Z]+);/ ) {
		push(@tail_parts, $1);
		next;
	}

	if ($inConstruct == 0 && $mapped_file[$i] =~ /public $name_capitalized/ ) {

		$mapped_file[$i] = "		public $name_capitalized" . "Model" . " (ModelPart root) {\n";
		$i++;
		splice(@mapped_file, $i, 0, "		super(root);\n");
		$i++;
		while ($mapped_file[$i] =~ /getChild/) {
			$i++;
		}
		splice(@mapped_file, $i, 0, $ANIMATOR_INIT);
		$inConstruct = -1;
		next;
	}

	if ( $mapped_file[$i] =~ /public void setupAnim/ ) {
		$i--;
		splice(@mapped_file, $i, 4, () );
		next;
	}
}	# }}}

my $tail_parts_serialized = serializeArray(@tail_parts);

foreach (@mapped_file) {
	$_ =~ s/\/\*PERL_CAPITALIZED_NAME\*\//$name_capitalized/g;
	$_ =~ s/\/\*PERL_NAME_LOWERCASE\*\//$name_lowercase/g;
	$_ =~ s/\/\*PERL_ANIMATOR_PRESET\*\//$preset/;
	
	if ( $leg_type eq "centaur" ) {
		$_ =~ s/\/\*PERL_IMPLEMENTS\*\//$CENTAUR_IMPLEMENTS/;
		$_ =~ s/\/\*PERL_LEG_OVERRIDES\*\//$CENTAUR_LEG_OVERRIDES/;

		$_ =~ s/\/\*PERL_ABDOMEN_STUFF\*\//$centaur_legs/

		$_ =~ s/\h\)\);$//;

	}

	if ( $leg_type eq "legless" ) {
		$_ =~ s/\/\*PERL_IMPLEMENTS\*\//$LEGLESS_IMPLEMENTS/;
		$_ =~ s/\/\*PERL_LEG_OVERRIDES\*\//$LEGLESS_LEG_OVERRIDES/;
		$_ s/\/\*PERL_ABDOMEN_STUFF\8\//$legless_abdomen/

	}

	if ( $leg_type eq "biped" ) {
		$_ =~ s/\/\*PERL_IMPLEMENTS\*\///;
		$_ =~ s/\/\*PERL_LEG_OVERRIDES\*\//$BIPED_LEG_OVERRIDES/;

	}


	if ( $_ =~ /\/\*PERL_ANIMATED_EARS\*\// ) {
		if ( $preset eq "wolf" || $preset eq "cat" || $preset eq "Deer" ) {
			$_ =~ s/\/\*PERL_ANIMATED_EARS\*\//$ears/;
		}
		$_ =~ s/\/\*PERL_ANIMATED_EARS\*\///;
	}

	if ( $_ =~ /\/\*PERL_LEG_STUFF\*\// {
		if ( $leg_type eq "biped" ) {
			
		}
	}

	if ( $_ =~ /\/\*PERL_ABDOMEN_STUFF\8\// {
		if ( $leg_type eq "legless" ) {
		}

		if ( $leg_type eq "centaur" ) {
		}

	}

	if ( $_ =~ /\/\*PERL_WINGED_STUFF\*\// ) {

	}

	$_ =~ s/\/\*PERL_TAIL_PARTS_ARRAY\*\//$tail_parts_serialized/;
	
}

print @mapped_file;


sub getlopt {# {{{
	my $eval = 0;
	foreach(@_) {
		if ( $eval == 0 ) {
			if ( $_ eq "-f" ) { $eval = 1; next; }
			if ( $_ eq "-p" ) { $eval = 2; next; }
			if ( $_ eq "-M" ) { $is_masked = 1; next; }
			if ( $_ eq "-h" ) { printHelp(); exit(0); }

			next;
		}

		if ( $eval == 1 ) {
			$input_file_name = $_;
			$output_file_name = $_;
			$output_file_name =~ s/\.java/Model\.java/;
			$eval = 0;
			next;
		}

		if ( $eval == 2 ) {
			if (  $_ eq "wolf" || $_ eq "cat" || $_ eq "deer" ||
				$_ eq "human" || $_ eq "bird" || 
				$_ eq "orca" ||  $_ eq "shark" || $_ eq "dragon" ||
			        $_ eq "taur" ||
				$_ eq "snake" ||
				$_ eq "wingedDragon" ) {
				$preset = $_ . "Like";
				$eval = 0;
				next;
			}

			if ( $_ eq "leglessShark" || $_ eq "leglessMantaRay" ) {
				$preset = $_;
				$eval = 0;
				next;
			}

			die "Unknown preset: $_\nAborted.";
		}
	}
	if( $eval != 0 ) {
		die "Missing argument for an option";
	}
       	if ( $input_file_name eq '' || $preset eq '' ) { die 'Input file or preset empty. Aborted.';}


}# }}}

sub serializeArray {# {{{
	return "" if scalar(@_) == 0;

	my $ret = $_[0];
	for (my $i = 1; $i < scalar(@_); $i++ ) {
		$ret = $ret . ", " . $_[$i];
	}
	return($ret);
}# }}}

sub printHelp { #{{{
	print "BJCJ model converter by KJEntytek303

Used to convert blockbench exported .java model to the format changed mc accepts.
This software isn't meant to be called directly by the user, rather from a script.
By default, the converted file is printed to STDOUT.

Usage:
./bjcj.pl -f [FILE] -p [PRESET] [OPTIONS]

FILE specifies blockbench .java model to convert. Removes '/' from path names automatically before conversion.
PRESET specifies presets to use. Valid presets are:
	wolf
	cat
	deer
	orca
	shark
	dragon
	taur
	snake
	wingedDragon
	leglessShark
	leglessMantaRay

OPTIONS:
	-h	-Print this screen
"
}#}}}
