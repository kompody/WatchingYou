package com.yurentsy.watchingyou.mvp.model.file;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;

/**
 * Created by silan on 20.11.2018.
 */

public class FileManager {
    private static File storageDir = App.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

    public static Observable<Uri> getUriImageFile() {
        return Observable.create(emmiter -> {
            Context context = App.getInstance();
            File photoFile = null;
            try {
                photoFile = createImageFile();
                if (photoFile != null) {
                    Uri photoUri = FileProvider.getUriForFile(context,
                            context.getString(R.string.file_provider),
                            photoFile);
                    emmiter.onNext(photoUri);
                } else {
                    emmiter.onError(new RuntimeException(context.getString(R.string.text_file_create_error)));
                }
            } catch (IOException ex) {
                emmiter.onError(ex);
            } finally {
                emmiter.onComplete();
            }
        });

    }

    private static File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File fileImage = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return fileImage;
    }

    public static Observable<Boolean> deleteFile(String urlPhoto) {
        return Observable.just(new File(urlPhoto))
                .map(tempFile -> {
                    for (File file : storageDir.listFiles()) {
                        if (file.getName().equals(tempFile.getName())) {
                            return file.delete();
                        }
                    }
                    return false;
                });
    }
}
