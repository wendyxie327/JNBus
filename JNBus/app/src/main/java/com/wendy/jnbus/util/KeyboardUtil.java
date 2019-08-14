package com.wendy.jnbus.util;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.wendy.jnbus.R;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class KeyboardUtil {

    private static final String TAG = "KeyboardUtil";

    private Context ctx;
    private Activity act;
    private KeyboardView keyboardView;
    private Keyboard keyboard;// 数字键盘

    private EditText ed;

    /** 设置对于点击按钮的监听*/
    private KeyboardViewListener keyboardViewListener ;

    public interface KeyboardViewListener{
        void onClickKey(int keyCode);
    }

    public KeyboardUtil(Activity act, Context ctx, EditText edit) {
        this.act = act;
        this.ctx = ctx;
        this.ed = edit;
        keyboard = new Keyboard(ctx, R.xml.keyboard_search);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
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
            int start = ed.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
                hideKeyboard();
                keyboardViewListener.onClickKey(primaryCode);
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            }   else if ( primaryCode == 60000 ){
                editable.insert(start, "BRT");
            }   else if ( primaryCode == 60001 ){
                editable.insert(start, "K");
            }   else if ( primaryCode == 60002 ){
                editable.insert(start, "T");
            }   else if ( primaryCode == 60003 ){
                editable.insert(start, "B");
            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    };

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
    }
}
