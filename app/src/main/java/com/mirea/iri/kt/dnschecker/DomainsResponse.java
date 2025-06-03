package com.mirea.iri.kt.dnschecker;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DomainsResponse {
    @SerializedName("domains")
    private List<Domain> domains;

    public List<Domain> getDomains() {
        return domains; }

    public static class Domain {
        @SerializedName("domain")
        private String domain;

        public String getDomain() {
            return domain; }
    }
}