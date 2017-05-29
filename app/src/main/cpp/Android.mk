LOCAL_PATH := $(call my-dir)
my_LOCAL_PATH := $(LOCAL_PATH)

include $(LOCAL_PATH)/libs/Android.mk

LOCAL_PATH := $(my_LOCAL_PATH)
include $(CLEAR_VARS)
LOCAL_C_INCLUDES := $(LOCAL_PATH)/libs/
LOCAL_CPP_EXTENSION := .cpp .cc
LOCAL_MODULE    := compressor
LOCAL_SRC_FILES := compressor.cpp
LOCAL_SHARED_LIBRARIES := jpeg9
ifeq ($(TARGET_ARCH_ABI),x86)
    LOCAL_CFLAGS += -ffast-math -mtune=atom -mssse3 -mfpmath=sse
endif
include $(BUILD_SHARED_LIBRARY)

