package com.yurentsy.watchingyou.mvp.di.modules;

import com.yurentsy.watchingyou.mvp.model.image.ImageLoader;
import com.yurentsy.watchingyou.mvp.model.image.ImageLoaderPicasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    @Singleton
    public ImageLoader imageLoader() {
        return new ImageLoaderPicasso();
    }
}
