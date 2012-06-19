MAIN_CLASS = org.hettoo.vui.Test
SRC_DIR = src
BUILD_DIR = bin
CLASS_DIR = $(BUILD_DIR)/classes

SOURCES = $(shell find $(SRC_DIR) -iname '*.java')
CLASSES = $(subst .java,.class,$(subst $(SRC_DIR),$(CLASS_DIR),$(SOURCES)))

all: classes

run: classes
	java -cp $(CLASS_DIR) $(MAIN_CLASS)

classes: $(CLASSES)

$(CLASSES): $(CLASS_DIR) $(SOURCES)
	javac -Xlint:unchecked -d $(CLASS_DIR) $(SOURCES)

$(CLASS_DIR):
	mkdir -p $@

.PHONY: all run classes
