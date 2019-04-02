package com.example.task1.Activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.task1.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditActivity extends AppCompatActivity {

    public static final String EXTRA_ID="com.example.task1.EXTRA_ID";
    public static final String EXTRA_TITLE="com.example.task1.EXTRA_TITLE";
    public static final String EXTRA_SUBTITLE="com.example.task1.EXTRA_SUBTITLE";
    public static final String EXTRA_DESCRIPTION="com.example.task1.EXTRA_DESCRIPTION";
    public static final String EXTRA_URL="com.example.task1.EXTRA_URL";
    private Pattern urlPattern;
    private Matcher matcher;
    public static String pattern = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        final EditText etTitle=(EditText)findViewById(R.id.etTitle);
        final EditText etSubtitle=(EditText)findViewById(R.id.etSubtitle);
        final EditText etDescription=(EditText)findViewById(R.id.etDescription);
        final EditText etUrl=(EditText)findViewById(R.id.etUrl);
        final Button btnSave=(Button)findViewById(R.id.btnAdd);
        final ImageView ivPreview=(ImageView)findViewById(R.id.ivPreviewImage);



        if(getIntent().hasExtra(EXTRA_ID)){
            setTitle("Edit Book");
            etTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
            etSubtitle.setText(getIntent().getStringExtra(EXTRA_SUBTITLE));
            etDescription.setText(getIntent().getStringExtra(EXTRA_DESCRIPTION));
            etUrl.setText(getIntent().getStringExtra(EXTRA_URL));
            Picasso.get().load(etUrl.getText().toString()).into(ivPreview);


            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }



            etUrl.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    urlPattern = Pattern.compile(pattern);
                    //Log.d("Regex Success","Reges Matches");

                    if(!Pattern.compile(pattern).matcher(etUrl.getText().toString()).matches()) {
                        Toast.makeText(EditActivity.this,"Url Not Matches",Toast.LENGTH_SHORT).show();
                        Picasso.get().load(R.drawable.ic_broken_image).into(ivPreview);

                    }else{
                        Picasso.get().load(etUrl.getText().toString()).into(ivPreview);
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title=etTitle.getText().toString();
                    String subtitle=etSubtitle.getText().toString();
                    String description=etDescription.getText().toString();
                    String url=etUrl.getText().toString();


                    Intent data=new Intent();
                    data.putExtra(EXTRA_TITLE,title);
                    data.putExtra(EXTRA_SUBTITLE,subtitle);
                    data.putExtra(EXTRA_DESCRIPTION,description);
                    data.putExtra(EXTRA_URL,url);

                    int id=getIntent().getIntExtra(EXTRA_ID,-1);
                    if(id!=-1){
                        data.putExtra(EXTRA_ID,id);
                    }

                    setResult(Activity.RESULT_OK,data);
                    finish();
                }
            });



        }


    }
    /*public final static boolean isValidEmail(String url) {
        Pattern urlPattern = Pattern.compile(pattern);
        if (url !=Pattern.compile(pattern).matcher(etUrl.getText().toString()).matches()) {
            return false;
        } else {
            //android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }*/


        public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


}
