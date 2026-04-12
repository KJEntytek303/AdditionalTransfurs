#!/usr/bin/perl
#KSTFG - KJEntytek's Simple Transfur Generator

use warnings;
use strict;

#global variables
my $VERSION = '0.1';

#pre-main {{{

my $errored=0;
my $mode='NORMAL';
my $array='';
my @infile = ();

#}}}

#variables #{{{

my $template = "data/java/tf-templates/generic-variant.java.template";	#template file
my $extend = "ChangedEntity";	#which class to extend
my @implements = ();
my $name='';

my @presets=();
my @attributes=();
my @abilities=();
my @scares=();			#what mobs fear the variant
my @tags=();

my $transfur_sound="";
my $transfur_mode="REPLICATION";
my $mining_speed="";
my $entity_shape="";
my $use_item_mode="NORMAL";
my $fly="";
my $jumps="";
my $vision="NORMAL";
my $climb="false";
my $z_offset="";
my $freezing_ticks="";
my $breathing_mode="";
my $powder_snow_walkable="false";
my $transfur_color="0xfdfdfd";
my $egg_back="0xfdfdfd";
my $egg_front="0xf0f0f0";
my $spawn_placement="ON_GROUND";
my $spawn_heightmap="MOTION_BLOCKING_NO_LEAVES";
my $latex_type="";
my $riding_offset='';
my $builder='EntityType.Builder.of(PERL_ENTITY_NAME::new, MobCategory.MONSTER).clientTrackingRange(10).sized(0.7F, 1.93F)';
my $gender = "";

#}}}

getsopt(@ARGV);

my $name_lowercase = $name;
$name_lowercase =~ s/([A-Z])/_$1/g;
$name_lowercase =~ s/^_//;
$name_lowercase =~ tr/[A-Z]/[a-z]/;

my $name_uppercase = $name_lowercase;
$name_uppercase =~ tr/[a-z]/[A-Z]/;

while ( ! eof STDIN ) {
	push @infile, <STDIN>;
}

