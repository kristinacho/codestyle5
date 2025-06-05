package ru.Beklemysheva.inputhandler.config;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public final class DoubleInputConfig {
    private final String prompt;
    private final String formatHint;
    private final String errorMsg;
    private final Double min;
    private final Double max;
    private final List<Double> allowedValues;

    private DoubleInputConfig(Builder builder) {
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
        private Double min = null;
        private Double max = null;
        private List<Double> allowedValues = new ArrayList<>();

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

        public Builder setMin(Double min) {
            this.min = min;
            return this;
        }

        public Builder setMax(Double max) {
            this.max = max;
            return this;
        }

        public Builder setAllowedValues(List<Double> allowedValues) {
            this.allowedValues = new ArrayList<>(Objects.requireNonNull(allowedValues));
            return this;
        }

        public DoubleInputConfig build() {
            return new DoubleInputConfig(this);
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

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public List<Double> getAllowedValues() {
        return allowedValues;
    }
}
