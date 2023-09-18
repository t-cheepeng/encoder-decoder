package service;

import model.ParseResult;

public class Parser {

    public ParseResult parse(String[] args) {
        ParseResult.Builder builder = ParseResult.Builder.aParseResult();

        if (args.length < 3) {
            return builder.withStatus(ParseResult.Status.FAILURE)
                          .withErrorMessage("Insufficient number of arguments")
                          .build();
        }

        if (!args[0].equals("enc") && !args[0].equals("dec")) {
            return builder.withStatus(ParseResult.Status.FAILURE)
                          .withErrorMessage("Unable to parse mode. Use only 'enc' or 'dec'")
                          .build();
        }

        String plaintext = args[1];
        for (char c : plaintext.toCharArray()) {
            if (!isValidCharacter(c)) {
                return builder.withStatus(ParseResult.Status.FAILURE)
                              .withErrorMessage("Invalid plaintext character: " + c)
                              .build();
            }
        }

        char offset = args[2].toCharArray()[0];
        if (!isValidCharacter(offset)) {
            return builder.withStatus(ParseResult.Status.FAILURE)
                          .withErrorMessage("Invalid offset character: " + offset).build();
        }

        EncoderDecoder.Mode mode = args[0].equals(
                "enc") ? EncoderDecoder.Mode.ENCODE_MODE : EncoderDecoder.Mode.DECODE_MODE;
        return builder.withStatus(ParseResult.Status.SUCCESS)
                      .withMode(mode)
                      .withPlaintext(args[1])
                      .withOffset(offset)
                      .build();
    }

    private boolean isValidCharacter(char c) {
        return (c >= 65 && c <= 90) || (c >= 40 && c <= 57) || c == 32;
    }
}
