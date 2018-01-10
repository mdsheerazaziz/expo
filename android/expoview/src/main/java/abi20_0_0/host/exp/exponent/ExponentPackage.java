// Copyright 2015-present 650 Industries. All rights reserved.

package abi20_0_0.host.exp.exponent;

import abi20_0_0.com.facebook.react.ReactPackage;
import abi20_0_0.com.facebook.react.bridge.JavaScriptModule;
import abi20_0_0.com.facebook.react.bridge.NativeModule;
import abi20_0_0.com.facebook.react.bridge.ReactApplicationContext;
import abi20_0_0.com.facebook.react.uimanager.ViewManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import host.exp.exponent.ExponentManifest;
import host.exp.exponent.analytics.EXL;
import host.exp.exponent.kernel.ExperienceId;
import host.exp.exponent.kernel.ExponentKernelModuleProvider;
import host.exp.exponent.utils.ScopedContext;
import abi20_0_0.host.exp.exponent.modules.api.sensors.AccelerometerModule;
import abi20_0_0.host.exp.exponent.modules.api.AmplitudeModule;
import abi20_0_0.host.exp.exponent.modules.api.ConstantsModule;
import abi20_0_0.host.exp.exponent.modules.api.ContactsModule;
import abi20_0_0.host.exp.exponent.modules.api.CryptoModule;
import abi20_0_0.host.exp.exponent.modules.api.DocumentPickerModule;
import abi20_0_0.host.exp.exponent.modules.api.ErrorRecoveryModule;
import abi20_0_0.host.exp.exponent.modules.api.FabricModule;
import abi20_0_0.host.exp.exponent.modules.api.FacebookModule;
import abi20_0_0.host.exp.exponent.modules.api.FileSystemModule;
import abi20_0_0.host.exp.exponent.modules.api.FingerprintModule;
import abi20_0_0.host.exp.exponent.modules.api.FontLoaderModule;
import abi20_0_0.host.exp.exponent.modules.api.GoogleModule;
import abi20_0_0.host.exp.exponent.modules.api.sensors.GyroscopeModule;
import abi20_0_0.host.exp.exponent.modules.api.ImageCropperModule;
import abi20_0_0.host.exp.exponent.modules.api.ImagePickerModule;
import abi20_0_0.host.exp.exponent.modules.api.KeepAwakeModule;
import abi20_0_0.host.exp.exponent.modules.api.KeyboardModule;
import abi20_0_0.host.exp.exponent.modules.api.LocationModule;
import abi20_0_0.host.exp.exponent.modules.api.NotificationsModule;
import abi20_0_0.host.exp.exponent.modules.api.PedometerModule;
import abi20_0_0.host.exp.exponent.modules.api.PermissionsModule;
import abi20_0_0.host.exp.exponent.modules.api.RNViewShotModule;
import abi20_0_0.host.exp.exponent.modules.api.SQLiteModule;
import abi20_0_0.host.exp.exponent.modules.api.ScreenOrientationModule;
import abi20_0_0.host.exp.exponent.modules.api.SegmentModule;
import abi20_0_0.host.exp.exponent.modules.api.ShakeModule;
import abi20_0_0.host.exp.exponent.modules.api.SpeechModule;
import abi20_0_0.host.exp.exponent.modules.api.URLHandlerModule;
import abi20_0_0.host.exp.exponent.modules.api.UtilModule;
import abi20_0_0.host.exp.exponent.modules.api.WebBrowserModule;
import abi20_0_0.host.exp.exponent.modules.api.av.AVModule;
import abi20_0_0.host.exp.exponent.modules.api.av.video.VideoViewManager;
import abi20_0_0.host.exp.exponent.modules.api.components.LinearGradientManager;
import abi20_0_0.host.exp.exponent.modules.api.components.barcodescanner.BarCodeScannerModule;
import abi20_0_0.host.exp.exponent.modules.api.components.barcodescanner.BarCodeScannerViewManager;
import abi20_0_0.host.exp.exponent.modules.api.components.camera.CameraModule;
import abi20_0_0.host.exp.exponent.modules.api.components.camera.CameraViewManager;
import abi20_0_0.host.exp.exponent.modules.api.components.lottie.LottiePackage;
import abi20_0_0.host.exp.exponent.modules.api.components.maps.MapsPackage;
import abi20_0_0.host.exp.exponent.modules.api.components.svg.SvgPackage;
import abi20_0_0.host.exp.exponent.modules.api.fbads.AdSettingsManager;
import abi20_0_0.host.exp.exponent.modules.api.fbads.BannerViewManager;
import abi20_0_0.host.exp.exponent.modules.api.fbads.InterstitialAdManager;
import abi20_0_0.host.exp.exponent.modules.api.fbads.NativeAdManager;
import abi20_0_0.host.exp.exponent.modules.api.fbads.NativeAdViewManager;
import abi20_0_0.host.exp.exponent.modules.api.gl.GLObjectManagerModule;
import abi20_0_0.host.exp.exponent.modules.api.gl.GLViewManager;
import abi20_0_0.host.exp.exponent.modules.api.IntentLauncherModule;
import abi20_0_0.host.exp.exponent.modules.api.SecureStoreModule;
import abi20_0_0.host.exp.exponent.modules.api.sensors.MagnetometerModule;
import abi20_0_0.host.exp.exponent.modules.api.sensors.MagnetometerUncalibratedModule;
import abi20_0_0.host.exp.exponent.modules.api.standalone.branch.RNBranchModule;
import abi20_0_0.host.exp.exponent.modules.internal.ExponentAsyncStorageModule;
import abi20_0_0.host.exp.exponent.modules.internal.ExponentIntentModule;
import abi20_0_0.host.exp.exponent.modules.internal.ExponentUnsignedAsyncStorageModule;
import abi20_0_0.host.exp.exponent.modules.test.ExponentTestNativeModule;

