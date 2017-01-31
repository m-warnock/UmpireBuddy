package com.michael.android.umpirebuddy;

public class Counter {
    private int mTextResId;

    public Counter(int textResId){
        mTextResId = textResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
}
