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
        flywheelPID = new PID(.0001,3e-8,0,0.01,flywheelSupplier);

//        flywheelPID = new PID(0,0,0,3,flywheelSupplier);

    }
    public void setFlywheelRPM(double rpm) {
        flywheelPID.setTarget(rpm);
        flywheelMotor.setPower(flywheelPID.autoControl());
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