public class ExponentPackage implements ReactPackage {

  private static final String TAG = ExponentPackage.class.getSimpleName();

  private final boolean mIsKernel;
  private final Map<String, Object> mExperienceProperties;
  private final JSONObject mManifest;

  private ExponentPackage(boolean isKernel, Map<String, Object> experienceProperties, JSONObject manifest) {
    mIsKernel = isKernel;
    mExperienceProperties = experienceProperties;
    mManifest = manifest;
  }

  public ExponentPackage(Map<String, Object> experienceProperties, JSONObject manifest) {
    mIsKernel = false;
    mExperienceProperties = experienceProperties;
    mManifest = manifest;
  }

  public static ExponentPackage kernelExponentPackage(JSONObject manifest) {
    Map<String, Object> kernelExperienceProperties = new HashMap<>();
    return new ExponentPackage(true, kernelExperienceProperties, manifest);
  }


  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    boolean isVerified = false;
    if (mManifest != null) {
      isVerified = mManifest.optBoolean(ExponentManifest.MANIFEST_IS_VERIFIED_KEY);
    }

    List<NativeModule> nativeModules = new ArrayList<>(Arrays.<NativeModule>asList(
        new URLHandlerModule(reactContext),
        new ConstantsModule(reactContext, mExperienceProperties, mManifest),
        new ShakeModule(reactContext),
        new FontLoaderModule(reactContext),
        new KeyboardModule(reactContext),
        new UtilModule(reactContext, mExperienceProperties),
        new ExponentIntentModule(reactContext, mExperienceProperties)
    ));

    if (mIsKernel) {
      // Never need this in versioned code. Comment this out if this is in an abi package
      // nativeModules.add((NativeModule) ExponentKernelModuleProvider.newInstance(reactContext));
    }

