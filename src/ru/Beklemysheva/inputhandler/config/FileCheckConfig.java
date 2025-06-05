/**
 * Конфигурация для валидации файлов.
 * Этот класс предоставляет настройки для проверки и обработки файлов.
 */
package ru.Beklemysheva.inputhandler.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий конфигурацию для валидации файлов.
 */
public final class FileCheckConfig {
    private final boolean checkExistence;
    private final boolean checkExtension;
    private final String errorMsg;
    private final List<String> allowedExtensions;

    /**
     * Конструктор для создания объекта FileCheckConfig на основе билдера.
     *
     * @param builder Билдер, содержащий настройки конфигурации.
     */
    private FileCheckConfig(Builder builder) {
        this.checkExistence = builder.checkExistence;
        this.checkExtension = builder.checkExtension;
        this.errorMsg = builder.errorMsg;
        this.allowedExtensions = Collections.unmodifiableList(builder.allowedExtensions);
    }

    /**
     * Билдер для создания объекта FileCheckConfig.
     */
    public static class Builder {
        private boolean checkExistence = false;
        private boolean checkExtension = false;
        private String errorMsg = "";
        private List<String> allowedExtensions = new ArrayList<>();

        /**
         * Устанавливает флаг проверки существования файла.
         *
         * @param checkExistence Флаг проверки существования файла.
         * @return Текущий билдер.
         */
        public Builder setCheckExistence(boolean checkExistence) {
            this.checkExistence = checkExistence;
            return this;
        }

        /**
         * Устанавливает флаг проверки расширения файла.
         *
         * @param checkExtension Флаг проверки расширения файла.
         * @return Текущий билдер.
         */
        public Builder setCheckExtension(boolean checkExtension) {
            this.checkExtension = checkExtension;
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
         * Устанавливает список разрешенных расширений файлов.
         *
         * @param allowedExtensions Список разрешенных расширений файлов.
         * @return Текущий билдер.
         */
        public Builder setAllowedExtensions(List<String> allowedExtensions) {
            this.allowedExtensions = new ArrayList<>(allowedExtensions);
            return this;
        }

        /**
         * Создает объект FileCheckConfig на основе текущих настроек билдера.
         *
         * @return Объект FileCheckConfig.
         */
        public FileCheckConfig build() {
            return new FileCheckConfig(this);
        }
    }

    /**
     * Проверяет, нужно ли проверять существование файла.
     *
     * @return true, если нужно проверять существование файла, иначе false.
     */
    public boolean isCheckExistence() {
        return checkExistence;
    }

    /**
     * Проверяет, нужно ли проверять расширение файла.
     *
     * @return true, если нужно проверять расширение файла, иначе false.
     */
    public boolean isCheckExtension() {
        return checkExtension;
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
     * Возвращает список разрешенных расширений файлов.
     *
     * @return Список разрешенных расширений файлов.
     */
    public List<String> getAllowedExtensions() {
        return allowedExtensions;
    }
}