package com.quickblox.android.framework.tests.content;

import android.content.res.AssetManager;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.tests.GenericTestCase;
import com.quickblox.android.framework.tests.helpers.FileHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 15:07
 */
public class ContentTestCase extends GenericTestCase {

    public static final String TEST_ASSETS_ROOT = "test_files";
    AssetManager assetManager;

    File fileObject;
    QBFile file;

    @Override
    public void setUp() throws Exception {
        assetManager = getInstrumentation().getContext().getResources().getAssets();
    }

    public File getRandomFile() {
        File file = null;
        try {
            String[] assetsFileNames = assetManager.list(TEST_ASSETS_ROOT);
            int n = new Random().nextInt(assetsFileNames.length);

            String fileName = assetsFileNames[n];
            InputStream is = assetManager.open(TEST_ASSETS_ROOT + "/" + fileName);

            file = FileHelper.getFileFromAsset(is, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public ArrayList<File> getRandomFiles() {
        ArrayList<File> files = new ArrayList<File>();
        try {
            for (String fileName : assetManager.list(TEST_ASSETS_ROOT)) {
                InputStream is = assetManager.open(TEST_ASSETS_ROOT + "/" + fileName);
                File file = FileHelper.getFileFromAsset(is, fileName);
                files.add(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}