foreach ( @infile ) { #load config file
	loop_begin:

	if( ( $_ =~ /^;/ ) || ( $_ =~ /^\h*$/ ) ) { 
		next;
	}

	if ($mode eq 'NORMAL') { #{{{

		if ( $_ =~ /^[A-Z_]+=\[\h*/ ) { #if array opening {{{
$mode ='ARRAY';
			goto loop_begin; #reevaluate as array.
		} # }}}

		if ( $_ =~ /^TEMPLATE=(.+)/ ) { # {{{
			$template = "data/java/tf-templates/" . $1 . ".java.template";
		} #}}}

		if ( $_ =~ /^ACCESSORIES=(.+)/ ) {
			my $tmp = "tmp/data/additional_transfurs/accessories/entities/" . $1;
			if ( -f $tmp ) {
				open ( CACS_FILE, ">>", $tmp ) or die "KSTfG: Couldn't open file '$tmp': $!";
				print CACS_FILE "additional_transfurs:" , $name_lowercase, "\n";
				close ( CACS_FILE );
				next;
			}
			die "KSTfG: Assertion Failed: Accessory file innaccessible.";
		}

		if ( $_ =~ /^EXTEND=([a-zA-Z0-9])+\h*/ ) { $extend = $1; }
		if ( $_ =~ /^TRANSFUR_SOUND=(.+)\h*/ ) { $transfur_sound = $1; }
		if ( $_ =~ /^TRANSFUR_MODE=(ABSORPTION|REPLICATION|NONE)\h*/ ) { $transfur_mode = $1; }
		if ( $_ =~ /^MINING=(WEAK|NORMAL|STRONG)\h*/ ) { $mining_speed=$1; }
		if ( $_ =~ /^ENTITY_SHAPE=(ANTHRO|FERAL|TAUR|NAGA|MER)\h*/ ) { $entity_shape = $1; }
		if ( $_ =~ /^USE_ITEM_MODE=(NORMAL|MOUTH|NONE)\h*/ ) { $use_item_mode = $1; }
		if ( $_ =~ /^FLY=(NONE|CT|ELYTRA|BOTH)\h*/ ) { $fly = $1; }
		if ( $_ =~ /^JUMPS=(\d+)\h*/ ) { $jumps = $1; }
		if ( $_ =~ /^VISION=(NORMAL|NIGHT_VISION|BLIND|REDUCED|VAVE_VISION)\h*/ ) { $vision = $1; }
		if ( $_ =~ /^CLIMB=(true|false)\h*/ ) { $climb = $1; }
		if ( $_ =~ /^Z_OFFSET=(\d+\.\d+)\h*/ ) { $z_offset = $1; }
		if ( $_ =~ /^TICKS_TO_FREEZE=(\d+)\h*/ ) { $freezing_ticks = $1; }
		if ( $_ =~ /^BREATH=(NORMAL|WATER|ANY|NONE)\h*/ ) { $breathing_mode = $1; }
		if ( $_ =~ /^POWDER_SNOW_WALKABLE=(true|false)\h*/ ){ $powder_snow_walkable = $1; }
		if ( $_ =~ /^TRANSFUR_COLOR=(0x[0-9a-fA-F]{,6})\h*/ ) { $transfur_color = $1; }
		if ( $_ =~ /^ABILITY_COLOR_1ST=(0x[0-9a-fA-F]{,6})\h*/ ) { $egg_back = $1; }
		if ( $_ =~ /^ABILITY_COLOR_2ND=(0x[0-9a-fA-F]{,6})\h*/ ) { $egg_front = $1; }
		if ( $_ =~ /^LATEX_TYPE=(NONE|WHITE_LATEX|DARK_LATEX)/ ) { $latex_type = $1; }
		if ( $_ =~ /^SPAWN_PLACEMENT=(ON_GROUND|IN_WATER|NO_RESTRICTIONS|IN_LAVA|null)/ ) { $spawn_placement = $1; }
		if ( $_ =~ /^SPAWN_HEIGHTMAP=(WORLD_SURFACE_WG|WORLD_SURFACE|OCEAN_FLOOR_WG|OCEAN_FLOOR|MOTION_BLOCKING|MOTION_BLOCKING_NO_LEAVES|null)/ ) { $spawn_heightmap = $1; }
		if ( $_ =~ /^GENDER=(MALE|FEMALE)/ ) { $gender = $1; next; }
		if ( $_ =~ /^BUILDER=(.+)/ ) { $builder = $1; }

		next;
	} #}}}

	if ( $mode eq 'ARRAY' ) { # {{{

		if ( $_ =~ /^]\h*/ ) { #{{{
			$array = '';
			$mode = 'NORMAL';
			next;
		} # }}}

		if ( $array eq '' ) { # if we drop from normal mode, get option {{{
			$_ =~ /([A-Z]+)=\[\h*/;
			$array = $1;
			next;
		} #}}}
		
		if ( $array eq 'TAGS' ) {
			$_ =~ /((^[a-z][a-z0-9_]*):([a-z][a-z0-9_\/]*))\h*(false|)?\h*$/;
			open( TAGFILE, '>>', "tmp/data/$2/tags/entity_types/$3.mctag") or die "KSTfG: Couldn't open file: tmp/data/$2/tags/entity_types/$3.mctag : $!";
			print TAGFILE "additional_transfurs:", $name_lowercase, "\n";
			close (TAGFILE);
			next;
		}

		if ( $array eq 'PRESETS' ) { #{{{
			$_ =~ /(.+)\h*/;
			push( @presets, $1 );
			next;
		} #}}}

		if ( $array eq 'ABILITIES' ) { #{{{
			$_ =~ /(.+)\h*/;
			push( @abilities, $1 );
			next;
		} #}}}

		if ( $array eq 'ATTRIBUTES' ) { #{{{
			$_ =~ /(.+)\h*/;
			push @attributes, $1;
			next;
		} #}}}

		if ( $array eq 'SCARES' ) { #{{{
			$_ =~ /(.+)\h*/;
			push( @scares, $1 );
			next;
		} #}}}
		
		if ( $array eq 'DIMENSIONS' ) { #{{{
			$_ =~ /(.+)\h*/;
			next;
		} #}}}
	} #}}}

	$errored = 1;
	print STDERR "KSTfG: Assertion failed - bad mode: $mode\n";
}

die 'KSTfG: Errors occurred, compilation aborted' if $errored;

#main
if ( $extend eq '' ) { print STDERR "KSTfG: Warning: Extend empty, defaulting to ChangedEntity\n"; $extend = "ChangedEntity"; }

#Ternary operator spam
$transfur_sound = ( $transfur_sound eq '' ) ? '' : ".sound( $transfur_sound.getId() )";
$mining_speed = ( $mining_speed eq "" ) ? "" : ".miningStrength( MiningStrength.$mining_speed )";
$use_item_mode = ( $use_item_mode eq "" ) ? "" : ".itemUseMode( UseItemMode.$use_item_mode )";
$fly = ( $fly eq "" ) ? "" : ( $fly eq "NONE" ) ? ".glide(false)" : ".glide(true)";
$jumps = ($jumps eq '' ) ? "" : ".extraJumps($jumps)";
$vision = ( $vision eq "" ) ? "" : ".visionType(VisionType.$vision)";
$climb = ( $climb eq "false" ) ? "" : ".climb()";
my $climb_override = ( $climb eq "" ) ? "" : "\tprivate static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(" . $name . ".class, EntityDataSerializers.BYTE);
protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte) 0 );
	}

	public void tick() {
		super.tick(); 
		if (!this.level().isClientSide) { this.setClimbing(this.horizontalCollision); }
	}

	public boolean onClimbable() { return this.isClimbing(); }

	public boolean isClimbing() { return ((Byte)this.entityData.get(DATA_FLAGS_ID) & 1) != 0; }

	public void setClimbing(boolean p_33820_) {
		byte b0 = (Byte)this.entityData.get(DATA_FLAGS_ID);
		b0 = ( p_33820_ ) ? (byte) (b0 | 1) : (byte) (b0 & -2);
		this.entityData.set(DATA_FLAGS_ID, b0);
	}

	public void makeStuckInBlock(BlockState p_33796_, Vec3 p_33797_) {
		if (!p_33796_.is(Blocks.COBWEB)) { super.makeStuckInBlock(p_33796_, p_33797_); }
	}\n";

