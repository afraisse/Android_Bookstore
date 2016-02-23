package org.emn.afraisse.service;

import org.emn.afraisse.model.Book;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * @author Adrian
 *
 * Service interface to fetch Books over HTTP
 */
public interface LibraryService {

    @GET("books")
    Call<List<Book>> listBook();

}
