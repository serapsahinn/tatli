package com.example.tatli;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private final DatabaseReference databaseReference;

    public FirebaseHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.databaseReference = database.getReference();
    }

    public DatabaseReference getAllTatli() {
        return databaseReference;
    }

    public DatabaseReference getTatliById(String id) {
        return databaseReference.child(id);
    }
}
