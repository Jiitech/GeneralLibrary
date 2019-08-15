# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

# 设置libjpeg的头文件以及so库路径
set(libjpeg_dir ${CMAKE_SOURCE_DIR}/src/main/cpp/libjpeg)
set(INC_DIR ${libjpeg_dir}/include)
set(libjpeg_lib_dir ${libjpeg_dir}/prebuilt)

# 添加libjpeg的头文件目录，方便自己调用libjpeg库的代码
include_directories(${INC_DIR})

# 添加自己的jni代码
set(SOURCE_FILES ${libjpeg_dir}/compress.c)
add_library(compress SHARED
            ${SOURCE_FILES})

# 查找NDK工具集提供的库
find_library(log-lib log)
find_library(graphics jnigraphics)

# 添加编译完成的第三方libjpeg库
add_library(libjpeg SHARED IMPORTED)
set_target_properties(libjpeg PROPERTIES IMPORTED_LOCATION ${libjpeg_lib_dir}/${ANDROID_ABI}/libjpeg.so)


# 链接全部的库文件，多个使用空格分开
target_link_libraries(compress libjpeg ${log-lib} ${graphics})