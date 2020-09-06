package com.web.netscanner;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Cacher {
    private static final long CACHE_WAIT_TIME = 5000;
    private final Context context;
    private LinkedList<String> data = new LinkedList<>();
    private final String filename;
    private final Handler handler;
    private long lastAdd;
    private final int maxSize;
    private Runnable writeToDiskRunnable = new Runnable() {
        public void run() {
            Cacher.this.writeToDisk();
        }
    };

    public Cacher(Context context2, int i, String str) {
        this.context = context2;
        this.maxSize = i;
        this.filename = str;
        this.handler = new Handler();
        loadFromCacheFile();
    }

    private void loadFromCacheFile() {
        File file = new File(this.context.getCacheDir(), this.filename);
        if (file.exists()) {
            try {
                String trim = readFileToString(file).trim();
                if (trim.endsWith(",")) {
                    trim = trim.substring(0, trim.length() - 1);
                }
                this.data = new LinkedList<>();
                this.data.addAll(Arrays.asList(trim.split(",")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeToDiskWithThrottling() {
        this.handler.removeCallbacks(this.writeToDiskRunnable);
        this.handler.postDelayed(this.writeToDiskRunnable, CACHE_WAIT_TIME);
    }

    /* access modifiers changed from: private */
    public void writeToDisk() {
        File file = new File(this.context.getCacheDir(), this.filename);
        StringBuilder sb = new StringBuilder();
        Iterator it = this.data.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(",");
        }
        try {
            createFile(file, sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> get() {
        return this.data;
    }

    public boolean add(String str) {
        if (TextUtils.isEmpty(str) || this.data.contains(str)) {
            return false;
        }
        this.data.add(str);
        this.lastAdd = System.currentTimeMillis();
        if (this.data.size() > this.maxSize) {
            this.data.pop();
        }
        writeToDiskWithThrottling();
        return true;
    }

    private static void createFile(File file, String str) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        try {
            printWriter.println(str);
            if (printWriter != null) {
                printWriter.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th = th;
        }
    }

    private static String readFileToString(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        char[] cArr = new char[1024];
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                sb.append(String.valueOf(cArr, 0, read));
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }
}
