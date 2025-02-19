package newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsrecyclerview);
         recyclerView=findViewById(R.id.rview);
        new FetchNewsTask().execute();
    }



    private class FetchNewsTask extends AsyncTask<Void, Void, ArrayList<NewsData>> {
        protected ArrayList<NewsData> doInBackground(Void... voids) {
            Test testObj = new Test();
            String url = testObj.getUrl();
            String responseString = testObj.getRequest(url);

            if (responseString == null || responseString.isEmpty()) {
                Toast.makeText(NewsActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

            return testObj.parseNewsData(responseString);
        }
        protected void onPostExecute(ArrayList<NewsData> dataList) {
            if (dataList != null) {
                ClassAdapter classAdapter = new ClassAdapter(dataList);
                recyclerView.setLayoutManager(new LinearLayoutManager(NewsActivity.this));
                recyclerView.setAdapter(classAdapter);
            } else {
                Toast.makeText(NewsActivity.this, "Error fetching news", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
