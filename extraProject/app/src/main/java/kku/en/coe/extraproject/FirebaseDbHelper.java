package kku.en.coe.extraproject;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDbHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private List<ObjectEvent> events = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<ObjectEvent> events,List<String> keys);
        void DataIsInserted();
        void DataIsUpdatede();
        void DataIsDeleted();
    }

    public FirebaseDbHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");
    }

    public void readEvents(final DataStatus dataStatus) {
        mRef.child("JIROT JOE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    ObjectEvent objectEvent = keyNode.getValue(ObjectEvent.class);
                    events.add(objectEvent);
                }
                dataStatus.DataIsLoaded(events,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
