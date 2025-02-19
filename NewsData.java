package newsapp;

class NewsData {
    private String hashId;
    private String title;
    private String source_url;
    private String image_url;
    private String content;

    public NewsData(String hashId, String title, String source_url, String image_url, String content) {

        this.hashId = hashId;
        this.title = title;
        this.source_url = source_url;
        this.image_url = image_url;
        this.content = content;
    }


    public String getHashId() {
        return hashId;
    }


    public String getTitle() {
        return title;
    }

    public String getSourceUrl() {
        return source_url;
    }


    public String getImageUrl() {
        return image_url;
    }

    public String getContent() {
        return content;
    }
}