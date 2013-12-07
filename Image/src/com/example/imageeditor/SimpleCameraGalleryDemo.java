package com.example.imageeditor;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SimpleCameraGalleryDemo  extends Activity {

	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_GALLERY = 2;
	private static final int PICK_FROM_GALLERY_BW = 3;
	//protected static final int CAMERA_REQUEST = 10;
	ImageView imgview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imgview = (ImageView) findViewById(R.id.imageView1);
		Button buttonCamera = (Button) findViewById(R.id.btn_take_camera);
		Button buttonGallery = (Button) findViewById(R.id.btn_select_gallery);
		Button bandw = (Button) findViewById(R.id.bandw);
		bandw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				 // call android default gallery
				 intent.setType("image/*");
				 intent.setAction(Intent.ACTION_GET_CONTENT);
				// intent.putExtra("return-data", true);
				 startActivityForResult(Intent.createChooser(intent,
				 "Complete action using"), PICK_FROM_GALLERY_BW);
				
			}
		});
		buttonCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				 intent.putExtra(MediaStore.EXTRA_OUTPUT,
				 MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
				 // ******** code for crop image
				 intent.putExtra("crop", "true");
				 intent.putExtra("aspectX", 0);
				 intent.putExtra("aspectY", 0);
				 intent.putExtra("outputX", 200);
				 intent.putExtra("outputY", 150);

				 try {

				 intent.putExtra("return-data", true);
				 startActivityForResult(intent, PICK_FROM_CAMERA);

				 } catch (ActivityNotFoundException e) {
				 // Do nothing for now
				 }
				
			}
		});
		
		 buttonGallery.setOnClickListener(new View.OnClickListener() {

			 @Override
			 public void onClick(View v) {
			 // TODO Auto-generated method stub
			 Intent intent = new Intent();
			 // call android default gallery
			 intent.setType("image/*");
			 intent.setAction(Intent.ACTION_GET_CONTENT);
			 // ******** code for crop image
			 intent.putExtra("crop", "true");
			 intent.putExtra("aspectX", 0);
			 intent.putExtra("aspectY", 0);
			 intent.putExtra("outputX", 200);
			 intent.putExtra("outputY", 150);

			 try {

			 intent.putExtra("return-data", true);
			 startActivityForResult(Intent.createChooser(intent,
			 "Complete action using"), PICK_FROM_GALLERY);

			 } catch (ActivityNotFoundException e) {
			 // Do nothing for now
			 }
			 }
			 });
		 
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if (requestCode == PICK_FROM_CAMERA) {
			 Bundle extras = data.getExtras();
			 if (extras != null) {
			 Bitmap photo = extras.getParcelable("data");
			 imgview.setImageBitmap(photo);

			 }
			 }

			 if (requestCode == PICK_FROM_GALLERY) {
			 Bundle extras2 = data.getExtras();
			 if (extras2 != null) {
			 Bitmap photo = extras2.getParcelable("data");
			 imgview.setImageBitmap(photo);

			 }
			 if (requestCode == PICK_FROM_GALLERY_BW) {
				 Toast.makeText(this, "Hi", Toast.LENGTH_LONG).show();
				 

				 
			 }
			 }
			 }
		
	

	public Bitmap toGrayscale(Bitmap bmpOriginal) {
        final int height = bmpOriginal.getHeight();
        final int width = bmpOriginal.getWidth();

        final Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas c = new Canvas(bmpGrayscale);
        final Paint paint = new Paint();
        final ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        final ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
