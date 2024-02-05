// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;
//#######################################
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
//#######################################
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.*;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/* This class declares the subsystem for the robot drivetrain if controllers are connected via CAN. Make sure to go to
 * RobotContainer and uncomment the line declaring this subsystem and comment the line for PWMDrivetrain.
 *
 * The subsystem contains the objects for the hardware contained in the mechanism and handles low level logic
 * for control. Subsystems are a mechanism that, when used in conjuction with command "Requirements", ensure
 * that hardware is only being used by 1 command at a time.
 */
public class CANDrivetrain extends SubsystemBase {
  /*Class member variables. These variables represent things the class needs to keep track of and use between
  different method calls. */
  DifferentialDrive m_drivetrain;

  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public CANDrivetrain() {
    // CANSparkMax leftFront = new CANSparkMax(kLeftFrontID, CANSparkLowLevel.MotorType.kBrushed);
    // CANSparkMax leftRear = new CANSparkMax(kLeftRearID, CANSparkLowLevel.MotorType.kBrushed);
    // CANSparkMax rightFront = new CANSparkMax(kRightFrontID, CANSparkLowLevel.MotorType.kBrushed);
    // CANSparkMax rightRear = new CANSparkMax(kRightRearID, MotorType.kBrushed);
    WPI_TalonSRX leftFront = new WPI_TalonSRX(kLeftFrontID);
    WPI_TalonSRX leftRear = new WPI_TalonSRX(kLeftRearID);
    WPI_TalonSRX rightFront = new WPI_TalonSRX(kRightFrontID);
    WPI_TalonSRX rightRear = new WPI_TalonSRX(kRightRearID);

    


    

    /*Sets current limits for the drivetrain motors. This helps reduce the likelihood of wheel spin, reduces motor heating
     *at stall (Drivetrain pushing against something) and helps maintain battery voltage under heavy demand */
    // leftFront.setSmartCurrentLimit(kCurrentLimit);
    // leftRear.setSmartCurrentLimit(kCurrentLimit);
    // rightFront.setSmartCurrentLimit(kCurrentLimit);
    // rightRear.setSmartCurrentLimit(kCurrentLimit);
    leftFront.configContinuousCurrentLimit(kCurrentLimit);
    leftRear.configContinuousCurrentLimit(kCurrentLimit);
    rightFront.configContinuousCurrentLimit(kCurrentLimit);
    rightRear.configContinuousCurrentLimit(kCurrentLimit);

    

    // Set the rear motors to follow the front motors.
    // leftRear.follow(leftFront);
    // rightRear.follow(rightFront);
    leftRear.set(ControlMode.Follower, kLeftFrontID);
    rightRear.set(ControlMode.Follower, kRightFrontID);

    // Invert the left side so both side drive forward with positive motor outputs
    leftFront.setInverted(false);
    leftRear.setInverted(false);
    rightFront.setInverted(true);
    rightRear.setInverted(true);

    // Put the front motors into the differential drive object. This will control all 4 motors with
    // the rears set to follow the fronts
    m_drivetrain = new DifferentialDrive(leftFront, rightFront);
  }

  /*Method to control the drivetrain using arcade drive. Arcade drive takes a speed in the X (forward/back) direction
   * and a rotation about the Z (turning the robot about it's center) and uses these to control the drivetrain motors */
  public void arcadeDrive(double speed, double rotation) {
    m_drivetrain.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    /*This method will be called once per scheduler run. It can be used for running tasks we know we want to update each
     * loop such as processing sensor data. Our drivetrain is simple so we don't have anything to put here */
  }
}
