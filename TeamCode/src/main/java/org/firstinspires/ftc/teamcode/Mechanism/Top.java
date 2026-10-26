package org.firstinspires.ftc.teamcode.Mechanism;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
// I have once again made an absolutely AWFUL class
// so lemme know and I'll try to fix it up later :D
// - Ben

public class Top {
    private DcMotorEx flywheel;
    public Top(@NonNull HardwareMap hardwareMap)
    {
        try {
            flywheel = hardwareMap.get(DcMotorEx.class, "Flywheel");
        } catch (Exception e)
        {
            flywheel = null;
        }
    }
    public double getRPMS()
    {
        if(flywheel != null)
            return flywheel.getVelocity()*60/28;
        else
            return -1;
    }
    //we probably need to do a PID here and the RPMS method is like 100% wrong but
    //is it a crime for a boy to try his best and fail?
    // checkmate, arthritis.
    public void spinFlywheel(double pow)
    {
        if(flywheel != null)
            flywheel.setPower(pow);
    }


}
