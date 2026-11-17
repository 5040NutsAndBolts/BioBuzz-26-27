package org.firstinspires.ftc.teamcode.OpModes.Auto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.MoveToPosition;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanism.Flywheel;
import org.firstinspires.ftc.teamcode.Mechanism.Intake;
import org.firstinspires.ftc.teamcode.Mechanism.Top;
@Autonomous(name="ScrimAuto", group="Autonomous")
public class ScrimAuto extends OpMode {
    private Drivetrain dt;
    private Intake i;
    private Flywheel f;
    private ElapsedTime time = new ElapsedTime();
    private MoveToPosition motionController;
    private MoveToPosition.Pose currentTarget;
    private double[] errThreshold = {
            1.5, 1.5, 1.04 //cm,cm,radians
    };
    private double[] maxVel = {
            0.6,0.6,0.35
    };

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap);
        i = new Intake(hardwareMap);
        f = new Flywheel(hardwareMap);
        time = new ElapsedTime(0);
    }

    @Override
    public void loop() {
        if(time.seconds() < 1)
            dt.robotOrientedDrive(-0.5,0,0);
        else
            dt.robotOrientedDrive(0,0,0);
        telemetry.addLine(dt.toString());
        telemetry.update();
    }

}
