package com.mopub.facebook.admob;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import com.mopub.nativeads.FacebookAdRenderer;
import com.mopub.nativeads.GooglePlayServicesAdRenderer;
import com.mopub.nativeads.GooglePlayServicesViewBinder;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;
import com.sofit.adshelper.helper.MyMoPub;

import java.util.EnumSet;

public class Ad_Helper extends Activity{

     public static MoPubView moPubView1;
     static String TAG= "MOP_UB";
     static MoPubNative moPubNative;
     static NativeAd nativeAd1;
     static boolean connected = false;

     static MoPubNative.MoPubNativeNetworkListener moPubNativeNetworkListener;
     static NativeAd.MoPubNativeEventListener moPubNativeEventListener;

     public static MoPubInterstitial interstitial;

     public static void loadIntersitialAd(Activity activity){
       new MyMoPub().init(activity, activity.getString(R.string.mop_ub_interstitial_test_id));
       interstitialAd(activity);
   }

    private static void interstitialAd(Activity activity){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                interstitial = new MoPubInterstitial(activity,activity.getString(R.string.mop_ub_interstitial_test_id));
                //if you want to add listener//
                interstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                    @Override
                    public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
                        Log.e(TAG, "intersitial ad: loaded success");
                        interstitial = moPubInterstitial;
                    }

