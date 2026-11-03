
SRC_DIR_GENG := ./generated/stage1
BUILD_DIR_GENG := ./generated/stage2

SRCS_GENG := $(shell find $(SRC_DIR_GENG) -type f )
OBJS_GENG_CP := $(foreach i,$(SRCS_GENG),$(shell my_var=`grep -E '^gendered=true'< "$(i)"`; if [[ "$$my_var" == "" ]]; then echo "$(i)" | sed -e 's#/stage1/#/stage2/#g'; fi))
OBJS_GENG_SPLIT := $(foreach i,$(SRCS_GENG),$(shell my_var=`grep -E '^gendered=true'< "$(i)"`; if [[ "$$my_var" != "" ]]; then echo $(i) | sed -e 's/^\(.*\)$$/& &/' -e 's/\.klof/Female.klof/' -e 's/\.klof$$/Male.klof/' | sed -e 's#/stage1/#/stage2/#g'; fi ))

$(BUILD_DIR_GENG):
	mkdir $@

.PHONY: GenG
GenG: $(OBJS_GENG_CP) $(OBJS_GENG_SPLIT) $(BUILD_DIR_GENG) VKSTG

$(OBJS_GENG_CP): VKSTG $(BUILD_DIR_GENG)
	$(MAKE) -f Stage2.1-cp.make

$(OBJS_GENG_SPLIT): VKSTG $(BUILD_DIR_GENG)
	$(MAKE) -f Stage2.2-clone.make

