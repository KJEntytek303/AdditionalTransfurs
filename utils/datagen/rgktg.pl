#!/usr/bin/perl

#RGKTG - Renderer Generator for KJEntytek's Transfur Generator.

use warnings;
use strict;

my @mapped_file=();

my $name = '';
my $shadow_size = "0.5";
my $name_lowercase;
my $renderer_template='data/java/renderers/generic-renderer.template.java';
my $armor_model='ArmorLatexMaleWolfModel.MODEL_SET';
my $renderer_type='HUMANOID';
my $eyes='false';
my $forced_sclera='';
my $forced_iris1='';
my $forced_iris2='';
my $emissive='false';
my $translucent='false';
my $multihanded='false';
my $centaur_stuff='';
my $gas_mask_layer='';
my $model_scale='';
my $is_masked='';

getsopt(@ARGV);

$name_lowercase = $name;
$name_lowercase =~ s/([A-Z])/_$1/g;
$name_lowercase =~ s/^_//;
$name_lowercase =~ tr/[A-Z]/[a-z]/;

#Load values from STDIN
#sed and print

while ( !eof STDIN ) {
	push ( @mapped_file, <STDIN> );
}

my $mode = 'normal';
foreach ( @mapped_file ) {
	if ( $mode eq 'normal' ) {
		if ( $_ =~ /^(.+)=\[/ ) { #array
			$mode = 'array';
			#There are no array-type vars in renderers - skip array.
			next;
		}
		if ( $_ =~ /^ENTITY_SHAPE=(TAUR|)/) { $centaur_stuff = $1; next; }
		if ( $_ =~ /^RENDERER_TEMPLATE=(.+)/ ) { $renderer_template=$1; next; }
		if ( $_ =~ /^ARMOR_MODEL=(.+)/ ) { $armor_model=$1; next; }
		if ( $_ =~ /^RENDERER_TYPE=(HUMANOID)/ ) { $renderer_type=$1;  } #UNUSED
		if ( $_ =~ /^EYES_PRESENT=(true|false)/ ) { $eyes=$1 }
		if ( $_ =~ /^MULTIHANDED_RENDERER=(true|false)/ ) { $multihanded=$1; }
		if ( $_ =~ /^IRIS_1ST_COLOR=((0x[0-9a-fA-F]{1,6})\h*(dl|)|)/ ) { $forced_iris1 = ( $1 ) ? $2 . $3 : ""; }
		if ( $_ =~ /^IRIS_2ND=((0x[0-9a-fA-F]{1,6})\h*(dl|)|)/ ) { $forced_iris2 = ( $1 ) ? $2 . $3 : ""; }
		if ( $_ =~ /^SCLERA_COLOR=((0x[0-9a-fA-F]{1,6})|)/ ) { $forced_sclera = ( $1 ) ? $2 : ""; }
		if ( $_ =~ /^GAS_MASK_LAYER=(.+)/ ) { $gas_mask_layer= $1;  }
		if ( $_ =~ /^EMISSIVE_LAYER=(true|false)/ ) { $emissive=$1; }
		if ( $_ =~ /^TRANSLUCENT_LAYER=(true|false)/ ) { $translucent=$1; }
		if ( $_ =~ /^MODEL_SCALE=(\d+\.\d+)/ ) { $model_scale=$1; }
		if ( $_ =~ /^IS_MASKED=(true|false)/ ) { $is_masked=$1; }
		if ( $_ =~ /^SHADOW_SIZE=(\d+\.\d+)/ ) { $shadow_size=$1; }
	}
	if ( $mode eq 'array' ) {
		if ( $_ =~ /^]/ ) { $mode = 'normal'; }
		next;
	}
}

