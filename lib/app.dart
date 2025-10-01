import 'package:flutter/material.dart';
import 'core/theme.dart';
import 'screens/home/home_screen.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Money & Betting App',
      theme: appTheme,
      // You can expand routing as your app grows
      home: const HomeScreen(),
      debugShowCheckedModeBanner: false,
      // Add localization support if needed
      // localizationsDelegates: [],
      // supportedLocales: [],
    );
  }
}
