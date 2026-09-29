/*package org.firstinspires.ftc.teamcode.Swerve.Swerve;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="JustSwerve", group="Teleop")
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
        telemetry.addLine(dt.toString());

        telemetry.addData("Angle", "%.4f rad", dt.readAbsoluteAngle(dt.steerEncoder[0]));
        telemetry.addData("enc1 V / max", "%.3f / %.3f", dt.steerEncoder[0].getVoltage(), dt.steerEncoder[0].getMaxVoltage());
        telemetry.addData("enc2 V / max", "%.3f / %.3f", dt.steerEncoder[1].getVoltage(), dt.steerEncoder[1].getMaxVoltage());
        telemetry.update();
        telemetry.update();
    }
}

 */