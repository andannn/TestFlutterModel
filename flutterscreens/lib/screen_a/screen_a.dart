import 'package:flutter/material.dart';
import 'package:my_flutter/channel/method_channel.dart';
import 'package:my_flutter/screen_a/screen_a_state.dart';
import 'package:provider/provider.dart';

class ScreenA extends StatelessWidget {
  const ScreenA({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (_) => ScreenAState(
            handler: Provider.of<MethodHandler>(context, listen: false),
          ),
        ),
      ],
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: const MyHomePage(title: 'Screen A'),
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    final state = Provider.of<ScreenAState>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed t button this many times:',
            ),
            Text(
              '${state.state}',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            TextButton(
              onPressed: () {
                showDialog(
                    context: context,
                    builder: (BuildContext context) {
                      return AlertDialog(
                        title: const Text('Some info'),
                        actions: [
                          TextButton(
                            onPressed: () {
                              Navigator.of(context).pop();
                            },
                            child: const Text('OK'),
                          )
                        ],
                      );
                    });
              },
              child: const Text('Launch Dialog'),
            ),
            TextButton(
              onPressed: () {
                Provider.of<ScreenAState>(context, listen: false).requestData();
              },
              child: const Text('get data from android'),
            ),
            TextButton(
              onPressed: () {
                Provider.of<MethodHandler>(context, listen: false)
                    .navigateToScreenB();
              },
              child: const Text('navigate to screen b'),
            ),
          ],
        ),
      ),
    );
  }
}
