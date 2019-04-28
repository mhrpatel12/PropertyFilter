package com.agentdesk.propertyfilter.ui.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BasePresenter<View extends BaseView> {

    protected View view;

    protected BasePresenter(View view) {
        this.view = view;
    }

    void destroyView() {
        //avoid memory leak
        view = null;
    }
}
