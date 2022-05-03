package wtf.kiddo.skidcraft.mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public enum Category {
    COMBAT("a"),
    PLAYER("b"),
    EXPLOITS("c"),
    VISUALS("d"),
    WORLD("e"),
    GLOBAL("null");

    private final String character;

    Category(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }
}