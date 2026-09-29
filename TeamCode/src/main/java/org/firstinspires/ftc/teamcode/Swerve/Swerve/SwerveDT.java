/*package org.firstinspires.ftc.teamcode.Swerve.Swerve;

import static androidx.core.math.MathUtils.clamp;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.hardware.VoltageSensor;
public class SwerveDT {
    private final double[] moduleX = {15,-15}; //assign the positions of the modules
    private final double[] moduleY = {20,-20}; //assign the positions of the modules
    private final DcMotorEx[] motorA = new DcMotorEx[2];
    private final DcMotorEx[] motorB = new DcMotorEx[2];
    public final VoltageSensor voltageSensor;
    public AnalogInput[] steerEncoder = new AnalogInput[2];

    public SwerveDT(@NonNull HardwareMap hardwareMap){
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");

        motorA[0] = hardwareMap.get(DcMotorEx.class, "Module1A");
        motorB[0] = hardwareMap.get(DcMotorEx.class, "Module1B");
        motorA[1] = hardwareMap.get(DcMotorEx.class, "Module2A");
        motorB[1] = hardwareMap.get(DcMotorEx.class, "Module2B");

        motorA[0].setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorA[1].setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorB[0].setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorB[1].setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        motorB[0].setDirection(DcMotorSimple.Direction.REVERSE);
        motorB[1].setDirection(DcMotorSimple.Direction.REVERSE);

        steerEncoder[0] = hardwareMap.get(AnalogInput.class, "steeringEncoder1");
        steerEncoder[1] = hardwareMap.get(AnalogInput.class, "steeringEncoder2");

        for (DcMotor m : motorA) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        for (DcMotor m : motorB) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void sRobotOrientedDrive(double forward, double sideways, double rotation){
        for (int i = 0; i < 2; i++) {
            double mvx = forward - rotation * moduleY[i];
            double mvy = sideways + rotation * moduleX[i];
            double targetAngle = Math.atan2(mvy,mvx);
            double targetSpeed = Math.hypot(mvx,mvy);

            double currentAngle = readAbsoluteAngle(steerEncoder[i]);
            double angleError = wrapAngle(targetAngle - currentAngle);

            double azSign = (i == 0) ? 1 : -1;
            double steerCmd = azSign * clamp(angleError, -1, 1);
            double driveCmd = clamp(targetSpeed, -1, 1);

            double vA = -driveCmd - steerCmd;
            double vB = driveCmd - steerCmd;

            motorA[i].setPower(clamp(vA, -.25, .25));
            motorB[i].setPower(clamp(vB, -.25, .25));
        }

    }

    public double readAbsoluteAngle(AnalogInput enc) {
        double voltage = enc.getVoltage();
        double maxVoltage = 3.3; // check your encoder's actual output range
        return (voltage / maxVoltage) * 2 * Math.PI; // maps to [-pi, pi]
    }
    double wrapAngle(double a) {
        while (a > Math.PI) a -= 2 * Math.PI;
        while (a < -Math.PI) a += 2 * Math.PI;
        return a;
    }






}
*/