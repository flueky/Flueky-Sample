cmake_minimum_required(VERSION 3.4.1)

add_library(hello-jni SHARED
        ../../../../cpp-src/hello-jni.c)

# Include libraries needed for hello-jni lib
target_link_libraries(hello-jni
        android
        log)
