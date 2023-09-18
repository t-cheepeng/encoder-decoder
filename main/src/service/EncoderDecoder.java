package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncoderDecoder {

    private static final List<Character> REFERENCE_TABLE;
    private static final Map<Character, Integer> REFERENCE_MAP;

    static {
        REFERENCE_TABLE = List.of('A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' ,
                                  'J' , 'K' , 'L' , 'M' , 'N' , 'O' , 'P' , 'Q' , 'R' ,
                                  'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z' , '0' ,
                                  '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' ,
                                  '(' , ')' , '*' , '+' , ',' , '-' , '.' , '/');
        REFERENCE_MAP = new HashMap<>();
        for (int i = 0; i < REFERENCE_TABLE.size(); i++) {
            REFERENCE_MAP.put(REFERENCE_TABLE.get(i), i);
        }
    }

    private final EncoderDecoder.Mode mode;
    private final String plaintext;
    private final char offset;

    public EncoderDecoder(EncoderDecoder.Mode mode, String plaintext, char offset) {
        this.mode = mode;
        this.plaintext = plaintext;
        this.offset = offset;
    }

    public String output() {
        if (mode == Mode.ENCODE_MODE) {
            return encode();
        } else {
            return decode();
        }
    }

    private String encode() {
        int offsetAmt = REFERENCE_MAP.get(offset);
        StringBuilder builder = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (c == ' ') {
                builder.append(c);
                continue;
            }

            int charIdx = REFERENCE_MAP.get(c);
            int encodedCharIdx = (charIdx - offsetAmt + REFERENCE_TABLE.size()) % REFERENCE_TABLE.size();
            builder.append(REFERENCE_TABLE.get(encodedCharIdx));
        }

        return builder.toString();
    }

    private String decode() {
        int offsetAmt = REFERENCE_MAP.get(offset);
        StringBuilder builder = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (c == ' ') {
                builder.append(c);
                continue;
            }

            int charIdx = REFERENCE_MAP.get(c);
            int encodedCharIdx = (charIdx + offsetAmt) % REFERENCE_TABLE.size();
            builder.append(REFERENCE_TABLE.get(encodedCharIdx));
        }

        return builder.toString();
    }

    public enum Mode {
        ENCODE_MODE, DECODE_MODE
    }
}
