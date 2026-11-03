package org.firstinspires.ftc.teamcode.OpModes.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="IntakeTest", group="Teleop")
public class IntakeTest extends OpMode {
    private org.firstinspires.ftc.teamcode.Mechanism.Intake b;

    @Override
    public void init() {
        b = new org.firstinspires.ftc.teamcode.Mechanism.Intake(hardwareMap);
    }

    @Override
    public void loop() {
        b.runIntake(gamepad1.triangle, gamepad1.cross, 0.75);
    }
}