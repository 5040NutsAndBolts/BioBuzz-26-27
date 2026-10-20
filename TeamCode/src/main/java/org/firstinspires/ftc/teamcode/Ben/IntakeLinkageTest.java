package org.firstinspires.ftc.teamcode.Ben;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="IntakeLinkageTest", group="Teleop")
public class IntakeLinkageTest extends OpMode {

    private CRServo topServo;
    private CRServo bottomServo;
    private double base = 0;


    public void mrSpinTheServo(double howFastDoIWantIt) {
        topServo.setPower(howFastDoIWantIt);
        bottomServo.setPower(-howFastDoIWantIt);
    }

    @Override
    public void init()
    {
        topServo = hardwareMap.get(CRServo.class, "TopServo");
        bottomServo = hardwareMap.get(CRServo.class, "BottomServo");
    }

    // make a method that takes one parameter and spins both servos


    @Override
    public void loop()
    {
        double pow = gamepad1.left_stick_y;

        if(pow == 0)
            pow = base;
        mrSpinTheServo(pow);

        if(gamepad1.aWasPressed())
            base += 0.05;
        if(gamepad1.bWasPressed())
            base -= 0.05;

        telemetry.addData("Base Power", base);
    }
}
