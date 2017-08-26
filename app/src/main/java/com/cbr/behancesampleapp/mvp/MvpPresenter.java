package com.cbr.behancesampleapp.mvp;

/**
 * Created by dimitrios on 24/08/2017.
 */

public interface MvpPresenter<VIEW extends MvpView> {

    void subscribe();

    void unsubscribe();

}