$z_offset = ( $z_offset eq "" ) ? "" : ".cameraZOffset($z_offset)";
$freezing_ticks = ( $freezing_ticks eq "" ) ? "" : "\@Override\n\tpublic int getTicksRequiredToFreeze() { return $freezing_ticks; }";
$breathing_mode = ( $breathing_mode eq "" ) ? "" : ".breatheMode(TransfurVariant.BreatheMode.$breathing_mode)";
if ($powder_snow_walkable eq "true" ) { push ( @implements, "PowderSnowWalkable" ); }
$latex_type = ( $latex_type eq "" ) ? "" : "public LatexType getLatexType() { return ChangedLatexTypes.$latex_type.get(); }";
if ( $gender ) { push( @implements, "GenderedEntity" ); }
my $gender_overrides = ( $gender ) ? "\@Override\n\tpublic Gender getGender() { return Gender.$gender; }" : "";

my $centaur_overrides = ( $entity_shape ne "TAUR" ) ? '' : '@Override
	public boolean isSaddleable() { return false; }

	@Override
	public void equipSaddle(@Nullable SoundSource p_21748_) { this.equipSaddle(this, p_21748_); }

	@Override
	public boolean isSaddled() { return this.isSaddled(this); }

	protected void doPlayerRide(Player player) { this.doPlayerRide(this, player); }

	public double getPassengersRidingOffset() { return super.getPassengersRidingOffset() + getTorsoYOffset(this) - (1.0 / 8.0); }

	public InteractionResult mobInteract(Player p_30713_, InteractionHand p_30714_) {
		if (isSaddled()) {
			this.doPlayerRide(p_30713_);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}

		return InteractionResult.PASS;
	}';

if ( $entity_shape eq 'TAUR' ) { push ( @implements, ( "LatexTaur< " . $name . " >" ) ); }

my $legless_overrides = ( $entity_shape ne 'MER' && $entity_shape ne 'NAGA' ) ? '' : '@Override
	public boolean isVisuallySwimming() {
		if (this.getUnderlyingPlayer() != null &&
		  this.getUnderlyingPlayer().isEyeInFluidType( ForgeMod.WATER_TYPE.get() ) ) return true;
		return super.isVisuallySwimming();
	}';

my $transfur_mode_builder = ".transfurMode(TransfurMode.$transfur_mode)";
$transfur_color = ( $transfur_color eq "" ) ? "" : "public Color3 getTransfurColor(TransfurCause cause) { return Color3.fromInt($transfur_color); }";
$entity_shape = ( $entity_shape eq "" ) ? "" : '@Override' . "\n\tpublic getEntityShape() { return EntityShape.$entity_shape; }";

#prepare arrays{{{
foreach( @abilities ) {
	$_ = "\t\t\t   .addAbility(" . $_ . ")\n";
}

foreach ( @scares ) {
	$_ = "\t\t\t   .scares(" . $_ . ".class)\n";
}

foreach ( @attributes ) {
	$_ =~ /^(.+):(.+)/;
	my $attribute = $1;
	my $value = $2;

	$_ = "\t\tattributes.getInstance(" . $attribute . ").setBaseValue(" . $value . ");\n";
}

