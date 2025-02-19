package newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.Viewholder> {

    private final ArrayList<NewsData> dataList;



    public ClassAdapter(ArrayList<NewsData> dataList) {

        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ClassAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        NewsData item = dataList.get(position);


        holder.textViewTitle.setText(item.getTitle());
        holder.textViewContent.setText(item.getContent());
        displayImage(item.getImageUrl(), holder.image);

         holder.cardId.setOnClickListener(v -> {
             Intent intent=new Intent(v.getContext(),show_content_Activity.class);
             intent.putExtra("url",item.getSourceUrl());
             v.getContext().startActivity(intent);
         });






    }

    private void displayImage(String image_url, ImageView image) {
        new Thread(() -> {

            try {
                URL url = new URL(image_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                connection.connect();

                InputStream input = connection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(input);

                image.post(() -> image.setImageBitmap(bitmap));


                input.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }).start();
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewContent;
        private final ImageView image;


        private  final CardView cardId;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            textViewTitle = itemView.findViewById(R.id.TextView1);
            textViewContent = itemView.findViewById(R.id.TextView2);

            cardId=itemView.findViewById(R.id.cardId);
        }
    }
}
