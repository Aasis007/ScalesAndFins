package com.example.aasis.scalesandfins.Model;

public class CheckUserResponse {
    private boolean exists;
    private boolean error_msg;

    public CheckUserResponse() {
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean isError_msg() {
        return error_msg;
    }

    public void setError_msg(boolean error_msg) {
        this.error_msg = error_msg;
    }
}
