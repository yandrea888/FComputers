package com.example.fcomputers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fcomputers.adapters.ComputerAdapter;
import com.example.fcomputers.connection.FirebaseConnection;
import com.example.fcomputers.models.ComputerModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BaseActivity extends AppCompatActivity {

    protected ComputerModel model;
    protected ArrayList<ComputerModel>modelArrayList;
    protected ComputerAdapter adapter;

    protected FirebaseFirestore db;
    protected FirebaseAuth mAuth;
    protected FirebaseStorage mFirebaseStorage;
    //protected StorageReference mStorageRef;

    protected Query query;
    protected CollectionReference collectionReference;
    protected StorageReference mStorageReference, fileReference;

    protected String COLLECTION_NAME = "computers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    protected void init(){
        model = new ComputerModel();
        db = FirebaseConnection.ConnectionFirestore();
        mAuth = FirebaseConnection.ConnectionAuth();
        mFirebaseStorage = FirebaseConnection.ConnectionStorage();
        collectionReference = db.collection(COLLECTION_NAME);
    }

    protected void makeSimpleToast(String message, int duration){
        Toast.makeText(this, message, duration).show();
    }

    protected void makeSimpleAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    protected void goToList(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    protected void goToCreate(){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
    protected void goToEdit(){
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }
    protected void goToSearch(){
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }
    protected void goToDetail(ComputerModel model){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }
}
