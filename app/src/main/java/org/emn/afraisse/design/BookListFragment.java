package org.emn.afraisse.design;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private CallbackListener listener;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (CallbackListener) context;
    }

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO : Add listener on tap item

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
                bookList = null;
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public interface CallbackListener {
        void onBookSelected(Book book);
    }
}
