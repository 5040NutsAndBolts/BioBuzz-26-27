package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanism.Bottom;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanism.Top;

@TeleOp(name="FullRobot", group="Teleop")
public class FullRobot extends OpMode  {
    private Drivetrain dt;
    private Bottom b;
    private Top t;

    @Override
    public void init()
    {
        dt = new Drivetrain(hardwareMap);
        b = new Bottom(hardwareMap, 0.2);
        t = new Top(hardwareMap);
    }
    @Override
    public void loop()
    {
        dt.robotOrientedDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
        if(gamepad1.crossWasPressed())
            b.runIntakeIn();
        else if(gamepad1.triangleWasPressed())
            b.runIntakeOut();

        if(gamepad1.circleWasPressed())
            b.runTransferIn();
        else if(gamepad1.triangleWasPressed())
            b.runTransferOut();

        if(gamepad1.leftBumperWasPressed())
            t.spinFlywheel(0.5);
        else if(gamepad1.rightBumperWasPressed())
            t.spinFlywheel(-0.5);

        telemetry.addData("Flywheel RPMS", t.getRPMS());
        telemetry.addData("Current Odo", dt.getPosition());
    }

}
