package org.firstinspires.ftc.teamcode.Helpers.camera.Color;

import android.util.Size;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Helpers.camera.CameraStreamProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class ColorLocate {

    public final CameraStreamProcessor cameraStreamProcessor;
    public final colorDetectionPipeline cPipe;
    public final VisionPortal vPortal;

    public ColorLocate(@NonNull HardwareMap hardwareMap) {

        cameraStreamProcessor = new CameraStreamProcessor();
        cPipe = new colorDetectionPipeline();

        vPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "color"))
                .addProcessors(cPipe, cameraStreamProcessor)
                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
                .setCameraResolution(new Size(800,600))
                .enableLiveView(true)
                .build();


    }


    public void close() {
        if (vPortal != null) {
            vPortal.close();
        }
    }

    public void check(){ //only important if using multiple cameras
        vPortal.getActiveCamera();
    }

    public String currState(){
        return "" + vPortal.getCameraState();
    }

}