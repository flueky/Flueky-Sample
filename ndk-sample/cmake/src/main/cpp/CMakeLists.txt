cmake_minimum_required(VERSION 3.4.1)

add_library(
        hello-jni # so 库的名称 libhello-jni.so
        SHARED # 设置为分享库
        # 指定C源文件的路径
        ../../../../cpp-src/hello-jni.c
)

#find_library( # Sets the name of the path variable.
#        android-lib
#
#        # Specifies the name of the NDK library that
#        # you want CMake to locate.
#        android)

find_library(
        log-lib # 设置路径变量名称
        log # 指定CMake需要加载的NDK库
)

# 链接hello-jni库依赖的库，注意下面变量名的配置
target_link_libraries(hello-jni
#        ${android-lib}
        ${log-lib}
)
