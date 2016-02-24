package org.emn.afraisse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.emn.afraisse.design.BookListFragment;
import org.emn.afraisse.design.BookListRecyclerAdapter;
import org.emn.afraisse.design.BookDetailFragment;
import org.emn.afraisse.model.Book;

/**
 * @author Adrian
 */
public class LibraryActivity extends AppCompatActivity implements BookListRecyclerAdapter.BookItemClickListener {

    private View fragmentDetail;
    private View fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        fragmentDetail = findViewById(R.id.fragment_book_detail);
        fragmentList = findViewById(R.id.fragment_book_list);

        boolean landscape = getResources().getBoolean(R.bool.landscape);
        if (landscape) {

        } else {
            fragmentDetail.setVisibility(View.GONE);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_book_list, new BookListFragment(), BookListFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void onClickBookItem(Book book) {
        // goto fragment detail

        if (getResources().getBoolean(R.bool.landscape)) {

        } else {
            fragmentList.setVisibility(View.GONE);

            BookDetailFragment fragment = new BookDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(getString(R.string.book_item_key), book);
            fragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_book_detail, fragment, BookDetailFragment.class.getSimpleName())
                    .commit();

            fragmentDetail.setVisibility(View.VISIBLE);
        }
    }
}
