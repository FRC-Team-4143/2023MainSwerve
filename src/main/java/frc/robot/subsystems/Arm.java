package frc.robot.subsystems;

// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase{
    // private CANSparkMax elevatorMotor;
    // private SparkMaxPIDController m_pidController;
    // private RelativeEncoder m_encoder;
    // public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
    private CANSparkMax clawMotor;
    private CANSparkMax elevatorMotor;
    private CANSparkMax rotatorMotor; 

    public Arm(){ 
        elevatorMotor = new CANSparkMax(10, MotorType.kBrushless);
        clawMotor = new CANSparkMax(3, MotorType.kBrushed);
        rotatorMotor = new CANSparkMax(5, MotorType.kBrushless);
        
        // m_pidController = elevatorMotor.getPIDController();
        // m_encoder = elevatorMotor.getEncoder();

    // // PID coefficients
    // kP = 5e-5; 
    // kI = 1e-6;
    // kD = 0; 
    // kIz = 0; 
    // kFF = 0.000156; 
    // kMaxOutput = 1; 
    // kMinOutput = -1;
    // maxRPM = 5700;
    // // Smart Motion Coefficients
    // maxVel = 2000; // rpm
    // maxAcc = 1500;
    // // set PID coefficients
    // m_pidController.setP(kP);
    // m_pidController.setI(kI);
    // m_pidController.setD(kD);
    // m_pidController.setIZone(kIz);
    // m_pidController.setFF(kFF);
    // m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    }

    public void setClawSpeed(double clawSpeed){
        clawMotor.set(clawSpeed);
        System.out.println("Sub is working");
    }

    public void setRotateSpeed(double rotateSpeed){
        rotatorMotor.set(rotateSpeed);
    }

    public void setElevatorSpeed(double elevateSpeed){
        elevatorMotor.set(elevateSpeed);
    }
}
