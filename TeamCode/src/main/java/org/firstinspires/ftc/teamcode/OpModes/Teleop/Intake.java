package org.firstinspires.ftc.teamcode.OpModes.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanism.Bottom;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;

@TeleOp(name="Intake", group="Teleop")
public class Intake extends OpMode {
    private Bottom b;

    @Override
    public void init() {
        b = new Bottom(hardwareMap);
    }

    @Override
    public void loop() {
        b.runIntake(gamepad1.triangle, gamepad1.cross, 0.75);
    }
}