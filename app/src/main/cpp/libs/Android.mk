LOCAL_PATH := $(call my-dir)

# Prebuilt Lib
#include $(CLEAR_VARS)
#LOCAL_MODULE := magic
#LOCAL_SRC_FILES := libmagic.so
#include $(PREBUILT_SHARED_LIBRARY)

include $(call all-subdir-makefiles)

