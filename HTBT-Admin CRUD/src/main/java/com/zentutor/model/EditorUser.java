package com.zentutor.model;

public class EditorUser extends Admin {
    public EditorUser(int id, String name, String email, String password) {
        super(id, name, email, password, "Editor");
    }

    //prevent "editor users" from changing their role
    @Override
    public void setRole(String role) {

        throw new UnsupportedOperationException("Editors cannot change their role");
    }
}