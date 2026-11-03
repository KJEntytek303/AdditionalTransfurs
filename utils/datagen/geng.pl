#!/usr/bin/perl
#GenG - Gendered Generator

use strict;
use warnings;
use File::Copy;

my @gendered_pairs = ();


my $mode = 'NORMAL';

if(scalar(@ARGV) != 1) {
	die "Incorrect number of arguments, expected 1";
}

my $ifile = $ARGV[0];
my $ofile = $ifile;
$ofile =~ s/stage1/stage2/;
my $is_gendered="false";

open( my $FILE, "<", $ifile ) or die "Geng: Couldn't open file $ifile: $!";
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
	$ofile =~ s/\.klof$//;
	my $male_path = $ofile . "Male.klof";
	my $female_path = $ofile . "Female.klof";
	my $FILEHANDLE;

	copy( $_[0], $male_path );
	copy( $_[0], $female_path );
	
	open ( $FILEHANDLE, ">>", $male_path );
	print $FILEHANDLE "\ngender=MALE\n";
	close $FILEHANDLE;
	
	open ( $FILEHANDLE, ">>", $female_path );
	print $FILEHANDLE "\ngender=FEMALE\n";
	close $FILEHANDLE;
	$male_path =~ s/.klof$//;
	$male_path =~ s/^.+\/([A-Za-z]+)$/$1/;

	$female_path =~ s/.klof$//;
	$female_path =~ s/^.+\/([A-Za-z]+)$/$1/;

	push( @gendered_pairs, $ofile );
} else {
	copy( $ifile, $ofile);
}
