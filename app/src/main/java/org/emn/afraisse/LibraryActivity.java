package org.emn.afraisse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.emn.afraisse.model.Book;
import org.emn.afraisse.service.LibraryService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Adrian on 23/02/2016.
 */
public class LibraryActivity extends AppCompatActivity {

    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                if (response.code() == 200)
                    bookList = response.body();
                else bookList = null;
            }

            @Override
            public void onFailure(Throwable t) {
                bookList = null;
            }
        });
    }
}
