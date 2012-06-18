PACKAGE = org.hettoo.vui
MAIN_CLASS = Test
SRC_DIR = src
BUILD_DIR = bin
CLASS_DIR = $(BUILD_DIR)/classes

SOURCES = $(shell find $(SRC_DIR) -iname '*.java')
CLASSES = $(patsubst $(SRC_DIR)%,$(CLASS_DIR)%,$(SOURCES:.java=.class))

all: classes

run: classes
	java -cp $(CLASS_DIR) $(PACKAGE).$(MAIN_CLASS)

classes: $(CLASSES)

$(CLASSES): $(CLASS_DIR) $(SOURCES)
	javac -d $(CLASS_DIR) $(SOURCES)

$(CLASS_DIR):
	mkdir -p $@

.PHONY: all run classes
