package org.firstinspires.ftc.teamcode.OpModes.Auto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Helpers.MoveToPosition;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;

@Autonomous(name="mtpTest", group="Autonomous")
public class mtpTest extends OpMode {
    private Drivetrain dt;



    private MoveToPosition motionController;

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap);
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

        motionController.moveToPosition(new MoveToPosition.Pose(5,0,0), errThreshold, maxVel);

        telemetry.addLine(dt.toString());
        telemetry.update();
    }

}


