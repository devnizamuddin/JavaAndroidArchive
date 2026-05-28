package com.example.nizamuddinshamrat.librarymanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.nizamuddinshamrat.librarymanagement.BookAdapter.clickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListActivity extends AppCompatActivity implements clickListener,SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private  BookAdapter bookAdapter;
    private DataSourceBook dataSourceBook;
    private ArrayList<BookInformation> bookInformations;
    boolean admin;
    UserInformation userInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.bookRecylerView);


        dataSourceBook = new DataSourceBook(this);
        bookInformations= dataSourceBook.getAllBooks();
        bookAdapter = new BookAdapter(this,bookInformations,this);
        userInformation = (UserInformation) getIntent().getSerializableExtra("UserInformation");


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(bookAdapter);
        bookAdapter.Update(bookInformations);

        admin = getIntent().getBooleanExtra("Admin",false);

    }

    @Override
    public void onClick(BookInformation bookInformation) {
        Toast.makeText(ListActivity.this,bookInformation.getBookName(),Toast.LENGTH_SHORT).show();

        if (admin == true){
            Intent intent = new Intent(ListActivity.this,BookActivity.class);
            intent.putExtra("BookInfo",bookInformation);
            intent.putExtra("Update",true);
            startActivity(intent);
        }

        else {

            int bookId = bookInformation.getBookId();
            String bookName = bookInformation.getBookName();
            String writerName = bookInformation.getWriterName();
            String bookCategory = bookInformation.getBookCategory();
            String bookLanguage = bookInformation.getBookLanguage();
            BookInformation bookInformation1 = new BookInformation(bookId,bookName,writerName,bookCategory,bookLanguage);
            Intent intent = new Intent(ListActivity.this,BookDetailActivity.class);
            intent.putExtra("bookInfo",bookInformation1);
            intent.putExtra("UserInfo",userInformation);
            startActivity(intent);
        }


    }
    @Override
    public void onLogClick(final BookInformation bookInformation) {

        if (admin == true){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(bookInformation.getBookName());
            alertDialog.setMessage("Do you want to delete this book??");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean status =  dataSourceBook.deleteBook(bookInformation.getBookId());
                    bookInformations.remove(bookInformation);
                    bookAdapter.Update(bookInformations);
                    if (status){
                        Toast.makeText(ListActivity.this,"Book deleted",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ListActivity.this,"Book not deleted",Toast.LENGTH_SHORT).show();

                    }
                }
            });
            alertDialog.setNegativeButton("No",null);

            alertDialog.show();
        }
        else {
            Toast.makeText(this,bookInformation.getBookName(),Toast.LENGTH_SHORT).show();
        }
    }

    //menu Item
    public void logout(MenuItem item) {
        startActivity(new Intent(ListActivity.this,LoginActivity.class));
    }

    public void viewProfile(MenuItem item) {
        Intent intent = new Intent(ListActivity.this,UserProfileActivity.class);
        intent.putExtra("UserInfo",userInformation);
        startActivity(intent);
    }

    //for Search Vieww
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        MenuItem viewProfile = menu.findItem(R.id.profile);
        if (admin == true){
            viewProfile.setVisible(false);

        }
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        bookAdapter.SetFilter(bookInformations);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<BookInformation> books=new ArrayList<>();
        ArrayList<BookInformation> unsortedBooksByName=new ArrayList<>();
        ArrayList<BookInformation> unsortedBooksByWriter=new ArrayList<>();
        ArrayList<BookInformation> booksByCatagory=new ArrayList<>();
        ArrayList<BookInformation> booksByLang=new ArrayList<>();
        for(BookInformation book: bookInformations){

            String name=book.getBookName().toLowerCase();
            if(name.contains(newText)){
                unsortedBooksByName.add(book);
            }

        }
        sortByName(unsortedBooksByName,newText);
        books.addAll(unsortedBooksByName);
        for(BookInformation book: bookInformations){

            String name=book.getBookName().toLowerCase();
            String writer=book.getWriterName().toLowerCase();
            if(writer.contains(newText)&&!(name.contains(newText))){
                unsortedBooksByWriter.add(book);
            }
        }
        sortByWriter(unsortedBooksByWriter,newText);
        books.addAll(unsortedBooksByWriter);
        for(BookInformation book: bookInformations){
            String catagory=book.getBookCategory().toLowerCase();
            String name=book.getBookName().toLowerCase();
            String writer=book.getWriterName().toLowerCase();

            if(catagory.contains(newText)&&!(name.contains(newText)||writer.contains(newText))){
                booksByCatagory.add(book);
            }
        }
        books.addAll(booksByCatagory);
        for(BookInformation book: bookInformations){
            String catagory=book.getBookCategory().toLowerCase();
            String name=book.getBookName().toLowerCase();
            String writer=book.getWriterName().toLowerCase();
            String lang=book.getBookLanguage().toLowerCase();
            if (lang.contains(newText)&&!(name.contains(newText)||writer.contains(newText)||catagory.contains(newText)))
            {
                booksByLang.add(book);
            }
        }
        books.addAll(booksByLang);
        bookAdapter.SetFilter(books);
        return true;
    }
    void sortByName(ArrayList<BookInformation> books,String key){
        for (BookInformation name :books){
            name.setIndex(name.getBookName().toLowerCase().indexOf(key));
        }
        Collections.sort(books, new Comparator<BookInformation>() {
            @Override
            public int compare(BookInformation o1, BookInformation o2) {
                if(o1.getIndex()==o2.getIndex())
                    return 0;
                return o1.getIndex() < o2.getIndex() ? -1 : 1;
            }
        });
    }
    void sortByWriter(ArrayList<BookInformation> books,String key){
        for (BookInformation name :books){
            name.setIndex(name.getWriterName().toLowerCase().indexOf(key));
        }
        Collections.sort(books, new Comparator<BookInformation>() {
            @Override
            public int compare(BookInformation o1, BookInformation o2) {
                if(o1.getIndex()==o2.getIndex())
                    return 0;
                return o1.getIndex() < o2.getIndex() ? -1 : 1;
            }
        });
    }
}
