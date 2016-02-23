package org.emn.afraisse.design;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.emn.afraisse.R;
import org.emn.afraisse.model.Book;

import java.util.List;

/**
 * @author Adrian
 */
public class BookListRecyclerAdapter extends RecyclerView.Adapter<BookListRecyclerAdapter.ViewHolder> {

    private List<Book> books;
    private LayoutInflater layoutInflater;

    public BookListRecyclerAdapter(Context context, List<Book> books) {
        this.books = books;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BookItemView v = (BookItemView) layoutInflater.inflate(R.layout.custom_view_book_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((BookItemView) holder.itemView).bindView(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(BookItemView bookItemView) {
            super(bookItemView);
        }
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
