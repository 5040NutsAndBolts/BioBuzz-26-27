package org.firstinspires.ftc.teamcode.Helpers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;

import java.util.function.Supplier;


public class MoveToPosition {
    public Drivetrain dt;
    Pose target;
    Supplier<Double> xSupplier = () -> dt.getPosition()[0];
    Supplier<Double> ySupplier = () -> dt.getPosition()[1];
    Supplier<Double> headingSupplier = () -> Math.toRadians(dt.getPosition()[2]);

    /**
     PIDs, not worried about a wrap around bug since
     we normalize the angle.
     */
    PID xController = new PID(0.02,1e-4,0, xSupplier);
    PID yController = new PID(0.02,1e-4,0, ySupplier);
    PID headingController = new PID(0.03,3e-5,0, () -> {
        return Math.toDegrees(dt.getPosition()[2]);
    });

    //just a way to organize position and angle in a simple way
    public static class Pose {
        public double x, y, heading;
        public Pose(double x, double y, double heading) {
            this.x = x;
            this.y = y;
            this.heading = heading;
        }
    }
    /**
     * for finding the shortest path between the angles.
     * without wrapping the error in this way, the robot
     * may spin the wrong way and spend unnecessary time.
     * ex. heading is 350 degrees and target is 10 degrees,
     * rather than going with 10 - 350 = -340 and spinning
     * -340 degrees to the target, it will fix it so that
     * it spins 20 degrees
     */
    double normalizeAngle(double radians){
        while (radians >= Math.PI)
            radians -= 2 * Math.PI;
        while (radians < -Math.PI)
            radians += 2 * Math.PI;
        return radians;
    }

    public void moveToPosition(Pose target, double[] errThreshold, double[] maxVel){
        // Reset the PID timers and errors before starting the movement profile
        xController.reset();
        yController.reset();
        headingController.reset();

        xController.setTarget(target.x);
        yController.setTarget(target.y);

        while(!Thread.currentThread().isInterrupted()) {
        // Read from suppliers automatically inside the autoControl loop
            double currentX = xSupplier.get();
            double currentY = ySupplier.get();
            double currentHeading = headingSupplier.get();

            double deltaX = target.x - currentX;
            double deltaY = target.y - currentY;
            double deltaTheta = normalizeAngle(target.heading - currentHeading);

            // Since heading requires custom error wrapping, update its target relative to current error
            headingController.setTarget(currentHeading + deltaTheta);

            // Let the suppliers inside PID handle capturing positions automatically!
            double xOut = xController.autoControl();
            double yOut = yController.autoControl();
            double turnOut = headingController.autoControl();

            dt.fieldOrientedDrive(xOut, yOut, turnOut);

            if (Math.abs(deltaX) < errThreshold[0] &&
                Math.abs(deltaY) < errThreshold[1] &&
                Math.abs(deltaTheta) < errThreshold[2]) {

                dt.robotOrientedDrive(0, 0, 0);
                break;
            }
        }
    }

}