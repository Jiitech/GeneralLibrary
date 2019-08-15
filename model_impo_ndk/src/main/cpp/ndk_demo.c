//
// Created by 吴开杰 on 2019/5/10.
// 此文件是模仿jni的调用过程
// ndk是一个工具集，通过jni的方式进行调用
// 第一：创建JNIEnv的结构体
// 第二：在JNIEnv的结构体中编写相关的函数
// 第三：在main方法中，调用JNIEnv中的方法（这就类似在Android项目中jni的调用过程）
//

#include <stdlib.h>
#include <stdio.h>

// JNI结构体指针别名
typedef struct MYJNINativeInterface_ *JNIEnv;

// 结构体
struct MYJNINativeInterface_
{
    char *(*NEWStringUTF)(JNIEnv *, char *);
};

// 函数实现
char *NEWStringUTF(JNIEnv *env, char *str)
{
    return str;
}

int main(int argc, char const *argv[])
{
    // 实例化结构体
    struct MYJNINativeInterface_ struct_env;
    struct_env.NEWStringUTF = NEWStringUTF;

    JNIEnv e = &struct_env;
    JNIEnv *env = &e;

    // 通过二级指针调用函数
    char* str = (*env)->NEWStringUTF(env,"test");
    printf("%s",str);

    return 0;
}
