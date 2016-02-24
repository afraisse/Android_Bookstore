package org.emn.afraisse.design;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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

    /**
     * ViewHolder pattern
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Book item;
        private BookItemClickListener listener;

        public ViewHolder(BookItemView bookItemView) {
            super(bookItemView);
            bookItemView.setOnClickListener(this);
            listener = (BookItemClickListener) bookItemView.getContext();
        }

        @Override
        public void onClick(View v) {
            listener.onClickBookItem(item);
        }

        public void setItem(Book item) {
            this.item = item;
        }
    }

    /**
     * Listener for click events on list items
     */
    public interface BookItemClickListener {
        void onClickBookItem(Book book);
    }

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
        Book book = books.get(position);
        ((BookItemView) holder.itemView).bindView(book);
        holder.setItem(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        this.notifyDataSetChanged();
    }
}
