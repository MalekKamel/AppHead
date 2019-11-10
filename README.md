AppHead
===========
Head View like Facebook Messenger for Android.

<img src="https://github.com/ShabanKamell/AppHead/blob/master/blob/raw/demo_new.gif" height="400">

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
 builder = Head.Builder(R.drawable.icon).headView(HeadView.Args().onClick {..})
 AppHead(builder).show(activity)
```
All available options

``` kotlin
// build HeadView
val headViewArgs = HeadView.Args()
        .layoutRes(R.layout.app_head_red, R.id.headImageView)
        .onClick {..}
        .onLongClick {..}
        .alpha(0.8f)
        .allowBounce(false)
        .onFinishInflate {..}
        .setupImage {..}
        .onDismiss {..}
        .dismissOnClick(false)
        .preserveScreenLocation(false)

// build DismissView
val dismissViewArgs = DismissView.Args()
        .alpha(0.5f)
        .scaleRatio(1.0)
        .drawableRes(R.drawable.ic_dismiss)
        .onFinishInflate {..}
        .setupImage {..}

// build BadgeView
val badgeViewArgs = BadgeView.Args()
        .layoutRes(R.layout.badge_view, R.id.tvCount)
        .position(BadgeView.Position.TOP_END)
        .count("3333")

val builder = Head.Builder(R.drawable.ic_messenger_red)
        .headView(headViewArgs)
        .dismissView(dismissViewArgs)
        .badgeView(badgeViewArgs)

AppHead(builder).show(activity)
```
### Components
AppHead has 3 main components
- [ ] **HeadView**: the draggable & dismissable view.
- [ ] **DimissView**: the view at the bottom that acts as bounds within which the **HeadView** can be dimissed.
- [ ] **BadgeView**(optinal): displays the number of notifications.

### Customize Components Layouts
In addition to configuring all options of the components, you also can define full custom components layouts.
To customize `HeadView`, `DimissView` or `BadgeView` 
you must define the rootview as `HeadView`, `DimissView` or `BadgeView`

``` xml
<com.sha.apphead.HeadView 
    ..>
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
   ..>
    <ImageView
        android:id="@+id/dimissImageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/ic_dimiss"
        android:layout_margin="4dp"
        />
</com.sha.apphead.DismissView>
```

``` xml
<com.sha.apphead.BadgeView
    ..>
    <TextView
        android:id="@+id/tvCount"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"
        />
</com.sha.apphead.BadgeView>
```

### Options & Default Values
#### Headview.Args

|          **Option**                       | **Type**                | **Default** |
| ------------------------------ | --------------------------- | ----------------------------- |
| **layoutRes**                  |    Layout Res               | R.layout.app_head             |
| **imageViewId**                |    ID Res                   | R.layout.ivHead               |
| **drawableRes**                |    Drawable Res             | 0(REQUIRED                    |
| **alpha**                      |    Float                    | 1f                            |
| **allowBounce**                |    Boolean                  | true                          |
| **preserveScreenLocation**     |    Boolean                  | true                          |
| **dismissOnClick**             |    Boolean                  | true                          |
| **setupImage**                 |    ((ImageView) -> Unit)?   | null                          |
| **onFinishInflate**            |    ((HeadView) -> Unit)?    | null                          |
| **onClick**                    |    ((HeadView) -> Unit)?    | null                          |
| **onLongClick**                |    ((HeadView) -> Unit)?    | null                          |
| **onDismiss**                  |    ((HeadView) -> Unit)?    | null                          |

#### DismissView.Args
|          **Option**                       | **Type**                | **Default** |
| ------------------------------ | --------------------------- | ----------------------------- |
| **layoutRes**                  |    Layout Res               | R.layout.dismiss_view         |
| **imageViewId**                |    ID Res                   | R.layout.ivDismiss            |
| **drawableRes**                |    Drawable Res             | R.drawable.ic_dismiss_apphead |
| **scaleRatio**                 |    Double                   | 1.5                           |
| **alpha**                      |    Float                    | 1f                            |
| **setupImage**                 |    ((ImageView) -> Unit)?   | null                          |
| **onFinishInflate**            |    ((DismissView) -> Unit)? | null                          |

#### BadgeView.Args
|          **Option**                       | **Type**                | **Default** |
| ------------------------------ | --------------------------- | ----------------------------- |
| **layoutRes**                  |    Layout Res               | R.layout.badge_view           |
| **count**                      |    String                   | ""                            |
| **countTextViewId**            |    ID Res                   | R.id.tvCount                  |
| **position**                   |    BadgeView.Position       | BadgeView.Position.TOP_END    |

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
