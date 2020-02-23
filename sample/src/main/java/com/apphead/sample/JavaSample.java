package com.apphead.sample;

import androidx.fragment.app.FragmentActivity;

import com.sha.apphead.AppHead;
import com.sha.apphead.BadgeView;
import com.sha.apphead.DismissView;
import com.sha.apphead.Head;
import com.sha.apphead.HeadView;

import kotlin.Unit;

public class JavaSample {

    /**
     * a sample for building the head view using Java
     * Please note that the project supports Java 8 lambda in sample/build.gradle
     *compileOptions {
     *         targetCompatibility Config.javaVersion
     *         sourceCompatibility Config.javaVersion
     *}
     * @param activity
     */
    void appHead(FragmentActivity activity) {

        // build HeadView
        HeadView.Args headViewArgs = new HeadView.Args()
                .layoutRes(R.layout.app_head_red, R.id.headImageView)
                .onClick(headView -> {
                    // your logic
                    return Unit.INSTANCE;
                })
                .onLongClick(headView -> {
                    // your logic
                    return Unit.INSTANCE;
                })
                .alpha(0.9f)
                .allowBounce(false)
                .onFinishInflate(headView -> {
                    // your logic
                    return Unit.INSTANCE;
                })
                .setupImage(imageView -> {
                    // your logic
                    return Unit.INSTANCE;
                })
                .onDismiss(headView -> {
                    // your logic
                    return Unit.INSTANCE;
                })
                .dismissOnClick(false)
                .preserveScreenLocation(false);

        // build DismissView
        DismissView.Args dismissViewArgs = new DismissView.Args()
                .alpha(0.5f)
                .scaleRatio(1.0)
                .drawableRes(R.drawable.ic_dismiss)
                .onFinishInflate(dismissView -> {
                    // your logic
                    return Unit.INSTANCE;
                })
                .setupImage(imageView -> {
                    // your logic
                    return Unit.INSTANCE;
                });

        BadgeView.Args badgeViewArgs = new BadgeView.Args()
                .layoutRes(R.layout.badge_view, R.id.tvCount)
                .position(BadgeView.Position.BOTTOM_END)
                .count("100");

        Head.Builder builder = new Head.Builder(R.drawable.ic_messenger_red)
                .headView(headViewArgs)
                .dismissView(dismissViewArgs)
                .badgeView(badgeViewArgs);

        new AppHead(builder).show(activity);
    }
}
