package ru.Beklemysheva.inputhandler.config;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public final class IntInputConfig {
    private final String prompt;
    private final String formatHint;
    private final String errorMsg;
    private final Integer min;
    private final Integer max;
    private final List<Integer> allowedValues;

    private IntInputConfig(Builder builder) {
        this.prompt = builder.prompt;
        this.formatHint = builder.formatHint;
        this.errorMsg = builder.errorMsg;
        this.min = builder.min;
        this.max = builder.max;
        this.allowedValues = Collections.unmodifiableList(builder.allowedValues);
    }

    public static class Builder {
        private String prompt = "";
        private String formatHint = "";
        private String errorMsg = "";
        private Integer min = null;
        private Integer max = null;
        private List<Integer> allowedValues = new ArrayList<>();

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

        public Builder setMin(Integer min) {
            this.min = min;
            return this;
        }

        public Builder setMax(Integer max) {
            this.max = max;
            return this;
        }

        public Builder setAllowedValues(List<Integer> allowedValues) {
            this.allowedValues = new ArrayList<>(Objects.requireNonNull(allowedValues));
            return this;
        }

        public IntInputConfig build() {
            return new IntInputConfig(this);
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

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public List<Integer> getAllowedValues() {
        return allowedValues;
    }
}
