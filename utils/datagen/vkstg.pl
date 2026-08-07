#!/usr/bin/perl
#VKSTG - Validator of KJEntytek's Simple Transfur Generator
#WARNING: Shell Tainted script.

use strict;
use warnings;

if( $^O ne "linux" && $^O ne "darwin" ) {
	print STDERR "Warning: Shell tainted script.\n";
	print STDERR "Unexpected errors might arise on windows\n";
}

my @mapped_file = ();
my @IFILE = ();
my $errored = 0;
my $errored_array= 0 ;
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

		if ( $_ =~ /^([a-zA-Z0-9_]+)=\[\h*/ ) { #if array opening {{{
			$mode ='ARRAY';
			goto loop_begin; #reevaluate as array.
		} # }}}

		if ( $_ =~ /^template=(.+)/ ) { # {{{
			if(-f ( "data/java/tf-templates/$1" . ".java.template" ) ) {
				push( @mapped_file, $_ );
				next;
			}

			$errored = 1;
			print STDERR "Error: Template file $1 not found, line $i\n";
			print STDERR "\tAt:\n\t$IFILE[$i-1]\n";
			next;
		} #}}}

		if ( $_ =~ /^rendererTemplate=(.+)/ ) {
			if( -f "data/java/renderers/$1" ) {
				push ( @mapped_file, $_ );
				next;
			}

			$errored = 1;
			print STDERR "Error: Renderer template $1 not found, line $i\n";
			next;
		}
		if ( $_ =~ /^accessories=(.+)/ ) {
			if ( -f "data/data/additional_transfurs/accessories/entities/$1" ) {
				push ( @mapped_file, $_ );
				next;
			}
			$errored = 1;
			print STDERR "Error: $_: data/data/additional_transfurs/accessories/$1: No such file, line $i\n";
			next;
		}

		if ( $_ =~ /^extend=([a-zA-Z0-9])+\h*/ ||
		$_ =~ /^transfurSound=(.+)\h*/ ||
		$_ =~ /^transfurSound=(ABSORPTION|REPLICATION|NONE)\h*/ || 
		$_ =~ /^mining=(WEAK|NORMAL|STRONG)\h*/ || 
		$_ =~ /^entityShape=(ANTHRO|FERAL|TAUR|NAGA|MER)\h*/ || 
		$_ =~ /^useItemMode=(NORMAL|MOUTH|NONE)\h*/ || 
		$_ =~ /^fly=(NONE|CT|ELYTRA|BOTH)\h*/ ||
		$_ =~ /^jumps=(\d+)\h*/ ||
		$_ =~ /^vision=(NORMAL|NIGHT_VISION|BLIND|REDUCED|VAVE_VISION)\h*/ ||
		$_ =~ /^climb=(true|false)\h*/ || 
		$_ =~ /^cameraZOffset=(\d+\.\d+)\h*/ ||
		$_ =~ /^ticksToFreeze=(\d+)\h*/ ||
		$_ =~ /^breath=(NORMAL|WATER|ANY|NONE)\h*/ ||
		$_ =~ /^powerSnowWalkable=(true|false)\h*/ ||
		$_ =~ /^transfurColor=(0x[0-9a-fA-F]{,6})\h*/ ||
		$_ =~ /^abilityColor_1=(0x[0-9a-fA-F]{,6})\h*/ ||
		$_ =~ /^abilityColor_2=(0x[0-9a-fA-F]{,6})\h*/ ||
		$_ =~ /^latexType=(WHITE_LATEX|DARK_LATEX|NONE)/ ||
		$_ =~ /^gender=(MALE|FEMALE)/ ||
		$_ =~ /^spawn_placement=(ON_GROUND|IN_WATER|NO_RESTRICTIONS|IN_LAVA|null)/ ||
		$_ =~ /^spawn_heightmap=(WORLD_SURFACE_WG|WORLD_SURFACE|OCEAN_FLOOR_WG|OCEAN_FLOOR|MOTION_BLOCKING|MOTION_BLOCKING_NO_LEAVES|null)/ ||
		$_ =~ /^builder=(.+)/ ||
		$_ =~ /^spawn_min=(\d)*/ ||
		$_ =~ /^spawn_max=(\d)*/ ||
		$_ =~ /^spawn_weight=(\d)*/ ||
		$_ =~ /^rendererType=/ ||
		$_ =~ /^multihandedRenderer=(true|false)/ ||
		$_ =~ /^armorModel=(.+)/ ||
		$_ =~ /^eyes_present=(true|false)\h*$/ ||
		$_ =~ /^eyes_iris_1_color=(0x([0-9a-fA-F]{,6})\h*(dl|)\h*$)/ ||
		$_ =~ /^eyes_iris_2_color=(0x([0-9a-fA-F]{,6})\h*(dl|)\h*$)/ ||
		$_ =~ /^eyes_sclera_color=(0x([0-9a-fA-F]{,6})\h*(dl|)\h*$)/ ||
		$_ =~ /^gasMaskLayer=(.+)/ ||
		$_ =~ /^isMasked=(true|false)/ ||
		$_ =~ /^emissiveLayer=(true|false)/ ||
		$_ =~ /^translucentLayer=(true|false)/ ||
		$_ =~ /^modelScale=(\d+\.\d+)/ ||
		$_ =~ /^shadowSize=(\d+\.\d+)/ ||
		$_ =~ /^animation_preset=(.)+\h*$/ ||
		$_ =~ /^ridingOffset=(\d+\.\d+)/ ||
		$_ =~ /^gendered=(true|false)/
		) { push( @mapped_file, $_ ); next; }

		if ( $_ =~ /^biomePreset=(\.)/ ) {
			if ( !(-f ("data/data/additional_transfurs/forge/biome_modifier/$1" . ".bpst" ))) {
				$errored = 1;
				print STDERR "Error: No such file 'data/data/additional_transfurs/forge/biome$1', line $i:\n$_";
			}
			next;
		}

		$errored = 1;
		chomp( $_);
		print STDERR "Invalid option $_, line $i\n";
		next;
	} #}}}

	if ( $mode eq 'ARRAY' ) { # {{{

		if ( $_ =~ /^]\h*/ ) { #array end
			$array = '';
			$errored_array = 0;
			$mode = 'NORMAL';
			push( @mapped_file, $_ );
			next;
		} 

		if ( $array eq '' ) { # if we drop from normal mode, get option 
			$_ =~ /([A-Za-z0-9]+)=\[\h*/;
			$array = $1;
			push( @mapped_file, $_ );
			next;
		}

		if ( $array eq 'presets' ) {
			$_ =~ /([^\h])/;
			my $tmp = $_;
			chomp $tmp;
			if ( -f "data/klofs/presets/$tmp.klof" ) {
				push( @dependencies, "data/klofs/presets/$tmp.klof");
				next;
			}

			$errored = 1;
			print STDERR "Error: data/klofs/presets/$_.klof: No such file, line $i";
			next;
		}

		if ( $array eq 'tags' ) {

			if ( $_ =~ /((^[a-z][a-z0-9_]*):([a-z][a-z0-9_\/]*))\h*(false|)?\h*$/ ) {
				if ( -f "tmp/data/$2/tags/entity_types/$3.mctag" ) {
					push ( @mapped_file, $_ );
					next;
				}
				print STDERR "Error: tmp/data/$2/tags/entity_types/$3.mctag : No such file, line $i\n";
				next;
			}

			$errored = 1;
			print STDERR "Error: Invalid tag entry '$_', line $i\n";
		}

		if ( $array eq 'abilities' ||
			$array eq 'attributes' ||
			$array eq 'scares'
		) { push( @mapped_file, $_ ); next; };

		if ( $errored_array == 0 ) {
			print STDERR "Unknown array definition: \"$array\", line " . ($i-1) . "\n";
			$errored_array=1;
			$errored = 1;
		}
		next;
	} #}}}

	$errored = 1;
	print STDERR "Internal Compiler Error - bad mode: $mode, line $i\n";
	print STDERR "\tAt:\n\t\n";
}

if ( scalar(@dependencies) != 0 ) {
	foreach (@dependencies) {
		resolveDependencies($_)
	}
	@dependencies = ();
	@IFILE = @mapped_file;
	@mapped_file = ();
	goto reevaluate_file;
}


if ( $errored ) { die 'Errors occurred, compilation aborted'; }

print @mapped_file;

sub resolveDependencies {
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
		if ( scalar(@tmp) == 0 ) {
			print STDERR "vkstg: Broken or empty template file $_[0]\n";
			$errored = 1;
		}
	}
	splice (@mapped_file, 0, 0, @tmp);
}
