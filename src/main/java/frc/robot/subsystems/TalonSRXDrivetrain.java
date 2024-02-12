package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TalonSRXDrivetrain extends SubsystemBase {
    DifferentialDrive m_drivetrain;

    public TalonSRXDrivetrain() {
        WPI_TalonSRX leftFront = new WPI_TalonSRX(kLeftFrontID);
        WPI_TalonSRX leftRear = new WPI_TalonSRX(kLeftRearID);
        WPI_TalonSRX rightFront = new WPI_TalonSRX(kRightFrontID);
        WPI_TalonSRX rightRear = new WPI_TalonSRX(kRightRearID);

        // Set current limits (adjust values as needed)
        leftFront.configContinuousCurrentLimit(kCurrentLimit, 0);
        leftRear.configContinuousCurrentLimit(kCurrentLimit, 0);
        rightFront.configContinuousCurrentLimit(kCurrentLimit, 0);
        rightRear.configContinuousCurrentLimit(kCurrentLimit, 0);

        // Set rear motors to follow the front motors
        leftRear.follow(leftFront);
        rightRear.follow(rightFront);

        // Invert the left side (if necessary)
        leftFront.setInverted(false);
        leftRear. setInverted(false);
        rightFront.setInverted(true);
        rightRear. setInverted(true);

        // Create the differential drive object
        m_drivetrain = new DifferentialDrive(leftFront, rightFront);
    }

    public void arcadeDrive(double speed, double rotation) {
        m_drivetrain.arcadeDrive(speed, rotation);
    }

    @Override
    public void periodic() {
        // Nothing to do here for now
    }
}