foreach ( @implements ) {
	$_ = $_ . ', ';
}
if ( scalar(@implements) > 0 ) { $implements[ scalar(@implements) -1 ] =~ s/, $//; }

# }}}


my $TEMPLATE;
open( $TEMPLATE, '<', $template ) or die "KSTfG: Couldn't open file $template, $!";
my @mapped_file = <$TEMPLATE>;
close ($TEMPLATE);

my $implemented = 0;

foreach ( @mapped_file ) {
	macro_loop_begin:
	$_ =~ s/PERL_ENTITY_NAME/$name/g;
	$_ =~ s/PERL_LOWERCASE_ENTITY_NAME/$name_lowercase/g;
	$_ =~ s/PERL_CAPITALIZED_ENTITY_NAME/$name_uppercase/g;
	$_ =~ s/PERL_EXTENDS/$extend/g;
	if ( scalar( @implements ) > 0 && $implemented == 0 ) {
		$_ =~ s/\/\*PERL_IMPLEMENTS\*\//implements \/\*PERL_IMPLEMENT_ARRAY\*\//; 
		$_ =~ s/\/\*PERL_IMPLEMENT_ARRAY\*\//@implements \/\*PERL_IMPLEMENT_ARRAY\*\//;
		$implemented = 1;
	}

	#DEPRECATED - Entity Init is planned to be moved.
	if ( $_ =~ /PERL_ENTITY_BUILDER/ ) {
		$_ =~ s/PERL_ENTITY_BUILDER/$builder/;
		goto macro_loop_begin;
	}

	$_ =~ s/PERL_COLOR_1ST/$egg_back/g;
	$_ =~ s/PERL_COLOR_2ND/$egg_front/g;
	$_ =~ s/\/\*PERL_BREATHE_MODE\*\//$breathing_mode/;
	$_ =~ s/\/\*PERL_FLIGHT\*\//$fly/;
	$_ =~ s/\/\*PERL_EXTRA_JUMPS\*\//$jumps/;
	$_ =~ s/\/\*PERL_CAN_CLIMB\*\//$climb/;
	$_ =~ s/\/\*PERL_DEFAULT_VISION_TYPE\*\//$vision/;
	$_ =~ s/\/\*PERL_MINING_STRENGHT\*\//$mining_speed/;
	$_ =~ s/\/\*PERL_ITEM_USE_MODE\*\//$use_item_mode/;
	$_ =~ s/\/\*PERL_SCARES\*\//@scares/;
	$_ =~ s/\/\*PERL_DEFAULT_TRANSFUR_MODE\*\//$transfur_mode_builder/;
	$_ =~ s/\/\*PERL_ABILITIES\*\//@abilities/;
	$_ =~ s/\/\*PERL_CAMERA_Z_OFFSET\*\//$z_offset/;
	$_ =~ s/\/\*PERL_DEFAULT_TRANSFUR_SOUND\*\//$transfur_sound/;
	$_ =~ s/PERL_TRANSFUR_MODE_OVERRIDE/$transfur_mode/;
	$_ =~ s/\/\*PERL_LATEX_TYPE_OVERRIDE\*\//$latex_type/;
	$_ =~ s/PERL_SPAWN_PLACEMENT/$spawn_placement/;
	$_ =~ s/PERL_SPAWN_HEIGHTMAP/$spawn_heightmap/;
	$_ =~ s/\/\*PERL_ATTRIBUTES\*\//@attributes/;
	$_ =~ s/\/\*PERL_TRANSFUR_COLOR\*\//$transfur_color/;
	$_ =~ s/\/\*PERL_TICKS_TO_FREEZE\*\//$freezing_ticks/;
	$_ =~ s/\/\*PERL_CLIMBING_OVERRIDE\*\//$climb_override/;
	$_ =~ s/\/\*PERL_LEGLESS_OVERRIDE\*\//$legless_overrides/;
	$_ =~ s/\/\*PERL_CENTAUR_OVERRIDE\*\//$centaur_overrides/;
	$_ =~ s/\/\*PERL_GENDER\*\//$gender_overrides/;
}


print @mapped_file;
exit(0);

#subroutines
sub getsopt {# {{{
	my $eval = '';

	foreach (@_) {
		if ( $eval eq '' ) {
			if ( $_ eq '-n' ) {
				$eval = "name";
				next;
			}
			if ($_ eq '-h' ) {
				printHelp();
				exit(0);
			}
		}

		if ( $eval eq 'name' ) {
			if ( $_ =~ /[A-Z][a-zA-Z0-9]*/ ) {
				$name = $_;
				$eval = '';
				next;
			}
		}
	}
	if ( $name eq '' ) { die "KSTfG: Error: no class name given.\nErrors occurred, compilation aborted\n"; }
}# }}}

sub printHelp { 
	print "
KJEntytek's303 Line Oriented Format Transfur Generator
Version $VERSION

USAGE:
./generator.sh [OPTION] [FILE]

OPTIONS:
 -h		- Displays this message
 -n NAME	- Sets class name to NAME. Required
";

} 
