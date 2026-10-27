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
    private boolean runWheel;
    private double power;

    @Override
    public void init()
    {
        dt = new Drivetrain(hardwareMap);
        b = new Bottom(hardwareMap);
        t = new Top(hardwareMap);
        runWheel = false;
        power = 0.5;
    }

    /* Controller Setup
     *
     * intake       - right trigger in
     *              - left trigger out
     *
     * transfer     - right bumper in
     *              - left bumper out
     *
     * flywheel     - circle toggle on off
     *              - up and down arrow for flywheel power
     *
     */

    @Override
    public void loop()
    {
        dt.robotOrientedDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
        b.runIntake(gamepad1.right_trigger_pressed, gamepad1.left_trigger_pressed, 0.75);
        b.runTransfer(gamepad1.right_bumper, gamepad1.left_bumper, 0.75);

        if(gamepad1.circleWasPressed())
            runWheel = !runWheel;

        if(runWheel)
            t.spinFlywheel(power);
        else
            t.spinFlywheel(0);

        if(gamepad1.dpadUpWasPressed())
            power += 0.05;
        if(gamepad1.dpadDownWasPressed())
            power -= 0.05;

        telemetry.addData("Flywheel RPMS", t.getRPMS());
        telemetry.addData("Current Odo", dt.getPosition());
    }

}
