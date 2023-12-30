import 'package:flutter/material.dart';
import 'package:my_flutter/channel/method_channel.dart';
import 'package:my_flutter/screen_a/screen_a.dart';
import 'package:my_flutter/screen_b/screen_b.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    Provider<MethodHandler>(
      create: (_) => FlutterMethodChannel(),
      child: const ScreenA(),
    ),
  );
}

void screenA() {
  runApp(
    Provider<MethodHandler>(
      create: (_) => FlutterMethodChannel(),
      child: const ScreenA(),
    ),
  );
}

void screenB() {
  runApp(
    const ScreenB(),
  );
}
