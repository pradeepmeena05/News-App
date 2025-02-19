package newsapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Test testObj = new Test();

        String url = testObj.getUrl();
        String responseString = testObj.getRequest(url);

        if (responseString == null || responseString.isEmpty()) {
            System.out.println("Failed to fetch response.");
            return;
        }

        ArrayList<NewsData> dataList = testObj.parseNewsData(responseString);

        for (NewsData data : dataList) {
            System.out.println("Title: " + data.getTitle());
            System.out.println("HashId: " + data.getHashId());
            System.out.println("Source: " + data.getSourceUrl());
            System.out.println("Image: " + data.getImageUrl());
            System.out.println("Content: " + data.getContent());

        }
    }

    String getRequest(String url) {
        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestProperty("accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            try {
                StringBuilder jsonString = new StringBuilder();
                while (scanner.hasNext()) {
                    jsonString.append(scanner.nextLine());
                }
                return jsonString.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    ArrayList<NewsData> parseNewsData(String responseString) {
        ArrayList<NewsData> dataList = new ArrayList<>();
        try {
            JSONObject responseJson = new JSONObject(responseString);
            Iterator<String> outerIterator = responseJson.keys();

            while (outerIterator.hasNext()) {
                String key = outerIterator.next();

                JSONObject jsonObject = responseJson.optJSONObject(key);

                if (jsonObject != null) {

                    Iterator<String> innerIterator = jsonObject.keys();

                    while (innerIterator.hasNext()) {

                        String innerKey = innerIterator.next();

                        JSONArray jsonArray = jsonObject.optJSONArray(innerKey);
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject arrayObject = jsonArray.getJSONObject(i);
                                JSONObject newsObj = arrayObject.optJSONObject("news_obj");

                                if (newsObj != null) {
                                    String hashId = newsObj.optString("hash_id");
                                    String title = newsObj.optString("title");
                                    String sourceUrl = newsObj.optString("source_url");
                                    String imageUrl = newsObj.optString("image_url");
                                    String content = newsObj.optString("content");

                                    dataList.add(new NewsData(hashId, title, sourceUrl, imageUrl, content));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    String getUrl() {
        return "https://www.inshorts.com/api/en/news?category=top_stories&max_limit=20&include_card_data=true";
    }
}
