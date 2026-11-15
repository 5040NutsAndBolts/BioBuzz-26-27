package org.firstinspires.ftc.teamcode.Mechanism;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Helpers.PID;

import java.util.function.Supplier;

public class Flywheel {
    public DcMotorEx flywheelMotor;
    PID flywheelPID;
    Supplier<Double> flywheelSupplier = () -> (flywheelMotor.getVelocity() * 60.0) / 28.0;//*60/28 is the conversion factor for ticks/sec to rpm
    public Flywheel(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "Flywheel");
        flywheelPID = new PID(.003,3e-3,0,30,flywheelSupplier);
        flywheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setFlywheelRPM(double rpm) {
        flywheelPID.setTarget(rpm);
        flywheelMotor.setPower(
                Math.max(-1.0, Math.min(1.0, flywheelPID.autoControl())));
    }

    public double getFlywheelPosition(){
        return flywheelMotor.getCurrentPosition();
    }
    public double getFlywheelRPM(){
        return flywheelSupplier.get();
    }
    public double getMotorOutput(){
        return flywheelPID.currentOutput;
    }
    public void resetFlywheelPID(){
        flywheelPID.reset();
    }

}
