# Activity 

## 生命周期

通过跳转页面，返回页面，按下 Home 键等方式监测生命周期方法的执行。

## 启动模式

例子演示了4个启动模式。

```Xml
<activity android:name=".MainActivity" android:launchMode="standard">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

<activity android:name=".SecondActivity" android:launchMode="singleTask" />
<activity android:name=".ThirdActivity" android:launchMode="singleTop" />
<activity android:name=".FourthActivity" android:launchMode="singleInstance" />
```

通过四个Activity之间的相互跳转，反映出 Activity 栈的实时情况。

## 跳转动画

演示了通过 ActivityOptions 类和 overridePendingTransition 方法实现 Activity 切换的过渡动画。





