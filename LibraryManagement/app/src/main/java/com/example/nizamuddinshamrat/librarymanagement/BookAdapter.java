package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saran on 12/25/2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookListViewHolder> {

    private Context context;
    private ArrayList<BookInformation> Books;
    private clickListener listener;

    public BookAdapter(Context context, ArrayList<BookInformation> books,clickListener listener) {
        this.context = context;
        Books = books;
        this.listener = listener;
    }


    @Override
    public BookListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.book_single_row,parent,false);
        return new BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListViewHolder holder, int position) {
        BookInformation bookInformation=Books.get(position);


        holder.bookNameTV.setText(bookInformation.getBookName());
        holder.bookWriterNameTV.setText(bookInformation.getWriterName());
        holder.bookCategory.setText(bookInformation.getBookCategory());
    }

    @Override
    public int getItemCount() {
        return Books.size();
    }

    public class BookListViewHolder extends RecyclerView.ViewHolder {
        TextView bookNameTV;
        TextView bookWriterNameTV;
        TextView bookCategory;

        public BookListViewHolder(View itemView) {
            super(itemView);
            bookNameTV=itemView.findViewById(R.id.listBookNameTV);
            bookWriterNameTV=itemView.findViewById(R.id.listWriterTV);
            bookCategory =itemView.findViewById(R.id.listCatagoryTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(Books.get(getAdapterPosition()));
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    listener.onLogClick(Books.get(getAdapterPosition()));

                    return true;
                }
            });

        }
    }
    public void Update(ArrayList<BookInformation> Books){
        this.Books=Books;
        notifyDataSetChanged();
    }
    public interface clickListener{
        void onClick(BookInformation bookInformation);
        void onLogClick(BookInformation bookInformation);
    }
    public void SetFilter(ArrayList<BookInformation> book){
        Books=new ArrayList<BookInformation>();
        Books.addAll(book);
        notifyDataSetChanged();
    }
}
