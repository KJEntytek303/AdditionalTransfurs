SRC_DIR_GENG := ./generated/stage1
BUILD_DIR_GENG := ./generated/stage2
SRCS_GENG := $(shell find $(SRC_DIR_GENG) -type f | grep -Ev '\.d$$')
OBJS_GENG_SPLIT := $(foreach i,$(SRCS_GENG),$(shell my_var=`grep -E '^gendered=true'< "$(i)"`; if [[ "$$my_var" != "" ]]; then echo $(i) | sed -e 's/^\(.*\)$$/& &/' -e 's/\.klof/Female.klof/' -e 's/\.klof$$/Male.klof/' | sed -e 's#/stage1/#/stage2/#g'; fi ))

.PHONY: GengSplit
GengSplit: $(OBJS_GENG_SPLIT)
	
$(BUILD_DIR_GENG)/%Female.klof: $(SRC_DIR_GENG)/%.klof
	cp $< $@
	echo -e "\ngender=FEMALE" >> $@

$(BUILD_DIR_GENG)/%Male.klof: $(SRC_DIR_GENG)/%.klof
	cp $< $@
	echo -e "\ngender=MALE\n" >> $@