#prepare opts
if ($eyes eq 'true' ) {
	$eyes = "this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())\n";
	$eyes .= ( $forced_sclera ) ? "\t\t\t.withSclera(Color3.fromInt(" . $forced_sclera . "))" : "";
	if ( $forced_iris1 ) {
		$forced_iris1 =~ /(0x[0-9a-fA-F]{1,6})\h*(dl|)/;
		$eyes .= ( $2 ) ? "\t\t\t.withLeftIris(CustomEyesLayer.fixedIfNotDarkLatexOverrideLeft(Color3.fromInt($1)))\n" : "\t\t\t.withLeftIris(Color3.fromInt($1))\n";
	}
	if ( $forced_iris2 ) {
		$forced_iris2 =~ /(0x[0-9a-fA-F]{1,6})\h*(dl|)/;
		$eyes .= ( $2 ) ? "\t\t\t.withRightIris(CustomEyesLayer.fixedIfNotDarkLatexOverrideRight(Color3.fromInt($1)))\n" : "\t\t\t.withRightIris(Color3.fromInt($1))\n";
	}

	$eyes .= "\t\t\t.build());";
} else { $eyes = "" ; }

$emissive = ( $emissive eq 'true' ) ? "this.addLayer(new EmissiveBodyLayer(this, AdditionalTransfurs.modResource(\"textures/$name_lowercase" . "_emissive.png\")));" : "";
$translucent = ( $translucent eq 'true' ) ? "this.addLayer(new TranslucentBodyLayer(this, AdditionalTransfurs.modResource(\"textures/$name_lowercase" . "_translucent.png\")));" : "";
$multihanded = ( $multihanded eq 'true' ) ? "this.addLayer(new DoubleItemInHandLayer(this, context.getItemInHandRenderer()));" : "";

$centaur_stuff = ( $centaur_stuff ) ? "this.addLayer(new SaddleLayer(this, ( $name" . "Model )this.getModel(), AdditionalTransfurs.modResource(\"textures/$name_lowercase" . "_saddle.png\")));
\t\t\tthis.addLayer(new TaurChestPackLayer(this, context.getModelSet()));" : "";

$model_scale = ( $model_scale ) ? "\@Override\n\tprotected void scale( $name_lowercase entity, PoseStack pose, float partialTick) {\n\t\tfloat modelScale = $model_scale ;\n\t\tpose.scale(modelScale, modelScale, modelScale);\n\t}" : "";
$gas_mask_layer = ( $gas_mask_layer ) ? "this.addLayer(" . $gas_mask_layer . "(this, context.getModelSet()));" : "";

#sed
open (my $TFILE, '<', $renderer_template);
@mapped_file = <$TFILE>;
close ($TFILE);

foreach (@mapped_file) {
	$_ =~ s/PERL_ENTITY_NAME/$name/g;
	$_ =~ s/PERL_LOWERCASE_NAME/$name_lowercase/g;
	$_ =~ s/PERL_ARMOR_MODEL/$armor_model/;
	$_ =~ s/PERL_SHADOW_SIZE/$shadow_size/;
	$_ =~ s/\/\*PERL_CUSTOM_EYES\*\//$eyes/;
	$_ =~ s/\/\*PERL_EMISSIVE_LAYER\*\//$emissive/;
	$_ =~ s/\/\*PERL_TRANSLUCENT_LAYER\*\//$translucent/;
	$_ =~ s/\/\*PERL_MULTIHANDED\*\//$multihanded/;
	$_ =~ s/\/\*PERL_CENTAURS\*\//$centaur_stuff/;
	$_ =~ s/\/\*PERL_GAS_MASK_LAYER\*\//$gas_mask_layer/;
	$_ =~ s/\/\*PERL_MODEL_SCALE\*\//$model_scale/;
}

print @mapped_file;



sub getsopt {
	my $eval = '';
	foreach (@_) {
		if ( $eval eq '' ) {
			if ( $_ eq '-h' ) {
				printHelp();
				exit 0;
			}

			if ( $_ eq '-n' ) {
				$eval = 'name';
				next;
			}

			die "Error: Unknown parameter $_\nErrors occurred, compilation aborted";

		}
		if ( $eval eq 'name' ) {
			$name = $_;
			$eval = '';
			next;
		}
	}
	if ( $name eq '' ) { die "Error: no name given.\nErrors occured, compilation aborted.\n"; }
}

sub printHelp {

}
