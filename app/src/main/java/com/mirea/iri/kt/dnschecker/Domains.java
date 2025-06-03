package com.mirea.iri.kt.dnschecker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Domains {
    @GET("/v1/domains/search")
    Call<DomainsResponse> searchDomains(@Query("domain") String query);
}