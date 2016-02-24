package org.emn.afraisse.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.emn.afraisse.R;
import org.emn.afraisse.model.Book;

/**
 * @author Adrian
 */
public class BookDetailFragment extends Fragment {
    private Book book;

    private ImageView cover;
    private TextView title;
    private TextView price;
    private TextView isbn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);

        cover = (ImageView) view.findViewById(R.id.book_detail_cover);
        title = (TextView) view.findViewById(R.id.book_detail_title);
        price = (TextView) view.findViewById(R.id.book_detail_price);
        isbn = (TextView) view.findViewById(R.id.book_detail_isbn);

        book = getArguments().getParcelable(getString(R.string.book_item_key));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Glide.with(this)
                .load(book.cover)
                .fitCenter()
                .into(cover);
        title.setText(book.title);
        price.setText(getString(R.string.price, book.price));
        isbn.setText(book.isbn);
    }
}
