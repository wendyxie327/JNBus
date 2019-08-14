package com.wendy.jnbus.util;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.wendy.jnbus.R;
import com.wendy.jnbus.ui.JNBusApplication;

import java.util.Calendar;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class SearchKeyboard {

    private static final String TAG = "SearchKeyboard";

    private Context ctx;
    private Activity act;
    private KeyboardView keyboardView;
    private Keyboard keyboard;// 数字键盘

    private EditText ed;
    public static final int MIN_CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;
    private int lastKeyBoard = 0;

    /** 设置对于点击按钮的监听*/
    private KeyboardViewListener keyboardViewListener ;
    private InputMethodManager imm ;

    public interface KeyboardViewListener{
        void onClickKey(int keyCode);
    }

    public SearchKeyboard(Activity act, Context ctx, EditText edit) {
        this.act = act;
        this.ctx = ctx;
        this.ed = edit;
        imm = (InputMethodManager) JNBusApplication.getContext().getSystemService(INPUT_METHOD_SERVICE);
        keyboard = new Keyboard(ctx, R.xml.keyboard_search);
        keyboardView = act.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(false); // 设为true时，点击按钮，将提出对话框，提示选中内容
        keyboardView.setOnKeyboardActionListener(listener);
        keyboardViewListener = (KeyboardViewListener) act;
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {

        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            long currentTime = Calendar.getInstance().getTimeInMillis();
            int start = ed.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
                hideKeyboard();
                keyboardViewListener.onClickKey(primaryCode);
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        if (editable.toString().toUpperCase().endsWith("BRT")){
                            editable.delete(start - 3, start);
                        }else {
                            editable.delete(start - 1, start);
                        }
                    }
                }
            }   else if ( primaryCode == 60000 ){
                if ( lastKeyBoard == primaryCode && currentTime - lastClickTime < MIN_CLICK_DELAY_TIME) {
                    // 快速切换
                    if (editable.toString().toUpperCase().endsWith("B")){
                        editable.insert(start, "RT");
                    }else {
                        editable.delete(start - 2, start);
                    }
                }else {
                    editable.insert(start, "B");
                }

            }   else if ( primaryCode == 60001 ){
                editable.insert(start, "K");
            }   else if ( primaryCode == 60002 ){
                editable.insert(start, "T");
            }   else if ( primaryCode == 60003 ){ // 切换到系统键盘
                showInput();
                hideKeyboard();
            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
            lastKeyBoard = primaryCode;
            lastClickTime = currentTime;
        }
    };

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        hideInput();
        if (visibility == View.GONE ) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
    }

    public void showInput() {
        ed.requestFocus();
        imm.showSoftInput(ed, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideInput() {
        imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);
    }

    public boolean isSysInputShowing(){
        return imm.isActive(ed);
    }

}
