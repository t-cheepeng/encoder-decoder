package main;

import model.ParseResult;
import service.EncoderDecoder;
import service.Parser;

public class Main {

    private static final String USAGE = "\nUsage:\n\tMain.java <'enc'|'dec'> <plaintext> <offset_char>\n";

    public static void main(String[] args) {
        Parser parser = new Parser();
        ParseResult parseResult = parser.parse(args);

        if (parseResult.getStatus() == ParseResult.Status.FAILURE) {
            System.out.println(parseResult.getErrorMessage());
            System.out.println(USAGE);
            System.exit(-1);
        }

        EncoderDecoder encoderDecoder = new EncoderDecoder(parseResult.getMode(), parseResult.getPlaintext(),
                                                           parseResult.getOffset());
        System.out.println(encoderDecoder.output());
    }
}