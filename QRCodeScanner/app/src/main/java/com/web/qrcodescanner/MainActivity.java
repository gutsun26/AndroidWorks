package com.web.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.Object;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    SurfaceView cameraView;
    TextView barCodeInfo;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cameraView = (SurfaceView)findViewById(R.id.camera_view);
        barCodeInfo = (TextView)findViewById(R.id.code_info);
        try
        {
            File file = new File(Environment.getExternalStorageDirectory(),"myqrcode.jpg");
            Bitmap myQRCode = BitmapFactory.decodeStream(
                    getAssets().open(file.getName())
            );
            barcodeDetector =
                    new BarcodeDetector.Builder(this)
                            .setBarcodeFormats(Barcode.QR_CODE)
                            .build();
            Frame myFrame = new Frame.Builder()
                    .setBitmap(myQRCode)
                    .build();
            SparseArray<Barcode> barcodes = barcodeDetector.detect(myFrame);
            // Check if at least one barcode was detected
            if(barcodes.size() != 0)
            {

                // Print the QR code's message
                barCodeInfo.setText("My QR Code's Data" + barcodes.valueAt(0));
            }
        }
        catch (Exception e)
        {
            barCodeInfo.setText(e.toString());
        }

         /*
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                try
                {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie)
                {
                    toast(ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>()
        {
            @Override
            public void release()
            {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections)
            {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0)
                {
                    // Use the post method of the TextView
                    barCodeInfo.post(new Runnable()
                    {
                        public void run()
                        {
                            // Update the TextView
                            barCodeInfo.setText(barcodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });

          */

    }//end of onCreate()


    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
