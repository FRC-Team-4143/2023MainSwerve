package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class RotateDown extends CommandBase{
    private Arm arm;
    
    public RotateDown(Arm arm){
        this.arm = arm;
    }

    @Override
    public void execute() {
        arm.setRotateSpeed(-0.25);
        System.out.println("it's working");
    }

    @Override
    public void end(boolean interrupted){
        arm.setRotateSpeed(0);
    }
}