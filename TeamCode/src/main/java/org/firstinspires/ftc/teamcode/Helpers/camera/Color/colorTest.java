package org.firstinspires.ftc.teamcode.Helpers.camera.Color;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Helpers.camera.Color.ColorLocate;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.util.List;


@TeleOp(name = "Color Finder", group = "TeleOp")
public class colorTest extends OpMode {

    ColorLocate camera;
    @Override
    public void init(){
        camera = new ColorLocate(hardwareMap);
    }

    @Override
    public void start(){
        FtcDashboard.getInstance().startCameraStream(camera.cameraStreamProcessor, 60);
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running Color Pipeline!");
        telemetry.addData("Rect Width", colorDetectionPipeline.width);
        telemetry.addData("Rect Height", colorDetectionPipeline.height);
        telemetry.update();
    }


}