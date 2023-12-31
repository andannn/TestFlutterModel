import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../channel/method_channel.dart';

class LoginA extends StatelessWidget {
  const LoginA({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const LoginAPage(title: 'LoginA'),
    );
  }
}

class LoginAPage extends StatefulWidget {
  const LoginAPage({super.key, required this.title});

  final String title;

  @override
  State<LoginAPage> createState() => _LoginAPageState();
}

class _LoginAPageState extends State<LoginAPage> {
  int _counter = 0;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextButton(
              onPressed: () {
                Provider.of<MethodHandler>(context, listen: false)
                    .navigateToLoginB();
              },
              child: const Text('Login'),
            )
          ],
        ),
      ),
    );
  }
}
