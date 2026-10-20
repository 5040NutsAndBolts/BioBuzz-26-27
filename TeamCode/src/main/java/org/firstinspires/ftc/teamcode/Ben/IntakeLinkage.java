package org.firstinspires.ftc.teamcode.Ben;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeLinkage {
    private final CRServo topServo;
    private final CRServo bottomServo;
    public IntakeLinkage(@NonNull HardwareMap hardwareMap)
    {
        topServo = hardwareMap.get(CRServo.class, "ServoOne");
        bottomServo = hardwareMap.get(CRServo.class, "ServoTwo");
    }

    public void spin(int pow)
    {
        topServo.setPower(pow);
        bottomServo.setPower(pow);
    }

}
