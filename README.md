#这是一个高仿MIUI V5短信的标题效果

###具体的效果呢，看一下截图：<br>
![演示](http://7xqsjj.com1.z0.glb.clouddn.com/GIF.gif "gif")

###具体用法:
1.先放入布局文件中，这里有一个自定义属性`item_count`，设置可见的标签数量。

```Xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:range="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.liran.lenovo.tabpagerindicator.MainActivity">

    <com.liran.lenovo.tabpagerindicator.ViewPagerIndicator
        android:id="@+id/id_indicator"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:background="#2C3034"
        android:orientation="horizontal"
        range:item_count="3">
    </com.liran.lenovo.tabpagerindicator.ViewPagerIndicator>

    <android.support.v4.view.ViewPager
        android:id="@+id/id_vp"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"></android.support.v4.view.ViewPager>


</LinearLayout>

```


2.在java代码中填充数据并且设置viewpager就OK了

```Java
        viewPagerIndicator.setTabItemTitles(mDatas);
        viewPager.setAdapter(adapter);
        viewPagerIndicator.setViewPager(viewPager, 0);
```
    
目前没有发现Bug，如果大家发现可以告诉我，我改一下。