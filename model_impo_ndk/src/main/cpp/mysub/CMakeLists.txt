# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

#生成so动态库
ADD_LIBRARY(mysub-lib SHARED mysub.cpp)
target_link_libraries(mysub-lib log)