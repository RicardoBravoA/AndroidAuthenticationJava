package com.rba.library;

public class AuthenticationEntity {

    private String title;
    private String subtitle;
    private String description;
    private String buttonText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    private AuthenticationEntity(AuthenticationBuilder authenticationBuilder) {
        this.title = authenticationBuilder.title;
        this.subtitle = authenticationBuilder.subtitle;
        this.description = authenticationBuilder.description;
        this.buttonText = authenticationBuilder.buttonText;
    }

    public static class AuthenticationBuilder {
        private String title;
        private String subtitle;
        private String description;
        private String buttonText;


        public AuthenticationBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public AuthenticationBuilder setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public AuthenticationBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AuthenticationBuilder setButtonText(String buttonText) {
            this.buttonText = buttonText;
            return this;
        }

        public AuthenticationEntity build() {
            return new AuthenticationEntity(this);
        }

    }

}
