package org.firstinspires.ftc.teamcode.Helpers.odo;

import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Odometry {
    private final GoBildaPinpointDriver pinpoint;

    public Odometry (@NonNull HardwareMap hardwareMap, float xOffset, float yOffset) {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "Pinpoint");

        //We use the swingarm GoBilda pods, change if you are using different pods (it's ticks/mm)
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        //Depends on mounting of pods
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        //THIS WILL CHANGE WITH EVERY DRIVETRAIN!
        //Offsets of each pod from the center of the robot (in mm)
        pinpoint.setOffsets(xOffset,yOffset);

        //Recalibrate IMU
        pinpoint.resetPosAndIMU();
        pinpoint.recalibrateIMU();
    }

    public GoBildaPinpointDriver getPinpoint() {
        return pinpoint;
    }

    public void update() {
        pinpoint.update();
    }

    public double[] getPosition() {
        return new double[] {
                (pinpoint.getPosition().getX(DistanceUnit.CM)),
                (pinpoint.getPosition().getY(DistanceUnit.CM)),
                Math.toDegrees(pinpoint.getHeading())
        };
    }

    public void reset() {
        pinpoint.resetPosAndIMU();
        pinpoint.setPosition(new Pose2D(DistanceUnit.CM,0,0, AngleUnit.DEGREES,0));
    }

    public void set(Pose2D pose) {
        pinpoint.resetPosAndIMU();
        pinpoint.setPosition(pose);
    }
    public void reset(int x, int y, int heading) {
        pinpoint.setPosition(new Pose2D(DistanceUnit.CM,x,y, AngleUnit.DEGREES,heading));
    }
    @NonNull
    @Override
    public String toString() {
        return "Odometry:\n" +
                "\tStatus: " + pinpoint.getDeviceStatus() + "\n" +
                "\tX: " + (pinpoint.getPosition().getX(DistanceUnit.CM)) + "\n" +
                "\tY: " + (pinpoint.getPosition().getY(DistanceUnit.CM)) + "\n" +
                "\tHeading: " + Math.toDegrees(pinpoint.getHeading());
    }
}