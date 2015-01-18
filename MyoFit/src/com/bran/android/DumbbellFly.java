package com.bran.android;

import android.util.Log;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Myo.VibrationType;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;

public class DumbbellFly extends Exercise {

  private boolean down = true;
  private float minAngle = 75;
  private float downAngle = 10;

  private float formThreshold = 40;

  private int direction;

  private long formTimeDiff;
  private static final int TIME_DIFF = 2000;

  public DumbbellFly() {
    super("Dumbbell Fly",ExerciseType.DUMBBELL_FLY);

    direction = 1;
    form = true;
  }

  @Override
  public void processData(Myo myo, long timestamp, Quaternion quaternion, DataType type) {
    //ADD SUPER THING HERE

    if (myo.getXDirection().equals(XDirection.TOWARD_ELBOW))
    direction = -1;

    if(type.equals(DataType.ORIENTATION)) {

      float pitch = (float) Math.toDegrees(Quaternion.pitch(quaternion));
      float roll = (float) Math.toDegrees(Quaternion.roll(quaternion));
      float yaw = (float) Math.toDegrees(Quaternion.yaw(quaternion));

      //Log.i("BicepCurl", "pitch: "+pitch);
      //Log.i("BicepCurl", "roll: "+roll);
      //Log.i("BicepCurl", "yaw: "+yaw);

      if(started) {
        if(down && pitch > minAngle) {
          down = false;
          rep++;
          if (!form)
          form = true;
          // TODO: MAKE OPTION
          // myo.vibrate(VibrationType.SHORT);
        } else if (!down && pitch < downAngle) {
          down = true;
        }
      }
    }
  }
}
