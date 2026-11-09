package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanism.Flywheel;
import org.firstinspires.ftc.teamcode.Mechanism.Intake;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanism.Top;
//CHRIIIIIIIISTIAN
// read my code :D
@TeleOp(name="FWTest", group="Teleop")
public class FlywheelTest extends OpMode  {

    private Flywheel shooter;
    private boolean runWheel;

    @Override
    public void init()
    {
        shooter = new Flywheel(hardwareMap);
        runWheel = false;
    }

    /* Controller Setup
     *
     * intake       - right trigger in
     *              - left trigger out
     *
     * transfer     -
     *
     * flywheel     - circle toggle on off
     *              - up and down arrow for flywheel power
     *
     */

    @Override
    public void loop()
    {

        if(gamepad1.circleWasPressed())
            runWheel = !runWheel;

        if(runWheel)
            shooter.setFlywheelRPM(0);
        else
            shooter.setFlywheelRPM(0);



        telemetry.addData("Flywheel RPMS", shooter.getFlywheelRPM());
    }

}

