# Copyright (C) The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# 更多参数设置见 https://developer.android.google.cn/ndk/guides/android_mk
#
# 讲真，这个参数我看不懂。从 官方demo 抄来的。用于指定源文件的时候使用
abspath_wa = $(join $(filter %:,$(subst :,: ,$1)),$(abspath $(filter-out %:,$(subst :,: ,$1))))

# 指定当前路径
LOCAL_PATH := $(call my-dir)

# 指定源文件路径
JNI_SRC_PATH := $(call abspath_wa, $(LOCAL_PATH)/../../../../cpp-src)

# 声明 clear 变量
include $(CLEAR_VARS)

# 指定 so 库的名称 libhello-jni.so
LOCAL_MODULE    := hello-jni
# 指定 c 源文件
LOCAL_SRC_FILES := $(JNI_SRC_PATH)/hello-jni.c
# 添加需要依赖的NDK库
LOCAL_LDLIBS := -llog -landroid
# 指定为分享库
include $(BUILD_SHARED_LIBRARY)
