#!/usr/bin/perl
#GenG - Gendered Generator
#Warning: Shell tainted script.
#This is the only script from KSTfG toolset that is supposed to run before VKSTG.

use strict;
use warnings;

if( $^O ne "linux" && $^O ne "darwin" ) {
	print STDERR "Warning: Shell tainted script.\n";
	print STDERR "Unexpected errors might arise on Windows\n";
}


opendir(my $DIR, "tmp/klofs/variants") or die "Couldn't open directory './tmp/klofs/variants/': $!";
my @files = readdir($DIR);
closedir ($DIR);

my $mode = 'NORMAL';

foreach (@files) {
	$_ = "tmp/klofs/variants/$_";
	my $is_gendered="false";

	open( my $FILE, "<", $_ ) or die "Couldn't open file $_: $!";
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
		
		system("cp", $_, $tmp . "Male.klof");
		system("cp", $_, $tmp . "Female.klof");
		system("rm $_");
	}
}
