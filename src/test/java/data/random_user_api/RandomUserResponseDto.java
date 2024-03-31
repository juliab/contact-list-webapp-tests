package data.random_user_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class represents the response from the Random User API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class RandomUserResponseDto {
    private RandomUserDto[] results;
    private Info info;

    public RandomUserDto[] getResults() {
        return results;
    }

    public void setResults(RandomUserDto[] results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    static class Info {
        private String seed;
        private int results;
        private int page;
        private String version;

        public String getSeed() {
            return seed;
        }

        public void setSeed(String seed) {
            this.seed = seed;
        }

        public int getResults() {
            return results;
        }

        public void setResults(int results) {
            this.results = results;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
