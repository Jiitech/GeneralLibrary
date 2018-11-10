
# 针对安卓6.0及以上版本，对于一些敏感的权限手动处理
+ AndroidManifest.xml文件中定义需要请求的权限
+ Activity或者Fragment中请求权限
+ 在Activity中处理权限
+ 通过注解将处理结果返回Activity中的定义好的方法里面

step-1:声明权限
```
<uses-permission android:name="android.permission.CALL_PHONE"></uses-permission>
```

step-2:请求权限
```
                 PermissionHelper
                        .with(MainActivity.this)
                        .requestCode(REQUEST_PHONE_CODE)
                        .requestPermissionList(new String[]{
                                Manifest.permission.CALL_PHONE
                                ,Manifest.permission.WRITE_CONTACTS})
                        .request();
```

step-3:处理权限
```
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e("call","---------onRequestPermissionsResult:"+requestCode);

        PermissionHelper.requestPermissionResult(this,requestCode,permissions);

    }
```

step-4:回调处理的成功或者失败
```
    /**
     * 打电话成功
     */
    @PermissionSucceed(requestCode = REQUEST_PHONE_CODE)
    private void callSuccess() {

        Log.e("call","拨打成功");

    }

    /**
     * 打电话失败
     */
    @PermissionFail(requestCode = REQUEST_PHONE_CODE)
    private void callFail() {

        Log.e("call","拨打失败");

    }
```
