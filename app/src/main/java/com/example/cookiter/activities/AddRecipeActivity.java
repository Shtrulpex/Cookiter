package com.example.cookiter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cookiter.R;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {
    ArrayList<String> ls;
    ArrayList<Integer> idL;
    String[] s=new String[]{" "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        ls = new ArrayList<>();
        idL = new ArrayList<>();
        ls.add("");
        idL.add(-1);
    }

    public void addProduct(View v){
        Intent i = new Intent(AddRecipeActivity.this, AddProductActivity.class);
        i.putExtra("ls", ls);
        i.putExtra("idL", idL);
        i.putExtra("login", getIntent().getStringExtra("login"));
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(getIntent().getStringExtra("login"));

        try {
            if (getIntent().getBooleanExtra("k", false)) {
                ls = getIntent().getStringArrayListExtra("ls");
                idL = getIntent().getIntegerArrayListExtra("idL");

                s = new String[ls.size()];

                for (int i = 0; i < ls.size(); i++) {
                    s[i] = ls.get(i);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, s);
                ListView lv = findViewById(R.id.productsList);
                lv.setAdapter(adapter);
            }
        }catch(Exception e){
            e.getLocalizedMessage();
        }
    }

    public void addRecipe(View v){
        
    }
}
