# Introduction

**FreeReflection** is a library that lets you use reflection without any restriction above Android P.

## Usage

Add dependency to your project(jcenter):

```gradle
implementation 'me.weishu:free_reflection:1.0.0'
```

Then, you can use reflection freely with just one line code:

```java
Reflection.unseal(context);
```
