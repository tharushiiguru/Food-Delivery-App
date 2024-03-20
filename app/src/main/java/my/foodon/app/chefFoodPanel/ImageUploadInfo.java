package my.foodon.app.chefFoodPanel;

public class ImageUploadInfo {
    private String imageId;
    private String imageName;
    private String imageUrl;

    public ImageUploadInfo() {
    }

    public ImageUploadInfo(String imageId, String imageName, String imageUrl) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
