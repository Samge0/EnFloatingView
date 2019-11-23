package com.imuxuan.floatingview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

/**
 * @ClassName EnFloatingView
 * @Description 悬浮窗
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:04
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:04
 */
public class EnFloatingView extends FloatingMagnetView {

    private long mLastTouchDownTime;
    private static final int TOUCH_TIME_THRESHOLD = 150;
    private final ImageView mIcon;

    public EnFloatingView(@NonNull Context context) {
        super(context, null);
        inflate(context, R.layout.en_floating_view, this);
        mIcon = findViewById(R.id.icon);
    }

    public void setIconImage(@DrawableRes int resId){
        mIcon.setImageResource(resId);
    }

    public void setIconImage(String value){
        RequestOptions requestOptions = new RequestOptions()
                .transforms(new MultiTransformation(new CenterCrop(), new CircleCrop()))
                .override(80,80)
                .dontAnimate();
        value = TextUtils.isEmpty(value) ? IMGS[new Random().nextInt(IMGS.length-1)] : value;
        Glide.with(this)
                .load(value)
                .apply(requestOptions)
                .into(mIcon);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastTouchDownTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_UP:
                    /*if (isOnClickEvent()) {
                        dealClickEvent();
                    }*/
                    break;
            }
        }
        return true;
    }

    protected boolean isOnClickEvent() {
        return System.currentTimeMillis() - mLastTouchDownTime < TOUCH_TIME_THRESHOLD;
    }


    public static String[] IMGS = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551159497308&di=71014bbd13ea75d6be2feaff17caf45a&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180919%2F20%2F1537359819-YiVOhgMorQ.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1869501936,3504089593&fm=214&gp=0.jpg",
            "http://life.southmoney.com/tuwen/UploadFiles_6871/201809/20180913134852829.jpg",
            "http://life.southmoney.com/tuwen/UploadFiles_6871/201809/20180913134852581.jpg",
            "http://b-ssl.duitang.com/uploads/item/201605/11/20160511204624_xsuyH.thumb.700_0.jpeg",
            "http://a-ssl.duitang.com/uploads/item/201607/02/20160702122645_4MQF8.thumb.700_0.png",
            "http://life.southmoney.com/tuwen/UploadFiles_6871/201809/20180917165700473.jpg",
            "http://img5.duitang.com/uploads/item/201410/13/20141013125421_vCSCU.jpeg",
            "http://b-ssl.duitang.com/uploads/item/201411/06/20141106005939_rncwa.jpeg",
            "http://b-ssl.duitang.com/uploads/item/201410/10/20141010165753_VFeGQ.jpeg",
            "http://www.qqtxwm.com/uploads/allimg/180912/1-1P912231542.jpg",
            "http://www.qqtxwm.com/uploads/allimg/180912/1-1P912231542.jpg"
    };


}
