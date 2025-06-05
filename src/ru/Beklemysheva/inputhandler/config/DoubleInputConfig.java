/**
 * Конфигурация для валидации дробных числовых входных данных.
 * Этот класс предоставляет настройки для проверки и обработки дробных числовых входных данных.
 */
package ru.Beklemysheva.inputhandler.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий конфигурацию для валидации дробных числовых входных данных.
 */
public final class DoubleInputConfig {
    private final String prompt;
    private final String formatHint;
    private final String errorMsg;
    private final Double min;
    private final Double max;
    private final List<Double> allowedValues;

    /**
     * Конструктор для создания объекта DoubleInputConfig на основе билдера.
     *
     * @param builder Билдер, содержащий настройки конфигурации.
     */
    private DoubleInputConfig(Builder builder) {
        this.prompt = builder.prompt;
        this.formatHint = builder.formatHint;
        this.errorMsg = builder.errorMsg;
        this.min = builder.min;
        this.max = builder.max;
        this.allowedValues = Collections.unmodifiableList(builder.allowedValues);
    }

    /**
     * Билдер для создания объекта DoubleInputConfig.
     */
    public static class Builder {
        private String prompt = "";
        private String formatHint = "";
        private String errorMsg = "";
        private Double min = null;
        private Double max = null;
        private List<Double> allowedValues = new ArrayList<>();

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
         * Устанавливает минимальное значение.
         *
         * @param min Минимальное значение.
         * @return Текущий билдер.
         */
        public Builder setMin(Double min) {
            this.min = min;
            return this;
        }

        /**
         * Устанавливает максимальное значение.
         *
         * @param max Максимальное значение.
         * @return Текущий билдер.
         */
        public Builder setMax(Double max) {
            this.max = max;
            return this;
        }

        /**
         * Устанавливает список разрешенных значений.
         *
         * @param allowedValues Список разрешенных значений.
         * @return Текущий билдер.
         */
        public Builder setAllowedValues(List<Double> allowedValues) {
            this.allowedValues = new ArrayList<>(Objects.requireNonNull(allowedValues));
            return this;
        }

        /**
         * Создает объект DoubleInputConfig на основе текущих настроек билдера.
         *
         * @return Объект DoubleInputConfig.
         */
        public DoubleInputConfig build() {
            return new DoubleInputConfig(this);
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
     * Возвращает минимальное значение.
     *
     * @return Минимальное значение.
     */
    public Double getMin() {
        return min;
    }

    /**
     * Возвращает максимальное значение.
     *
     * @return Максимальное значение.
     */
    public Double getMax() {
        return max;
    }

    /**
     * Возвращает список разрешенных значений.
     *
     * @return Список разрешенных значений.
     */
    public List<Double> getAllowedValues() {
        return allowedValues;
    }
}