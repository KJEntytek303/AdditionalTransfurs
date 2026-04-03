#!/bin/bash
rm -r tmp/ generated/
mkdir -p generated/ tmp/
cp -R data/* tmp/

#GenG generates gendered variants before the cache is generated, and before validator.
./geng.pl

#Create asset and data directory structure.
find tmp -type f > tmp/cache
find data/data -type d | sed -e "s/^data/generated/" | xargs mkdir
find data/assets -type d | sed -e "s/^data/generated/" | xargs mkdir
mkdir -p generated/java/renderers generated/java/registries generated/java/transfurs/

#Run Validator and cache results.
#Fire Transfur generator which might append stuff to GTMPG and GREG, as well as generate additional data and asset files.

errored=0
variants=$( ls -1 tmp/klofs/variants )
for variant in ${variants[@]}; do
	variant_name=$( echo $variant | sed -e 's/\.klof$//')
	variant="tmp/klofs/variants/${variant}"

	./vkstg.pl < $variant > tmp/vkstg.tmp
	if [[ $? != 0 && !$errored ]]; then
		errored=1;
		continue;
       	fi
	
	./kstfg.pl -n $variant_name < tmp/vkstg.tmp > generated/java/transfurs/$variant_name.java
	./rgktg.pl -n $variant_name < tmp/vkstg.tmp > generated/java/renderers/${variant_name}Renderer.java
	echo -e "\n$variant_name" >> tmp/variants.greg
done

#GTMPG and GREG might get extra input from KSTfG. That's why they must be fired later.

./gtmpg.pl
#accessory slots. Unused now.
#./gtmpg.pl -t tmp/<shit> -e cacs
./greg.pl < tmp/variants.greg

cp -R ./generated/java/registries/* ../../src/main/java/net/kjentytek303/additional_transfurs/init/
cp -R ./generated/java/transfurs/* ../../src/main/java/net/kjentytek303/additional_transfurs/entity/generated/
cp -R ./generated/java/renderers/* ../../src/main/java/net/kjentytek303/additional_transfurs/client/renderer/generated/
cp -R ./generated/data/* ../../src/main/resources/data/
cp -R ./generated/assets/*
