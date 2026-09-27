package org.firstinspires.ftc.teamcode.Ben;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BenTester {

    public final DcMotorEx tMotor0, tMotor1, tMotor2, tMotor3;

    public BenTester(@NonNull HardwareMap hardwareMap) {
        tMotor0 = hardwareMap.get(DcMotorEx.class, "tMotor0");
        tMotor1 = hardwareMap.get(DcMotorEx.class, "tMotor1");
        tMotor2 = hardwareMap.get(DcMotorEx.class, "tMotor2");
        tMotor3 = hardwareMap.get(DcMotorEx.class, "tMotor3");

    }

}
