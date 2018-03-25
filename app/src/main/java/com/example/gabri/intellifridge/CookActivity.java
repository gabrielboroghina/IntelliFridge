package com.example.gabri.intellifridge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabri.intellifridge.engine.Cook;
import com.example.gabri.intellifridge.engine.Item;
import com.example.gabri.intellifridge.engine.Recipe;
import com.example.gabri.intellifridge.engine.UserDataSingleton;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talksick on 3/25/2018.
 */


class CustomAdapter3 extends ArrayAdapter<Recipe> {
    private final Activity context;
    private final List<Recipe> items;

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public CustomAdapter3(Activity context, List<Recipe> items) {
        super(context, R.layout.list_cook, items);
        // TODO Auto-generated constructor stub

        this.context=  context;
        this.items = items;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cook, null, true);


        TextView txtTitle = (TextView) rowView.findViewById(R.id.item_name);
        TextView extratxt = (TextView) rowView.findViewById(R.id.item_quantity);
        ImageView image = (ImageView) rowView.findViewById(R.id.icon1);

        txtTitle.setText(items.get(position).name);
        extratxt.setText("" + items.get(position).recipeURL);
    try {
        new DownloadImageTask(image)
                .execute(items.get(position).imageURL);
    } catch (Exception e){
        e.printStackTrace();
            image.setImageDrawable(context.getResources().getDrawable(R.drawable.square));
        }


        //   double a = items.get(position).type.perishability / 14;
     //   if (a > 1) a = 1.0;
      //  image.setColorFilter(Color.rgb((int)((1 - a) * 255),(int)(a * 255),0));
        return rowView;
    };
}

public class CookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);
        final int n_days = getIntent().getIntExtra("days", 5);

        Button btn = findViewById(R.id.btn_shopping);
        ListView list_view = findViewById(R.id.lv_shopping);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EatActivity.class));
            }
        });

        final List<Recipe> list = Cook.chooseRecipes(UserDataSingleton.getInstance().getFridge());
        final CustomAdapter3 adapter = new CustomAdapter3(this, list);
        adapter.notifyDataSetChanged();
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           //     Toast.makeText(getApplicationContext(), list.get(position).text + " eaten!", Toast.LENGTH_LONG).show();
             //   UserDataSingleton.getInstance().getFridge().getItems().remove(list.get(position));

          //      adapter.clear();
            //    adapter.addAll(UserDataSingleton.getInstance().getFridge().getItems());
            //    adapter.notifyDataSetChanged();
             //   parent.invalidate();
            }
        });
    }
}