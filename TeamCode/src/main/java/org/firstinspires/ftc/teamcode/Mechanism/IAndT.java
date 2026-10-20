package org.firstinspires.ftc.teamcode.Mechanism;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IAndT {
    private final DcMotorEx intake;
    private final CRServo transfer;

    //alright so default speed because it is easy to do and i want to
    //make this look like i actually did work today
    private final double DEFAULT_SPEED;

    //okay so these are for when i inevitably do testing and
    //i need to change the sign
    private final double iSign = 1;
    private final double tSign = 1;

    //Constructors!!

    public IAndT(@NonNull HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "Intake");
        transfer = hardwareMap.get(CRServo.class, "Transfer");
        DEFAULT_SPEED = 0.5;
    }
    public IAndT(@NonNull HardwareMap hardwareMap, double defaultSpeed) {
        intake = hardwareMap.get(DcMotorEx.class, "Intake");
        transfer = hardwareMap.get(CRServo.class, "Transfer");
        DEFAULT_SPEED = defaultSpeed;
    }

    //Intake and Transfer have extra methods for default speeds without parameters
    //but these are for specific inputs

    public void runIntake(double pow) {
        intake.setPower(iSign*pow);
    }
    public void runTransfer(double pow) {
        transfer.setPower(tSign*pow);
    }
    public void runIAndT(double pow) {
        intake.setPower(iSign*pow);
        transfer.setPower(tSign*pow);
    }
    public void runIAndT(double pow1,double pow2) {
        intake.setPower(iSign*pow1);
        transfer.setPower(tSign*pow2);
    }

    //Extra default speed methods
    //aka random bullshit fluff

    public void runIntakeIn() {
        intake.setPower(iSign*DEFAULT_SPEED);
    }
    public void runIntakeOut() {
        intake.setPower(iSign*-DEFAULT_SPEED);
    }
    public void runTransferIn() {
        transfer.setPower(tSign*DEFAULT_SPEED);
    }
    public void runTransferOut() {
        transfer.setPower(tSign*-DEFAULT_SPEED);
    }
    public void runIAndTIn() {
        intake.setPower(iSign*DEFAULT_SPEED);
        transfer.setPower(tSign*DEFAULT_SPEED);
    }
    public void runIAndTOut() {
        intake.setPower(iSign*-DEFAULT_SPEED);
        transfer.setPower(tSign*-DEFAULT_SPEED);
    }

}
