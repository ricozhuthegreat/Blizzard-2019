/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.Base;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hang;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;
import frc.robot.commandgroups.AutoCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.TestArmPID;
import frc.robot.commands.TuneBaseEncPID;
import frc.robot.commands.TuneBaseGyroPID;
import frc.robot.commands.TuneElevatorPID;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  private static Command teleopCommand;
  private static Command testCommand;

  private static CommandGroup autoCommandGroup;

  public static Base base;
  public static Elevator elevator;
  public static Intake intake;
  public static Hang hang;
  public static Vision vision;
  public static OI oi;
  NetworkTableEntry goalEntry;

  @Override
  public void robotInit() {
    base = new Base();
    intake = new Intake();
    elevator = new Elevator();
    hang = new Hang();
    vision = new Vision();
    oi = new OI();
    // NetworkTableInstance inst = NetworkTableInstance.getDefault();
    // NetworkTable table = inst.getTable("SmartDashboard");
    // goalEntry = table.getEntry("goal:closest");
    
    teleopCommand = null;
    testCommand = new TuneBaseEncPID();
    autoCommandGroup = new AutoCommandGroup();

    push();
    report();
  }

  @Override
  public void robotPeriodic() {
    // double goalAngle = (double)goalEntry.getNumber(0);
    // System.out.println(goalAngle);
    // if(goalAngle != 0){
    // }
  }

  @Override
  public void disabledInit() {
    if(teleopCommand != null)
      teleopCommand.cancel();
    if (testCommand != null)
      testCommand.cancel();
    if (autoCommandGroup != null)
      autoCommandGroup.cancel();

    Robot.elevator.stopPID();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();

    // Reset sensors
    if (OI.resetBaseEnc.get())
    {
      Robot.base.resetLeftEnc();
      Robot.base.resetRightEnc();
      System.out.println("Base encoders reset.");
    }
    if (OI.resetElevatorEnc.get())
    {
      Robot.elevator.resetElevatorEnc();
      System.out.println("Elevator encoder reset.");
      Robot.hang.resetArmEnc();
    }
    if (OI.resetGyro.get())
    {
      Robot.base.resetGyro();
      System.out.println("Gyro reset.");
    }

    report();
  }

  @Override
  public void autonomousInit() {
    // if (testCommand != null)
    //   testCommand.start();
    if (autoCommandGroup != null)
      autoCommandGroup.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    report();
  }

  @Override
  public void teleopInit() {
    if(teleopCommand != null) {
      teleopCommand.start();
    }
    Robot.elevator.stopPID();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    report();
  }

  @Override
  public void testPeriodic() {
    if (testCommand != null)
      testCommand.start();
  }

  public static void push() 
  {
    SmartDashboard.putNumber("h:lo", 25);
    SmartDashboard.putNumber("s:lo", 50);
    SmartDashboard.putNumber("v:lo", 50);
    SmartDashboard.putNumber("h:hi", 100);
    SmartDashboard.putNumber("s:hi", 255);
    SmartDashboard.putNumber("v:hi", 255);
    SmartDashboard.putNumber("contour:min", 200);
    SmartDashboard.putBoolean("hsv:toggle", false);
  }

  public static void report()
  {
    base.report();
    elevator.report();
    intake.report();
    vision.report();
    SmartDashboard.putNumberArray("hsv:lower", 
      new double[]{SmartDashboard.getNumber("h:lo", 0), SmartDashboard.getNumber("s:lo", 0), SmartDashboard.getNumber("v:lo", 0)});
    SmartDashboard.putNumberArray("hsv:upper", 
    new double[]{SmartDashboard.getNumber("h:hi", 0), SmartDashboard.getNumber("s:hi", 0), SmartDashboard.getNumber("v:hi", 0)});
  }

}
