package org.firstinspires.ftc.teamcode.MaxBranch;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Helpers.PID;

import java.util.function.Supplier;

public class Flywheel {
    private double rpm;
    private DcMotorEx flywheelMotor;
    PID flywheelPID;
    Supplier<Double> flywheelSupplier = () -> flywheelMotor.getVelocity()*(60 / 28);//*60/28 is the conversion factor for ticks/sec to rpm
    public Flywheel() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "Flywheel");
        flywheelPID = new PID(5,.1,0,3,flywheelSupplier);
    }
    public void setFlywheelRPM(double rpm) {
        flywheelPID.setTarget(rpm);
        flywheelMotor.setPower(flywheelPID.autoControl());
    }
}
