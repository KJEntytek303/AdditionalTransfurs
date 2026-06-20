#!/usr/bin/perl
package eklof;

use strict;
use warnings;
use Exporter qw(import);

our @EXPORT_OK = qw( get getFromArray getFromHash
			set
			slicePath
			$lof_errno $lof_errlvl
			);
our %EXPORT_TAGS = ( "all" => \@EXPORT_OK);

our $lof_errno = "";
our $lof_errlvl = 1;

#Eklof is either an array or a hash.
#constructors

#Getters
sub get { # Eklof* data_struct, scalar path
	my $data_reference = $_[0];
	my @paths = slicePath( $_[1] );

	if ( !defined $paths[0] ) {
		_LOF_Error ( "Bad Path: '" . $_[1] . "' when trying to access an array, stacktrace: " . $_[1] . ".\n" );
		return undef;
	}

	my $type = ref $data_reference;
	my $current_element;

	#case $type in
	if ( $type eq "ARRAY" ) {
		$current_element = getFromArray( @_ );
		goto ret;
	}

	if ( $type eq "HASH") {
		$current_element = getFromHash( @_ );
		goto ret;
	}

	if ( $type eq "" ) { #is scalar
		goto ret;
	}
	#default:
	_LOF_Error( "Unknown element type '" . $type . "' at " . $paths[0] );
	return undef;
	#esac

	ret:
	if ( !defined $current_element ) {
		_LOF_Warn( "Undefined element: " . $paths[0] . ".\n" );
	}
	return $current_element;

	
}

sub set { # Eklof* data_struct, scalar path, scalar_or_ref value;
	
}

sub slicePath { # scalar string
 	$_[0] =~ /^([a-zA-Z0-9_]+)\.(.*)$/;
	if ( !defined $1 ) { return undef; }
	my $first = $1;
	my $last = $2;

	return ($first, $last);
}

#Internal, but if someone knows the exact type it might be useful
sub getFromArray { # Eklof* data_struct, scalar path {{{
	my $data_reference = $_[0];
	my @paths = slicePath( $_[1] );
	
	if ( $paths[0] =~ /^([^\d]+)$/ ) {
		_LOF_Error ( "Bad Path: '" . $paths[0] . "' when trying to access an array, stacktrace: " . $paths[0] . ".\n" );
		return undef;
	}

	if ( $paths[0] > $#{$data_reference} || $paths[0] < 0 ) {
		_LOF_Warn ( "Array out of bounds: '" . $paths[0] . "' stacktrace: " . $paths[0] . ".\n" );
		return undef;
	}

	my $obtained_element = ${$data_reference}[$paths[0]];
	if ( !defined $obtained_element ) {
		_LOF_Warn( "Undefined element: " . $paths[0] . ".\n" );
		return undef;
	}

	if ( !defined $paths[1] || $paths[1] eq '') {
		return $obtained_element;
	}

	my $ret = get( $obtained_element, $paths[1] );
	if ( !defined $ret ) {
		_LOF_Warn( "Undefined element: " . $paths[0] . ".\n" );
       	}
	return $ret;
}# }}}

sub getFromHash {
	my $data_reference = $_[0];
	my @paths = slicePath( $_[1] );

	my $obtained_element = ${$data_reference}{$paths[0]};
	if ( !defined $obtained_element ) {
		_LOF_Warn( "Undefined element: " . $paths[0] . ".\n" );
		return undef;
	}

	if ( !defined $paths[1] || $paths[1] eq '') {
		return $obtained_element;
	}

	my $ret = get( $obtained_element, $paths[1] );
	if ( !defined $ret ) {
		_LOF_Warn( "Undefined element: " . $paths[0] . ".\n" );
       	}
	return $ret;

}

#internal, set up your own error messager
sub _LOF_Error { # scalar error_msg
	$lof_errno .= $_[0];
	if ( $lof_errlvl >= 1 ) {
		print STDERR $_[0];
	}
}

sub _LOF_Warn { # scalar error_msg
	$lof_errno .= $_[0];
	if ( $lof_errlvl >= 2 ) {
		print STDERR $_[0];
	}
}


1;
