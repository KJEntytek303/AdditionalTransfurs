#!/usr/bin/perl
#VKSTG - Validator of KJEntytek's Simple Transfur Generator
#WARNING: Shell Tainted script.

use strict;
#use warnings; #ENABLE ONLY DURING TESTING, generally keep it disabled on production.

if( $^O ne "linux" && $^O ne "darwin" ) {
	print STDERR "Warning: Shell tainted script.\n";
	print STDERR "Unexpected errors might arise on windows\n";
}

my @mapped_file = ();
my @IFILE = ();
my $errored = 0;
my $mode = 'NORMAL';
my $array = '';
my $current_array = '';


while ( !eof STDIN ) {
	push( @IFILE, <STDIN> );
}


my $i = 0;

my @dependencies = ();

reevaluate_file:


foreach ( @IFILE ) {

	$i++;
	loop_begin:

	if( ( $_ =~ /^;/ ) || ( $_ =~ /^\h*$/ ) ) { 
		next;
	}

	if ($mode eq 'NORMAL') { #{{{

		if ( $_ =~ /^([A-Z_]+)=\[\h*/ ) { #if array opening {{{
			$mode ='ARRAY';
			goto loop_begin; #reevaluate as array.
		} # }}}

		if ( $_ =~ /^TEMPLATE=(.+)/ ) { # {{{
			if(-f ( "data/java/tf-templates/$1" . ".java.template" ) ) {
				push( @mapped_file, $_ );
				next;
			}

			$errored = 1;
			print STDERR "Error: Template file $1 not found, line $i\n";
			print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
			next;
		} #}}}

		if ( $_ =~ /^RENDERER_TEMPLATE=(.+)/ ) {
			if( -f "data/java/renderers/$1.java" ) {
				push ( @mapped_file, $_ );
				next;
			}

			$errored = 1;
			print STDERR "Error: Renderer template $1 not found, line $i\n";
			print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
			next;
		}
		if ( $_ =~ /^ACCESSORIES=(.+)/ ) {
			if ( -f "data/data/additional_transfurs/accessories/entities/$1" ) {
				push ( @mapped_file, $_ );
				next;
			}
			$errored = 1;
			print STDERR "Error: $_: data/data/additional_transfurs/accessories/$1: No such file, line $i\n";
			print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
			next;
		}

		if ( $_ =~ /^EXTEND=([a-zA-Z0-9])+\h*/ ||
		$_ =~ /^TRANSFUR_SOUND=(.+)\h*/ ||
		$_ =~ /^TRANSFUR_MODE=(ABSORPTION|REPLICATION|NONE)\h*/ || 
		$_ =~ /^MINING=(WEAK|NORMAL|STRONG)\h*/ || 
		$_ =~ /^ENTITY_SHAPE=(ANTHRO|FERAL|TAUR|NAGA|MER)\h*/ || 
		$_ =~ /^USE_ITEM_MODE=(NORMAL|MOUTH|NONE)\h*/ || 
		$_ =~ /^FLY=(NONE|CT|ELYTRA|BOTH)\h*/ ||
		$_ =~ /^JUMPS=(\d+)\h*/ ||
		$_ =~ /^VISION=(NORMAL|NIGHT_VISION|BLIND|REDUCED|VAVE_VISION)\h*/ ||
		$_ =~ /^CLIMB=(true|false)\h*/ || 
		$_ =~ /^Z_OFFSET=(\d+\.\d+)\h*/ ||
		$_ =~ /^TICKS_TO_FREEZE=(\d+)\h*/ ||
		$_ =~ /^BREATH=(NORMAL|WATER|ANY|NONE)\h*/ ||
		$_ =~ /^POWDER_SNOW_WALKABLE=(true|false)\h*/ ||
		$_ =~ /^TRANSFUR_COLOR=(0x[0-9a-fA-F]{,6})\h*/ ||
		$_ =~ /^ABILITY_COLOR_1ST=(0x[0-9a-fA-F]{,6})\h*/ ||
		$_ =~ /^ABILITY_COLOR_2ND=(0x[0-9a-fA-F]{,6})\h*/ ||
		$_ =~ /^LATEX_TYPE=(WHITE_LATEX|DARK_LATEX|NONE)/ ||
		$_ =~ /^GENDER=(MALE|FEMALE)/ ||
		$_ =~ /^SPAWN_PLACEMENT=(ON_GROUND|IN_WATER|NO_RESTRICTIONS|IN_LAVA|null)/ ||
		$_ =~ /^SPAWN_HEIGHTMAP=(WORLD_SURFACE_WG|WORLD_SURFACE|OCEAN_FLOOR_WG|OCEAN_FLOOR|MOTION_BLOCKING|MOTION_BLOCKING_NO_LEAVES|null)/ ||
		$_ =~ /^BUILDER=(.+)/ ||
		$_ =~ /^MIN_SPAWN=(\d)*/ ||
		$_ =~ /^MAX_SPAWN=(\d)*/ ||
		$_ =~ /^SPAWN_WEIGHT=(\d)*/ ||
		$_ =~ /^RENDERER_TYPE=/ ||
		$_ =~ /^MULTIHANDED_RENDERER=(true|false)/ ||
		$_ =~ /^ARMOR_MODEL=(.+)/ ||
		$_ =~ /^EYES_PRESENT=(true|false)\h*$/ ||
		$_ =~ /^IRIS_1ST_COLOR=(0x([0-9a-fA-F]{,6})\h*(dl|)\h*$)/ ||
		$_ =~ /^IRIS_2ND_COLOR=(0x([0-9a-fA-F]{,6})\h*(dl|)\h*$)/ ||
		$_ =~ /^SCLERA_COLOR=(0x([0-9a-fA-F]{,6})\h*(dl|)\h*$)/ ||
		$_ =~ /^GAS_MASK_LAYER=(.+)/ ||
		$_ =~ /^IS_MASKED=(true|false)/ ||
		$_ =~ /^EMISSIVE_LAYER=(true|false)/ ||
		$_ =~ /^TRANSLUCENT_LAYER=(true|false)/ ||
		$_ =~ /^MODEL_SCALE=(\d+\.\d+)/ ||
		$_ =~ /^SHADOW_SIZE=(\d+\.\d+)/ ||
		$_ =~ /^ANIM_PRESET=(.)+\h*$/ ||
		$_ =~ /^RIDING_OFFSET=(\d+\.\d+)/ ||
		$_ =~ /^GENDERED=(true|false)/
		) { push( @mapped_file, $_ ); next; }

		if ( $_ =~ /^BIOME_PRESET=(\.)/ ) {
			if ( !(-f ("data/data/additional_transfurs/forge/biome_modifier/$1" . ".bpst" ))) {
				$errored = 1;
				print STDERR "Error: No such file 'data/data/additional_transfurs/forge/biome$1', line $i:\n$_";
				print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
			}
			next;
		}

		$errored = 1;
		chomp( $_);
		print STDERR "Invalid option $_, line $i\n";
		print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
		next;
	} #}}}

	if ( $mode eq 'ARRAY' ) { # {{{

		if ( $_ =~ /^]\h*/ ) { #array end
			$array = '';
			$mode = 'NORMAL';
			push( @mapped_file, $_ );
			next;
		} 

		if ( $array eq '' ) { # if we drop from normal mode, get option 
			$_ =~ /([A-Z]+)=\[\h*/;
			$array = $1;
			push( @mapped_file, $_ );
			next;
		}

		if ( $array eq 'PRESETS' ) {
			$_ =~ /([^\h])/;
			my $tmp = $_;
			chomp $tmp;
			if ( -f "data/klofs/presets/$tmp.klof" ) {
				push( @dependencies, "data/klofs/presets/$tmp.klof");
				next;
			}

			$errored = 1;
			print STDERR "Error: data/klofs/presets/$_.klof: No such file, line $i";
			print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
			next;
		}

		if ( $array eq 'TAGS' ) {

			if ( $_ =~ /((^[a-z][a-z0-9_]*):([a-z][a-z0-9_\/]*))\h*(false|)?\h*$/ ) {
				if ( -f "tmp/data/$2/tags/entity_types/$3.mctag" ) {
					push ( @mapped_file, $_ );
					next;
				}
				print STDERR "Error: tmp/data/$2/tags/entity_types/$3.mctag : No such file, line $i\n";
				print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
				next;
			}

			$errored = 1;
			print STDERR "Error: Invalid tag entry '$_'\n";
			print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
		}

		if ( $array eq 'ABILITIES' ||
			$array eq 'ATTRIBUTES' ||
			$array eq 'SCARES' ||
			$array eq 'DIMENSIONS'
		) { push( @mapped_file, $_ ); next; };

		print STDERR "Unknown array definition: \"$array\", field: \"$_\", line $i";
		print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
		$errored = 1;
		next;
	} #}}}

	$errored = 1;
	print STDERR "Internal Compiler Error - bad mode: $mode, line $i\n";
	print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
}

if ( scalar(@dependencies) != 0 ) {
	resolveDependencies($dependencies[0]);
	shift( @dependencies );
	goto reevaluate_file;
}


if ( $errored ) { die 'Errors occurred, compilation aborted'; }

print @mapped_file;

sub resolveDependencies() {
	#Inject the file at the beginning.
	my @tmp;
	
	if ( $^O eq "MSWin32" ) {

		print STDERR "Warning: VKSTG is running untested PS commands.\n";

 		( @tmp = `.\\vkstg.pl < $_[0]` );
		system( ".\\vkstg.pl < $_[0] > %homedrive%%homepath%\\trash.tmp" ) && die "Broken template file $_[0]\n";
		system( "del %homedrive%%homepath%\\trash.tmp" );

	}
	else { #likely linux and mac	
 		( @tmp = `./vkstg.pl < $_[0]` );
		system( "./vkstg.pl < $_[0] > /dev/null" ) && die "Broken template file $_[0]\n";
	}
	splice (@mapped_file, 0, 0, @tmp);
	@IFILE = @mapped_file;
	@mapped_file = ();
}
