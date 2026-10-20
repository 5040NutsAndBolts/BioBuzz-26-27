package org.firstinspires.ftc.teamcode.Mechanism;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
// Ben

public class FlywheelProto {

    public final DcMotorEx tMotor0, tMotor1, tMotor2, tMotor3;

    public FlywheelProto(@NonNull HardwareMap hardwareMap) {
        tMotor0 = hardwareMap.get(DcMotorEx.class, "tMotor0");
        tMotor1 = hardwareMap.get(DcMotorEx.class, "tMotor1");
        tMotor2 = hardwareMap.get(DcMotorEx.class, "tMotor2");
        tMotor3 = hardwareMap.get(DcMotorEx.class, "tMotor3");

    }

}
