package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanism.SwerveDT;

@TeleOp(name="JustDrivetrain", group="Teleop")
public class JustSwerve extends OpMode {
    private SwerveDT dt;

    @Override
    public void init() {
        dt = new SwerveDT(hardwareMap);
    }

    //fl port 2 fr port 3 bl port 0 br port 1
    @Override
    public void loop() {
        dt.sRobotOrientedDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
        dt.updateOdo();

        telemetry.addLine(dt.toString());
        telemetry.update();
    }
}