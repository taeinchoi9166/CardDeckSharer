package entity;

public class CardInfo {

    /**
     * attack : 2200
     * attr : 어둠
     * defense : 1200
     * engName : Foucault's Cannon
     * kind : 펜듈럼 / 일반
     * level : 5
     * name : 후코의 마포석
     * penEffect : ①: 이 카드를 발동한 턴의 엔드 페이즈에, 필드의 앞면 표시의 마법 / 함정 카드 1장을 대상으로 하고 발동할 수 있다. 그 카드를 파괴한다.
     * penScale : 2
     * text : 몽환의 공간을 방황하는 기계 장치의 생명체였을 것이다. 최고의 수수께끼는 과거의 기록이 거의 남아 있지 않다..는 것이다.그 이유...인지...간섭...거부일까...?...삭제...
     * type : 마법사족
     * imageURL : https://storage.googleapis.com/ygoprodeck.com/pics/43785278.jpg
     */

    private String attack;
    private String attr;
    private String defense;
    private String engName;
    private String kind;
    private String level;
    private String name;
    private String penEffect;
    private String penScale;
    private String text;
    private String type;
    private String imageURL;

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPenEffect() {
        return penEffect;
    }

    public void setPenEffect(String penEffect) {
        this.penEffect = penEffect;
    }

    public String getPenScale() {
        return penScale;
    }

    public void setPenScale(String penScale) {
        this.penScale = penScale;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
