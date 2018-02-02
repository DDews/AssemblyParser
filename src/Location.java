public class Location extends Node {
    private static final int MAX_LOCATION_LENGTH = 5;

    public Location(Tokens tokens) throws Exception {
        Token next = tokens.next();
        token = next;
        type = Type.LOCATION;
        if (next.type != Lexicon.WORD && next.type != Lexicon.REGISTER) {
            throwEx("Expected WORD or REGISTER for memory location, but encountered: " + next);
            return;
        }
        if (next.data.length() > MAX_LOCATION_LENGTH) {
            throwEx("Memory location exceeds max length of " + MAX_LOCATION_LENGTH + ": " + next);
            return;
        }
    }
}
