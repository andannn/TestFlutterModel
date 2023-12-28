import 'package:flutter/services.dart';

abstract class CommonRepository {
  Future<int> foo();

  Future<String> bar();
}

class FlutterMethodChannel implements CommonRepository {

  final _methodChannel = const MethodChannel("com.example.testfluttermodel/channel");
  @override
  Future<String> bar() async {
    throw UnimplementedError();
  }

  @override
  Future<int> foo() async {
    print("JQN foo call");
    final result = await _methodChannel.invokeMethod<int>('foo');
    print("JQN foo result");
    return result ?? 0;
  }
}
