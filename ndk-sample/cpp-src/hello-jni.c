#include <string.h>
#include <jni.h>
#include "com_flueky_demo_MainActivity.h"
#include "util/log.h"

/**
 * JNI 示例，演示native方法返回一个字符串，Java 源码见
 *
 * ndk-sample/app/src/main/java/com/flueky/demo/MainActivity.java
 */
JNIEXPORT jstring JNICALL
Java_com_flueky_demo_MainActivity_hello( JNIEnv* env,
                                                  jobject thiz )
{
#if defined(__arm__)
    #if defined(__ARM_ARCH_7A__)
        #if defined(__ARM_NEON__)
            #if defined(__ARM_PCS_VFP)
                #define ABI "armeabi-v7a/NEON (hard-float)"
            #else
                #define ABI "armeabi-v7a/NEON"
            #endif
        #else
            #if defined(__ARM_PCS_VFP)
                #define ABI "armeabi-v7a (hard-float)"
            #else
                #define ABI "armeabi-v7a"
            #endif
        #endif
    #else
        #define ABI "armeabi"
    #endif
#elif defined(__i386__)
    #define ABI "x86"
#elif defined(__x86_64__)
    #define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
    #define ABI "mips64"
#elif defined(__mips__)
    #define ABI "mips"
#elif defined(__aarch64__)
    #define ABI "arm64-v8a"
#else
    #define ABI "unknown"
#endif

    LOGD("日志输出示例");

    return (*env)->NewStringUTF(env, "Hello from JNI !  Compiled with ABI " ABI ".");
}