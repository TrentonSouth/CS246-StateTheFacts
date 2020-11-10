package com.example.statethefacts;

import android.content.Context;

public class FactsListPresenter {
    public String LoadFacts(Context ctx) {
        String strFacts = "";
        GetFacts gf = new GetFacts();
        Facts facts = gf.Fetch(ctx);
        return strFacts;
    }
}
