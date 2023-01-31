package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final XboxController driver = new XboxController(0);
    private final XboxController operator = new XboxController(1);
        /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    //private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftStick.value);

    //Arm buttons :)
    //Claw

    private final JoystickButton clawOpenButton = new JoystickButton(driver, XboxController.Button.kB.value);
    private final JoystickButton clawCloseButton = new JoystickButton(driver, XboxController.Button.kX.value);

    //Rotator
    private final JoystickButton rotateUpButton = new JoystickButton(driver, XboxController.Button.kA.value);
    private final JoystickButton rotateDownButton = new JoystickButton(driver, XboxController.Button.kY.value);

    //Elevator
    private final JoystickButton elevatorUpButton = new JoystickButton(driver, XboxController.Button.kStart.value);
    private final JoystickButton elevatorDownButton = new JoystickButton(driver, XboxController.Button.kBack.value);


    //pickup 
    private final JoystickButton rollerinButton = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton rolleroutButton = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
    private final JoystickButton rollerstopButton = new JoystickButton(driver, XboxController.Button.kRightStick.value);

    private final JoystickButton pickupInButton = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
    private final JoystickButton pickupOutButton = new JoystickButton(operator, XboxController.Button.kRightBumper.value);


    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Arm arm = new Arm();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis) * 0.5, 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        SmartDashboard.putData("Zero Gyro", new InstantCommand(() -> s_Swerve.zeroGyro()));
        //myBoolean.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        pickupInButton.onTrue(new InstantCommand(() -> arm.pickupRetract()));
        pickupOutButton.onTrue(new InstantCommand(() -> arm.pickupExtend()));

        //rollerstopButton.onTrue(new InstantCommand(() -> arm.rollerstop()).finallyDo(interrupted -> {arm.rollerstop();}));
        rollerinButton.whileTrue(new RollersIn(arm));
        rolleroutButton.whileTrue(new RollersOut(arm));
        clawCloseButton.whileTrue(new ClawClose(arm));
        clawOpenButton.whileTrue(new ClawOpen(arm));
        rotateUpButton.whileTrue(new RotateUp(arm));
        rotateDownButton.whileTrue(new RotateDown(arm));
        elevatorUpButton.whileTrue(new ElevatorUp(arm));
        elevatorDownButton.whileTrue(new ElevatorDown(arm));


    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}
