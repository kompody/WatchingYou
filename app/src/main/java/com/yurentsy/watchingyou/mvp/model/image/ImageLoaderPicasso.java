package com.yurentsy.watchingyou.mvp.model.image;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yurentsy.watchingyou.R;

/**
 * Created by silan on 18.11.2018.
 */

public class ImageLoaderPicasso implements ImageLoader<ImageView> {

    @Override
    public void loadInto(@Nullable String url, ImageView container) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_autorenew_black_24dp)
                .error(R.drawable.ic_crop_original_black_24dp)
                .into(container);
    }
}
