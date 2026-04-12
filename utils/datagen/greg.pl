#!/usr/bin/perl

use warnings;
use strict;

#GReG - Global Registry Generator by KJEntytek303.

my @variants = ();
my $errored = 0;
my $output_dir = "generated/java/registry";

while ( !eof STDIN ) {
	my $tmp = <STDIN>;
	push( @variants, $tmp );
}

my @mapped_file = ();

my $i = 0;
foreach (@variants) {
	$i++;
	chomp( $_ );
	if ( ( $_ =~ /^\h*$/ ) || ( $_ =~ /^;/ )) {
		next;
	}
		
	if ( ! ( $_ =~ /^[A-Z][a-zA-Z]*/ ) ) {
		print STDERR "GReG: Invalid variant: $_, line $i\n";
		next;
	}

	push(@mapped_file, $_);
}
@variants = @mapped_file;
undef @mapped_file;
undef $i;

if ($errored == 1) {
	die "GReG: Errors Ocurred, compilation aborted.\n";
}
generateEntities(@variants);
generateLayers(@variants);
generateRenderers(@variants);
generateTransfurs(@variants);

sub generateEntities {# {{{
	my $ROBJECT = '	public static final RegistryObject<EntityType<PERL_ENTITY_NAME>> PERL_CAPITALIZED_ENTITY_NAME = PERL_ENTITY_NAME.getEntityInitRObject();';
	my $SPAWN="\t\tPERL_ENTITY_NAME.registerSpawns(event);";

	open(my $RFILE, '<', 'tmp/java/registry/InitEntities.java') or die "GReG: Couldn't open entity registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @robjects = ();
	my @spawns = ();
	foreach (@_) {
		my $variant_name = $_;

		my $variant_capitalized_name = $variant_name;
		$variant_capitalized_name =~ s/([A-Z])/_$1/g;
		$variant_capitalized_name =~ s/^_//;
		$variant_capitalized_name =~ tr/[a-z]/[A-Z]/;

		my $robject = $ROBJECT;

		$robject =~ s/PERL_ENTITY_NAME/$variant_name/g;
		$robject =~ s/PERL_CAPITALIZED_ENTITY_NAME/$variant_capitalized_name/;

		my $spawn = $SPAWN;
		$spawn =~ s/PERL_ENTITY_NAME/$variant_name/;

		push( @robjects,( $robject,"\n" ) );
		push( @spawns,  ( $spawn, "\n" ) );
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_ENTITIES\*\//@robjects/;
		$_ =~ s/\/\*PERL_REGISTER_SPAWNS\*\//@spawns/;
	}

	open( WFILE, '>', 'generated/java/registry/InitEntities.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}

sub generateLayers {# {{{
	my $IMPORT = 'import net.kjentytek303.additional_transfurs.client.renderer.model.PERL_MODEL_NAME;';
	my $ROBJECT = '		event.registerLayerDefinition(PERL_MODEL_NAME.LAYER_LOCATION, PERL_MODEL_NAME::createBodyLayer);';

	open(my $RFILE, '<', 'tmp/java/registry/InitLayerDefinitions.java') or die "GReG: Couldn't open LayerDefinition registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @imports = (); 
	my @robjects = ();
	foreach (@_) {
		my $model_name = $_ . "Model";

		my $import = $IMPORT;
		$import =~ s/PERL_MODEL_NAME/$model_name/;

		my $robject = $ROBJECT;
		$robject =~ s/PERL_MODEL_NAME/$model_name/g;

		push( @imports, $import );
		push( @robjects, $robject );
	}

	foreach (@robjects) {
		$_ = $_ . "\n";
	}

	foreach (@imports) {
		$_ = $_ . "\n";
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_MODEL_IMPORTS\*\//@imports/;
		$_ =~ s/\/\*PERL_LAYER_DEFINITIONS\*\//@robjects/;
	}

	open( WFILE, '>', $output_dir . '/InitLayerDefinitions.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}

sub generateRenderers {# {{{
	my $ROBJECT = '		registerHumanoid(event, InitEntities.PERL_CAPITALIZED_NAME.get(), PERL_RENDERER_NAME::new);';

	open(my $RFILE, '<', 'tmp/java/registry/InitRenderers.java') or die "GReG: Couldn't open EntityRenderer registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @robjects = ();
	foreach (@_) {
		my $variant_name = $_;

		my $variant_capitalized_name = $variant_name;
		$variant_capitalized_name =~ s/([A-Z])/_$1/g;
		$variant_capitalized_name =~ s/^_//;
		$variant_capitalized_name =~ tr/[a-z]/[A-Z]/;

		my $renderer_name = $variant_name . 'Renderer';

		my $robject = $ROBJECT;

		$robject =~ s/PERL_CAPITALIZED_NAME/$variant_capitalized_name/g;
		$robject =~ s/PERL_RENDERER_NAME/$renderer_name/g;

		push( @robjects, $robject );
	}

	foreach (@robjects) {
		$_ = $_ . "\n";
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_RENDERERS\*\//@robjects/;
	}

	open( WFILE, '>', $output_dir . '/InitRenderers.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}

sub generateTransfurs {# {{{
	my $ROBJECT = '	public static final RegistryObject<TransfurVariant<PERL_ENTITY_NAME>> PERL_CAPITALIZED_ENTITY_NAME_VARIANT= TF_REGISTRY.register("PERL_LOWERCASE_NAME", PERL_ENTITY_NAME::getTFInitBuilder);';

	open(my $RFILE, '<', 'tmp/java/registry/InitTransfurs.java') or die "GReG: Couldn't open TransfurVariant registry file: $!. Aborted";
	my @mapped_file = <$RFILE>;
	close($RFILE);

	my @robjects = ();
	foreach (@_) {
		my $variant_name = $_;

		my $variant_capitalized_name = $variant_name;
		$variant_capitalized_name =~ s/([A-Z])/_$1/g;
		$variant_capitalized_name =~ s/^_//;
		$variant_capitalized_name =~ tr/[a-z]/[A-Z]/;

		my $variant_lowercase_name = $variant_capitalized_name;
		$variant_lowercase_name =~ tr/[A-Z]/[a-z]/;

		my $robject = $ROBJECT;

		$robject =~ s/PERL_ENTITY_NAME/$variant_name/g;
		$robject =~ s/PERL_CAPITALIZED_ENTITY_NAME/$variant_capitalized_name/g;
		$robject =~ s/PERL_LOWERCASE_NAME/$variant_lowercase_name/g;

		push( @robjects, $robject );
	}

	foreach (@robjects) {
		$_ = $_ . "\n";
	}

	foreach (@mapped_file) {
		$_ =~ s/\/\*PERL_TRANSFURS\*\//@robjects/;
	}

	open( WFILE, '>', $output_dir . '/InitTransfurs.java' );
	print WFILE @mapped_file;
	close( WFILE );
}# }}}
