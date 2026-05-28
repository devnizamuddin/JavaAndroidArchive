package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookDetailActivity extends AppCompatActivity {

    BookInformation bookInformation;
    UserInformation userInformation;
    private TextView bookNameTV,bookIdTV,bookWriterTV,bookCatagoryTV,bookLanguageTV,rateTV;
    private RatingBar showrateRB,rateRB;
    private RecyclerView commentRV;
    private EditText commentET;
    private CommentAdapter commentAdapter;
    private ImageButton sendComment;
    DataSourceFeedBack dataSourceFeedBack;
    private ArrayList<FeedbackInfo> feedbackofId;
    private ArrayList<FeedbackInfo>allfeedBack;
    FeedbackInfo feedbackInfo;
    TextView textView;
    int bookId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book_detail);

        bookNameTV = findViewById(R.id.bookNameTV);
        bookIdTV = findViewById(R.id.bookIdTV);
        bookWriterTV = findViewById(R.id.bookWriterTV);
        bookCatagoryTV = findViewById(R.id.bookCatagoryTV);
        bookLanguageTV = findViewById(R.id.bookLanguageTV);

        dataSourceFeedBack = new DataSourceFeedBack(this);

        //feed back araylist
        feedbackofId = dataSourceFeedBack.getAllFeedbackById(bookId);
        allfeedBack = dataSourceFeedBack.getAllFeedbackInfos();

        for (FeedbackInfo feedbackInfo: feedbackofId){
            String comment = feedbackInfo.getComment();
            textView.setText(comment);
        }


    





       bookInformation = (BookInformation) getIntent().getSerializableExtra("bookInfo");
       userInformation = (UserInformation) getIntent().getSerializableExtra("UserInfo");
         bookId = bookInformation.getBookId();
        String bookIdS = Integer.toString(bookInformation.getBookId());
        String bookName = bookInformation.getBookName();
        String writerName = bookInformation.getWriterName();
        String bookCategory = bookInformation.getBookCategory();
        String bookLanguage = bookInformation.getBookLanguage();
        bookIdTV.setText(bookIdS);
        bookNameTV.setText(bookName);
        bookWriterTV.setText(writerName);
        bookCatagoryTV.setText(bookCategory);
        bookLanguageTV.setText(bookLanguage);


    }

    public void submit(View view) {
        commentAdapter.Update(feedbackofId);
        int bookId = bookInformation.getBookId();
        int userId = userInformation.getUserId();
        float rating = rateRB.getRating();
        String comment = commentET.getText().toString();
        FeedbackInfo feedbackInfo = new FeedbackInfo(rating,comment,bookId,userId);
       boolean status =  dataSourceFeedBack.incertFeedback(feedbackInfo);
        if (status){
            Toast.makeText(this,"feedback Incerted",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"feedback not Incerted",Toast.LENGTH_SHORT).show();
        }


    }


    public void logout(MenuItem item) {
        startActivity(new Intent(BookDetailActivity.this,LoginActivity.class));
    }

    public void viewProfile(MenuItem item) {
        Intent intent = new Intent(BookDetailActivity.this,UserProfileActivity.class);
        intent.putExtra("UserInfo",userInformation);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }
}
