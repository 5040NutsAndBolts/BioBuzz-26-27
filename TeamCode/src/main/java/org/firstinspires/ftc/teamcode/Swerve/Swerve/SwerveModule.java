/*package org.firstinspires.ftc.teamcode.Mechanism;

import static androidx.core.math.MathUtils.clamp;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Helpers.odo.Odometry;
import com.qualcomm.robotcore.hardware.VoltageSensor;
public class SwerveModule {
    private final double[] modulePos = new double[2]; //assign the positions of the modules
    public DcMotorEx motorA, motorB;
    private final double azimuthSign;
    private final double encoderOffset;
    private final double maxPower;
    public AnalogInput steerEncoder;

    public SwerveModule(double posX, double posY, double azimuthSign, double wheelSign) {


    }
/*
    public double readAbsoluteAngle(AnalogInput enc) {
        double voltage = enc.getVoltage();
        double maxVoltage = 3.3; // check your encoder's actual output range
        return (voltage / maxVoltage) * 360; // maps to [-pi, pi]
    }
    double wrapAngle(double a) {
        while (a > Math.PI) a -= 2 * Math.PI;
        while (a < -Math.PI) a += 2 * Math.PI;
        return a;
    }



}

*/
