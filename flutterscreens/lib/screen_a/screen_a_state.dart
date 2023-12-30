import 'package:flutter/cupertino.dart';
import 'package:my_flutter/channel/method_channel.dart';

class ScreenAState with ChangeNotifier {
  ScreenAState({required this.handler});

  final MethodHandler handler;

  int state = 0;

  void requestData() async {
    final result = await handler.foo();

    state = result;

    notifyListeners();
  }
}