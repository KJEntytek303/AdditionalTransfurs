#!/usr/bin/perl
#GenG - Gendered Generator
#Warning: Shell tainted script.
#This is the only script from KSTfG toolset that is supposed to run before VKSTG.

use strict;
use warnings;
use File::Copy;

my @gendered_pairs = ();

opendir(my $DIR, "tmp/klofs/variants") or die "GenG: Couldn't open directory './tmp/klofs/variants/': $!";
my @files = readdir($DIR);
closedir ($DIR);

my $mode = 'NORMAL';

foreach (@files) {
	$_ = "tmp/klofs/variants/$_";
	my $is_gendered="false";

	open( my $FILE, "<", $_ ) or die "Geng: Couldn't open file $_: $!";
	foreach (<$FILE>) {
	
		if( ( $_ =~ /^;/ ) || ( $_ =~ /^\h*$/ ) ) { 
			next;
		}
	
		if ($mode eq 'NORMAL') {
	
			if ( $_ =~ /^([A-Z_]+)=\[\h*/ ) { #if array opening
				$mode ='ARRAY';
				next;
			}

			if ( $_ =~ /^GENDERED=(true|false)/ ) { $is_gendered = $1; last; }
		}
	
		if ( $mode eq 'ARRAY' ) {
			if ( $_ =~ /^]/ ) { $mode = 'NORMAL'; }
			next;
		}
	}
	close($FILE);
	
	if ($is_gendered eq "true") {
		my $tmp = $_;
		$tmp =~ s/\.klof$//;
		my $male_path = $tmp . "Male.klof";
		my $female_path = $tmp . "Female.klof";
		my $FILEHANDLE;

		copy( $_, $male_path );
		copy( $_, $female_path );
		
		unlink $_;
		open ( $FILEHANDLE, ">>", $male_path );
		print $FILEHANDLE "\nGENDER=MALE\n";
		close $FILEHANDLE;
		
		open ( $FILEHANDLE, ">>", $female_path );
		print $FILEHANDLE "\nGENDER=FEMALE\n";
		close $FILEHANDLE;
		$male_path =~ s/.klof$//;
		$male_path =~ s/^.+\/([A-Za-z]+)$/$1/;

		$female_path =~ s/.klof$//;
		$female_path =~ s/^.+\/([A-Za-z]+)$/$1/;

		
		my $male_name_uppercase = $male_path;
		$male_name_uppercase =~ s/([A-Z])/_$1/g;
		$male_name_uppercase =~ s/^_//;
		$male_name_uppercase =~ tr/[a-z]/[A-Z]/;

		my $female_name_uppercase = $male_name_uppercase;
		$female_name_uppercase =~ s/MALE$/FEMALE/;

		my $entity_name_uppercase = $male_name_uppercase;
		$entity_name_uppercase =~ s/_MALE//;

		my $robject = "\tpublic static final GenderedPair < " . $male_path . ", " . $female_path . " > " . $entity_name_uppercase . "S = registerPair( " . $male_name_uppercase . ", " . $female_name_uppercase . " );\n";

		push( @gendered_pairs, $robject );
	}
}

my @mapped_file = ();

open ( my $TF_REG, "<", "tmp/java/registry/InitTransfurs.java" );
@mapped_file = <$TF_REG>;
close $TF_REG;

foreach ( @mapped_file ) {
	$_ =~ s/\/\*PERL_GENDERED\*\//@gendered_pairs/;
}

open WFILE, ">", "tmp/java/registry/InitTransfurs.java";
print WFILE @mapped_file;
close WFILE;


