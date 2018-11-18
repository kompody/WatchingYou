package com.yurentsy.watchingyou.mvp.model.image;

import android.support.annotation.Nullable;

/**
 * Created by silan on 18.11.2018.
 */

public interface ImageLoader<T> {
    void loadInto(@Nullable String url, T container);
}