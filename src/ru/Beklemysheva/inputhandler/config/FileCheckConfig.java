package ru.Beklemysheva.inputhandler.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class FileCheckConfig {
    private final boolean checkExistence;
    private final boolean checkExtension;
    private final String errorMsg;
    private final List<String> allowedExtensions;

    private FileCheckConfig(Builder builder) {
        this.checkExistence = builder.checkExistence;
        this.checkExtension = builder.checkExtension;
        this.errorMsg = builder.errorMsg;
        this.allowedExtensions = Collections.unmodifiableList(builder.allowedExtensions);
    }

    public static class Builder {
        private boolean checkExistence = false;
        private boolean checkExtension = false;
        private String errorMsg = "";
        private List<String> allowedExtensions = new ArrayList<>();

        public Builder setCheckExistence(boolean checkExistence) {
            this.checkExistence = checkExistence;
            return this;
        }

        public Builder setCheckExtension(boolean checkExtension) {
            this.checkExtension = checkExtension;
            return this;
        }

        public Builder setErrorMsg(String errorMsg) {
            this.errorMsg = Objects.requireNonNull(errorMsg);
            return this;
        }

        public Builder setAllowedExtensions(List<String> allowedExtensions) {
            this.allowedExtensions = new ArrayList<>(allowedExtensions);
            return this;
        }

        public FileCheckConfig build() {
            return new FileCheckConfig(this);
        }
    }

    public boolean isCheckExistence() { return checkExistence; }
    public boolean isCheckExtension() { return checkExtension; }
    public String getErrorMsg() { return errorMsg; }
    public List<String> getAllowedExtensions() { return allowedExtensions; }
}
