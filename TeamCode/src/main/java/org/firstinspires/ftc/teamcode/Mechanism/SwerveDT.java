package org.firstinspires.ftc.teamcode.Mechanism;

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
public class SwerveDT {
    private final double[] moduleX = {15,-15}; //assign the positions of the modules
    private final double[] moduleY = {20,-20}; //assign the positions of the modules
    private final DcMotor[] motorA = new DcMotor[2];
    private final DcMotor[] motorB = new DcMotor[2];
    private final Odometry odo;
    public final VoltageSensor voltageSensor;
    AnalogInput[] steerEncoder = new AnalogInput[2];

    public SwerveDT(@NonNull HardwareMap hardwareMap){
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");

        motorA[0] = hardwareMap.get(DcMotorEx.class, "Module1A");
        motorB[0] = hardwareMap.get(DcMotorEx.class, "Module1B");
        motorA[1] = hardwareMap.get(DcMotorEx.class, "Module2A");
        motorB[1] = hardwareMap.get(DcMotorEx.class, "Module2B");

        motorA[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorA[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorB[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorB[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        for (DcMotor m : motorA) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        for (DcMotor m : motorB) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        odo = new Odometry(hardwareMap, 133.35f, 152.4f); //fix these offsets
        this.resetOdo();
    }

    public void sRobotOrientedDrive(double forward, double sideways, double rotation){
        for (int i = 0; i<2; i++){
            double mvx = sideways - rotation * moduleY[i];
            double mvy = forward + rotation * moduleX[i];
            double targetAngle = Math.atan2(mvy,mvx);
            double targetSpeed = Math.hypot(mvx,mvy);

            double currentAngle = readAbsoluteAngle(steerEncoder[i]);
            double angleError = wrapAngle(targetAngle - currentAngle);

            double steerCmd = clamp(angleError, -1, 1);
            double driveCmd = clamp(targetSpeed, -1, 1);

            double vA = driveCmd + steerCmd;
            double vB = driveCmd - steerCmd;

            motorA[i].setPower(clamp(vA, -1, 1));
            motorB[i].setPower(clamp(vB, -1, 1));
        }

    }

    public void updateOdo() {
        odo.update();
    }
    double readAbsoluteAngle(AnalogInput enc) {
        double voltage = enc.getVoltage();
        double maxVoltage = 3.3; // check your encoder's actual output range
        return (voltage / maxVoltage) * 2 * Math.PI - Math.PI; // maps to [-pi, pi]
    }
    double wrapAngle(double a) {
        while (a > Math.PI) a -= 2 * Math.PI;
        while (a < -Math.PI) a += 2 * Math.PI;
        return a;
    }






    public void resetOdo() { odo.reset(); }
}
