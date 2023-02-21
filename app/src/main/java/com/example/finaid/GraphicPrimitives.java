package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class GraphicPrimitives extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_primitives);

        Bitmap bg = Bitmap.createBitmap(720, 1280,
                Bitmap.Config.ARGB_8888);

        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setBackgroundDrawable(new BitmapDrawable(bg));

        Canvas canvas = new Canvas(bg);

        Paint paint = new Paint();
        Paint paint2 = new Paint();
        Paint paintr = new Paint();
        Paint painty = new Paint();
        Paint paintg = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);

        paint2.setColor(Color.GRAY);
        paint2.setTextSize(50);
        canvas.drawText("Traffic Signal", 120, 150, paint);
        canvas.drawRect(200, 200, 600, 900, paint2);
        canvas.drawRect(350, 900, 450, 1600, paint2);
        paintr.setColor(Color.RED);
        canvas.drawCircle(400, 350, 100, paintr);
        painty.setColor(Color.YELLOW);
        canvas.drawCircle(400, 550, 100, painty);
        paintg.setColor(Color.GREEN);
        canvas.drawCircle(400, 750, 100, paintg);

    }
}