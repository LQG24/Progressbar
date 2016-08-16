/**
 * Created by YuGang Yang on October 31, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package com.example.administrator.progressbar.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.example.administrator.progressbar.R;
import com.example.administrator.progressbar.utils.KeyboardUtils;

import static com.example.administrator.progressbar.utils.Utils.checkNotNull;


public class StarterCommon {

  private ProgressLoading mProgressLoading;
  private ProgressLoading mUnBackProgressLoading;
  private boolean progressShow;

  private Activity activity;

  public StarterCommon(Activity activity) {
    checkNotNull(activity, "activity == null");
    this.activity = activity;
  }

  public void onDestroy() {
    mProgressLoading = null;
    mUnBackProgressLoading = null;
    activity = null;
  }

  public void showProgressLoading(int resId) {
    if (!isFinishing()) {
      showProgressLoading(activity.getString(resId));
    }
  }

  private boolean isFinishing() {
    return activity == null || activity.isFinishing();
  }

  public void showProgressLoading(String text) {
    if (mProgressLoading == null) {
      mProgressLoading = new ProgressLoading(activity, R.style.ProgressLoadingTheme);
      mProgressLoading.setCanceledOnTouchOutside(true);
      mProgressLoading.setOnCancelListener(new DialogInterface.OnCancelListener() {
        @Override public void onCancel(DialogInterface dialog) {
          progressShow = false;
        }
      });
    }
    if (!TextUtils.isEmpty(text)) {
      mProgressLoading.text(text);
    } else {
      mProgressLoading.text(null);
    }
    progressShow = true;
    mProgressLoading.show();
  }

  public boolean isProgressShow() {
    return progressShow;
  }

  public void dismissProgressLoading() {
    if (mProgressLoading != null && ! isFinishing()) {
      progressShow = false;
      mProgressLoading.dismiss();
    }
  }

  public void showUnBackProgressLoading(int resId) {
    showUnBackProgressLoading(activity.getString(resId));
  }

  // 按返回键不可撤销的
  public void showUnBackProgressLoading(String text) {
    if (mUnBackProgressLoading == null) {
      mUnBackProgressLoading = new ProgressLoading(activity, R.style.ProgressLoadingTheme) {
        @Override public void onBackPressed() {
        }
      };
    }
    if (!TextUtils.isEmpty(text)) {
      mUnBackProgressLoading.text(text);
    } else {
      mUnBackProgressLoading.text(null);
    }
    mUnBackProgressLoading.show();
  }

  public void dismissUnBackProgressLoading() {
    if (mUnBackProgressLoading != null && !isFinishing()) {
      mUnBackProgressLoading.dismiss();
    }
  }

  public void hideSoftInputMethod() {
    try {
      if (activity.getCurrentFocus() != null) {
        KeyboardUtils.hide(activity, activity.getCurrentFocus().getWindowToken());
      }
    } catch (Exception e) {
      // Nothing
    }
  }

  public void showSoftInputMethod() {
    try {
      KeyboardUtils.show(activity);
    } catch (Exception e) {
      // Nothing
    }
  }

  public boolean isImmActive() {
    return KeyboardUtils.isActive(activity);
  }



}
