package entity;

public class Card {
    private int id;
    private String name;
    private String engName;
    private String type;
    private String imageURL;
    private String detailSpec;

    public Card() {
    }

    public Card(int id, String name, String engName, String type, String imageURL, String detailSpec) {
        this.id = id;
        this.name = name;
        this.engName = engName;
        this.type = type;
        this.imageURL = imageURL;
        this.detailSpec = detailSpec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDetailSpec() {
        return detailSpec;
    }

    public void setDetailSpec(String detailSpec) {
        this.detailSpec = detailSpec;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", engName='" + engName + '\'' +
                ", type='" + type + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", detailSpec='" + detailSpec + '\'' +
                '}';
    }
}
