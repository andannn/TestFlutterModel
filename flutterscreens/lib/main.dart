import 'package:flutter/material.dart';
import 'package:my_flutter/channel/method_channel.dart';
import 'package:my_flutter/login_a/login_a.dart';
import 'package:my_flutter/login_b/login_b.dart';
import 'package:my_flutter/screen_a/screen_a.dart';
import 'package:my_flutter/screen_b/screen_b.dart';
import 'package:provider/provider.dart';

void main(List args) {
  Widget? content() => switch (args[0]) {
        'loginA' => const LoginA(),
        'loginB' => const LoginB(),
        'screenA' => const ScreenA(),
        'screenB' => const ScreenB(),
        _ => throw 'invalid argument $args'
      };

  runApp(
    Provider<MethodHandler>(
      create: (_) => FlutterMethodChannel(),
      child: content(),
    ),
  );
}
