package com.thesame.baselibrary.utlis.launchstarter.task;
/**
 * Created by whz  on 2019-06-27
 */
public abstract class MainTask extends Task {

    @Override
    public boolean runOnMainThread() {
        return true;
    }
}
