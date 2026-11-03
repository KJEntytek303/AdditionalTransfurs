#Stage 1 file for autocopy.sh - perform variant klof validation

SRC_DIR_VKSTG := ./data/klofs/variants
BUILD_DIR_VKSTG := ./generated/stage1
SRCS_VKSTG := $(shell find $(SRC_DIR_VKSTG) -type f)
OBJS_VKSTG := $(SRCS_VKSTG:$(SRC_DIR_VKSTG)/%=$(BUILD_DIR_VKSTG)/%)
DEPS_VKSTG := $(OBJS_VKSTG:%=%.d) 

$(BUILD_DIR_VKSTG): 
	mkdir -p $@

.PHONY: VKSTG
VKSTG: $(OBJS_VKSTG) 
	echo $(DEPS_VKSTG)

$(BUILD_DIR_VKSTG)/%: $(SRC_DIR_VKSTG)/% $(BUILD_DIR_VKSTG)
	./vkstg.pl -o $@.d < $< > $@

#-include $(DEPS_VKSTG)
