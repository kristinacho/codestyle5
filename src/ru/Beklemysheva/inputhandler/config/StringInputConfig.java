package ru.Beklemysheva.inputhandler.config;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class StringInputConfig {
    private final String prompt;
    private final String formatHint;
    private final String errorMsg;
    private final Integer minLength;
    private final Integer maxLength;
    private final List<String> allowedValues;
    private final boolean requireUppercaseFirstLetter;
    private final boolean allowOnlyLettersAndSpaces;

    public StringInputConfig(Builder builder) {
        this.prompt = builder.prompt;
        this.formatHint = builder.formatHint;
        this.errorMsg = builder.errorMsg;
        this.minLength = builder.minLength;
        this.maxLength = builder.maxLength;
        this.allowedValues = Collections.unmodifiableList(builder.allowedValues);
        this.requireUppercaseFirstLetter = builder.requireUppercaseFirstLetter;
        this.allowOnlyLettersAndSpaces = builder.allowOnlyLettersAndSpaces;
    }

    public static class Builder {
        private String prompt = "";
        private String formatHint = "";
        private String errorMsg = "";
        private Integer minLength = null;
        private Integer maxLength = null;
        private List<String> allowedValues = new ArrayList<>();
        private boolean requireUppercaseFirstLetter = false;
        private boolean allowOnlyLettersAndSpaces = false;

        public Builder setPrompt(String prompt) {
            this.prompt = Objects.requireNonNull(prompt);
            return this;
        }

        public Builder setFormatHint(String formatHint) {
            this.formatHint = Objects.requireNonNull(formatHint);
            return this;
        }

        public Builder setErrorMsg(String errorMsg) {
            this.errorMsg = Objects.requireNonNull(errorMsg);
            return this;
        }

        public Builder setMinLength(Integer minLength) {
            this.minLength = minLength;
            return this;
        }

        public Builder setMaxLength(Integer maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public Builder setAllowedValues(List<String> allowedValues) {
            this.allowedValues = new ArrayList<>(Objects.requireNonNull(allowedValues));
            return this;
        }

        public Builder requireUppercaseFirstLetter() {
            this.requireUppercaseFirstLetter = true;
            return this;
        }

        public Builder allowOnlyLettersAndSpaces() {
            this.allowOnlyLettersAndSpaces = true;
            return this;
        }

        public StringInputConfig build() {
            return new StringInputConfig(this);
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public String getFormatHint() {
        return formatHint;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }

    public boolean isRequireUppercaseFirstLetter() {
        return requireUppercaseFirstLetter;
    }

    public boolean isAllowOnlyLettersAndSpaces() {
        return allowOnlyLettersAndSpaces;
    }
}
