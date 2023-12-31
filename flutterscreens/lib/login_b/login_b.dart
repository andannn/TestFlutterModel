import 'package:flutter/material.dart';
import 'package:my_flutter/channel/method_channel.dart';
import 'package:provider/provider.dart';

class LoginB extends StatelessWidget {
  const LoginB({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const LoginBPage(title: 'LoginB'),
    );
  }
}

class LoginBPage extends StatefulWidget {
  const LoginBPage({super.key, required this.title});

  final String title;

  @override
  State<LoginBPage> createState() => _LoginBPageState();
}

class _LoginBPageState extends State<LoginBPage> {
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
                    .navigateToMainFlow();
              },
              child: const Text('To Main Flow'),
            )
          ],
        ),
      ),
    );
  }
}
