LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, java)

LOCAL_PACKAGE_NAME := TestFileIO

LOCAL_PROGUARD_ENABLED := disabled

LOCAL_USE_AAPT2 := true

include $(BUILD_PACKAGE)

# include $(BUILD_PREBUILT)
# LOCAL_MODULE := TestFileIO
# LOCAL_MODULE_CLASS := APPS
# LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)

# LOCAL_CERTIFICATE := platform
# LOCAL_PRIVILEGED_MODULE := true
