package com.directa24.main.challenge;

/** Daniel Nacher 2024-07-05 */
public class ChallengeException extends Exception {

    private String message;

    public ChallengeException(String message) {
        this.message = message;
    }
}
