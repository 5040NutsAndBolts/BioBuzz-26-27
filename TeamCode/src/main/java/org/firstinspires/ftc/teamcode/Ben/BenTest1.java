package org.firstinspires.ftc.teamcode.Ben;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="BenTest1", group="Teleop")
public class BenTest1 extends OpMode {

    private BenTester bt;
    private double p0, p1, inc;
    private boolean running;
    @Override
    public void init(){
        bt = new BenTester(hardwareMap);
        p0 = 0.15; //0.19
        p1 = 0.55; //0.48
        inc = 0.01;
        running = false;
    }
    @Override
    public void loop(){
        //this puts it on the robot ig
        telemetry.addData("P0:", p0);
        telemetry.addData("P1:", p1);
        //actually does the on and off
        if(running) {
            bt.tMotor0.setPower((-p0));
            bt.tMotor1.setPower((p1));
        }
        else {
            bt.tMotor0.setPower(0);
            bt.tMotor1.setPower(0);
        }

        //uppy downy code
        //      bumper and trigger do the thingy
        if(gamepad1.rightBumperWasPressed() && p1 < 1)
            p1 += inc;
        if(gamepad1.rightTriggerWasPressed() && p1 > 0)
            p1 -= inc;
        if(gamepad1.leftBumperWasPressed() && p0 < 1)
            p0 += inc;
        if(gamepad1.leftTriggerWasPressed() && p0 > 0)
            p0 -= inc;

        //onny offy code
        //      circle turns on, x or cross turns off
        if(gamepad1.circleWasPressed())
            running = true;
        if(gamepad1.crossWasPressed())
            running = false;

        System.out.println("p0:" + p0 + "\tp1:" + p1);
    }
}