                    @Override
                    public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
                        Log.e(TAG, "intersitial ad: loading failed");
                        Log.e(TAG, "intersitial ad: loading failed: "+ moPubErrorCode.name());
                        interstitial = null;
                    }

                    @Override
                    public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
                        Log.e(TAG, "intersitial ad: shown");
                    }

                    @Override
                    public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
                        Log.e(TAG, "intersitial ad: clicked");
                    }

                    @Override
                    public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
                        Log.e(TAG, "intersitial ad: closed");
                    }
                });
                interstitial.load();
            }
        }, 1000);

   }

    public static void showIntersitial(Activity activity){
       if (interstitial != null && interstitial.isReady()){
           interstitial.show();
           interstitialAd(activity);
           Log.e(TAG, "interstitial already loaded: ad shown successfully");
       }
       else if (isOnline(activity)){
           interstitialAd(activity);
           Log.e(TAG, "connected to internet: loading ad");
       }
       else {
           Log.e(TAG, "intersitial: no internet connection or intersitial is null/not ready");
       }
   }

    public static void LoadMediationBannerAd(Activity activity, LinearLayout view){

        new MyMoPub().init(activity, activity.getString(R.string.mop_ub_banner_test_id));

       loadBannerAd(activity, view);
    }

    public static void loadBannerAd(Activity activity, LinearLayout view){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                moPubView1 = view.findViewById(R.id.adview);
                moPubView1.setAdUnitId(activity.getString(R.string.mop_ub_banner_test_id));
                moPubView1.setAutorefreshEnabled(false);
                moPubView1.setBannerAdListener(new MoPubView.BannerAdListener() {
                    @Override
                    public void onBannerLoaded(@NonNull MoPubView moPubView) {
                        Log.e(TAG, "banner ad: loaded success");
                        moPubView1  = moPubView;
                    }

                    @Override
                    public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
                        Log.e(TAG, "banner ad: loading failed");
                        moPubView1 = null;
                    }

                    @Override
                    public void onBannerClicked(MoPubView moPubView) {
                        Log.e(TAG, "banner ad: clicked");
                        moPubView1  = moPubView;
                    }

                    @Override
                    public void onBannerExpanded(MoPubView moPubView) {
                        Log.e(TAG, "banner ad: expanded");
                        moPubView1  = moPubView;
                    }

                    @Override
                    public void onBannerCollapsed(MoPubView moPubView) {
                        Log.e(TAG, "banner ad: collapsed");
                        moPubView1  = moPubView;
                    }
                });
                moPubView1.loadAd();
            }
        }, 1000);

    }

    public static void showBannerAd(Activity activity, LinearLayout layAd ){
        if (Ad_Helper.moPubView1 != null) {
            ViewGroup tempVg = (ViewGroup) moPubView1.getParent();
            tempVg.removeView(moPubView1);
            layAd.addView(moPubView1);
            Log.e(TAG, "banner already loaded" );
        }
        else if (isOnline(activity)){
            loadBannerAd(activity, layAd); // here load banner ad again
            Log.e(TAG, "Banner Ad: connected to internet : loading...");
        }
        else {
            Log.e(TAG, "Banner: no internet connection or Banner is null/not ready");
        }
    }

    public static void loadNativeAd(Activity activity){
        new MyMoPub().init(activity, activity.getString(R.string.mop_ub_native_test_id));
        nativeAd(activity);
    }

    private static void nativeAd(Activity activity){
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               moPubNativeNetworkListener = new MoPubNative.MoPubNativeNetworkListener() {
                   @Override
                   public void onNativeLoad(NativeAd nativeAd) {
                       nativeAd1 = nativeAd;
                       Log.e(TAG, "Native ad: loaded success");
                       //showNativeAd(activity, view);
                   }

                   @Override
                   public void onNativeFail(NativeErrorCode nativeErrorCode) {
                       Log.e(TAG, "Native ad: loading failed: " + nativeErrorCode.getIntCode());
                       Log.e(TAG, "Native ad: loading failed: " + nativeErrorCode);
                       nativeAd1 = null;
                   }
                   // We will be populating this below
               };

               moPubNativeEventListener = new NativeAd.MoPubNativeEventListener() {
                   @Override
                   public void onImpression(View view) {
                       // Impress is recorded - do what is needed AFTER the ad is visibly shown here
                   }

                   @Override
                   public void onClick(View view) {
                   }
               };

               moPubNative = new MoPubNative(activity, activity.getString(R.string.mop_ub_native_test_id),
                       moPubNativeNetworkListener);

               ViewBinder viewBinder = new ViewBinder.Builder(R.layout.mopubnative)
                       .titleId(R.id.mopub_native_ad_title)
                       .textId(R.id.mopub_native_ad_text)
                       .mainImageId(R.id.mopub_native_ad_main_imageview)
                       .iconImageId(R.id.mopub_native_ad_icon)
                       .callToActionId(R.id.mopub_native_ad_cta)
                       .privacyInformationIconImageId(R.id.mopub_native_ad_privacy)
                       .build();

       /* FacebookTemplateRenderer facebookAdRenderer = new FacebookTemplateRenderer(new NativeAdViewAttributes());
        moPubNative.registerAdRenderer(facebookAdRenderer);
*/
               FacebookAdRenderer facebookAdRenderer = new FacebookAdRenderer(
                       new FacebookAdRenderer.FacebookViewBinder.Builder(R.layout.facebooknative)
                               .titleId(R.id.fb_native_ad_title)
                               .textId(R.id.fb_native_ad_body)
                               .mediaViewId(R.id.fb_native_ad_media)
                               .adIconViewId(R.id.fb_nativeIcon)
                               .adChoicesRelativeLayoutId(R.id.fb_ad_choices_container)
                               .advertiserNameId(R.id.fb_native_ad_title) // Bind either the titleId or advertiserNameId depending on the FB SDK version
                               // End of binding to new layouts
                               .callToActionId(R.id.fb_native_ad_call_to_action)
                               .build());

               GooglePlayServicesAdRenderer googlePlayServicesAdRenderer = new GooglePlayServicesAdRenderer(
                       new GooglePlayServicesViewBinder.Builder(R.layout.mopubnative)
                               // bind to your `com.mopub.nativeads.MediaLayout` element
                               .iconImageId(R.id.mopub_native_ad_icon)
                               .titleId(R.id.mopub_native_ad_title)
                               .textId(R.id.mopub_native_ad_text)
                               .callToActionId(R.id.mopub_native_ad_cta)
                               .privacyInformationIconImageId(R.id.mopub_native_ad_privacy)
                               .build());

               moPubNative.registerAdRenderer(facebookAdRenderer);
               moPubNative.registerAdRenderer(googlePlayServicesAdRenderer);

               MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(viewBinder);
               moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);

               EnumSet<RequestParameters.NativeAdAsset> desiredAssets = EnumSet.of(
                       RequestParameters.NativeAdAsset.TITLE,
                       RequestParameters.NativeAdAsset.TEXT,
                       RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT,
                       RequestParameters.NativeAdAsset.MAIN_IMAGE,
                       RequestParameters.NativeAdAsset.ICON_IMAGE,
                       RequestParameters.NativeAdAsset.STAR_RATING
               );

               RequestParameters mRequestParameters = new RequestParameters.Builder()
                       .desiredAssets(desiredAssets)
                       .build();
               moPubNative.makeRequest(mRequestParameters);
           }
       }, 1000);
    }

    public static void showNativeAd(Activity activity, View view){
               if (nativeAd1 != null) {
                   ScrollView adParent = view.findViewById(R.id.nativeLayout);
                   View adView = nativeAd1.createAdView(activity, adParent);
                   nativeAd1.prepare(adView);
                   nativeAd1.renderAdView(adView);
                   adParent.removeAllViews();
                   adParent.addView(adView);
                   Log.e(TAG, "native already loaded");
               }
               else if (isOnline(activity)){
                   nativeAd(activity);
               }

               else {
                   Log.e(TAG, "Native: no internet connection or Native is null/not ready");
               }
    }

    public static boolean isOnline(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (interstitial != null){
           interstitial.destroy();
        }
        if (moPubView1 != null){
            moPubView1.destroy();
        }

        nativeAd1.destroy();
        moPubNative.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (moPubView1 != null) {
            moPubView1.pauseAutoRefresh();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (moPubView1 != null) {
            moPubView1.resumeAutoRefresh();
        }
    }
}
