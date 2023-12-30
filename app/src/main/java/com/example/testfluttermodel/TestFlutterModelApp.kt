package com.example.testfluttermodel

import android.app.Application
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.FlutterEngineGroupCache

class TestFlutterModelApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FlutterEngineGroupCache.getInstance().put(
            /* engineGroupId = */ "flutter_engine_group",
            /* engineGroup = */ FlutterEngineGroup(this)
        )
    }
}