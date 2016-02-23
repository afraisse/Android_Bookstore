package org.emn.afraisse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.emn.afraisse.design.BookListFragment;

/**
 * @author Adrian
 */
public class LibraryActivity extends AppCompatActivity {

    private BookListFragment bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        View fragmentDetail = findViewById(R.id.fragment_book_detail);
        boolean landscape = getResources().getBoolean(R.bool.landscape);
        if (landscape) {

        } else {
            fragmentDetail.setVisibility(View.GONE);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_book_list, new BookListFragment(), BookListFragment.class.getSimpleName())
                    .commit();
        }
    }
}
