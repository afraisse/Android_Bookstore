package org.emn.afraisse;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
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

    private View fragmentFrame1;
    private View fragmentFrame2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        fragmentFrame2 = findViewById(R.id.fragment_frame_2);
        fragmentFrame1 = findViewById(R.id.fragment_frame_1);

        boolean landscape = getResources().getBoolean(R.bool.landscape);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame_1, new BookListFragment(), BookListFragment.class.getSimpleName())
                    .commit();
        }

        if (!landscape) {
            fragmentFrame2.setVisibility(View.GONE);
        } else {
            // If in landscape we clean the fragment1 backstack
            getSupportFragmentManager()
                    .popBackStack(BookListFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void onClickBookItem(Book book) {

        BookDetailFragment fragment = new BookDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.book_item_key), book);
        fragment.setArguments(bundle);

        // goto fragment detail
        if (getResources().getBoolean(R.bool.landscape)) {
            fragmentFrame2.setVisibility(View.VISIBLE);

            // Landscape - we use an other frame
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame_2, fragment, BookDetailFragment.class.getSimpleName())
                    .commit();
        } else {
            // We replace the fragment in the same frame
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame_1, fragment, BookDetailFragment.class.getSimpleName())
                    .addToBackStack(BookListFragment.class.getSimpleName())
                    .commit();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
