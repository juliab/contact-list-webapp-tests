package service.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a negative response from the Contact service.
 */
public class ContactErrorResponseDto {

    private Map<String, ErrorDetails> errors;
    @JsonProperty("_message")
    private String detailedMessage;
    private String message;

    public Map<String, ErrorDetails> getErrors() {
        return errors;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public String getMessage() {
        return message;
    }

    static class ErrorDetails {

        private String name;
        private String message;
        private Properties properties;
        private String kind;
        private String path;
        private String value;
        private Reason reason;

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public Properties getProperties() {
            return properties;
        }

        public String getKind() {
            return kind;
        }

        public String getPath() {
            return path;
        }

        public String getValue() {
            return value;
        }

        public Reason getReason() {
            return reason;
        }

        static class Reason {
        }

        static class Properties {

            private String message;
            private String type;
            private String maxlength;
            private String path;
            private String value;
            private Reason reason;

            public String getMessage() {
                return message;
            }

            public String getType() {
                return type;
            }

            public String getMaxlength() {
                return maxlength;
            }

            public String getPath() {
                return path;
            }

            public String getValue() {
                return value;
            }

            public Reason getReason() {
                return reason;
            }

            static class Reason {

            }
        }
    }
}