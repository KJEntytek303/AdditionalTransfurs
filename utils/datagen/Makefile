#What does ATGT do?
#Generates transfurs. GenG vs cp conflicts, and GenG vs make deps conflicts must be resolved, as GenG alters VSKTG deps.
#Generates registries. GReG and IHDa conflicts must be resolved.
#Generates translations. JGen and IHDa conflicts must be resolved. Lang file should probably have multiple generation phases, and should be treated as unstructured, non-standard KLOF. Might need own Makefile.
#Generates datapack-related stuff. GTMPG, IHDa and cp conflicts must be resolved.
#Generates assets. IHDa and cp conflicts must be resolved.
#Generates models. Unautomatable, bjcj must be used manually or completely rewritten.


.PHONY: all
all: stage1_vkstg stage2_geng stage3_kstfg stage4_greg stage5_cp

include Stage1-VKSTG.make
include Stage2-GenG.make
#include Stage3-KSTfG.make
#include Stage4-GReG.make

.PHONY: stage1_vkstg
stage1_vkstg: VKSTG

.PHONY: stage2_geng
stage2_geng: GenG

.PHONY: stage3_kstfg
stage3_kstfg: stage2_geng

.PHONY: stage4_greg
stage4_greg: stage3_kstfg

.PHONY: stage5_cp
stage5_cp: stage4_greg

.PHONY: clean
clean:
	-rm -r generated/
