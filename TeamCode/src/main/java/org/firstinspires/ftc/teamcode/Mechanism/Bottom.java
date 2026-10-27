package org.firstinspires.ftc.teamcode.Mechanism;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

// This class is absolutely AWFUL so fix it up when finalizing for the season
// can probably (hopefully) just use the IAntT class because has cleaner code
// - Ben

public class Bottom {
    private DcMotorEx intake;
    private CRServo transfer;

    //alright so default speed because it is easy to do and i want to
    //make this look like i actually did work today

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
    }

    //Intake and Transfer have extra methods for default speeds without parameters
    //but these are for specific inputs
    public void runIntake(boolean check1, boolean check2, double pow)
    {
        if(intake != null) {
            if (check1)
                intake.setPower(iSign*pow);
            else if (check2)
                intake.setPower(-iSign*pow);
            else
                intake.setPower(0);
        }
    }
    public void runTransfer(boolean check1, boolean check2, double pow)
    {
        if(transfer != null) {
            if (check1)
                transfer.setPower(tSign*pow);
            else if (check2)
                transfer.setPower(-tSign*pow);
            else
                transfer.setPower(0);
        }
    }
    public void runBottom(boolean check1, boolean check2, double pow)
    {
        if(intake != null) {
            if (check1)
                intake.setPower(iSign*pow);
            else if (check2)
                intake.setPower(-iSign*pow);
            else
                intake.setPower(0);
        }
        if(transfer != null) {
            if (check1)
                transfer.setPower(tSign*pow);
            else if (check2)
                transfer.setPower(-tSign*pow);
            else
                transfer.setPower(0);
        }
    }


}
