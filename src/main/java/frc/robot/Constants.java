/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Constants {

    // PID Gains
    public static final double[] basePIDF = {0, 0, 0, 0};
    public static final double[] elevatorPID = {0, 0, 0};
    public static final double[] elevatorUpPID = {0, 0, 0};
    public static final double[] elevatorDownPID = {0, 0, 0};

    // PID Slots
    public static final int kElevatorUpPID = 0;
    public static final int kElevatorDownPID = 1;

    // Power Scalars
    public static final double kBasePower = 0.75;
    public static final double kElevatorPower = 0.6;
    public static final double kHangArmPower = 0.4;

    // Pneumatics Directions
    public static final Value kIntakePivotOut = Value.kForward;
    public static final Value kIntakePivotIn = (kIntakePivotOut == Value.kForward ? Value.kReverse : Value.kForward);
    public static final Value kInnerPushOut = Value.kForward;
    public static final Value kInnerPushIn = (kInnerPushOut == Value.kForward ? Value.kReverse : Value.kForward);
    public static final Value kOuterPushOut = Value.kReverse;
    public static final Value kOuterPushIn = (kOuterPushOut == Value.kForward ? Value.kReverse : Value.kForward);
    public static final Value kHangOut = Value.kForward;
    public static final Value kHangIn = (kHangOut == Value.kForward ? Value.kReverse : Value.kForward);

    // Trim
    public static final double kIntakeTrim = 0.15;

    // Tolerances
    public static final double kElevatorPIDTolerance = 10;

    // Default Values
    public static final ControlType kBasePIDDefaultType = ControlType.kVelocity;
    public static final ControlType kElevatorPIDDefaultType = ControlType.kPosition;

    // Limits
    public static final double kElevatorUpperLimit = 55.;
    public static final double kElevatorLowerLimit = Double.NEGATIVE_INFINITY;

    // Presets
    public static final double[] elevatorPresets = {0, 0, 0, 0};

}
