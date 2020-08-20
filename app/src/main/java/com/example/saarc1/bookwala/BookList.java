package com.example.saarc1.bookwala;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookList extends RecyclerView.Adapter<BookList.MyViewHolder> {

    private Context context;
    private List<Book> bookList;

    public BookList(Context context, List<Book> bookList){
        this.context = context;
        this.bookList = bookList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.book_item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder , final int position){


        Book book = bookList.get(position);
        holder.book_title_id.setText(book.getBookName());
        holder.book_price.setText(book.getBookPrice());
        holder.book_author.setText(book.getBookAuthor());
        Picasso.get().load(book.getBookImage()).into(holder.bookImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, bookdescription.class);

                intent.putExtra("bookName",bookList.get(position).getBookName());
                intent.putExtra("bookPrice", bookList.get(position).getBookPrice());
                intent.putExtra("bookAuthor", bookList.get(position).getBookAuthor());
                intent.putExtra("bookImage", bookList.get(position).getBookImage());
                intent.putExtra("bookId",bookList.get(position).getBookId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_title_id;
        TextView book_price;
        TextView book_author;
        ImageView bookImage;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            book_title_id = (TextView) itemView.findViewById(R.id.book_title_id) ;
            book_price = itemView.findViewById(R.id.book_price);
            book_author = itemView.findViewById(R.id.book_author);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            bookImage = itemView.findViewById(R.id.book_img_id);




        }


    }


    }

