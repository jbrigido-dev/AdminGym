package com.jbrigido.dev.controller.transaction;

import com.jbrigido.dev.view.transaction.TransactionView;

public class TransactionController {

    private TransactionView view;

    public TransactionController() {
        view = new TransactionView();
    }

    public TransactionView getView() {
        return view;
    }

}
