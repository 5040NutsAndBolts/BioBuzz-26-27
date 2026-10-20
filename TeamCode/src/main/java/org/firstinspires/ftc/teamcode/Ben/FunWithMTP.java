package org.firstinspires.ftc.teamcode.Ben;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Helpers.MoveToPosition;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;

@Autonomous(name="FunWithMTP", group="Teleop")
public class FunWithMTP extends OpMode {
    private Drivetrain dt;



    private MoveToPosition motionController;

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap, 55f, 153f);
        motionController = new MoveToPosition();

        motionController.dt = dt;


    }

    @Override
    public void loop() {

        double[] errThreshold = {
                1.5, 1.5, 1.04 //cm,cm,degrees
        };
        double[] maxVel = {
                0.6,0.6,0.35
        };

        dt.updateOdo();

        //motionController.moveToPosition(new MoveToPosition.Pose(5,0,0), errThreshold, maxVel);

        if(gamepad1.circleWasPressed())
            motionController.moveToPosition(
                    new MoveToPosition.Pose(110,0,270),
                    errThreshold,
                    maxVel);
        else if(gamepad1.crossWasPressed())
            motionController.moveToPosition(
                    new MoveToPosition.Pose(110,280,90),
                    errThreshold,
                    maxVel);
        else
            dt.robotOrientedDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);


        telemetry.addLine(dt.toString());
        telemetry.update();
    }

}


