package wtf.kiddo.skidcraft.mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public enum Category {
    Combat("a"),
    World("b"),
    Render("d"),
    Movement("f");

    private final String character;

    Category(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }
}