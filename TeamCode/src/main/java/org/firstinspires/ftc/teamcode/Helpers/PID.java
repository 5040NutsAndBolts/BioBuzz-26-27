package org.firstinspires.ftc.teamcode.Helpers;

import androidx.annotation.NonNull;

import java.util.function.Supplier;

public class PID {

    private final double kp, ki, kd;
    private double currentTarget;
    private double errorSum;
    private double lastError;
    private double lastOutput, currentOutput;
    private double integralLimit;
    private int deltaTime;
    private long lastTime;
    private final Supplier<Double> processVariable;

    public PID(double kp, double ki, double kd, double integralLimit, Supplier<Double> getPV){
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;

        processVariable = getPV;

        lastTime = System.currentTimeMillis();
        this.integralLimit =integralLimit;
    }

    public PID(double kp, double ki, double kd, Supplier<Double> getPV){
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;

        processVariable = getPV;
        lastTime = System.currentTimeMillis();
    }

    public void setTarget(double target){
        currentTarget = target;
    }

    //PID formula is output = error(t) * kp + ki * integral(error(t)) from 0 to t + kd * (d/dt * e(t))
    private double calculate(double current){
        //prevent floating point errors
        if(deltaTime < 5)
            return lastOutput;

        double currentError = currentTarget - current;
        errorSum += currentError * deltaTime;

        if (errorSum > integralLimit) {
            errorSum = integralLimit;
        } else if (errorSum < -integralLimit) {
            errorSum = -integralLimit;
        }


        double proportional = kp * currentError;
        double integral = ki * errorSum;
        double derivative = kd * (currentError - lastError);


        double output = proportional + integral + derivative;
        currentOutput = output;
        lastOutput = output;
        lastError = currentError;


        return output;
    }

    private double calculate() {
        return calculate(processVariable.get());
    }

    public double autoControl () {
        if (processVariable == null)
            throw new IllegalStateException("No getCurrent supplier set - use autoControl(double) instead.");
        updateDeltaTime();
        return calculate();
    }
    public double autoControl (double current) {
        updateDeltaTime();
        return calculate(current);
    }

    public void reset() {
        errorSum = 0;
        lastError = 0;
        lastOutput = 0;
        lastTime = System.currentTimeMillis();
    }


    private void updateDeltaTime() {
        long cur = System.currentTimeMillis();
        long tempTime = (int) (cur - lastTime);
        if (tempTime >= 5){
            deltaTime = (int) tempTime;
            lastTime = cur;
        } else deltaTime = 0;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "PID Controller: \n" +
                        "\tcurrentOutput: " + currentOutput+ "\n" +
                        "\tcurrentTarget: " +currentTarget;
    }

}
