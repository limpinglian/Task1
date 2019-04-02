package com.example.task1.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.task1.R;
import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;

public class AddBookActivity extends AppCompatActivity {
    public static final String EXTRA_ID="com.example.task1.EXTRA_ID";
    public static final String EXTRA_TITLE="com.example.task1.EXTRA_TITLE";
    public static final String EXTRA_SUBTITLE="com.example.task1.EXTRA_SUBTITLE";
    public static final String EXTRA_DESCRIPTION="com.example.task1.EXTRA_DESCRIPTION";
    public static final String EXTRA_URL="com.example.task1.EXTRA_URL";
    private ImageView ivPreview;
    private EditText etTitle;
    private EditText etSubtitle;
    private EditText etDescription;
    private EditText etUrl;
    private Button btnAdd;
   public static String pattern = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        ivPreview=findViewById(R.id.ivPreviewImage);
        etTitle=findViewById(R.id.etTitle);
        etSubtitle=findViewById(R.id.etSubtitle);
        etDescription=findViewById(R.id.etDescription);
        etUrl=findViewById(R.id.etUrl);
        btnAdd=findViewById(R.id.btnAdd);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Book");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBook();
            }
        });

        etUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Picasso.get().load(etUrl.getText().toString()).into(ivPreview);
                }
            }
        });

        etUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!Pattern.compile(pattern).matcher(etUrl.getText().toString()).matches()) {
                    Toast.makeText(AddBookActivity.this,"Url Not Matches",Toast.LENGTH_SHORT).show();
                    Picasso.get().load(R.drawable.broken).into(ivPreview);

                }

                }
            @Override
            public void afterTextChanged(Editable s) {

            }



        });



    }
    public void saveBook(){
        String title=etTitle.getText().toString();
        String subtitle=etSubtitle.getText().toString();
        String description=etDescription.getText().toString();
        String url=etUrl.getText().toString();



        if(title.trim().isEmpty()||subtitle.trim().isEmpty()||description.trim().isEmpty()||url.trim().isEmpty()){
            Toast.makeText(this,"Please Enter all info",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_SUBTITLE,subtitle);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_URL,url);
        setResult(RESULT_OK,data);
        finish();
    }
}
