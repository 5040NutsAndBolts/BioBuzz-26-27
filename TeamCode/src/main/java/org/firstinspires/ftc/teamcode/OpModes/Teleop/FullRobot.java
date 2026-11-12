package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanism.Flywheel;
import org.firstinspires.ftc.teamcode.Mechanism.Intake;
import org.firstinspires.ftc.teamcode.Mechanism.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanism.Top;
@TeleOp(name="FullRobot", group="Teleop")
public class FullRobot extends OpMode  {
    private Drivetrain dt;
    private Intake i;
    private Top t;
    private Flywheel shooter;
    private boolean primed;
    private boolean runWheel;
    private double flywheelTarget;
    private ElapsedTime transferTimer = new ElapsedTime();
    private boolean transferring = false;

    @Override
    public void init()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //all telemtry we add that goes to the driver hub, now also goes to ftcdashboard.
        //when connected to the robot wifi go to 192.168.43.1:8080/dash
        //theres a few other caveats and features of ftcdashboard but i'd recommend sticking to the graph function
        //we also need to take advantage of ftc dashboard and automatically logging our results in a txt file
        dt = new Drivetrain(hardwareMap);
        i = new Intake(hardwareMap);
        t = new Top(hardwareMap);
        shooter = new Flywheel(hardwareMap);
        i.transferReset();
        runWheel = false;
        flywheelTarget = 3800;
    }

    /* Controller Setup
     *
     * intake       - right trigger in
     *              - left trigger out
     *
     * transfer     - a to flippy floppy
     *
     * flywheel     - circle toggle on off
     *              - up and down arrow for flywheel power
     *
     */

    @Override
    public void loop()
    {
        dt.robotOrientedDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x*2, -gamepad1.right_stick_x*0.8);
        i.runIntake(gamepad1.right_trigger_pressed, gamepad1.left_trigger_pressed, 0.75);

        if (gamepad1.aWasPressed() && !transferring) {
            transferring = true;
            transferTimer.reset();
            i.runTransfer();
        }

        if (transferring && transferTimer.seconds() >= 1.5) {
            i.transferReset();
            transferring = false;
        }

        if(gamepad1.circleWasPressed())
            runWheel = !runWheel;

        if(runWheel) {
            shooter.setFlywheelRPM(flywheelTarget);
            primed = shooter.getFlywheelRPM() >= flywheelTarget - 100;
        }else {
            shooter.flywheelMotor.setPower(0);
            shooter.resetFlywheelPID();
        }
        if(gamepad1.dpadUpWasPressed())
            flywheelTarget += 50;
        if(gamepad1.dpadDownWasPressed())
            flywheelTarget -= 50;

        telemetry.addData("Flywheel RPMS", shooter.getFlywheelRPM());
        telemetry.addData("Flywheel Position in ticks", shooter.getFlywheelPosition());
        telemetry.addData("Flywheel Target", flywheelTarget);
        telemetry.addData("Good to shoot?", primed);
        telemetry.addData("Output power", shooter.getMotorOutput());


        telemetry.addData("Current Odo", dt.getPosition());
    }

}

