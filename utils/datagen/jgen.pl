#!/usr/bin/perl
#JGen - JSON Generator

use strict;
use warnings;

my $greg = "tmp/variants.greg";
my $mirrored_file = $greg;
my @spawn_egg_regex = ();
my @spawn_egg_entries = ();
my @entity_entries = ();
my @bag_of_bytes=();

open ( my $GREGFILE, "<", $greg );
open ( my $MIRRORFILE, "<", $mirrored_file );

getlopt();

my @mapped_greg = <$GREGFILE>;
my @mapped_mirror = <$MIRRORFILE>;

close ($GREGFILE);
close ($MIRRORFILE);

for ( my $i=0; $i < scalar( @mapped_greg); $i++ ) {
	
	chomp ( $mapped_greg[$i] );
	if ( $mapped_greg[$i] =~ /^\h*$/ ) {
		next;
	}

	my $name_lowercase = $mapped_greg[$i];
	$name_lowercase =~ s/([A-Z])/_$1/g;
	$name_lowercase =~ s/^_//;

	my $name_spaced = $name_lowercase;
	$name_spaced =~ tr/_/ /;

	$name_lowercase =~ tr/[A-Z]/[a-z]/;

	open ( EGGFILE, ">", "generated/assets/additional_transfurs/models/item/" . $name_lowercase . "_spawn_egg.json" );
	print EGGFILE '{
	"parent": "minecraft:item/template_spawn_egg"
}';
	close (EGGFILE);

	push (@entity_entries,  'entity.additional_transfurs.' . $name_lowercase . ': ' . $name_spaced . "\n" ) ;

	#Generate Lang Entries for spawn eggs
	if ( scalar( @spawn_egg_regex ) == 0 ) {
		push ( @spawn_egg_regex, '$spawn_egg_entry =~ s/$/ Spawn Egg/' );
	}
	
	my $spawn_egg_entry = $name_spaced;

	foreach ( @spawn_egg_regex ) {
		eval;
	}
	
	
	push ( @spawn_egg_entries, 'item.additional_transfurs.' . $name_lowercase . '_spawn_egg: ' . $spawn_egg_entry . "\n" );
}

my @mapped_lang = ();

while ( ! eof STDIN ) {
	@mapped_lang = <STDIN>;
}

foreach (@mapped_lang) {


	$_ =~ s/PERL_SPAWN_EGGS/@spawn_egg_entries/;
	$_ =~ s/PERL_ENTITIES/@entity_entries/;
}

print @mapped_lang;


sub getlopt {# {{{
	for ( my $i = 0; $i < scalar(@ARGV); $i++ ) {
		
		if ( $ARGV[$i] =~ /^--lang-file=(.+)$/ ) {
			if ( -f $1 ) {
				$mirrored_file = $1;
				next;
			}
			die "JGen: Error: $1: No such file."
		}

		if ( $ARGV[$i] =~ /^--regex=(.+)$/ ) {
			push (@spawn_egg_regex, $1);
			next;
		}
		die "JGen: Invalid option $ARGV[$i]";
	}
}# }}}