    if (isVerified) {
      try {
        ExperienceId experienceId = ExperienceId.create(mManifest.getString(ExponentManifest.MANIFEST_ID_KEY));
        ScopedContext scopedContext = new ScopedContext(reactContext, experienceId.getUrlEncoded());

        nativeModules.add(new ExponentAsyncStorageModule(reactContext, mManifest));
        nativeModules.add(new AccelerometerModule(reactContext));
        nativeModules.add(new GyroscopeModule(reactContext));
        nativeModules.add(new MagnetometerModule(reactContext));
        nativeModules.add(new MagnetometerUncalibratedModule(reactContext));
        nativeModules.add(new NotificationsModule(reactContext, mManifest, mExperienceProperties));
        nativeModules.add(new ContactsModule(reactContext));
        nativeModules.add(new FileSystemModule(reactContext, scopedContext));
        nativeModules.add(new LocationModule(reactContext, scopedContext));
        nativeModules.add(new CryptoModule(reactContext));
        nativeModules.add(new ImagePickerModule(reactContext, scopedContext));
        nativeModules.add(new FacebookModule(reactContext));
        nativeModules.add(new FabricModule(reactContext, mExperienceProperties));
        nativeModules.add(new FingerprintModule(reactContext));
        nativeModules.add(new GoogleModule(reactContext, mExperienceProperties));
        nativeModules.add(new PermissionsModule(reactContext));
        nativeModules.add(new AmplitudeModule(reactContext, scopedContext));
        nativeModules.add(new SegmentModule(reactContext, scopedContext));
        nativeModules.add(new BarCodeScannerModule(reactContext));
        nativeModules.add(new CameraModule(reactContext, scopedContext));
        nativeModules.add(new RNViewShotModule(reactContext));
        nativeModules.add(new KeepAwakeModule(reactContext));
        nativeModules.add(new ExponentTestNativeModule(reactContext));
        nativeModules.add(new WebBrowserModule(reactContext));
        nativeModules.add(new AVModule(reactContext, scopedContext));
        nativeModules.add(new NativeAdManager(reactContext));
        nativeModules.add(new AdSettingsManager(reactContext));
        nativeModules.add(new InterstitialAdManager(reactContext));
        nativeModules.add(new SQLiteModule(reactContext, scopedContext));
        nativeModules.add(new DocumentPickerModule(reactContext));
        nativeModules.add(new PedometerModule(reactContext));
        nativeModules.add(new RNBranchModule(reactContext));
        nativeModules.add(new ErrorRecoveryModule(reactContext, experienceId));
        nativeModules.add(new IntentLauncherModule(reactContext));
        nativeModules.add(new ScreenOrientationModule(reactContext));
        nativeModules.add(new SpeechModule(reactContext));
        nativeModules.add(new SecureStoreModule(reactContext, scopedContext, experienceId));
        nativeModules.add(new GLObjectManagerModule(reactContext));
      } catch (JSONException | UnsupportedEncodingException e) {
        EXL.e(TAG, e.toString());
      }
    } else {
      nativeModules.add(new ExponentUnsignedAsyncStorageModule(reactContext));
    }
    nativeModules.add(new ImageCropperModule(reactContext));

    return nativeModules;
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    List<ViewManager> viewManagers = new ArrayList<>(Arrays.<ViewManager>asList(
        new LinearGradientManager(),
        new VideoViewManager(),
        new BarCodeScannerViewManager(),
        new CameraViewManager(),
        new GLViewManager(),
        new NativeAdViewManager(reactContext),
        new BannerViewManager(reactContext)
    ));

    // Add view manager from 3rd party library packages.
    addViewManagersFromPackages(reactContext, viewManagers, Arrays.<ReactPackage>asList(
      new SvgPackage(),
      new MapsPackage(),
      new LottiePackage()
    ));

    return viewManagers;
  }

  private void addViewManagersFromPackages(ReactApplicationContext reactContext,
                                           List<ViewManager> viewManagers,
                                           List<ReactPackage> packages) {
    for (ReactPackage pack : packages) {
      viewManagers.addAll(pack.createViewManagers(reactContext));
    }
  }
}