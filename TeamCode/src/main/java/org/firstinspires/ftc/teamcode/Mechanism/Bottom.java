package org.firstinspires.ftc.teamcode.Mechanism;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

// This class is absolutely awful so fix it up when finalizing for the season
// can probably (hopefully) just use the IAntT class because has cleaner code
// - Ben

public class Bottom {
    private DcMotorEx intake;
    private CRServo transfer;

    //alright so default speed because it is easy to do and i want to
    //make this look like i actually did work today
    private final double DEFAULT_SPEED;

    //okay so these are for when i inevitably do testing and
    //i need to change the sign
    private final double iSign = 1;
    private final double tSign = 1;

    //Constructors!!

    public Bottom(@NonNull HardwareMap hardwareMap) {
        try {
            intake = hardwareMap.get(DcMotorEx.class, "Intake");
        }
        catch (Exception e) {
            intake = null;
        }
        try {
            transfer = hardwareMap.get(CRServo.class, "Transfer");
        }
        catch (Exception e) {
            transfer = null;
        }
        DEFAULT_SPEED = 0.5;
    }
    public Bottom(@NonNull HardwareMap hardwareMap, double defaultSpeed) {
        try {
            intake = hardwareMap.get(DcMotorEx.class, "Intake");
        }
        catch (Exception e) {
            intake = null;
        }
        try {
            transfer = hardwareMap.get(CRServo.class, "Transfer");
        }
        catch (Exception e) {
            transfer = null;
        }
        DEFAULT_SPEED = defaultSpeed;
    }

    //Intake and Transfer have extra methods for default speeds without parameters
    //but these are for specific inputs

    public void runIntake(double pow) {
        if(intake != null)
            intake.setPower(iSign*pow);
    }
    public void runTransfer(double pow) {
        if(transfer != null)
            transfer.setPower(tSign*pow);
    }
    public void runIAndT(double pow) {
        if(intake != null)
            intake.setPower(iSign*pow);
        if(transfer != null)
            transfer.setPower(tSign*pow);
    }
    public void runIAndT(double pow1,double pow2) {
        if(intake != null)
            intake.setPower(iSign*pow1);
        if(transfer != null)
            transfer.setPower(tSign*pow2);
    }

    //Extra default speed methods
    //aka random bullshit fluff

    public void runIntakeIn() {
        if(intake != null)
            intake.setPower(iSign*DEFAULT_SPEED);
    }
    public void runIntakeOut() {
        if(intake != null)
            intake.setPower(iSign*-DEFAULT_SPEED);
    }
    public void runTransferIn() {
        if(transfer != null)
            transfer.setPower(tSign*DEFAULT_SPEED);
    }
    public void runTransferOut() {
        if(transfer != null)
            transfer.setPower(tSign*-DEFAULT_SPEED);
    }
    public void runIAndTIn() {
        if(intake != null)
            intake.setPower(iSign*DEFAULT_SPEED);
        if(transfer != null)
            transfer.setPower(tSign*DEFAULT_SPEED);
    }
    public void runIAndTOut() {
        if(intake != null)
            intake.setPower(iSign*-DEFAULT_SPEED);
        if(transfer != null)
            transfer.setPower(tSign*-DEFAULT_SPEED);
    }

}
