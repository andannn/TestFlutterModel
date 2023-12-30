import 'package:flutter/services.dart';

abstract class MethodHandler {
  Future<int> foo();

  Future<String> bar();


  Future navigateToScreenB();
}

class FlutterMethodChannel implements MethodHandler {

  final _methodChannel = const MethodChannel("com.example.testfluttermodel/channel");
  @override
  Future<String> bar() async {
    throw UnimplementedError();
  }

  @override
  Future<int> foo() async {
    final result = await _methodChannel.invokeMethod<int>('foo');
    return result ?? 0;
  }

  @override
  Future navigateToScreenB() async {
    await _methodChannel.invokeMethod<int>('navigateToScreenB');
  }
}
