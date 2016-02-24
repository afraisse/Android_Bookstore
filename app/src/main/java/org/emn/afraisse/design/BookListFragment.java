package org.emn.afraisse.design;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.emn.afraisse.R;
import org.emn.afraisse.model.Book;
import org.emn.afraisse.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author Adrian
 */
public class BookListFragment extends Fragment {

    private List<Book> bookList;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.book_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // We setup the adapter with an empty list for now, we will retrieve the books late
        recyclerView.setAdapter(new BookListRecyclerAdapter(this.getActivity(), new ArrayList<Book>()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://henri-potier.xebia.fr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LibraryService library = retrofit.create(LibraryService.class);
            Call<List<Book>> call = library.listBook();

            // Call service async
            call.enqueue(new Callback<List<Book>>() {
                @Override
                public void onResponse(Response<List<Book>> response, Retrofit retrofit) {
                    bookList = response.body();
                    // hand over book data
                    ((BookListRecyclerAdapter) recyclerView.getAdapter()).setBooks(bookList);
                }

                @Override
                public void onFailure(Throwable t) {
                    bookList = new ArrayList<>();
                    Toast.makeText(BookListFragment.this.getActivity(), "Unable to retrieve books", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            bookList = savedInstanceState.getParcelableArrayList(getString(R.string.book_list_key));
            ((BookListRecyclerAdapter) recyclerView.getAdapter()).setBooks(bookList);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (bookList != null) ((BookListRecyclerAdapter) recyclerView.getAdapter()).setBooks(bookList);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.book_list_key), (ArrayList<? extends Parcelable>) bookList);
    }

}
