package com.jinkun.globalguide.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by coderwjq on 2017/3/20.
 */

public class ActivityManager {
    private Stack<Activity> mActivityStack = new Stack<>();

    private static ActivityManager mActivityManager = null;

    private ActivityManager() {

    }

    public static ActivityManager getInstance() {
        if (mActivityManager == null) {
            mActivityManager = new ActivityManager();
        }
        return mActivityManager;
    }

    /**
     * 向Activity管理栈中加入一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mActivityStack.push(activity);
    }

    public void removeCurrentActivity() {
        Activity activity = mActivityStack.lastElement();
        activity.finish();
        mActivityStack.pop();
    }

    public void removeAllActivity() {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            activity.finish();
            mActivityStack.remove(activity);
        }
    }

    public void removeAppointedActivity(Activity activity) {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity tempActivity = mActivityStack.get(i);
            if (tempActivity.getClass().equals(activity.getClass())) {
                tempActivity.finish();
                mActivityStack.remove(tempActivity);
                break;
            }
        }
    }

    public int getActivitySize(){
        return mActivityStack.size();
    }
}
