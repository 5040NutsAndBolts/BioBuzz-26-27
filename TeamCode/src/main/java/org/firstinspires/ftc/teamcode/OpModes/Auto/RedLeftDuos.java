package org.firstinspires.ftc.teamcode.OpModes.Auto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Helpers.MoveToPosition;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;

@Autonomous(name="RedLeftDuos", group="Autonomous")
public class RedLeftDuos extends OpMode {
    private Drivetrain dt;



    private MoveToPosition motionController;
    private MoveToPosition.Pose currentTarget;
    private double[] errThreshold = {
            1.5, 1.5, 1.04 //cm,cm,degrees
    };
    private double[] maxVel = {
            0.6,0.6,0.35
    };

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap);
        motionController = new MoveToPosition();

        motionController.dt = dt;

    }

    @Override
    public void loop() {

        dt.updateOdo();


        // move to position to be ready to shoot
        motionController.moveToPosition(new MoveToPosition.Pose(0,30 ,0), errThreshold, maxVel);
        // wait, use the camera once we know how to even do that

        // when tipped, shoot four pollen up

        // move back to the flower
        motionController.moveToPosition(new MoveToPosition.Pose(-30,2.5,0), errThreshold, maxVel);
        // intake the flower

        // move forward, back to shooting position
        motionController.moveToPosition(new MoveToPosition.Pose(0,30 ,0), errThreshold, maxVel);
        // shoot again

        // move to park
        motionController.moveToPosition(new MoveToPosition.Pose(-120,60 ,-Math.PI), errThreshold, maxVel);



        telemetry.addLine(dt.toString());
        telemetry.update();
    }

}


