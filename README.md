AppHead
===========
Head View like Facebook Messenger for Android

<img src="https://github.com/ShabanKamell/AppHead/blob/master/blob/raw/demo.gif" height="400">

#### Gradle:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

dependencies {
     implementation 'com.github.ShabanKamell:AppHead:x.y.z'
}
```
(Please replace x, y and z with the latest version numbers:  [![](https://jitpack.io/v/ShabanKamell/AppHead.svg)](https://jitpack.io/#ShabanKamell/AppHead)
)

### Usage
The most simple usage
``` kotlin
  val builder = Head.Builder(R.drawable.ic_messenger).onClick {..}
  AppHead(builder).show(activity)
```
All available options

``` kotlin
val builder = Head.Builder(R.drawable.ic_messenger_red)
        .headLayoutRes(R.layout.head_view, R.id.headImageView)
        .dismissLayoutRes(R.layout.dismiss_view, R.id.dimissImageView)
        .onClick {..}
        .onLongClick {..}
        .loadHeadImage { Picasso.get().load("...").into(it) }
        .dismissViewScaleRatio(1.0)
        .dismissDrawableRes(R.drawable.ic_dismiss)
        .dismissOnClick(false)
        .headViewAlpha(0.8f)
        .dismissViewAlpha(0.5f)
        .allowHeadBounce(false)
        .onFinishHeadViewInflate {..}
        .onFinishDismissViewInflate {..}
        .onDismiss {..}
AppHead(builder).show(activity)
```
### Components
AppHead has 2 main components
- [ ] **HeadView**: the draggable & dismissable view.
- [ ] **DimissView**: the view at the bottom that acts as bounds within which the **HeadView** can be dimissed.

### Customize Components Layouts
In addition to setting all options to the components, you also can define full custom components layouts.
To customize `HeadView` and/or `DimissView` you must define the rootview as `HeadView` or `DimissView`
``` xml
<com.sha.apphead.HeadView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <ImageView
        android:id="@+id/headImageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/ic_messenger"
        android:layout_margin="4dp"
        />

</com.sha.apphead.HeadView>

```

``` xml
<com.sha.apphead.DismissView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <ImageView
        android:id="@+id/dimissImageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/ic_dimiss"
        android:layout_margin="4dp"
        />

</com.sha.apphead.DismissView>
```

### Options & Default Values

|          **Option**                       | **Type**                | **Default** |
| ------------------------------ | --------------------------- | ----------------------------- |
| **headLayoutRes**              |    Layout Res               | R.layout.app_head             |
| **dismissLayoutRes**           |    Layout Res               | R.layout.dismiss_view         |
| **headDrawableRes**            |    Drawable Res             | R.layout.app_head             |
| **dismissDrawableRes**         |    Drawable Res             | R.drawable.ic_dismiss_apphead |
| **dismissViewScaleRatio**      |    Double                   | 1.5                           |                         
| **headViewAlpha**              |    Float                    | 1f                            |
| **dismissViewAlpha**           |    Float                    | 0.8f                          |
| **allowHeadBounce**            |    Boolean                  | true                          |
| **dismissOnClick**             |    Boolean                  | true                          |
| **onFinishHeadViewInflate**    |    ((HeadView) -> Unit)?    | null                          |
| **onFinishDismissViewInflate** |    ((DismissView) -> Unit)? | null                          |
| **loadHeadImage**              |    ((ImageView) -> Unit)?   | null                          |
| **onClick**                    |    ((HeadView) -> Unit)?    | null                          |
| **onLongClick**                |    ((HeadView) -> Unit)?    | null                          |
| **onDismiss**                  |    ((HeadView) -> Unit)?    | null                          |

#### Look at 'sample' module for the full code. For more advanced example.

## Credit
[henrychuangtw/Android-ChatHead](https://github.com/henrychuangtw/Android-ChatHead)
### License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
