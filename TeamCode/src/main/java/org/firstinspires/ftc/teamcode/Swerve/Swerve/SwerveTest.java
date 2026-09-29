/*package org.firstinspires.ftc.teamcode.Swerve.Swerve;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="SwerveMotorTest", group="Test")
public class SwerveTest extends OpMode {
    DcMotor m1a, m1b, m2a, m2b;
    AnalogInput e1, e2;

    @Override public void init() {
        m1a = hardwareMap.get(DcMotor.class, "Module1A");
        m1b = hardwareMap.get(DcMotor.class, "Module1B");
        m2a = hardwareMap.get(DcMotor.class, "Module2A");
        m2b = hardwareMap.get(DcMotor.class, "Module2B");
        e1 = hardwareMap.get(AnalogInput.class, "steeringEncoder1");
        e2 = hardwareMap.get(AnalogInput.class, "steeringEncoder2");
    }

    @Override public void loop() {
        m1a.setPower(gamepad1.a ? 0.3 : 0);
        m1b.setPower(gamepad1.b ? -0.3 : 0);
        m2a.setPower(gamepad1.x ? 0.3 : 0);
        m2b.setPower(gamepad1.y ? -0.3 : 0);
        telemetry.addData("Angle", "%.4f rad", (e1.getVoltage()/ e1.getMaxVoltage())* 360);
        telemetry.addData("enc1 V / max", "%.3f / %.3f", e1.getVoltage(), e1.getMaxVoltage());
        telemetry.addData("enc2 V / max", "%.3f / %.3f", e2.getVoltage(), e2.getMaxVoltage());
        telemetry.update();
    }
}

 */