package com.sky.data;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class ActivityController {
    private static List<Activity> activities = new ArrayList<Activity>();

    //添加界面
    public static void saveActivity(Activity activity) {
        activities.add(activity);
    }

    //删除界面
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    //全部关闭
    public static void finishActivty() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }


}
