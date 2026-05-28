package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {


    private Spinner categorySp,bookLanguageSp;
    private EditText bookName,writerName;
    private BookInformation bookInformation;
    private DataSourceBook dataSourceBook;
    private Button addBookBtn,addMoreBookBtn;
    boolean update;
    int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        categorySp = findViewById(R.id.categorySp);
        bookLanguageSp = findViewById(R.id.languageSp);

        bookName = findViewById(R.id.nameBookEt);
        writerName = findViewById(R.id.writerBookEt);

        addBookBtn = findViewById(R.id.addBookBtn);
        addMoreBookBtn = findViewById(R.id.addMoreBookBtn);

        dataSourceBook = new DataSourceBook(this);

        update = getIntent().getBooleanExtra("Update",false);
        BookInformation bookInformation = (BookInformation) getIntent().getSerializableExtra("BookInfo");


        if (update == true){
            bookId = bookInformation.getBookId();
            bookName.setText(bookInformation.getBookName());
            writerName.setText(bookInformation.getWriterName());
            categorySp.setSelection(getBookType().indexOf(bookInformation.getBookCategory()));
            bookLanguageSp.setSelection(getBookLanguage().indexOf(bookInformation.getBookLanguage()));
            addMoreBookBtn.setVisibility(View.INVISIBLE);
            addBookBtn.setText("Update");
        }




        ArrayAdapter<String> booktypeadapter = new ArrayAdapter<String>(this,
                R.layout.spinner,getBookType());

        categorySp.setAdapter(booktypeadapter);

        ArrayAdapter<String> languageadapter = new ArrayAdapter<String>(this,
                R.layout.spinner,getBookLanguage());

        bookLanguageSp.setAdapter(languageadapter);



    }
   private ArrayList<String>getBookType(){
        ArrayList<String>bookType = new ArrayList<>();
        bookType.add("Novel");
        bookType.add("History");
        bookType.add("Math");
        bookType.add("Comics");
        bookType.add("Adventure");
        bookType.add("Detective");
        bookType.add("Science fiction");
        bookType.add("Horror");
        bookType.add("Travel");
        bookType.add("Science");
        bookType.add("Biographies");
        return bookType;
   }
    private ArrayList<String>getBookLanguage(){
        ArrayList<String>languages = new ArrayList<>();
        languages.add("Bangla");
        languages.add("English");
        languages.add("Hindi");
        languages.add("Urdu");
        languages.add("Spanish");
        languages.add("Arabic");
        languages.add("Chinese");
        languages.add("Irish");
        languages.add("Latin");

        return languages;
    }

    public void addBooks(View view) {

        if (update == true){
            String bookNme = bookName.getText().toString() ;
            String bookWtr = writerName.getText().toString();
            String booktype = categorySp.getSelectedItem().toString();
            String booklan = bookLanguageSp.getSelectedItem().toString();
            BookInformation bookInformation = new BookInformation(bookId,bookNme,bookWtr,booktype,booklan);
            boolean status =dataSourceBook.updateBook(bookInformation);
            if (status){
                Toast.makeText(this,"Data Updated",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookActivity.this,ListActivity.class);
                intent.putExtra("Admin",true);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Data not Updated",Toast.LENGTH_SHORT).show();

            }
        }

        else {
            String bookNme = bookName.getText().toString() ;
            String bookWtr = writerName.getText().toString();
            String booktype = categorySp.getSelectedItem().toString();
            String booklan = bookLanguageSp.getSelectedItem().toString();

            bookInformation = new BookInformation(bookNme,bookWtr,booktype,booklan);
            boolean status =dataSourceBook.incertBookData(bookInformation);
            if (status){
                Toast.makeText(this,"Data incerted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookActivity.this,ListActivity.class);
                intent.putExtra("Admin",true);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Data not incerted",Toast.LENGTH_SHORT).show();

            }
        }



    }

    public void addMoreBooks(View view) {

        String bookNme = bookName.getText().toString() ;
        String bookWtr = writerName.getText().toString();
        String booktype = categorySp.getSelectedItem().toString();
        String booklan = bookLanguageSp.getSelectedItem().toString();

        bookInformation = new BookInformation(bookNme,bookWtr,booktype,booklan);
        boolean status =dataSourceBook.incertBookData(bookInformation);

        if (status){
            Toast.makeText(this,"Data incerted",Toast.LENGTH_SHORT).show();
            bookName.setText("");
            writerName.setText("");

        }
        else {
            Toast.makeText(this,"Data not incerted",Toast.LENGTH_SHORT).show();

        }

    }
}
