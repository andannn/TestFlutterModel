import 'package:flutter/services.dart';

abstract class MethodHandler {
  Future<int> foo();

  Future<String> bar();

  Future navigateToScreenB();

  Future navigateToLoginB();

  Future navigateToMainFlow();
}

class FlutterMethodChannel implements MethodHandler {
  final _methodChannel =
      const MethodChannel("com.example.testfluttermodel/channel");

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
  Future navigateToScreenB() {
    return _methodChannel.invokeMethod('navigateToScreenB');
  }

  @override
  Future navigateToLoginB() {
    return _methodChannel.invokeMethod('navigateToLoginB');
  }

  @override
  Future navigateToMainFlow() {
    return _methodChannel.invokeMethod('navigateToMainFlow');
  }
}
