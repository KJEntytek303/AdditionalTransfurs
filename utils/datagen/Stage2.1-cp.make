SRC_DIR_GENG := ./generated/stage1
BUILD_DIR_GENG := ./generated/stage2
SRCS_GENG := $(shell find $(SRC_DIR_GENG) -type f | grep -Ev '\.d$$')
OBJS_GENG_CP := $(foreach i,$(SRCS_GENG),$(shell my_var=`grep -E '^gendered=true'< "$(i)"`; if [[ "$$my_var" == "" ]]; then echo "$(i)" | sed -e 's#/stage1/#/stage2/#g'; fi))

.PHONY: GengCP
GengCP: $(OBJS_GENG_CP)
	
generated/stage2/%.klof: generated/stage1/%.klof
	cp $< $@

