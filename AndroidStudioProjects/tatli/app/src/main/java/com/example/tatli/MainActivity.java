package com.example.tatli;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseHelper firebaseHelper;
    private TatliAdapter adapter;
    private List<Tatli> tatliList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseHelper = new FirebaseHelper();
        tatliList = new ArrayList<>();


        listView = findViewById(R.id.listView);


        adapter = new TatliAdapter(this, tatliList, (tatli, position) -> {

            Intent intent = new Intent(MainActivity.this, TarifActivity.class);
            intent.putExtra("name", tatli.getName());
            intent.putExtra("description", tatli.getDescription());
            intent.putExtra("imageUrl", tatli.getImageUrl());
            intent.putExtra("videoUrl", tatli.getVideoUrl());
            intent.putExtra("tarif",tatli.getTarif());
            startActivity(intent);
        });
        listView.setAdapter(adapter);


        loadTatliFromFirebase();
    }

    private void loadTatliFromFirebase() {
        firebaseHelper.getAllTatli().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Data snapshot received: " + dataSnapshot.toString());
                
                if (!dataSnapshot.exists()) {
                    Log.d(TAG, "No data exists at this location");
                    return;
                }

                tatliList.clear();
                
                try {

                    for (DataSnapshot tatliSnapshot : dataSnapshot.getChildren()) {
                        String tatliId = tatliSnapshot.getKey(); // tatli1, tatli2, ...
                        Log.d(TAG, "Processing tatli with ID: " + tatliId);
                        
                        try {

                            String name = tatliSnapshot.child("name").getValue(String.class);
                            String description = tatliSnapshot.child("description").getValue(String.class);
                            String imageUrl = tatliSnapshot.child("imageUrl").getValue(String.class);
                            String videoUrl = tatliSnapshot.child("videoUrl").getValue(String.class);
                            String tarif=tatliSnapshot.child("tarif").getValue(String.class);

                            
                            Log.d(TAG, String.format("Parsed data for %s - name: %s, desc: %s, url: %s, video: %s", 
                                tatliId, name, description, imageUrl, videoUrl));
                            
                            if (name != null && description != null && imageUrl != null && videoUrl != null && tarif!=null) {

                                Tatli tatli = new Tatli(name, description, imageUrl, videoUrl,tarif);
                                tatliList.add(tatli);
                                Log.d(TAG, "Added tatli: " + tatli.toString());
                            } else {
                                Log.w(TAG, "Skipping " + tatliId + " due to null values");
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "Error parsing " + tatliId + ": " + e.getMessage());
                        }
                    }
                    
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "Total tatli loaded: " + tatliList.size());
                    
                    if (tatliList.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Hiç tatlı bulunamadı", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error in data processing: " + e.getMessage(), e);
                    Toast.makeText(MainActivity.this, "Veri yükleme hatası", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Firebase error: " + databaseError.getMessage());
                Toast.makeText(MainActivity.this, "Veri yükleme hatası: " + databaseError.getMessage(), 
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
}