package com.wukj.general.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/2 0002 下午 5:07
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/2 0002 下午 5:07
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */

public class ReadAssetsUtils {

    /**
     * @param context      //上下文
     * @param jsonFileName //flightLine/flightLine.json
     *                     //assets下面flightLine文件夹下面的flightLine.json文件
     *                     //如果是放在assets根目录下面，直接使用flightLine.json名字
     * @return
     */
    public static String readLocalJSON(Context context, String jsonFileName) {
        InputStreamReader inputStreamReader;
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open(
                    jsonFileName), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
