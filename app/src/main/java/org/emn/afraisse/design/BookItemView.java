package org.emn.afraisse.design;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.emn.afraisse.R;
import org.emn.afraisse.model.Book;

/**
 * @author Adrian
 * Custom view representing a book item.
 */
public class BookItemView extends LinearLayout {

    private ImageView coverImageView;
    private TextView titleTextView;

    public BookItemView(Context context) {
        this(context, null);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.coverImageView = (ImageView) findViewById(R.id.boot_item_cover);
        this.titleTextView = (TextView) findViewById(R.id.book_item_title);
    }

    public void bindView(Book book) {
        this.titleTextView.setText(book.title);
        Glide.with(this.getContext())
                .load(book.cover)
                .centerCrop()
                .into(this.coverImageView);

    }
}
