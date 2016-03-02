package infra;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface PokemonAPI {
    @GET("pokemon/{id}")
    Observable<PokemonData> getPokemonData(@Path("id") String id);
}
