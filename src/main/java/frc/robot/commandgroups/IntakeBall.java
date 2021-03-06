/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.FlipIntake;
import frc.robot.commands.MoveIntake;
import frc.robot.subsystems.Intake;

public class IntakeBall extends CommandGroup {
  public IntakeBall() {
    addParallel(new FlipIntake(Intake.Direction.OUT));
    addSequential(new MoveIntake(-1.0));
  }
}
