package entity;

public class CardItem {

    /**
     * detailSpec : 랭크 9 [ 기계족 / 엑시즈 / 효과 ] 공격력 2500/수비력 1500
     * id : 11014
     * title : CNo.15 기믹 퍼핏－시리얼킬러
     * type : 어둠
     */

    private String detailSpec;
    private String id;
    private String title;
    private String type;

    public String getDetailSpec() {
        return detailSpec;
    }

    public void setDetailSpec(String detailSpec) {
        this.detailSpec = detailSpec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
