package com.example.demobanque.controller;

public class AuthResponse {
    private String message;
    private boolean success;
    private Long userId;
    private String role;
    private String redirectUrl; // Nouveau champ pour le lien de redirection

    // Constructeur
    public AuthResponse(String message, boolean success, Long userId, String role, String redirectUrl) {
        this.message = message;
        this.success = success;
        this.userId = userId;
        this.role = role;
        this.redirectUrl = redirectUrl;
    }

    // Getters et Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
