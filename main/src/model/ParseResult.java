package model;

import service.EncoderDecoder;

public class ParseResult {
    Status status;
    EncoderDecoder.Mode mode;
    String plaintext;
    String errorMessage;
    char offset;

    private ParseResult() {}

    public Status getStatus() {
        return status;
    }

    public EncoderDecoder.Mode getMode() {
        return mode;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public char getOffset() {
        return offset;
    }

    public enum Status {
        SUCCESS,
        FAILURE
    }

    public static final class Builder {
        private Status status;
        private EncoderDecoder.Mode mode;
        private String plaintext;
        private String errorMessage;
        private char offset;

        private Builder() {
        }

        public static Builder aParseResult() {
            return new Builder();
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withMode(EncoderDecoder.Mode mode) {
            this.mode = mode;
            return this;
        }

        public Builder withPlaintext(String plaintext) {
            this.plaintext = plaintext;
            return this;
        }

        public Builder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder withOffset(char offset) {
            this.offset = offset;
            return this;
        }

        public ParseResult build() {
            ParseResult parseResult = new ParseResult();
            parseResult.status = this.status;
            parseResult.mode = this.mode;
            parseResult.errorMessage = this.errorMessage;
            parseResult.plaintext = this.plaintext;
            parseResult.offset = this.offset;
            return parseResult;
        }
    }
}
