package com.example.testfluttermodel;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.core.view.ViewKt;

import java.lang.reflect.Field;

import io.flutter.embedding.android.FlutterFragment;

public class CustomFlutterFragment extends FlutterFragment {

    private static final String TAG = "CustomFlutterFragment";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated " + requireView().getViewTreeObserver().hashCode());

        // 在 doOnDetach 中处理
        ViewKt.doOnDetach(requireView(), (v) -> {
            Log.d(TAG, "doOnDetach: requireView().viewTreeObserver " + requireView().getViewTreeObserver().hashCode());

            try {
                Class<?> targetClass = FlutterFragment.class;
                Field field = targetClass.getDeclaredField("onWindowFocusChangeListener");
                field.setAccessible(true);
                Object fieldValue = field.get(CustomFlutterFragment.this);
                Log.d(TAG, "doOnDetach: fieldValue " + fieldValue);

                // 移除 ViewTreeObserver.OnWindowAttachListener
                if (fieldValue instanceof ViewTreeObserver.OnWindowFocusChangeListener) {
                    removeOnWindowAttachListener((ViewTreeObserver.OnWindowFocusChangeListener) fieldValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private void removeOnWindowAttachListener(ViewTreeObserver.OnWindowFocusChangeListener listener) {
        requireView().getViewTreeObserver().removeOnWindowFocusChangeListener(listener);
    }
}