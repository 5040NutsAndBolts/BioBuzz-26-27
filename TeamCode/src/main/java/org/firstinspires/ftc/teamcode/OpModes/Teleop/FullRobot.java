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
@TeleOp(name="FullRobot", group="Teleop")
public class FullRobot extends OpMode  {
    private Drivetrain dt;
    private Intake i;
    private Top t;
    private Flywheel shooter;
    private boolean runWheel;
    private double power;

    @Override
    public void init()
    {
        dt = new Drivetrain(hardwareMap);
        i = new Intake(hardwareMap);
        t = new Top(hardwareMap);
        shooter = new Flywheel(hardwareMap);
        i.transferReset();
        runWheel = false;
        power = 0.5;
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
        dt.robotOrientedDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x*1.2, -gamepad1.right_stick_x);
        i.runIntake(gamepad1.right_trigger_pressed, gamepad1.left_trigger_pressed, 0.75);

        if(gamepad1.a){
            i.runTransfer();
            ElapsedTime time = new ElapsedTime();
            while(time.seconds() < 1.5){

            }
            i.transferReset();
        }

        if(gamepad1.circleWasPressed())
            runWheel = !runWheel;

        if(runWheel) {
            shooter.setFlywheelRPM(100);
            shooter.flywheelMotor.setPower(1);
        }else {
            shooter.setFlywheelRPM(0);
            shooter.flywheelMotor.setPower(0);
        }
        if(gamepad1.dpadUpWasPressed())
            power += 0.05;
        if(gamepad1.dpadDownWasPressed())
            power -= 0.05;

        telemetry.addData("Flywheel RPMS", shooter.getFlywheelRPM());
        telemetry.addData("Flywheel Raw RPMS", shooter.getRawFlywheelRPM());
        telemetry.addData("Flywheel Position in ticks", shooter.getFlywheelPosition());

        telemetry.addData("Current Odo", dt.getPosition());
    }

}

