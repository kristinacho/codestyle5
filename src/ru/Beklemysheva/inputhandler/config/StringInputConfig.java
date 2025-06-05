/**
 * Конфигурация для валидации строковых входных данных.
 * Этот класс предоставляет настройки для проверки и обработки строковых входных данных.
 */
package ru.Beklemysheva.inputhandler.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий конфигурацию для валидации строковых входных данных.
 */
public final class StringInputConfig {
    private final String prompt;
    private final String formatHint;
    private final String errorMsg;
    private final Integer minLength;
    private final Integer maxLength;
    private final List<String> allowedValues;
    private final boolean requireUppercaseFirstLetter;
    private final boolean allowOnlyLettersAndSpaces;

    /**
     * Конструктор для создания объекта StringInputConfig на основе билдера.
     *
     * @param builder Билдер, содержащий настройки конфигурации.
     */
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

    /**
     * Билдер для создания объекта StringInputConfig.
     */
    public static class Builder {
        private String prompt = "";
        private String formatHint = "";
        private String errorMsg = "";
        private Integer minLength = null;
        private Integer maxLength = null;
        private List<String> allowedValues = new ArrayList<>();
        private boolean requireUppercaseFirstLetter = false;
        private boolean allowOnlyLettersAndSpaces = false;

        /**
         * Устанавливает подсказку для ввода.
         *
         * @param prompt Подсказка для ввода.
         * @return Текущий билдер.
         */
        public Builder setPrompt(String prompt) {
            this.prompt = Objects.requireNonNull(prompt);
            return this;
        }

        /**
         * Устанавливает подсказку формата для ввода.
         *
         * @param formatHint Подсказка формата для ввода.
         * @return Текущий билдер.
         */
        public Builder setFormatHint(String formatHint) {
            this.formatHint = Objects.requireNonNull(formatHint);
            return this;
        }

        /**
         * Устанавливает сообщение об ошибке.
         *
         * @param errorMsg Сообщение об ошибке.
         * @return Текущий билдер.
         */
        public Builder setErrorMsg(String errorMsg) {
            this.errorMsg = Objects.requireNonNull(errorMsg);
            return this;
        }

        /**
         * Устанавливает минимальную длину строки.
         *
         * @param minLength Минимальная длина строки.
         * @return Текущий билдер.
         */
        public Builder setMinLength(Integer minLength) {
            this.minLength = minLength;
            return this;
        }

        /**
         * Устанавливает максимальную длину строки.
         *
         * @param maxLength Максимальная длина строки.
         * @return Текущий билдер.
         */
        public Builder setMaxLength(Integer maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        /**
         * Устанавливает список разрешенных значений.
         *
         * @param allowedValues Список разрешенных значений.
         * @return Текущий билдер.
         */
        public Builder setAllowedValues(List<String> allowedValues) {
            this.allowedValues = new ArrayList<>(Objects.requireNonNull(allowedValues));
            return this;
        }

        /**
         * Устанавливает требование к первой заглавной букве.
         *
         * @return Текущий билдер.
         */
        public Builder requireUppercaseFirstLetter() {
            this.requireUppercaseFirstLetter = true;
            return this;
        }

        /**
         * Разрешает использовать только буквы и пробелы.
         *
         * @return Текущий билдер.
         */
        public Builder allowOnlyLettersAndSpaces() {
            this.allowOnlyLettersAndSpaces = true;
            return this;
        }

        /**
         * Создает объект StringInputConfig на основе текущих настроек билдера.
         *
         * @return Объект StringInputConfig.
         */
        public StringInputConfig build() {
            return new StringInputConfig(this);
        }
    }

    /**
     * Возвращает подсказку для ввода.
     *
     * @return Подсказка для ввода.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Возвращает подсказку формата для ввода.
     *
     * @return Подсказка формата для ввода.
     */
    public String getFormatHint() {
        return formatHint;
    }

    /**
     * Возвращает сообщение об ошибке.
     *
     * @return Сообщение об ошибке.
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Возвращает минимальную длину строки.
     *
     * @return Минимальная длина строки.
     */
    public Integer getMinLength() {
        return minLength;
    }

    /**
     * Возвращает максимальную длину строки.
     *
     * @return Максимальная длина строки.
     */
    public Integer getMaxLength() {
        return maxLength;
    }

    /**
     * Возвращает список разрешенных значений.
     *
     * @return Список разрешенных значений.
     */
    public List<String> getAllowedValues() {
        return allowedValues;
    }

    /**
     * Проверяет, требуется ли первая заглавная буква.
     *
     * @return true, если требуется первая заглавная буква, иначе false.
     */
    public boolean isRequireUppercaseFirstLetter() {
        return requireUppercaseFirstLetter;
    }

    /**
     * Проверяет, разрешены ли только буквы и пробелы.
     *
     * @return true, если разрешены только буквы и пробелы, иначе false.
     */
    public boolean isAllowOnlyLettersAndSpaces() {
        return allowOnlyLettersAndSpaces;
    }
}