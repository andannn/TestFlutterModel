package com.example.testfluttermodel.core_flutter;

import android.content.Context;
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
        Log.d(TAG, "onViewCreated " + hashCode());

        ViewKt.doOnDetach(requireView(), (v) -> {
            try {
                Class<?> targetClass = FlutterFragment.class;
                Field field = targetClass.getDeclaredField("onWindowFocusChangeListener");
                field.setAccessible(true);
                Object fieldValue = field.get(CustomFlutterFragment.this);

                if (fieldValue instanceof ViewTreeObserver.OnWindowFocusChangeListener) {
                    removeOnWindowAttachListener((ViewTreeObserver.OnWindowFocusChangeListener) fieldValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public String getCachedEngineGroupId() {
        return "flutter_engine_group";
    }

    private void removeOnWindowAttachListener(ViewTreeObserver.OnWindowFocusChangeListener listener) {
        requireView().getViewTreeObserver().removeOnWindowFocusChangeListener(listener);
    }
}