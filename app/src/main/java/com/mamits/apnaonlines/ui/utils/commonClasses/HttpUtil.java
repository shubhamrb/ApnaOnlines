package com.mamits.apnaonlines.ui.utils.commonClasses;

import android.content.Context;

import com.google.gson.Gson;
import com.mamits.apnaonlines.R;
import com.mamits.apnaonlines.data.model.ErrorObject;

import org.json.JSONObject;

public class HttpUtil {
    private static Logger logger = new Logger(HttpUtil.class.getSimpleName());

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static JSONObject getServerErrorJsonObject(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(CommonUtils.ErrorClass.STATUS, 505);
            jsonObject.put(CommonUtils.ErrorClass.CODE, 3000);
            jsonObject.put(CommonUtils.ErrorClass.MESSAGE, context.getString(R.string.server_not_available));
            jsonObject.put(CommonUtils.ErrorClass.DEVELOPER_MESSAGE, context.getString(R.string.server_not_available));
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static ErrorObject getServerErrorPojo(Context context) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(getServerErrorJsonObject(context).toString(), ErrorObject.class);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
