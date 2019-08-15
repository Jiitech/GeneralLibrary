#include <jni.h>
#include <string>

// 模块开发，在mysub文件夹中，创建CMakeLists.txt，里面是子模块的代码

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_pingan_testndk_MainActivity_stringFromJNIOne(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++/mysub";
    return env->NewStringUTF(hello.c_str());
}