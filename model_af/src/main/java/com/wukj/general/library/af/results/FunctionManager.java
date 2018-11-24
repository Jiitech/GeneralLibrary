package com.wukj.general.library.af.results;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/24 下午9:06
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/24 下午9:06
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class FunctionManager {

    private static FunctionManager _instance;

    private FunctionManager(){}

    public static FunctionManager getInstance(){
        if (_instance == null){
            _instance = new FunctionManager();
        }
        return _instance;
    }

    private HashMap<String,FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String,FunctionNoParamWithResult> mFunctionNoParamWithResult;
    private HashMap<String,FunctionWithParamNoResult> mFunctionWithParamNoResult;
    private HashMap<String,FunctionWithParamWithResult> mFunctionWithParamWithResult;


    /**
     * 1.无参无返回值
     * @param function
     * @return
     */
    public FunctionManager addFunction(FunctionNoParamNoResult function){
        if (mFunctionNoParamNoResult == null){
            mFunctionNoParamNoResult = new HashMap<>();
        }
        mFunctionNoParamNoResult.put(function.mFunctionName,function);
        return this;
    }
    /**
     * 2.无参有返回值
     * @param function
     */
    public FunctionManager addFunction(FunctionNoParamWithResult function){
        if (mFunctionNoParamWithResult == null){
            mFunctionNoParamWithResult = new HashMap<>();
        }
        mFunctionNoParamWithResult.put(function.mFunctionName,function);
        return this;
    }

    /**
     * 3.有参无返回值
     * @param function
     */
    public FunctionManager addFunction(FunctionWithParamNoResult function){
        if (mFunctionWithParamNoResult == null){
            mFunctionWithParamNoResult = new HashMap<>();
        }
        mFunctionWithParamNoResult.put(function.mFunctionName,function);
        return this;
    }

    /**
     * 4.有参有返回值
     * @param function
     */
    public FunctionManager addFunction(FunctionWithParamWithResult function){
        if (mFunctionWithParamWithResult == null){
            mFunctionWithParamWithResult = new HashMap<>();
        }
        mFunctionWithParamWithResult.put(function.mFunctionName,function);
        return this;
    }

    /**
     * 1.无参无返回值
     * @param funcName
     */
    public String invokeFunction(String funcName){
        if (TextUtils.isEmpty(funcName)){
            return funcName;
        }
        if (mFunctionNoParamNoResult != null){
            FunctionNoParamNoResult f = mFunctionNoParamNoResult.get(funcName);
            if (f != null){
                f.function();
            } else {
                try {
                    throw new FunctionException();
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }

        }
        return funcName;
    }
    /**
     * 2.无参有返回值
     * @param funcName
     */
    public <Result> Result invokeFunction(String funcName,Class<Result> c){
        if (TextUtils.isEmpty(funcName)){
            return null;
        }
        if (mFunctionNoParamWithResult != null){
            FunctionNoParamWithResult f = mFunctionNoParamWithResult.get(funcName);
            if (f != null){
                if (c != null){
                    return c.cast(f.function());
                } else {
                    return (Result)f.function();
                }
            } else {
                try {
                    throw  new FunctionException();
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 3.有参无返回值
     * @param funcName
     * @param param
     * @param <Param>
     * @return
     */
    public <Param> void invokeFunction(String funcName,Param param){
        if (TextUtils.isEmpty(funcName)){
            return;
        }
        if (mFunctionWithParamNoResult != null){
            FunctionWithParamNoResult f = mFunctionWithParamNoResult.get(funcName);
            if (f != null){
                f.function(param);
            } else {
                try {
                    throw  new FunctionException();
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    /**
     * 4.有参有返回值
     * @param funcName
     * @param param
     * @param c
     * @param <Param>
     * @param <Result>
     * @return
     */
    public <Param,Result> Result invokeFunction(String funcName,Param param,Class<Result> c){
        if (TextUtils.isEmpty(funcName)){
            return null;
        }
        if (mFunctionWithParamWithResult != null){
            FunctionWithParamWithResult f = mFunctionWithParamWithResult.get(funcName);
            if (f != null){
                if (c != null){
                    return c.cast(f.function(param));
                } else {
                    return (Result)f.function(param);
                }
            } else {
                try {
                    throw  new FunctionException();
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
