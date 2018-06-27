# FreeReflection

**FreeReflection** is a library that lets you use reflection without any restriction above Android P.

## Usage

1. Add dependency to your project(jcenter):

```gradle
implementation 'me.weishu:free_reflection:1.2.0'
```

2. Add one line to your `Application.attachBaseContext` :

```java
@Override
protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    Reflection.unseal(base);
}
```

Then you can use the reflection API normally, all the restrictions are gone. Enjoy yourself :)

## Under the hood

[free-reflection-above-android-p](http://weishu.me/2018/06/07/free-reflection-above-android-p/)

## Donations

If you like this project, buy me a cup of coffee! :)

BitCoin: 39Wst8oL74pRP2vKPkPihH6RFQF4hWoBqU

## License

MIT License



